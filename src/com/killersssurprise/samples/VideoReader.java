package com.killersssurprise.samples;

import com.killersssurprise.сonverter.Converter;
import com.killersssurprise.сonverter.SimpleImageConverter;
import com.killersssurprise.palette.Palette;
import com.killersssurprise.utils.CvUtils;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class VideoReader {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.loadLibrary("opencv_ffmpeg300_64");
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Просмотр видео");
        window.setSize(1000, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        JLabel label = new JLabel();
        window.setContentPane(label);
        window.setVisible(true);

        VideoWriter writer = new VideoWriter("/home/mixa/initial_d/initial_d_output.avi",
//                VideoWriter.fourcc('X', 'V', 'I', 'D'), 25, new Size(1280, 720), true);
//                VideoWriter.fourcc('X', 'V', 'I', 'D'), 25, new Size(960, 540), true);
                VideoWriter.fourcc('X', 'V', 'I', 'D'), 25, new Size(640, 360), true);


        VideoCapture capture = new VideoCapture(
                "/home/mixa/initial_d/initial_d.avi");
//                "/home/mixa/OpenCV/opencv-4.1.1/samples/data/tree.avi");
        if (!capture.isOpened()) {
            System.out.println("Не удалось открыть видео");
            return;
        }
        Mat frame = new Mat();
        BufferedImage img = null;
        Palette p = new Palette();
        int frame_counter=0;

        while (capture.read(frame)) {

            if(frame_counter%5!=0) {


//            frame = SimpleImageConverter.medianBlurFiltration(frame, 1);
                Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                        new Size(7, 7));
                Imgproc.dilate(frame, frame, kernel);


                Core.add(frame, new Scalar(-15, 35, 45), frame);

//            Mat imgHSV = new Mat();
//            Imgproc.cvtColor(frame, imgHSV, Imgproc.COLOR_BGR2HSV);
//            Core.add(imgHSV, new Scalar(0, 0, 30), imgHSV);
//            Mat imgBGR = new Mat();
//            Imgproc.cvtColor(imgHSV, imgBGR, Imgproc.COLOR_HSV2BGR);
//            frame = imgBGR;

                Imgproc.medianBlur(frame, frame, 3);


//            Imgproc.resize(frame, frame, new Size(960, 540));
                Imgproc.resize(frame, frame, new Size(640, 360));

//            Mat mat2 = new Mat();
//            Imgproc.bilateralFilter(frame, mat2, 39, 5 * 2, 5 * 2);
//            frame = mat2;

//            Imgproc.resize(frame, frame, new Size(1280, 720));
                frame = Converter.colorConvert(frame, p);

            }else{
                Imgproc.resize(frame, frame, new Size(640, 360));
            }

            writer.write(frame);


//            Imgproc.resize(frame, frame, new Size(960, 540));
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
            }

            frame_counter++;

        }

        writer.release();

        System.out.println("Выход");
        capture.release();

    }
}
