/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import neyboychallanger.print.Logger;
import neyboychallanger.print.PrintScreenProcessor;
import neyboychallanger.util.PropertiesSaver;
import neyboychallanger.view.component.ImageJPanel;
import neyboychallanger.view.component.MiniPrintPreviewJLabel;
import neyboychallanger.view.component.PrintPreviewJLabel;

/**
 *
 * @author Rodrigo
 */
public class NeyChallangerJFrame extends javax.swing.JFrame implements Logger {

    private static final String VERSION = "0.0.2";
    private static final int LOG_LENGHT = 30;

    private static NeyChallangerJFrame singleton;

    public static NeyChallangerJFrame getSingleton() {
        if (singleton == null) {
            singleton = new NeyChallangerJFrame();
        }
        return singleton;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NeyChallangerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NeyChallangerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NeyChallangerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NeyChallangerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NeyChallangerJFrame().setVisible(true);
            }
        });
    }

    private PrintScreenProcessor pspEsquerdo;
    private PrintScreenProcessor pspDireito;

    /**
     * Creates new form NeyChallangerJFrame
     */
    public NeyChallangerJFrame() {
        singleton = this;
        init();
    }

    private void init() {
        initStyleBefore();
        initComponents();
        try {
            pspEsquerdo = new PrintScreenProcessor(true);
            pspEsquerdo.setPrintPreviewJLabels((PrintPreviewJLabel) lblPreviewE1,
                    (PrintPreviewJLabel) lblPreviewE2,
                    (PrintPreviewJLabel) lblPreviewE3);
            pspEsquerdo.setPrintPreviewJLabels((MiniPrintPreviewJLabel) lblPreviewE1mini,
                    (MiniPrintPreviewJLabel) lblPreviewE2mini,
                    (MiniPrintPreviewJLabel) lblPreviewE3mini);
            pspEsquerdo.setLogger(this);

            pspDireito = new PrintScreenProcessor(false);
            pspDireito.setPrintPreviewJLabels((PrintPreviewJLabel) lblPreviewD1,
                    (PrintPreviewJLabel) lblPreviewD2,
                    (PrintPreviewJLabel) lblPreviewD3);
            pspDireito.setPrintPreviewJLabels((MiniPrintPreviewJLabel) lblPreviewD1mini,
                    (MiniPrintPreviewJLabel) lblPreviewD2mini,
                    (MiniPrintPreviewJLabel) lblPreviewD3mini);
            pspDireito.setLogger(this);
        } catch (Exception e) {
            e.printStackTrace();
            log("Erro ao carregar processador de printsreen.");
        }
        initStyleAfter();
        load();
    }

    private void initStyleBefore() {
        ImageIcon iiFundo = new ImageIcon(getClass().getResource("/neyboychallanger/view/resource/fundo.png"));
        ImageIcon iiChallanger = new ImageIcon(getClass().getResource("/neyboychallanger/view/resource/challanger.png"));
        setIconImage(iiChallanger.getImage());
        setContentPane(new ImageJPanel(iiFundo.getImage()));
        revalidate();
        repaint();
    }

    private void initStyleAfter() {
        setLocationRelativeTo(null);
//        setBackground(Color.WHITE);
//        getContentPane().setBackground(Color.WHITE);
    }

    private int imputs = 0;
    private String lastText = "";

    @Override
    public void log(String text) {
        if (text == null) {
            return;
        }
        if (imputs > LOG_LENGHT) {
            String[] split = txaLog.getText().split("\n");
            if (split.length - LOG_LENGHT > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = split.length - LOG_LENGHT; i < split.length; i++) {
                    sb.append(split[i]).append("\n");
                }
                txaLog.setText(sb.toString().trim());
            }
            imputs = split.length;
        }
        if (lastText != null && !lastText.equals(text)) {
            txaLog.setText(txaLog.getText() + "\n" + text);
            lastText = text;
            imputs++;
        }
    }

    private boolean isControlerEnabled() {
        return pspEsquerdo.isEnabled() && pspDireito.isEnabled();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaLog = new javax.swing.JTextArea();
        btnStartCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        spnControlerPSPE3X = new javax.swing.JSpinner();
        spnControlerPSPE3Y = new javax.swing.JSpinner();
        spnControlerPSPE2X = new javax.swing.JSpinner();
        spnControlerPSPE2Y = new javax.swing.JSpinner();
        spnControlerPSPE1X = new javax.swing.JSpinner();
        lblPreviewE1 = new PrintPreviewJLabel();
        lblPreviewE2 = new PrintPreviewJLabel();
        lblPreviewE3 = new PrintPreviewJLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spnControlerPSPE1Y = new javax.swing.JSpinner();
        lblPreviewE1mini = new MiniPrintPreviewJLabel();
        lblPreviewE2mini = new MiniPrintPreviewJLabel();
        lblPreviewE3mini = new MiniPrintPreviewJLabel();
        jPanel2 = new javax.swing.JPanel();
        spnControlerPSPD3X = new javax.swing.JSpinner();
        spnControlerPSPD3Y = new javax.swing.JSpinner();
        spnControlerPSPD2X = new javax.swing.JSpinner();
        spnControlerPSPD2Y = new javax.swing.JSpinner();
        spnControlerPSPD1X = new javax.swing.JSpinner();
        lblPreviewD1 = new PrintPreviewJLabel();
        lblPreviewD2 = new PrintPreviewJLabel();
        lblPreviewD3 = new PrintPreviewJLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        spnControlerPSPD1Y = new javax.swing.JSpinner();
        lblPreviewD1mini = new MiniPrintPreviewJLabel();
        lblPreviewD2mini = new MiniPrintPreviewJLabel();
        lblPreviewD3mini = new MiniPrintPreviewJLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ney Boy Challanger v"+VERSION);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/neyboychallanger/view/resource/challanger.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("v"+VERSION);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);

        txaLog.setEditable(false);
        txaLog.setBackground(new java.awt.Color(240, 240, 240));
        txaLog.setColumns(30);
        txaLog.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txaLog.setRows(30);
        txaLog.setWrapStyleWord(true);
        txaLog.setFocusable(false);
        txaLog.setOpaque(false);
        txaLog.setRequestFocusEnabled(false);
        txaLog.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(txaLog);

        btnStartCancel.setBackground(new java.awt.Color(153, 255, 204));
        btnStartCancel.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnStartCancel.setText("START");
        btnStartCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartCancelActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0,0,0,0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pixels Esquerdos"));
        jPanel1.setOpaque(false);

        spnControlerPSPE3X.setModel(new javax.swing.SpinnerNumberModel(1750, 0, null, 1));
        spnControlerPSPE3X.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPE3XStateChanged(evt);
            }
        });

        spnControlerPSPE3Y.setModel(new javax.swing.SpinnerNumberModel(900, 0, null, 1));
        spnControlerPSPE3Y.setToolTipText("");
        spnControlerPSPE3Y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPE3YStateChanged(evt);
            }
        });

        spnControlerPSPE2X.setModel(new javax.swing.SpinnerNumberModel(1700, 0, null, 1));
        spnControlerPSPE2X.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPE2XStateChanged(evt);
            }
        });

        spnControlerPSPE2Y.setModel(new javax.swing.SpinnerNumberModel(870, 0, null, 1));
        spnControlerPSPE2Y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPE2YStateChanged(evt);
            }
        });

        spnControlerPSPE1X.setModel(new javax.swing.SpinnerNumberModel(1700, 0, null, 1));
        spnControlerPSPE1X.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPE1XStateChanged(evt);
            }
        });

        lblPreviewE1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 102), 2));

        lblPreviewE2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 0), 2));

        lblPreviewE3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0), 2));

        jLabel5.setText("x");

        jLabel6.setText("y");

        jLabel10.setText("y");

        jLabel11.setText("x");

        jLabel12.setText("y");

        jLabel13.setText("x");

        spnControlerPSPE1Y.setModel(new javax.swing.SpinnerNumberModel(850, 0, null, 1));
        spnControlerPSPE1Y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPE1YStateChanged(evt);
            }
        });

        lblPreviewE1mini.setToolTipText(" ");
        lblPreviewE1mini.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 102), 2));
        lblPreviewE1mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviewE1miniMouseClicked(evt);
            }
        });

        lblPreviewE2mini.setToolTipText(" ");
        lblPreviewE2mini.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 0), 2));
        lblPreviewE2mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviewE2miniMouseClicked(evt);
            }
        });

        lblPreviewE3mini.setToolTipText(" ");
        lblPreviewE3mini.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0), 2));
        lblPreviewE3mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviewE3miniMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewE1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPreviewE3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPreviewE3mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spnControlerPSPE3X, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnControlerPSPE3Y, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPreviewE2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblPreviewE2mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnControlerPSPE2X, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(spnControlerPSPE2Y, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPreviewE1mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnControlerPSPE1X, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnControlerPSPE1Y, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewE1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreviewE1mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnControlerPSPE1X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnControlerPSPE1Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewE2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnControlerPSPE2X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPreviewE2mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnControlerPSPE2Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewE3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnControlerPSPE3X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreviewE3mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(spnControlerPSPE3Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel13)))
        );

        jPanel2.setBackground(new java.awt.Color(0,0,0,0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pixels Direitos"));
        jPanel2.setOpaque(false);

        spnControlerPSPD3X.setModel(new javax.swing.SpinnerNumberModel(2150, 0, null, 1));
        spnControlerPSPD3X.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPD3XStateChanged(evt);
            }
        });

        spnControlerPSPD3Y.setModel(new javax.swing.SpinnerNumberModel(900, 0, null, 1));
        spnControlerPSPD3Y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPD3YStateChanged(evt);
            }
        });

        spnControlerPSPD2X.setModel(new javax.swing.SpinnerNumberModel(2120, 0, null, 1));
        spnControlerPSPD2X.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPD2XStateChanged(evt);
            }
        });

        spnControlerPSPD2Y.setModel(new javax.swing.SpinnerNumberModel(870, 0, null, 1));
        spnControlerPSPD2Y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPD2YStateChanged(evt);
            }
        });

        spnControlerPSPD1X.setModel(new javax.swing.SpinnerNumberModel(2100, 0, null, 1));
        spnControlerPSPD1X.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPD1XStateChanged(evt);
            }
        });

        lblPreviewD1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 102), 2));

        lblPreviewD2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 0), 2));

        lblPreviewD3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0), 2));

        jLabel7.setText("x");

        jLabel8.setText("y");

        jLabel14.setText("y");

        jLabel15.setText("x");

        jLabel16.setText("y");

        jLabel17.setText("x");

        spnControlerPSPD1Y.setModel(new javax.swing.SpinnerNumberModel(850, 0, null, 1));
        spnControlerPSPD1Y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnControlerPSPD1YStateChanged(evt);
            }
        });

        lblPreviewD1mini.setToolTipText(" ");
        lblPreviewD1mini.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 102), 2));
        lblPreviewD1mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviewD1miniMouseClicked(evt);
            }
        });

        lblPreviewD2mini.setToolTipText(" ");
        lblPreviewD2mini.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(234, 234, 0), 2));
        lblPreviewD2mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviewD2miniMouseClicked(evt);
            }
        });

        lblPreviewD3mini.setToolTipText(" ");
        lblPreviewD3mini.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0), 2));
        lblPreviewD3mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviewD3miniMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPreviewD1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPreviewD1mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnControlerPSPD1X, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnControlerPSPD1Y, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPreviewD3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPreviewD3mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnControlerPSPD3Y, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnControlerPSPD3X, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPreviewD2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPreviewD2mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnControlerPSPD2X, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnControlerPSPD2Y, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewD1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreviewD1mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnControlerPSPD1X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnControlerPSPD1Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewD2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(spnControlerPSPD2X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnControlerPSPD2Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel15)
                    .addComponent(lblPreviewD2mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewD3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(spnControlerPSPD3X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnControlerPSPD3Y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel16))
                    .addComponent(jLabel17)
                    .addComponent(lblPreviewD3mini, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/neyboychallanger/view/resource/cartaovermelho.png"))); // NOI18N
        jPanel3.add(jLabel3);
        jLabel3.setBounds(390, 10, 120, 186);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/neyboychallanger/view/resource/cartaoamarelo.png"))); // NOI18N
        jPanel3.add(jLabel4);
        jLabel4.setBounds(180, 0, 120, 190);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/neyboychallanger/view/resource/cartaoverde.png"))); // NOI18N
        jPanel3.add(jLabel9);
        jLabel9.setBounds(-10, 0, 120, 190);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(124, 124, 124)
                                        .addComponent(btnStartCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(119, 119, 119))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(2, 2, 2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStartCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartCancelActionPerformed
        txaLog.setText("");
        updateXY();
        boolean flag = !isControlerEnabled();
        pspEsquerdo.setEnabled(flag);
        pspDireito.setEnabled(flag);
        if (flag) {
            btnStartCancel.setText("CANCEL");
            btnStartCancel.setBackground(new Color(255, 153, 153));
        } else {
            btnStartCancel.setText("START");
            btnStartCancel.setBackground(new java.awt.Color(153, 255, 204));
        }
        repaint();
    }//GEN-LAST:event_btnStartCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        pspEsquerdo.setEnabled(false);
        pspDireito.setEnabled(false);
        updateXY();
    }//GEN-LAST:event_formWindowClosing

    private void spnControlerPSPE1XStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPE1XStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPE1XStateChanged

    private void spnControlerPSPE1YStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPE1YStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPE1YStateChanged

    private void spnControlerPSPE2XStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPE2XStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPE2XStateChanged

    private void spnControlerPSPE2YStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPE2YStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPE2YStateChanged

    private void spnControlerPSPE3XStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPE3XStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPE3XStateChanged

    private void spnControlerPSPE3YStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPE3YStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPE3YStateChanged

    private void spnControlerPSPD3XStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPD3XStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPD3XStateChanged

    private void spnControlerPSPD3YStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPD3YStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPD3YStateChanged

    private void spnControlerPSPD2XStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPD2XStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPD2XStateChanged

    private void spnControlerPSPD2YStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPD2YStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPD2YStateChanged

    private void spnControlerPSPD1XStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPD1XStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPD1XStateChanged

    private void spnControlerPSPD1YStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnControlerPSPD1YStateChanged
        updateXY();
    }//GEN-LAST:event_spnControlerPSPD1YStateChanged

    private void lblPreviewE1miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviewE1miniMouseClicked
        pspEsquerdo.moveMouseToXY1();
    }//GEN-LAST:event_lblPreviewE1miniMouseClicked

    private void lblPreviewE2miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviewE2miniMouseClicked
        pspEsquerdo.moveMouseToXY2();
    }//GEN-LAST:event_lblPreviewE2miniMouseClicked

    private void lblPreviewE3miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviewE3miniMouseClicked
        pspEsquerdo.moveMouseToXY3();
    }//GEN-LAST:event_lblPreviewE3miniMouseClicked

    private void lblPreviewD1miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviewD1miniMouseClicked
        pspDireito.moveMouseToXY1();
    }//GEN-LAST:event_lblPreviewD1miniMouseClicked

    private void lblPreviewD2miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviewD2miniMouseClicked
        pspDireito.moveMouseToXY2();
    }//GEN-LAST:event_lblPreviewD2miniMouseClicked

    private void lblPreviewD3miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviewD3miniMouseClicked
        pspDireito.moveMouseToXY3();
    }//GEN-LAST:event_lblPreviewD3miniMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPreviewD1;
    private javax.swing.JLabel lblPreviewD1mini;
    private javax.swing.JLabel lblPreviewD2;
    private javax.swing.JLabel lblPreviewD2mini;
    private javax.swing.JLabel lblPreviewD3;
    private javax.swing.JLabel lblPreviewD3mini;
    private javax.swing.JLabel lblPreviewE1;
    private javax.swing.JLabel lblPreviewE1mini;
    private javax.swing.JLabel lblPreviewE2;
    private javax.swing.JLabel lblPreviewE2mini;
    private javax.swing.JLabel lblPreviewE3;
    private javax.swing.JLabel lblPreviewE3mini;
    private javax.swing.JSpinner spnControlerPSPD1X;
    private javax.swing.JSpinner spnControlerPSPD1Y;
    private javax.swing.JSpinner spnControlerPSPD2X;
    private javax.swing.JSpinner spnControlerPSPD2Y;
    private javax.swing.JSpinner spnControlerPSPD3X;
    private javax.swing.JSpinner spnControlerPSPD3Y;
    private javax.swing.JSpinner spnControlerPSPE1X;
    private javax.swing.JSpinner spnControlerPSPE1Y;
    private javax.swing.JSpinner spnControlerPSPE2X;
    private javax.swing.JSpinner spnControlerPSPE2Y;
    private javax.swing.JSpinner spnControlerPSPE3X;
    private javax.swing.JSpinner spnControlerPSPE3Y;
    private javax.swing.JTextArea txaLog;
    // End of variables declaration//GEN-END:variables

    public void update() {
        boolean flag = isControlerEnabled();
        pspEsquerdo.setEnabled(flag);
        pspDireito.setEnabled(flag);
        if (flag) {
            btnStartCancel.setText("CANCEL");
            btnStartCancel.setBackground(new Color(255, 153, 153));
        } else {
            btnStartCancel.setText("START");
            btnStartCancel.setBackground(new java.awt.Color(153, 255, 204));
        }
        updateXY();
    }

    private void updateXY() {
        if (isControlerEnabled()) {
            return;
        }
        pspEsquerdo.setPrintScreenXY1(
                (int) spnControlerPSPE1X.getValue(),
                (int) spnControlerPSPE1Y.getValue());
        pspEsquerdo.setPrintScreenXY2(
                (int) spnControlerPSPE2X.getValue(),
                (int) spnControlerPSPE2Y.getValue());
        pspEsquerdo.setPrintScreenXY3(
                (int) spnControlerPSPE3X.getValue(),
                (int) spnControlerPSPE3Y.getValue());

        pspDireito.setPrintScreenXY1(
                (int) spnControlerPSPD1X.getValue(),
                (int) spnControlerPSPD1Y.getValue());
        pspDireito.setPrintScreenXY2(
                (int) spnControlerPSPD2X.getValue(),
                (int) spnControlerPSPD2Y.getValue());
        pspDireito.setPrintScreenXY3(
                (int) spnControlerPSPD3X.getValue(),
                (int) spnControlerPSPD3Y.getValue());
        repaint();
        save();
    }

    private void save() {
        Map<String, String> map = new HashMap(12);
        map.put("ControlerPSPD1X", String.valueOf(spnControlerPSPD1X.getValue()));
        map.put("ControlerPSPD1Y", String.valueOf(spnControlerPSPD1Y.getValue()));
        map.put("ControlerPSPD2X", String.valueOf(spnControlerPSPD2X.getValue()));
        map.put("ControlerPSPD2Y", String.valueOf(spnControlerPSPD2Y.getValue()));
        map.put("ControlerPSPD3X", String.valueOf(spnControlerPSPD3X.getValue()));
        map.put("ControlerPSPD3Y", String.valueOf(spnControlerPSPD3Y.getValue()));
        map.put("ControlerPSPE1X", String.valueOf(spnControlerPSPE1X.getValue()));
        map.put("ControlerPSPE1Y", String.valueOf(spnControlerPSPE1Y.getValue()));
        map.put("ControlerPSPE2X", String.valueOf(spnControlerPSPE2X.getValue()));
        map.put("ControlerPSPE2Y", String.valueOf(spnControlerPSPE2Y.getValue()));
        map.put("ControlerPSPE3X", String.valueOf(spnControlerPSPE3X.getValue()));
        map.put("ControlerPSPE3Y", String.valueOf(spnControlerPSPE3Y.getValue()));
        PropertiesSaver.save("lastuse.properties", map);
    }

    private void load() {
        Map<String, String> map = PropertiesSaver.load("lastuse.properties");
        spnControlerPSPD1X.setValue(Integer.valueOf(map.get("ControlerPSPD1X")));
        spnControlerPSPD1Y.setValue(Integer.valueOf(map.get("ControlerPSPD1Y")));
        spnControlerPSPD2X.setValue(Integer.valueOf(map.get("ControlerPSPD2X")));
        spnControlerPSPD2Y.setValue(Integer.valueOf(map.get("ControlerPSPD2Y")));
        spnControlerPSPD3X.setValue(Integer.valueOf(map.get("ControlerPSPD3X")));
        spnControlerPSPD3Y.setValue(Integer.valueOf(map.get("ControlerPSPD3Y")));
        spnControlerPSPE1X.setValue(Integer.valueOf(map.get("ControlerPSPE1X")));
        spnControlerPSPE1Y.setValue(Integer.valueOf(map.get("ControlerPSPE1Y")));
        spnControlerPSPE2X.setValue(Integer.valueOf(map.get("ControlerPSPE2X")));
        spnControlerPSPE2Y.setValue(Integer.valueOf(map.get("ControlerPSPE2Y")));
        spnControlerPSPE3X.setValue(Integer.valueOf(map.get("ControlerPSPE3X")));
        spnControlerPSPE3Y.setValue(Integer.valueOf(map.get("ControlerPSPE3Y")));
        updateXY();
    }
}
