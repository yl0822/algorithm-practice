package org.practice.challenge.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author feikong
 * @version 2022/10/8
 */
public class TiffConverter {

    public static byte[] tiffToPng(byte[] source) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(source);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(input);
            ImageIO.write(bufferedImage, "png", output);
            return output.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("图片转换异常", e);
        }
    }
}
