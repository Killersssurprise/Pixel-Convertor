package com.killersssurprise.Convertor;

import com.killersssurprise.ApplicationArguments.ApplicationArguments;
import com.killersssurprise.Palette.Palette;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;


public class SimpleImageToPixelbattleImageConvector {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private static final String KEY_WIDTH = "-width";
    private static final String KEY_HEIGHT = "-height";
    private static final String KEY_RED_CORRECTION = "-R";
    private static final String KEY_GREEN_CORRECTION = "-G";
    private static final String KEY_BLUE_CORRECTION = "-B";

    private static final String KEY_INPUT_PATH = "-input";
    private static final String KEY_OUTPUT_PATH = "-output";

    private static final String KEY_MEDIAN = "-median";

    public static void main(String[] args) {

        boolean debug = true;

        System.out.println("Args before: "+ Arrays.toString(args));

        ApplicationArguments arguments = new ApplicationArguments(args);


//        if(arguments.containKey(KEY_WIDTH)){
//            System.out.println("Arguments contain: "+KEY_WIDTH);
//        }


        System.out.println(arguments.toString());




//        String inputPath = "/home/mixa/crusader.bmp";
//        String outputPath = "/home/mixa/initial_d/crusader.png";

        String inputPath = "";
        String outputPath = "";


        if(debug){
            if(arguments.containKey(KEY_INPUT_PATH))
            inputPath = arguments.getValue(KEY_INPUT_PATH);

            if(arguments.containKey(KEY_OUTPUT_PATH))
            outputPath = arguments.getValue(KEY_OUTPUT_PATH);

        }

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
//        Mat output = new Mat();
//        imgIncome.convertTo(output, CvType.CV_8U);
//        imgIncome = output;


        /***/
        imgIncome = medianBlurFiltration(imgIncome, 1);

        /***/
//        Mat img2 = new Mat();
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
        Palette p = new Palette();
        colorConvert(imgIncome,p);
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


    public static Mat colorConvert(Mat income, Palette p) {

        int rows = income.rows(); //Calculates number of rows
        int cols = income.cols(); //Calculates number of columns

        int percentDoneCounter = 0;

        Imgproc.cvtColor(income,income,Imgproc.COLOR_BGR2RGB);


//        Mat incomeLab = new Mat();
//        Imgproc.cvtColor(income,incomeLab,Imgproc.COLOR_RGB2Lab);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                double[] data = income.get(i, j); //Stores element in an array

                try {

                    /**working*/
//                    String c = "#" + p.findlosestPaletteColorRGBData((int) data[2], (int) data[1], (int) data[0]).toUpperCase();
//                    Palette.PaletteColor closestColor = p.findlosestPaletteColorRGBData((int) data[2], (int) data[1], (int) data[0]);
//                    Palette.PaletteColor closestColor = p.findlosestPaletteColorRGBData((int) data[0], (int) data[1], (int) data[2]);


                    double[] labInput = Palette.rgb2lab(data[0], data[1], data[2]);
                    Palette.PaletteColor closestColor = p.findlosestPaletteColorLABData(labInput[0],labInput[1],labInput[2]);

//                    double[] labData = incomeLab.get(i, j); //Stores element in an array
//                    Palette.PaletteColor closestColor = p.findlosestPaletteColorLABData(labData[0],labData[1],labData[2]);



//                    double[] data2 = incomeLab.get(i, j); //Stores element in an array
//                    Palette.PaletteColor closestColor2 = p.findlosestPaletteColorLABData((int) data2[0], (int) data2[1], (int) data2[2]);

//                    if(!closestColor.hex.equals(closestColor2.hex)){
//                        System.out.println("Colors rgb different!: "+closestColor.hex+" != "+closestColor2.hex);
//                    }

//                    data[2] = closestColor.r;
//                    data[1] = closestColor.g;
//                    data[0] = closestColor.b;

                    data[0] = closestColor.r;
                    data[1] = closestColor.g;
                    data[2] = closestColor.b;


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
            if (percentDoneCounter < (int) done ) {
                percentDoneCounter = (int) done;
                clearConsole();
                if(percentDoneCounter%5==0)
                System.out.println("Converting... " + percentDoneCounter + "%");
            }

        }


        Imgproc.cvtColor(income,income,Imgproc.COLOR_RGB2BGR);

        return income;
    }

    public static Mat medianBlurFiltration(Mat imgIncome, int ksize) {
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
