/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.print;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import neyboychallanger.util.RGBString;
import neyboychallanger.util.TimerTaskLooper;
import neyboychallanger.view.NeyChallangerJFrame;
import neyboychallanger.view.component.MiniPrintPreviewJLabel;
import neyboychallanger.view.component.PrintPreviewJLabel;

/**
 *
 * @author Rodrigo
 */
public class PrintScreenProcessor implements Logger {

    private Robot bot;
    private Timer timer;
    private Runnable runnableTimer;
    private TimerTaskLooper looper;
    private Thread threadOutputProcessor;
    private Runnable runnableOutputProcessor;
    private Logger logger;
    private PrintScreenController psa1;
    private PrintScreenController psa2;
    private PrintScreenController psa3;
    private boolean enabled;
    private boolean started;
    private boolean left;
    private Object sleeperLock;
    private static int botActions;
    private List<Color> acceptableColors;
    private List<Color> failColors;

    public PrintScreenProcessor(boolean left) {
        this.left = left;
        init();
        initDefaultSettings();
    }

    public List<Color> getAcceptableColors() {
        if (acceptableColors == null) {
            acceptableColors = new ArrayList<>();
            initDefaultSettings();
        }
        return acceptableColors;
    }

    public List<Color> getFailColors() {
        if (failColors == null) {
            failColors = new ArrayList<>();
            initDefaultSettings();
        }
        return failColors;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        log(" Request to " + ((enabled) ? "start. " : "stop. "));
        this.enabled = enabled;
    }

    public void setPrintScreenXY1(int x, int y) {
        psa1.setX(x);
        psa1.setY(y);
    }

    public void setPrintScreenXY2(int x, int y) {
        psa2.setX(x);
        psa2.setY(y);
    }

    public void setPrintScreenXY3(int x, int y) {
        psa3.setX(x);
        psa3.setY(y);
    }

    public Image getPrintScreenIcon1() {
        return psa1.getSharedImage();
    }

    public Image getPrintScreenIcon2() {
        return psa2.getSharedImage();
    }

    public Image getPrintScreenIcon3() {
        return psa3.getSharedImage();
    }

    public void setPrintPreviewJLabels(PrintPreviewJLabel lb1,
            PrintPreviewJLabel lb2,
            PrintPreviewJLabel lb3) {
        psa1.addLabel(lb1);
        psa2.addLabel(lb2);
        psa3.addLabel(lb3);
    }

    private Runnable getRunnableOutputProcessor() {
        if (runnableOutputProcessor == null) {
            runnableOutputProcessor = new Runnable() {

                private Point p1, p2, p3;
                private long start;
                private Long delay1 = System.currentTimeMillis(),
                        delay2 = System.currentTimeMillis(),
                        delay3 = System.currentTimeMillis();
                private final Long DEFAULT_DELAY1 = 300L,
                        DEFAULT_DELAY2 = 100L,
                        DEFAULT_DELAY3 = 1L;
                private final int DEFAULT_ACCEPTANCE = 2;//px
                private Dimension d = new Dimension(1, 1);

                @Override
                public void run() {
                    try {
                        do {
                            if (!enabled) {
                                log(" Sleeping Zzz");
                                sleep();
                                continue;
                            }
                            if (botActions == 0) {
                                initBot();
                            }
                            long time = System.currentTimeMillis();
                            if (pixelMatchesWith(p3, getFailColors())) {
                                log(" Detected failure. Please click on play."
                                        + RGBString.RGBtoString(getColorOfPointOnScreen(p3)));
                                System.out.println(getFailColors());
                                setEnabled(false);
                                NeyChallangerJFrame.getSingleton().update();
                            } else if (!pixelMatchesWith(p1, getAcceptableColors())
                                    && delay1 + DEFAULT_DELAY1 <= time) {
                                pressButton("P1 " + (time - delay1));
                                delay1 = time;
                            } else if (!pixelMatchesWith(p2, getAcceptableColors())
                                    && delay2 + DEFAULT_DELAY2 <= time) {
                                pressButton(" P2 " + (time - delay2));
                                delay2 = time;
                            } else if (!pixelMatchesWith(p3, getAcceptableColors())
                                    && delay3 + DEFAULT_DELAY3 <= time) {
                                pressButton("  P3 " + (time - delay3));
                                delay3 = time;
                            }
                        } while (true);
                    } catch (Exception e) {
                    }
                }

                private void initBot() {
                    log(" Start! " + (start = System.currentTimeMillis()));
                    p1 = new Point(psa1.getX(), psa1.getY());
                    p2 = new Point(psa2.getX(), psa2.getY());
                    p3 = new Point(psa3.getX(), psa3.getY());
                    bot.setAutoWaitForIdle(true);
                    bot.mouseMove(p3.x + 20, p3.y - 20);
                    bot.mousePress(KeyEvent.BUTTON1_MASK);
                    bot.delay(1);
                    bot.mouseRelease(KeyEvent.BUTTON1_MASK);
                    bot.delay(1);
                    log(" Mouse Click at " + (p3.x + 20) + "x and " + (p3.y - 20) + "y");
                    botActions++;
                }

                private void sleep() {
                    try {
                        sleeperLock.wait(1000);
                    } catch (Throwable e) {
                    }
                }

                private boolean pixelMatchesWith(Point p, List<Color> list) {
                    for (Color c : list) {
                        if (pixelMatchesWith(p, c)) {
                            log("\t\t\tMATCH " + RGBString.RGBtoString(getColorOfPointOnScreen(p)));
                            return true;
                        }
                    }
                    //log("NOT A MATCH " + RGBString.RGBtoString(getColorOfPointOnScreen(p)));
                    return false;
                }
                private long printScreenOtimizador = System.currentTimeMillis();
                private Point lastPointOtimizador = null;
                private Color lastColorOtimizador = null;

                private Color getColorOfPointOnScreen(Point p) {
                    if (lastPointOtimizador != null && lastColorOtimizador != null) {
                        if (printScreenOtimizador == System.currentTimeMillis()
                                && p.x == lastPointOtimizador.x
                                && p.y == lastPointOtimizador.y) {
                            return lastColorOtimizador;
                        }

                    }
                    BufferedImage bi = bot.createScreenCapture(new Rectangle(p, d));
                    int rgb = bi.getRGB(0, 0);
                    Color c = new Color(rgb);
                    printScreenOtimizador = System.currentTimeMillis();
                    lastPointOtimizador = p;
                    lastColorOtimizador = c;
                    return c;
                }

                private boolean pixelMatchesWith(Point p, Color c) {
                    Color cScreen = getColorOfPointOnScreen(p);
                    if (c.getRed() <= cScreen.getRed() + DEFAULT_ACCEPTANCE
                            && c.getRed() >= cScreen.getRed() - DEFAULT_ACCEPTANCE
                            && c.getGreen() <= cScreen.getGreen() + DEFAULT_ACCEPTANCE
                            && c.getGreen() >= cScreen.getGreen() - DEFAULT_ACCEPTANCE
                            && c.getBlue() <= cScreen.getBlue() + DEFAULT_ACCEPTANCE
                            && c.getBlue() >= cScreen.getBlue() - DEFAULT_ACCEPTANCE) {
                        return true;
                    }
                    return false;
                }

                private void pressButton(String delay) {
                    if (!left) {
                        bot.keyPress(KeyEvent.VK_LEFT);
                        bot.delay(1);
                        bot.keyRelease(KeyEvent.VK_LEFT);
                        log(" <- " + delay + "ms ");
                    } else {
                        bot.keyPress(KeyEvent.VK_RIGHT);
                        bot.delay(1);
                        bot.keyRelease(KeyEvent.VK_RIGHT);
                        log(" -> " + delay + "ms ");
                    }
                    botActions++;
                }
            };
        }
        return runnableOutputProcessor;
    }

    private Thread getThreadOutputProcessor() {
        if (threadOutputProcessor == null) {
            threadOutputProcessor = new Thread(getRunnableOutputProcessor());
        }
        return threadOutputProcessor;
    }

    private Object getSleeperLock() {
        if (sleeperLock == null) {
            sleeperLock = new Object();
        }
        return sleeperLock;
    }

    private Runnable getRunnableTimer() {
        if (runnableTimer == null) {
            runnableTimer = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (enabled && !started) {
                            start();
                        } else if (!enabled) {
                            stop();
                        }
                        printScreen();

                    } catch (Throwable e) {
                        log(e);
                    }
                }
            };;
        }
        return runnableTimer;
    }

    private void initDefaultSettings() {
        List<Color> acceptableList = getAcceptableColors();
        if (acceptableList.isEmpty()) {
            acceptableList.add(new Color(112, 216, 132));
            acceptableList.add(new Color(100, 205, 120));
            acceptableList.add(new Color(91, 195, 111));
            acceptableList.add(new Color(83, 176, 100));
        }
        List<Color> failList = getFailColors();
        if (failList.isEmpty()) {
            failList.add(new Color(239, 73, 120));
            failList.add(new Color(212, 41, 89));
            failList.add(new Color(183, 30, 73));
        }
    }

    private void init() {
        psa1 = new PrintScreenController();
        psa2 = new PrintScreenController();
        psa3 = new PrintScreenController();
        timer = new Timer("PrintScreenProcessor Ativation Timer");
        //runnableTimer = getRunnableTimer();
        looper = new TimerTaskLooper(timer, getRunnableTimer());
        try {
            bot = new Robot();
        } catch (Throwable e) {
            log("Erro ao carregar bot.");
        }
        looper.schedule();
    }

    private void start() {
        if (!enabled) {
            return;
        }
        if (!started) {
            started = true;
            awakenOutputProcessor();
        }
    }

    private void awakenOutputProcessor() {
        Thread t = getThreadOutputProcessor();
        this.botActions = 0;
        if (t.getState() == Thread.State.NEW
                || t.getState() == Thread.State.RUNNABLE) {
            t.start();
        } else if (!t.isAlive()
                && t.isInterrupted()
                && t.getState() != Thread.State.TERMINATED) {
            getSleeperLock().notifyAll();
        }
    }

    private void stop() {
        if (enabled) {
            //can't stop MUST CONTINUE!
            throw new Error("Something is wrogn in my logic.");
        }
        started = false;
    }

    private void printScreen() {
        BufferedImage bi = bot.createScreenCapture(psa1.getPrintRectangle());
        psa1.setSharedIcon(bi);
        bi = bot.createScreenCapture(psa1.getExactRectangle());
        psa1.setSharedIconMini(bi);
        psa1.repaintRecursively();

        bi = bot.createScreenCapture(psa2.getPrintRectangle());
        psa2.setSharedIcon(bi);
        bi = bot.createScreenCapture(psa2.getExactRectangle());
        psa2.setSharedIconMini(bi);
        psa2.repaintRecursively();

        bi = bot.createScreenCapture(psa3.getPrintRectangle());
        psa3.setSharedIcon(bi);
        bi = bot.createScreenCapture(psa3.getExactRectangle());
        psa3.setSharedIconMini(bi);
        psa3.repaintRecursively();
    }

    public void log(Throwable t) {
        if (logger == null) {
            t.printStackTrace();
        } else {
            logger.log(t.getMessage());
        }
    }

    @Override
    public void log(String text) {
        if (logger == null) {
            System.out.println(text);
        } else {
            logger.log(text);
        }
    }

}
