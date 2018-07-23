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
public abstract class ColorUtilities {

    public static float RGBToHFromHSV(Color c) {
        float[] hsv = new float[3];
        hsv = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsv);
        return hsv[0] * 360f;
    }

    private ColorUtilities() {
    }

    public static String RGBtoString(int rgb) {
        return RGBtoString(new Color(rgb));
    }

    public static String RGBtoString(Color c) {
        return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }
}
