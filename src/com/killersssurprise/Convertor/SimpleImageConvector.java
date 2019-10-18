package com.killersssurprise.Convertor;

import com.killersssurprise.ApplicationArguments.ApplicationArguments;
import com.killersssurprise.Palette.Palette;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author killersssurprise
 * 17.10.19
 */

public class SimpleImageConvector {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private static final int FINISH_ERROR_CODE = -1;
    private static final String KEY_WIDTH = "-width";
    private static final String KEY_HEIGHT = "-height";
    private static final String KEY_RED_CORRECTION = "-r";
    private static final String KEY_GREEN_CORRECTION = "-g";
    private static final String KEY_BLUE_CORRECTION = "-b";

    private static final String KEY_INPUT_PATH = "-input";
    private static final String KEY_OUTPUT_PATH = "-output";

    private static final String KEY_MEDIAN = "-median";
    private static final String KEY_BILATERAL = "-bilateral";
    private static final String KEY_GAUSS = "-gauss";
    private static final String KEY_DILATE = "-dilate";
    private static final String KEY_ERODE = "-erode";
    private static final String KEY_HELP = "-help";

    private static final String KEY_COLORS = "-colors";

    public static void main(String[] args) {

        ApplicationArguments arguments = new ApplicationArguments(args);

        String inputPath = "";
        String outputPath = "";

        if (arguments.containKey(KEY_INPUT_PATH))
            inputPath = arguments.getValue(KEY_INPUT_PATH);
        else {
            System.out.println("Can't find " + KEY_INPUT_PATH);
            System.exit(FINISH_ERROR_CODE);
        }

        if (arguments.containKey(KEY_OUTPUT_PATH))
            outputPath = arguments.getValue(KEY_OUTPUT_PATH);
        else {
            System.out.println("Can't find " + KEY_OUTPUT_PATH);
            System.exit(FINISH_ERROR_CODE);
        }


        Mat imgIncome = Imgcodecs.imread(inputPath);

        if (arguments.containKey(KEY_MEDIAN)) {
            if (arguments.getIntValue(KEY_MEDIAN) < 1 || arguments.getIntValue(KEY_MEDIAN) % 2 == 0) {
                System.out.println(KEY_MEDIAN + " arg should be bigger than 0 and it can't be even!");
                System.exit(FINISH_ERROR_CODE);
            }
            Imgproc.medianBlur(imgIncome, imgIncome, arguments.getIntValue(KEY_MEDIAN));
        }

        if (arguments.containKey(KEY_DILATE)) {

            if (arguments.getIntValue(KEY_DILATE) < 1) {
                System.out.println(KEY_DILATE + " arg should be bigger than 0");
                System.exit(FINISH_ERROR_CODE);
            }
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                    new Size(arguments.getIntValue(KEY_DILATE), arguments.getIntValue(KEY_DILATE)));
            Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);

        }


        if (arguments.containKey(KEY_ERODE)) {

            if (arguments.getIntValue(KEY_ERODE) < 1) {
                System.out.println(KEY_ERODE + " arg should be bigger than 0");
                System.exit(FINISH_ERROR_CODE);
            }
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                    new Size(arguments.getIntValue(KEY_ERODE), arguments.getIntValue(KEY_ERODE)));
            Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);

        }

        if (arguments.containKey(KEY_BILATERAL)) {

            String[] bilArgs = arguments.getValue(KEY_BILATERAL).split(",");

            //3 args check
            if (bilArgs.length < 3) {
                System.out.println("There are just " + bilArgs.length + " arguments found for Bilateral filter args. " + (bilArgs.length > 1 ? "One" : "Two") + " more left");
                System.exit(FINISH_ERROR_CODE);
            }

            if (bilArgs.length >3) {
                System.out.println("More than 3 arguments found for Bilateral filter. Which ones should be in charge? ");
                System.exit(FINISH_ERROR_CODE);
            }

            for (String a : bilArgs) {
                if (Integer.parseInt(a) < 1) {
                    System.out.println("One of the arguments for Bilateral filter is less than 1..");
                    System.exit(FINISH_ERROR_CODE);
                }
            }


//            Imgproc.bilateralFilter(imgIncome, imgIncome, 39, 5 * 2, 5 * 2);
            //D, sigma color, sigma space
            Mat output = new Mat();
            Imgproc.bilateralFilter(imgIncome, output, Integer.parseInt(bilArgs[0]), Integer.parseInt(bilArgs[1]), Integer.parseInt(bilArgs[2]));
            imgIncome = output;

        }

        if (arguments.containKey(KEY_GAUSS)) {


            String[] gausArgs = arguments.getValue(KEY_GAUSS).split(",");

            //3 args check
            if (gausArgs.length < 3) {
                System.out.println("There are just " + gausArgs.length + " arguments found for Gauss filter args. " + (gausArgs.length > 1 ? "One" : "Two") + " more left");
                System.exit(FINISH_ERROR_CODE);
            }

            if (gausArgs.length >3) {
                System.out.println("More than 3 arguments found for Gauss filter. Which ones should be in charge? ");
                System.exit(FINISH_ERROR_CODE);
            }

            for (String a : gausArgs) {
                if (Integer.parseInt(a) < 0) {
                    System.out.println("One of the arguments for Gauss filter is less than 0..");
                    System.exit(FINISH_ERROR_CODE);
                }
            }

            if(Integer.parseInt(gausArgs[0])%2==0||Integer.parseInt(gausArgs[1])%2==0){
                System.out.println("One of the size arguments for Gauss filter is even but should be odd!");
                System.exit(FINISH_ERROR_CODE);
            }

            //size 1, size 2, sigma
//            Imgproc.GaussianBlur(imgIncome, imgIncome, new Size(1, 1), 0);
            Imgproc.GaussianBlur(imgIncome, imgIncome, new Size(Integer.parseInt(gausArgs[0]), Integer.parseInt(gausArgs[1])), Integer.parseInt(gausArgs[2]));


        }


        if (arguments.containKey(KEY_RED_CORRECTION)) {
            Core.add(imgIncome, new Scalar(0, 0, arguments.getIntValue(KEY_RED_CORRECTION)), imgIncome);
        }


        if (arguments.containKey(KEY_GREEN_CORRECTION)) {
            Core.add(imgIncome, new Scalar(0, arguments.getIntValue(KEY_GREEN_CORRECTION), 0), imgIncome);
        }


        if (arguments.containKey(KEY_BLUE_CORRECTION)) {
            Core.add(imgIncome, new Scalar(arguments.getIntValue(KEY_BLUE_CORRECTION), 0, 0), imgIncome);
        }


        if (arguments.containKey(KEY_WIDTH) && arguments.containKey(KEY_HEIGHT)) {

            if (arguments.getIntValue(KEY_HEIGHT) < 1) {
                System.out.println(KEY_HEIGHT + " arg should be bigger than 0");
                System.exit(FINISH_ERROR_CODE);
            }

            if (arguments.getIntValue(KEY_WIDTH) < 1) {
                System.out.println(KEY_WIDTH + " arg should be bigger than 0");
                System.exit(FINISH_ERROR_CODE);
            }

            Size scaleSize = new Size(arguments.getIntValue(KEY_WIDTH), arguments.getIntValue(KEY_HEIGHT));
            Imgproc.resize(imgIncome, imgIncome, scaleSize);


        } else {
            System.out.println("Not found one from width and height arguments, making size as incoming img");
        }


        if (arguments.containKey(KEY_COLORS)) {
            String[] newColors = arguments.getValue(KEY_COLORS).split(",");

            for (int i = 0; i < newColors.length; i++) {
                if (newColors[i].startsWith("#")) {
                    newColors[i] = newColors[i].substring(1);
                }

            }

            Palette.updateColors(newColors);
        }


        Palette p = new Palette();

        Convertor.colorConvert(imgIncome, p);


        Imgcodecs.imwrite(outputPath, imgIncome);

    }

    public static void printHelp() {
        System.out.println(KEY_WIDTH+" set the output image width");
        System.out.println(KEY_HEIGHT+" set the output image height");
        System.out.println(KEY_RED_CORRECTION+" set the output image red color correction");
        System.out.println(KEY_GREEN_CORRECTION+" set the output image green color correction");
        System.out.println(KEY_BLUE_CORRECTION+" set the output image blue color correction");
        System.out.println(KEY_INPUT_PATH+" set the input image path to proceed. For example-  Linux: '/home/folder/name.png' Windows: 'C:\\folder\\name.png'");
        System.out.println(KEY_OUTPUT_PATH+" set the output image path to save. For example-  Linux: '/home/folder/name.png' Windows: 'C:\\folder\\name.png'");
        System.out.println(KEY_MEDIAN+" set the output image median method filtration with arg K. Using: "+KEY_MEDIAN+" 3");
        System.out.println(KEY_BILATERAL+" set the output image bilateral method filtration with arg K,SIGMA_COLOR,SIGMA_SPACE (Use comma to separate them, not space or dots!). Using:  "+KEY_BILATERAL+" 30,5,5");
        System.out.println(KEY_GAUSS+" set the output image gauss method filtration with arg SIZE_1,SIZE_2,SIGMA (Use comma to separate them, not space or dots!). SIZE_1 and SIZE_2 shouldn't be even just odd. Using:  "+KEY_GAUSS+" 1,1,20");
        System.out.println(KEY_ERODE+" set the output image erode processing with arg KERNEL_SIDE. Using:  "+KEY_ERODE+" 3");
        System.out.println(KEY_DILATE+" set the output image dilate processing with arg KERNEL_SIDE. Using:  "+KEY_DILATE+" 3");
        System.out.println(KEY_COLORS+" set the output image colors palette. Replacing default one! (Use comma to separate them, not space or dots!) Using:  "+KEY_COLORS+" #FFFFFF,#000000,#CDCDCD");
        System.exit(1);
    }
}
