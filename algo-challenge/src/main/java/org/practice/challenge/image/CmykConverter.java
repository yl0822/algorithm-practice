package org.practice.challenge.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import org.apache.commons.imaging.Imaging;
import org.apache.sanselan.Sanselan;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import static org.practice.challenge.image.ImageUtil.readBytesByStreamFromPath;

/**
 * @author feikong
 * @version 2022/9/15
 */
public class CmykConverter {

    public static void rgbToCmyk(String source, String target) throws Exception{
        File sourceFile = new File(source);
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(sourceFile));
        ICC_Profile iccProfile = ICC_Profile.getInstance(CmykConverter.class.getClassLoader().getResourceAsStream("ISOcoated_v2_300_eci.icc"));
        ColorSpace space = new ICC_ColorSpace(iccProfile);
        ColorConvertOp op = new ColorConvertOp(bufferedImage.getColorModel().getColorSpace(), space, null);
        bufferedImage = op.filter(bufferedImage, null);
        ImageIO.write(bufferedImage, "jpg", new File(target));
    }

    public static void convertCmykToRgb(String source, String target) throws Exception {
        ICC_Profile iccProfile = Sanselan.getICCProfile(new File(source));
        ColorSpace cs = new ICC_ColorSpace(iccProfile);

        JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(readBytesByStreamFromPath(source)));
        Raster srcRaster = decoder.decodeAsRaster();

        BufferedImage result = new BufferedImage(srcRaster.getWidth(), srcRaster.getHeight(), BufferedImage.TYPE_INT_RGB);
        WritableRaster resultRaster = result.getRaster();

        ColorConvertOp cmykToRgb = new ColorConvertOp(cs, result.getColorModel().getColorSpace(), null);
        cmykToRgb.filter(srcRaster, resultRaster);
    }
}
