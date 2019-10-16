package com.killersssurprise.Palette;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Palette {

    private static final List<PaletteColor> colors = new ArrayList<>();//new ArrayList().add(new PaletteColor());

    static {


        colors.add(new PaletteColor("FFFFFF", 0));
        colors.add(new PaletteColor("C2C2C2", 1));
        colors.add(new PaletteColor("858585", 2));
        colors.add(new PaletteColor("474747", 3));
        colors.add(new PaletteColor("000000", 4));
        colors.add(new PaletteColor("3AAFFF", 5));
        colors.add(new PaletteColor("71AAEB", 6));
        colors.add(new PaletteColor("4A76A8", 7));
        colors.add(new PaletteColor("074BF3", 8));
        colors.add(new PaletteColor("5E30EB", 9));
        colors.add(new PaletteColor("FF6C5B", 10));
        colors.add(new PaletteColor("FE2500", 11));
        colors.add(new PaletteColor("FF218B", 12));
        colors.add(new PaletteColor("99244F", 13));
        colors.add(new PaletteColor("4D2C9C", 14));
        colors.add(new PaletteColor("FFCF4A", 15));
        colors.add(new PaletteColor("FEB43F", 16));
        colors.add(new PaletteColor("FE8648", 17));
        colors.add(new PaletteColor("FF5B36", 18));
        colors.add(new PaletteColor("DA5100", 19));
        colors.add(new PaletteColor("94E044", 20));
        colors.add(new PaletteColor("5CBF0D", 21));
        colors.add(new PaletteColor("C3D117", 22));
        colors.add(new PaletteColor("FCC700", 23));
        colors.add(new PaletteColor("D38301", 24));

    }

    public static void updateColors(String[] newColors){
        if(newColors.length>0){

            colors.clear();

            for(int i=0;i<newColors.length;i++){
                colors.add(new PaletteColor(newColors[i],i));
            }

        }
    }

    public static boolean containThisColor(String hex2) {

        for (PaletteColor c : colors) {
            if (c.hex.equals(hex2))
                return true;
        }
        return false;
    }

    public static double[] rgb2lab(double R, double G, double B) {
        //http://www.brucelindbloom.com

        double r, g, b, X, Y, Z, fx, fy, fz, xr, yr, zr;
        double Ls, as, bs;
        double eps = 216.f / 24389.f;
        double k = 24389.f / 27.f;

        double Xr = 0.964221f;  // reference white D50
        double Yr = 1.0f;
        double Zr = 0.825211f;

        // RGB to XYZ
        r = R / 255.f; //R 0..1
        g = G / 255.f; //G 0..1
        b = B / 255.f; //B 0..1

        // assuming sRGB (D65)
        if (r <= 0.04045)
            r = r / 12;
        else
            r = (float) Math.pow((r + 0.055) / 1.055, 2.4);

        if (g <= 0.04045)
            g = g / 12;
        else
            g = (float) Math.pow((g + 0.055) / 1.055, 2.4);

        if (b <= 0.04045)
            b = b / 12;
        else
            b = (float) Math.pow((b + 0.055) / 1.055, 2.4);


        X = 0.436052025f * r + 0.385081593f * g + 0.143087414f * b;
        Y = 0.222491598f * r + 0.71688606f * g + 0.060621486f * b;
        Z = 0.013929122f * r + 0.097097002f * g + 0.71418547f * b;

        // XYZ to Lab
        xr = X / Xr;
        yr = Y / Yr;
        zr = Z / Zr;

        if (xr > eps)
            fx = (float) Math.pow(xr, 1 / 3.);
        else
            fx = (float) ((k * xr + 16.) / 116.);

        if (yr > eps)
            fy = (float) Math.pow(yr, 1 / 3.);
        else
            fy = (float) ((k * yr + 16.) / 116.);

        if (zr > eps)
            fz = (float) Math.pow(zr, 1 / 3.);
        else
            fz = (float) ((k * zr + 16.) / 116);

        Ls = (116 * fy) - 16;
        as = 500 * (fx - fy);
        bs = 200 * (fy - fz);

        double[] lab = new double[3];

        lab[0] = (2.55 * Ls + .5);
        lab[1] = (as + .5);
        lab[2] = (bs + .5);

        return lab;
    }

    public PaletteColor findlosestPaletteColorRGBData(float r, float g, float b) {

        PaletteColor closestColor = null;
        float closestDistance = Integer.MAX_VALUE;
        for (final PaletteColor paletteColor : colors) {
            final float distance = paletteColor.rgbDistanceTo(r, g, b);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColor = paletteColor;

            }
        }
        return closestColor;
    }

    public PaletteColor findlosestPaletteColorLABData(double l, double a, double b) {

        PaletteColor closestColor = null;
        float closestDistance = Integer.MAX_VALUE;
        for (final PaletteColor paletteColor : colors) {
            final float distance = paletteColor.labDistanceTo(l,a,b);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColor = paletteColor;

            }
        }
        return closestColor;
    }

    public PaletteColor findlosestPaletteColorLABData(double l) {

        PaletteColor closestColor = null;
        float closestDistance = Integer.MAX_VALUE;
        for (final PaletteColor paletteColor : colors) {
            final float distance = paletteColor.labDistanceTo(l);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColor = paletteColor;

            }
        }
        return closestColor;
    }


    public static final class PaletteColor {
        public final double r;
        public final double g;
        public final double b;
        private int colorID;
        final String hex;
        private double[] lab;

        public PaletteColor(final String hex, int colorID) {
            this.hex = hex.toUpperCase();
            this.colorID = colorID;
            r = Color.decode("#" + hex).getRed();
            g = Color.decode("#" + hex).getGreen();
            b = Color.decode("#" + hex).getBlue();

            this.lab = rgb2lab(this.r, this.g, this.b);

        }

        int getColorID() {
            return colorID;
        }

        String getHex() {
            return hex;
        }

        public float rgbDistanceTo(float r, float g, float b) {

            double[] r1 = rgb2lab(r, g, b);

            return (float) Math.abs(Math.sqrt(
                    (Math.pow(r1[0] - lab[0], 2)) +
                            (Math.pow(r1[1] - lab[1], 2)) +
                            (Math.pow(r1[2] - lab[2], 2))));
        }

        public float labDistanceTo(double l, double a, double b) {

            return (float) Math.abs(Math.sqrt(
                    (Math.pow(l - lab[0], 2)) +
                            (Math.pow(a - lab[1], 2)) +
                            (Math.pow(b - lab[2], 2))));
        }

        public float labDistanceTo(double l) {

            return (float) Math.abs(Math.sqrt(
                    (Math.pow(l - lab[0], 2))));
        }

    }

    public static int findByColorIsIn(String codeIsIn) {
        return Objects.requireNonNull(colors.stream().filter(color -> codeIsIn.equals(color.getHex())).findFirst().orElse(null)).getColorID();
    }

    public static String toHex2(int r, int g, int b) {
        return String.format("%02X%02X%02X", r, g, b);
    }

}
