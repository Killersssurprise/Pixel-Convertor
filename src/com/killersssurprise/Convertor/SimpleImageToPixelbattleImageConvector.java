package com.killersssurprise.Convertor;

import com.killersssurprise.Palette.Palette;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;



public class SimpleImageToPixelbattleImageConvector {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {

        String inputPath = "/home/mixa/cheb.jpg";
        String outputPath = "/home/mixa/cheb.png";

        //read image

        Mat imgIncome = Imgcodecs.imread(inputPath); //Reads image from the file system and puts into matrix
//        int rows = imgIncome.rows(); //Calculates number of rows
//        int cols = imgIncome.cols(); //Calculates number of columns

        //convert image

//        Mat img2 = new Mat();
////        Imgproc.boxFilter(imgIncome, img2, -1, new Size(3, 3),
////                new Point(-1, -1), true);


//        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
//                new Size(3, 3));
//        Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);

//        for(int i=0;i<30;i++)

//        Mat hsv = new Mat();
//        Imgproc.cvtColor(imgIncome, hsv, Imgproc.COLOR_RGB2HSV);
//        Imgcodecs.imwrite("/home/mixa/crusader_HSV.png", hsv);

//        imgIncome = hsv;
        Mat output = new Mat();
        imgIncome.convertTo(output, CvType.CV_8U);
        imgIncome = output;


        /***/
        imgIncome = medianBlurFiltration(imgIncome, 1);

        /***/
        Mat img2 = new Mat();
//
//        Imgproc.bilateralFilter(imgIncome, img2, 39, 5 * 2, 5 * 2);
//        Imgproc.bilateralFilter(imgIncome, img2, 19, 5 * 2, 5 * 2);
//        imgIncome = img2;

//        Mat img2 = new Mat();
//        Imgproc.cvtColor(imgIncome, img2, Imgproc.COLOR_BGR2GRAY);
//        CLAHE clane = Imgproc.createCLAHE();
//        clane.setClipLimit(4);
//        clane.apply(img2, imgIncome);

//        Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);

//        Imgproc.medianBlur(imgIncome, img2, 5);
////        Imgproc.dilate(imgIncome, img2,new Mat());
////        Imgproc.bilateralFilter(imgIncome, img2, 5, 5 * 2, 5 * 2);
//
//        Imgproc.GaussianBlur(imgIncome, img2, new Size(1, 1), 0);


//        Palette p = new Palette();
//        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
//                new Size(3, 3));
//        Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);
//        Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_CLOSE, kernel);

//        Mat img2 = new Mat();
//        Imgproc.GaussianBlur(imgIncome, img2, new Size(9, 9), 0);
//        Mat result4 = new Mat();
//        Core.addWeighted(imgIncome, 1.5, img2, -0.5, 0, result4);

//        Imgproc.cvtColor(hsv, imgIncome, Imgproc.COLOR_HSV2RGB);

        /***/
//        imgIncome = medianBlurFiltration(imgIncome, 5);
//        imgIncome = gaussianBlurFiltration(imgIncome, 3, 0);
//        Size scaleSize = new Size(100, 100);
//        Size scaleSize = new Size(200, 250);
//        imgIncome = resize(imgIncome, scaleSize);
        colorConvert(imgIncome);
//        colorConvert(imgIncome);

//        boolean makingColor = true;
//        if (makingColor)
//            for (int i = 0; i < rows; i++) {
//                for (int j = 0; j < cols; j++) {
//
//                    double[] data = imgIncome.get(i, j); //Stores element in an array
//
//                    if(data==null || data.length==0){
//                        data =new double[3];
//                        data[2] = 0;
//                        data[1] = 0;
//                        data[0] = 0;
//                    }
//
//
//                    try {
//                        String hexValue = OperaColor.toHexNoSharp((int) data[2], (int) data[1], (int) data[0]);
//
////                        System.out.println("ColorFound = #" + hexValue + " closest is: " + Integer.toHexString(p.findClosetPaletteColorTo(hexValue, true)));
//
//                        data[2] = Color.decode("#" + Integer.toHexString(p.findClosetPaletteColorTo(hexValue, true))).getRed();
//                        data[1] = Color.decode("#" + Integer.toHexString(p.findClosetPaletteColorTo(hexValue, true))).getGreen();
//                        data[0] = Color.decode("#" + Integer.toHexString(p.findClosetPaletteColorTo(hexValue, true))).getBlue();
//
//                    }catch (Exception e){
////                        e.printStackTrace();
//                    }
//
//                    try {
//                        imgIncome.put(i, j, data);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
////                p.findClosetPaletteColorTo(hexValue);
//
//
////                pixels[i][j] = new Pixel(hexValue, hexValue);
//
//
//                }
////                System.out.println("i= "+i);
////                System.out.println("rows = "+rows);
////                System.out.println("i/rows= "+(double)i/rows);
//                System.out.println("Done: "+(float)i/rows*100+"%");
//
//            }


        /***/
//        imgIncome = medianBlurFiltration(imgIncome, 5);
//        imgIncome = gaussianBlurFiltration(imgIncome, 3, 0);

//        Size scaleSize = new Size(200, 250);
//        imgIncome = resize(imgIncome, scaleSize);
//        imgIncome = medianBlurFiltration(imgIncome, 1);

        //write image
        Imgcodecs.imwrite(outputPath, imgIncome);

    }


    private static Mat colorConvert(Mat income) {
        Palette p = new Palette();
        int rows = income.rows(); //Calculates number of rows
        int cols = income.cols(); //Calculates number of columns

        int percentDoneCounter = 0;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                double[] data = income.get(i, j); //Stores element in an array

                try {
                    /**was working*/
//                        String hexValue = OperaColor.toHexNoSharp((int) data[2], (int) data[1], (int) data[0]).toUpperCase();
                    String hexValue = Palette.toHex2((int) data[2], (int) data[1], (int) data[0]);

                    /**working*/
                    String c = "#" + p.findlosestPaletteColorRGBData((int) data[2], (int) data[1], (int) data[0]).toUpperCase();

                    data[2] = Color.decode(c).getRed();
                    data[1] = Color.decode(c).getGreen();
                    data[0] = Color.decode(c).getBlue();

//                        if(!Palette.containThisColor(hexValue.toUpperCase())){
////                            System.out.println("Color didn't found in palette: rgb: "+data[2]+" "+data[1]+" "+data[0]);
//                            System.out.println("Color didn't found in palette: hex: "+hexValue);
//                            System.out.println("RGB are: "+data[2]+" "+data[1]+" "+data[0]);
//                            System.out.println("Replaced with: "+c);
////                            if(data[2]!=Color.decode(c).getRed() || data[1]!=Color.decode(c).getGreen() || data[0]!=Color.decode(c).getBlue()){
////                                System.out.println("RGB different!");
////                            }
//
//                        }

//                        data[2] = Color.decode("#" + Integer.toHexString(p.findClosestPaletteColorTo(hexValue))).getRed();
//                        data[1] = Color.decode("#" + Integer.toHexString(p.findClosestPaletteColorTo(hexValue))).getGreen();
//                        data[0] = Color.decode("#" + Integer.toHexString(p.findClosestPaletteColorTo(hexValue))).getBlue();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    income.put(i, j, data);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            float done = (float) i / rows * 100;
            if (percentDoneCounter < (int) done) {
                percentDoneCounter = (int) done;
                clearConsole();
                System.out.println("Converting... " + percentDoneCounter + "%");
            }

        }

        return income;
    }

    private static Mat medianBlurFiltration(Mat imgIncome, int ksize) {
        Mat output = new Mat();
        Imgproc.medianBlur(imgIncome, output, ksize);
        return output;
    }

    private static Mat gaussianBlurFiltration(Mat imgIncome, int ksize, int sigmaX) {
        Mat output = new Mat();
        Imgproc.GaussianBlur(imgIncome, output, new Size(ksize, ksize), sigmaX);
        return output;
    }

    private static Mat resize(Mat imgIncome, Size scaleSize) {
        Mat output = new Mat();
        Imgproc.resize(imgIncome, output, scaleSize);
//        Imgproc.GaussianBlur(imgIncome, output, new Size(ksize, ksize), sigmaX);
        return output;
    }

    private static Mat boxFiltration(Mat imgIncome, int ddepth, int ksize, int sigmaX) {
        Mat output = new Mat();
//        Imgproc.boxFilter(imgIncome, output, -1, new Size(3, 3),
        Imgproc.boxFilter(imgIncome, output, ddepth, new Size(3, 3),
                new Point(-1, -1), true);
//        Imgproc.GaussianBlur(imgIncome, output, new Size(ksize, ksize), sigmaX);
        return output;
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

}
