package com.killersssurprise.сonverter;

import com.killersssurprise.palette.Palette;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * @author killersssurprise
 * 11.9.19
 */

public class Converter {

    public static Mat colorConvert(Mat income, Palette p) {

        int rows = income.rows(); //Calculates number of rows
        int cols = income.cols(); //Calculates number of columns

        int percentDoneCounter = 0;

        Imgproc.cvtColor(income, income, Imgproc.COLOR_BGR2RGB);
        System.out.print("Converting");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                double[] data = income.get(i, j); //Stores element in an array

                try {

                    double[] labInput = Palette.rgb2lab(data[0], data[1], data[2]);
                    Palette.PaletteColor closestColor = p.findClosestPaletteColorLABData(labInput[0], labInput[1], labInput[2]);

                    data[0] = closestColor.r;
                    data[1] = closestColor.g;
                    data[2] = closestColor.b;

                    income.put(i, j, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            float done = (float) i / rows * 100;
            if (percentDoneCounter < (int) done) {
                percentDoneCounter = (int) done;
                if (percentDoneCounter % 5 == 0)
                    System.out.print(".." + percentDoneCounter + "%");
            }

            if (done >= 99) {
                System.out.print(".." + 100 + "%");
            }

        }

        Imgproc.cvtColor(income, income, Imgproc.COLOR_RGB2BGR);

        return income;
    }

    public static Mat doBilateral(Mat input, int d, int sigmaColor, int sigmaSpace){

        Mat output = new Mat();
        Imgproc.bilateralFilter(input, output, d, sigmaColor, sigmaSpace);
        input = output;

        return input;
    }

    public static Mat doMedian(Mat input, int k){

        Imgproc.medianBlur(input, input, k);

        return input;
    }

    public static Mat doDilate(Mat input, int size){

        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size(size, size));
        Imgproc.morphologyEx(input, input, Imgproc.MORPH_DILATE, kernel);

        return input;
    }

    public static Mat doErode(Mat input, int size){

        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size(size,size));
        Imgproc.morphologyEx(input, input, Imgproc.MORPH_DILATE, kernel);

        return input;
    }

    public static Mat doGauss(Mat input, int size, int sigmaX, int sigmaY){

        Imgproc.GaussianBlur(input, input, new Size(size, size), sigmaX, sigmaY);

        return input;
    }

    public static Mat doRCorrection(Mat input, int r){

        Core.add(input, new Scalar(0, 0, r), input);

        return input;
    }

    public static Mat doGCorrection(Mat input, int g){

        Core.add(input, new Scalar(0, g, 0), input);

        return input;
    }

    public static Mat doBCorrection(Mat input, int b){

        Core.add(input, new Scalar(0, 0, b), input);

        return input;
    }

    public static Mat doResize(Mat input, int width, int height){

        Size scaleSize = new Size(width, height);
        Imgproc.resize(input, input, scaleSize);
        return input;
    }

}
