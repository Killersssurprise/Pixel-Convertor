package com.killersssurprise.Convertor;

import com.killersssurprise.Palette.Palette;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * @author killersssurprise
 * 11.9.19
 */

public class Convertor {

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
//                clearConsole();
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

}
