package com.killersssurprise.utils;

import org.opencv.core.Core;

public class Tester {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static void main(String[] args) {
//        VideoCapture capture = new VideoCapture(
//                "/home/mixa/initial_d/initial_d.avi");
////                "/home/mixa/OpenCV/opencv-4.1.1/samples/data/tree.avi");
//        if (!capture.isOpened()) {
//            System.out.println("Не удалось открыть видео");
//            return;
//        }
//
//        Mat frame = new Mat();
//        BufferedImage img = null;
//
//        capture.read(frame);
//
//
//        frame.convertTo(frame, CvType.CV_8U);
//
//        colorConvert(frame);
//
//
//    }
//
//    public static Mat colorConvert(Mat income) {
////        palette p = new palette();
//
//        Mat incomeLab = new Mat();
//        Imgproc.cvtColor(income, incomeLab, Imgproc.COLOR_BGR2Lab);
//
//        int i=0, j=0;
//
//        double[] data = income.get(i, j); //Stores element in an array
//
//        try {
//
////            palette.PaletteColor closestColor = p.findlosestPaletteColorRGBData((int) data[2], (int) data[1], (int) data[0]);
//
////            palette.PaletteColor paletteColor = new palette.PaletteColor("FF6C5B", 10);
//
//            double[] labData1 = palette.rgb2lab((int) data[2], (int) data[1], (int) data[0]);
//            double[] labData2 = incomeLab.get(i, j); //Stores element in an array
//
//
//            System.out.println("1 LabData1: "+ Arrays.toString(labData1));
//            System.out.println("1 LabData2: "+ Arrays.toString(labData2));
//
//
//
//            palette.PaletteColor paletteColor = new palette.PaletteColor("474747"/*, 10*/);
//            labData1 = palette.rgb2lab( paletteColor.r, paletteColor.g, paletteColor.b);
//
//            Mat m = new Mat(1,1, CvType.CV_8UC3, new Scalar(paletteColor.b,paletteColor.g,paletteColor.r));
//            Imgproc.cvtColor(m, m, Imgproc.COLOR_BGR2Lab);
//            labData2 = m.get(0,0);
//
//            System.out.println("2 LabData1: "+ Arrays.toString(labData1));
//            System.out.println("2 LabData2: "+ Arrays.toString(labData2));
//
//
////            palette.PaletteColor closestColor2 = p.findlosestPaletteColorLABData((int) labData2[0], (int) labData2[1], (int) labData2[2]);
//
////            if (!closestColor.hex.equals(closestColor2.hex)) {
////                System.out.println("Colors rgb different!: " + closestColor.hex + " != " + closestColor2.hex);
////            }
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        return income;
    }
}
