package org.practice.challenge.image;

import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.Sheet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author feikong
 * @version 2022/9/27
 */
public class ImageShower {

    public static void main(String[] args) {
        showFile("C:\\Users\\Administrator\\Pictures\\4afbfbedab64034fe6aebf3ea0c379310a551da2.jpg");
        showUrl("https://graphic-design-cos.kujiale.com/qhgeom/prod/kigma/pattern/2022/08/12/166028792789601405.png");
    }

    public static void showPpt(Drawable drawable){
        final BufferedImage bufferedImage = new BufferedImage(960, 540, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        drawable.applyTransform(graphics2D);
        drawable.draw(graphics2D);
        graphics2D.dispose();
        showImage(bufferedImage);
    }

    public static void showUrl(String url) {
        try {
            showImage(ImageIO.read(new URL(url)));
        } catch (IOException e) {
            throw new RuntimeException("数据读取失败");
        }
    }

    public static void showFile(String path) {
        try {
            showImage(ImageIO.read(new File(path)));
        } catch (IOException e) {
            throw new RuntimeException("数据读取失败");
        }
    }

    public static void showBytes(byte[] bytes){
        showStream(new ByteArrayInputStream(bytes));
    }

    public static void showStream(InputStream stream) {
        try {
            showImage(ImageIO.read(stream));
        } catch (IOException e) {
            throw new RuntimeException("数据读取失败");
        }
    }

    private static void showImage(BufferedImage image) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("PicReader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
