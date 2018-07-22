/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.util;

import java.awt.Color;

/**
 *
 * @author Rodrigo
 */
public abstract class RGBString {

    private RGBString() {
    }

    public static String RGBtoString(int rgb) {
        return RGBtoString(new Color(rgb));
    }

    public static String RGBtoString(Color c) {
        return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }
}
