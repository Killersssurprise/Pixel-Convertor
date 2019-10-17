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

    private static final String KEY_WIDTH = "-width";
    private static final String KEY_HEIGHT = "-height";
    private static final String KEY_RED_CORRECTION = "-r";
    private static final String KEY_GREEN_CORRECTION = "-g";
    private static final String KEY_BLUE_CORRECTION = "-b";

    private static final String KEY_INPUT_PATH = "-input";
    private static final String KEY_OUTPUT_PATH = "-output";

    private static final String KEY_MEDIAN = "-median";
    private static final String KEY_DILATE = "-dilate";
    private static final String KEY_ERODE = "-erode";

    private static final String KEY_COLORS = "-colors";

    public static void main(String[] args) {

        ApplicationArguments arguments = new ApplicationArguments(args);

//        System.out.println(arguments.toString());


        String inputPath = "";
        String outputPath = "";


        if (arguments.containKey(KEY_INPUT_PATH))
            inputPath = arguments.getValue(KEY_INPUT_PATH);

        if (arguments.containKey(KEY_OUTPUT_PATH))
            outputPath = arguments.getValue(KEY_OUTPUT_PATH);


        Mat imgIncome = Imgcodecs.imread(inputPath);

        if (arguments.containKey(KEY_MEDIAN))
            Imgproc.medianBlur(imgIncome, imgIncome, arguments.getIntValue(KEY_MEDIAN));


        if (arguments.containKey(KEY_DILATE)) {
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                    new Size(arguments.getIntValue(KEY_DILATE), arguments.getIntValue(KEY_DILATE)));
            Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);
        }


        if (arguments.containKey(KEY_ERODE)) {
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                    new Size(arguments.getIntValue(KEY_ERODE), arguments.getIntValue(KEY_ERODE)));
            Imgproc.morphologyEx(imgIncome, imgIncome, Imgproc.MORPH_DILATE, kernel);
        }


        if (arguments.containKey(KEY_RED_CORRECTION)) {
            Core.add(imgIncome, new Scalar(0, 0, arguments.getIntValue(KEY_RED_CORRECTION)), imgIncome);
        }


        if (arguments.containKey(KEY_GREEN_CORRECTION)) {
            Core.add(imgIncome, new Scalar(0, arguments.getIntValue(KEY_GREEN_CORRECTION), 0), imgIncome);
        }


        if (arguments.containKey(KEY_BLUE_CORRECTION)) {
            Core.add(imgIncome, new Scalar(arguments.getIntValue(KEY_GREEN_CORRECTION), 0, 0), imgIncome);
        }


        if (arguments.containKey(KEY_WIDTH) && arguments.containKey(KEY_HEIGHT)) {
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

}
