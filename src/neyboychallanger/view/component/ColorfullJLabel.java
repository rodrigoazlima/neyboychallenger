/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.view.component;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Rodrigo
 */
public class ColorfullJLabel extends JLabel {

    private Color color;

    public ColorfullJLabel(Color color) {
        setColor(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
        } else {
            this.color = Color.BLACK;
        }
    }

}
