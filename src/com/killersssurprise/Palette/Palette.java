package com.killersssurprise.Palette;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Palette {

    public static final List<PaletteColor> colors = new ArrayList<>();//new ArrayList().add(new PaletteColor());

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
//        colors.add(new PaletteColor("D38301", -1));

    }

    public static boolean containThisColor(String hex2) {

        for (PaletteColor c : colors) {
            if (c.hex.equals(hex2))
                return true;
        }
        return false;
    }

//    private static int hexGetR(String hex) {
//        int color = Integer.parseUnsignedInt(hex, 16);
//        return ((color & 0xff000000) >>> 24);
//    }
//
//    private static int hexGetG(String hex) {
//        int color = Integer.parseUnsignedInt(hex, 16);
//        return ((color & 0x00ff0000) >>> 16);
//    }
//
//    private static int hexGetB(String hex) {
//        int color = Integer.parseUnsignedInt(hex, 16);
//        return ((color & 0x0000ff00) >>> 8);
//    }

    public static double[] rgb2lab(float R, float G, float B) {
        //http://www.brucelindbloom.com

        float r, g, b, X, Y, Z, fx, fy, fz, xr, yr, zr;
        float Ls, as, bs;
        float eps = 216.f / 24389.f;
        float k = 24389.f / 27.f;

        float Xr = 0.964221f;  // reference white D50
        float Yr = 1.0f;
        float Zr = 0.825211f;

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

//    public static double[] hexGetHSV(int red, int green, int blue) {
//
////        Color c = Color.decode("#" + hex);
//
//        //method returns from 0 to 1
//        float[] hsvData = Color.RGBtoHSB(red,green, blue, null);
//
//
//        hsvData[0] *= 360;
//        hsvData[1] *= 100;
//        hsvData[2] *= 100;
//
//        double[] output = new double[3];
//        output[0] = hsvData[0];
//        output[1] = hsvData[1];
//        output[2] = hsvData[2];
//        return output;
//
//    }

//    public String findlosestPaletteColorHSVData(double[] dataHSV) {
//
//        PaletteColor closestColor = null;
//        float closestDistance = Integer.MAX_VALUE;
//        for (final PaletteColor paletteColor : colors) {
//            final float distance = paletteColor.distanceTo(dataHSV);
//            if (distance < closestDistance) {
//                closestDistance = distance;
//                closestColor = paletteColor;
//
//            }
//        }
//        return Objects.requireNonNull(closestColor).getHex();
//    }

    public String findlosestPaletteColorRGBData(float r, float g, float b) {

        PaletteColor closestColor = null;
        float closestDistance = Integer.MAX_VALUE;
        for (final PaletteColor paletteColor : colors) {
            final float distance = paletteColor.distanceTo(r, g, b);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColor = paletteColor;

            }
        }
        return Objects.requireNonNull(closestColor).getHex();
    }


    public static final class PaletteColor {
        private final int r;
        private final int g;
        private final int b;
        //        private final int color;
//        private final double h;
//        private final double s;
//        private final double v;
//        private final double[] hsvData;
        private int colorID;
        private final String hex;

        PaletteColor(final String hex, int colorID) {
            this.hex = hex.toUpperCase();
            this.colorID = colorID;
            r = Color.decode("#" + hex).getRed();
            g = Color.decode("#" + hex).getGreen();
            b = Color.decode("#" + hex).getBlue();

//            hsvData = hexGetHSV(r,g,b);
//            h=hsvData[0];
//            s=hsvData[1];
//            v=hsvData[2];

        }

        int getColorID() {
            return colorID;
        }

        String getHex() {
            return hex;
        }


//        int distanceTo(final int color) {
////        float distanceTo(final int color) {
//            final int deltaR = this.r - ((color & 0xff000000) >>> 24);
//            final int deltaG = this.g - ((color & 0x00ff0000) >>> 16);
//            final int deltaB = this.b - ((color & 0x0000ff00) >>> 8);
//            return (deltaR * deltaR) + (deltaG * deltaG) + (deltaB * deltaB);
////            return (0.3f*deltaR) + (0.59f*deltaG) + (0.11f*deltaB);
//        }

//        public float distanceTo(double[] hsvData) {
////            return (int) Math.min(Math.abs(data[0] - this.h), 360 - Math.abs(data[0] - this.h));
////            System.out.println("Distance to data: "+ Arrays.toString(data));
//
//
//
//            return (float) (Math.abs(hsvData[0] - this.hsvData[0]));
////            return (int) Math.min(Math.abs(data[0] - this.h), 360 - Math.abs(data[0] - this.h));
//
//        }

        public float distanceTo(float r, float g, float b) {

            double[] r1 = rgb2lab(r, g, b);
            double[] r2 = rgb2lab(this.r, this.g, this.b);


            return (float) Math.abs(Math.sqrt(
                    (Math.pow(r1[0] - r2[0], 2)) +
                            (Math.pow(r1[1] - r2[1], 2)) +
                            (Math.pow(r1[2] - r2[2], 2))));
        }

    }

    public static int findByColorIsIn(String codeIsIn) {
        return Objects.requireNonNull(colors.stream().filter(color -> codeIsIn.equals(color.getHex())).findFirst().orElse(null)).getColorID();
    }

    public static String toHex2(int r, int g, int b) {
//        return String.format("#%02X%02X%02X", r, g, b);
        return String.format("%02X%02X%02X", r, g, b);
    }

}
