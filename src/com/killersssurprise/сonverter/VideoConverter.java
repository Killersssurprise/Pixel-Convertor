package com.killersssurprise.сonverter;

import com.killersssurprise.applicationarguments.ApplicationArguments;
import com.killersssurprise.palette.Palette;
import com.killersssurprise.utils.CvUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static com.killersssurprise.applicationarguments.ApplicationArguments.*;

/**
 * @author killersssurprise
 * 19.10.19
 */
public class VideoConverter {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {


        ApplicationArguments arguments = new ApplicationArguments(args);

        JFrame window = new JFrame("Просмотр видео");
//        window.setSize(1000, 600);
        window.setSize(arguments.getIntValue(KEY_WIDTH), arguments.getIntValue(KEY_HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        JLabel label = new JLabel();
        window.setContentPane(label);
        window.setVisible(true);

//        VideoWriter writer = new VideoWriter("/home/mixa/initial_d/initial_d_output.avi",
        VideoWriter writer = new VideoWriter(arguments.getValue(KEY_OUTPUT_PATH),
//                VideoWriter.fourcc('X', 'V', 'I', 'D'), 25, new Size(640, 360), true);
                VideoWriter.fourcc('X', 'V', 'I', 'D'), 25, new Size(
                arguments.getIntValue(KEY_WIDTH),
                arguments.getIntValue(KEY_HEIGHT)), true);


        VideoCapture capture = new VideoCapture(
//                "/home/mixa/initial_d/initial_d.avi");
                arguments.getValue(KEY_INPUT_PATH));
        if (!capture.isOpened()) {
            System.out.println("Не удалось открыть видео");
            return;
        }
        Mat frame = new Mat();
        BufferedImage img;

        Palette p = new Palette();

        while (capture.read(frame)) {

//            frame = SimpleImageConverter.getMatAfterUpdate(arguments, frame);

//
//                Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
//                        new Size(7, 7));
//                Imgproc.dilate(frame, frame, kernel);


//                Core.add(frame, new Scalar(-15, 35, 45), frame);

//                Imgproc.medianBlur(frame, frame, 3);

//                Imgproc.resize(frame, frame, new Size(640, 360));
                Imgproc.resize(frame, frame, new Size(arguments.getIntValue(KEY_WIDTH), arguments.getIntValue(KEY_HEIGHT)));

                Converter.colorConvert(frame, p);

            writer.write(frame);

            // Здесь можно вставить код обработки кадра
            img = CvUtils.MatToBufferedImage(frame);
            if (img != null) {
                ImageIcon imageIcon = new ImageIcon(img);
                label.setIcon(imageIcon);
                label.repaint();
                window.pack();
            }
            try {
                Thread.sleep(10);

                frame.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        writer.release();

        System.out.println("Выход");
        capture.release();

    }

}
