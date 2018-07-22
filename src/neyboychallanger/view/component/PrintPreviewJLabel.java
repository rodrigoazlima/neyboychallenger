/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.view.component;

import neyboychallanger.print.PrintScreenController;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Rodrigo
 */
public class PrintPreviewJLabel extends JLabel {

    protected PrintScreenController controller;

    public PrintScreenController getSharedImage() {
        return controller;
    }

    public void setPrintScreenController(PrintScreenController controller) {
        this.controller = controller;
        revalidate();
        repaint();
    }

    @Override
    public Icon getIcon() {
        if (controller != null) {
            return controller.getIcon();
        } else {
            return super.getIcon();
        }
    }

}
