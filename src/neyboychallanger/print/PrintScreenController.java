/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.print;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import neyboychallanger.view.component.PrintPreview;

/**
 *
 * @author Rodrigo
 */
public class PrintScreenController {

    private ImageIcon sharedmini;
    private ImageIcon shared;
    private int x = 0, y = 0, w = 60, h = 60;
    private List<PrintPreview> listPreviewLabels;

    public PrintScreenController() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Rectangle getPrintRectangle() {
        return new Rectangle(x - w / 2, y - h / 2, w, h);
    }

    public Rectangle getExactRectangle() {
        return new Rectangle(x, y, 1, 1);
    }

    public Image getSharedImage() {
        return getShared().getImage();
    }

    public Image getSharedImageMini() {
        return getSharedMini().getImage();
    }

    public ImageIcon getShared() {
        if (shared == null) {
            shared = new ImageIcon();
        }
        return shared;
    }

    public ImageIcon getSharedMini() {
        if (sharedmini == null) {
            sharedmini = new ImageIcon();
        }
        return sharedmini;
    }

    public Icon getIcon() {
        return getShared();
    }

    public Icon getIconMini() {
        return getSharedMini();
    }

    public void setSharedIcon(Image i) {
        getShared().setImage(i);
    }

    public void setSharedIconMini(Image i) {
        getSharedMini().setImage(i);
    }

    public void addLabel(PrintPreview label) {
        getListPreviewLabels().add(label);
        label.setPrintScreenController(this);
    }

    public List<PrintPreview> getListPreviewLabels() {
        if (listPreviewLabels == null) {
            listPreviewLabels = new ArrayList();
        }
        return listPreviewLabels;
    }

    public void repaintRecursively() {
        for (PrintPreview pp : getListPreviewLabels()) {
            pp.repaint();
        }
    }

}
