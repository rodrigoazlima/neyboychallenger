/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import neyboychallanger.util.ColorUtilities;

/**
 *
 * @author Rodrigo
 */
public class MiniPrintPreviewJLabel extends PrintPreviewJLabel {

    @Override
    public Icon getIcon() {
        return super.controller.getIconMini();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(controller.getSharedImageMini(), 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public String getToolTipText() {
        if (controller != null) {
            Image i = controller.getSharedImageMini();
            if (i instanceof BufferedImage) {
                BufferedImage bi = (BufferedImage) i;
                int rgb = bi.getRGB(0, 0);
                return ColorUtilities.RGBtoString(rgb);
            }
        }
        return super.getToolTipText();

    }
}
