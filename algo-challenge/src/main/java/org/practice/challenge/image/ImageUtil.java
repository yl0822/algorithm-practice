package org.practice.challenge.image;

import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

/**
 * @author feikong
 * @version 2022/9/14
 */
public class ImageUtil {

    public static byte[] readBytesByStreamFromUrl(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        return read(connection.getInputStream());
    }

    public static byte[] readBytesByStreamFromPath(String path) throws Exception {
        return read(new FileInputStream(path));
    }

    private static byte[] read(InputStream inputStream) throws Exception{
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    public static byte[] readBytesByBuffer(String path) throws Exception {
        BufferedImage image = ImageIO.read(new FileInputStream(path));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return os.toByteArray();
    }

    public static void readBytesByBufferWithoutCompress(String inputFile, String outputFile) throws Exception{
        final BufferedImage image = ImageIO.read(new FileInputStream(inputFile));
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("png");
        if (iter.hasNext()) {
            ImageWriter writer = iter.next();
            ImageWriteParam param = writer.getDefaultWriteParam();
//            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            // 该参数在0.92～0.93左右是最接近原图大小的，最大是1
//            param.setCompressionQuality(1f);
            FileImageOutputStream out = new FileImageOutputStream(new File(outputFile));
            writer.setOutput(out);
            writer.write(null, new IIOImage(image, null, null), param);
            out.close();
            writer.dispose();
        }
    }

    public static byte[] readBytesByPpt(String path) throws Exception {
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(path));
        return ((PictureShape) ppt.getSlides().get(0).getShapes().get(0)).getPictureData().getData();
    }

    public static void write2File(byte[] data, String path) throws Exception {
        System.out.println("byte size : " + data.length);
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
    }
}
