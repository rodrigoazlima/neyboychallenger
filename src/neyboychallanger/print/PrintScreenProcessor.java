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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import neyboychallanger.util.ColorUtilities;
import neyboychallanger.util.TimerTaskLooper;
import neyboychallanger.view.NeyChallangerJFrame;
import neyboychallanger.view.component.PrintPreviewJLabel;

/**
 *
 * @author Rodrigo
 */
public class PrintScreenProcessor implements Logger {

    private static long DEFAULT_DELAY1 = 320L,
            DEFAULT_DELAY2 = 120L,
            DEFAULT_DELAY3 = 20L;
    private final int DEFAULT_ACCEPTANCE = 2;//px

    public static void setDefaultDelays(long d1, long d2, long d3) {
        DEFAULT_DELAY1 = d1;
        DEFAULT_DELAY2 = d2;
        DEFAULT_DELAY3 = d3;
    }

    private void initDefaultSettings() {
        List<Color> acceptableList = getAcceptableColors();
        if (acceptableList.isEmpty()) {
            acceptableList.add(new Color(255, 255, 255));
            acceptableList.add(new Color(229, 247, 232));
            acceptableList.add(new Color(203, 238, 209));
            acceptableList.add(new Color(201, 235, 207));
            acceptableList.add(new Color(177, 230, 187));
            acceptableList.add(new Color(163, 225, 175));
            acceptableList.add(new Color(146, 220, 160));
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

    private void isValidGreen(Color c) {

    }

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
    private Runnable executeInvokePlease;

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

    public void moveMouseToXY1() {
        moveMouseToXY(psa1.getX(), psa1.getY());
    }

    public void moveMouseToXY2() {
        moveMouseToXY(psa2.getX(), psa2.getY());
    }

    public void moveMouseToXY3() {
        moveMouseToXY(psa3.getX(), psa3.getY());
    }

    public void moveMouseToXY(int x, int y) {
        if (executeInvokePlease != null) {
            SwingUtilities.invokeLater(() -> {
                moveMouseToXY(x, y);
            });
            return;
        }
        this.executeInvokePlease = () -> {
            bot.mouseMove(x, y);
        };
        awakenOutputProcessor();
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
            runnableOutputProcessor = new OutputProcessorRunnable();
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
            botActions = 0;
            awakenOutputProcessor();
        }
    }

    private void awakenOutputProcessor() {
        Thread t = getThreadOutputProcessor();
        if (!t.isAlive()
                && (t.getState() == Thread.State.NEW
                || t.getState() == Thread.State.RUNNABLE)) {
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

    private class OutputProcessorRunnable implements Runnable {

        private Point p1;
        private Point p2;
        private Point p3;
        private long start = 0;
        private long delay = 0;
        private Dimension d = new Dimension(1, 1);

        public OutputProcessorRunnable() {
        }

        @Override
        public void run() {
            try {
                do {
                    if (executeInvokePlease != null) {
                        executeInvokePlease.run();
                        executeInvokePlease = null;
                    }
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
                        log(" Detected failure. Please click on play. ");
                        setEnabled(false);
                        NeyChallangerJFrame.getSingleton().update();

                    } else if (delay + DEFAULT_DELAY1 <= time
                            && !pixelMatchesWith(p1, getAcceptableColors(), true)) {
                        pressButton("P1 " + (time - delay)
                                + "ms\t\t\t" + ColorUtilities.RGBtoString(getColorOfPointOnScreen(p1))
                                + "\t" + ColorUtilities.RGBToHFromHSV(getColorOfPointOnScreen(p1)) + "º");

                        delay = time;

                    } else if (delay + DEFAULT_DELAY2 <= time
                            && !pixelMatchesWith(p2, getAcceptableColors(), true)) {
                        pressButton("P-2 " + (time - delay)
                                + "ms\t\t\t" + ColorUtilities.RGBtoString(getColorOfPointOnScreen(p2))
                                + "\t" + ColorUtilities.RGBToHFromHSV(getColorOfPointOnScreen(p2)) + "º");

                        delay = time;

                    } else if (delay + DEFAULT_DELAY3 <= time
                            && !pixelMatchesWith(p3, getAcceptableColors(), true)) {
                        pressButton("P--3 "
                                + (time - delay)
                                + "ms\t\t\t" + ColorUtilities.RGBtoString(getColorOfPointOnScreen(p3))
                                + "\t" + ColorUtilities.RGBToHFromHSV(getColorOfPointOnScreen(p3)) + "º");

                        delay = time;

                    }
                } while (true);
            } catch (Exception e) {
            }
        }

        private void initBot() {
            log(" Started! " + format(delay = start = System.currentTimeMillis()));
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
                //getSleeperLock().wait(1000);
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        private boolean pixelMatchesWith(Point p, List<Color> list) {
            return pixelMatchesWith(p, list, false);
        }

        private boolean pixelMatchesWith(Point p, List<Color> list, boolean checkHSB) {
            Color cScreen = getColorOfPointOnScreen(p);
            if (checkHSB && pixelMatchesWithHSB(cScreen)) {
                return true;
            }
            for (Color c : list) {
                if (pixelMatchesWith(c, cScreen)) {
                    return true;
                }
            }
            return false;
        }

        private boolean pixelMatchesWithHSB(Color cScreen) {
            float h = ColorUtilities.RGBToHFromHSV(cScreen);
            if (h >= (130f - DEFAULT_ACCEPTANCE)
                    && h <= (130f + DEFAULT_ACCEPTANCE)) {
                return true;
            }
            return false;
        }

        private boolean pixelMatchesWith(Color c, Color cScreen) {
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

        private void pressButton(String log) {
            if (!left) {
                bot.keyPress(KeyEvent.VK_LEFT);
                bot.delay(1);
                bot.keyRelease(KeyEvent.VK_LEFT);
                log(" <- " + log);
            } else {
                bot.keyPress(KeyEvent.VK_RIGHT);
                bot.delay(1);
                bot.keyRelease(KeyEvent.VK_RIGHT);
                log(" -> " + log);
            }
            botActions++;
        }

        private SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

        private String format(long time) {
            return sdf.format(new Date(time));
        }
    }

}
