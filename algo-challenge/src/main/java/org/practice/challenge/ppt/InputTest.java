package org.practice.challenge.ppt;

import com.sun.javafx.image.PixelUtils;
import org.apache.poi.hslf.usermodel.HSLFPictureShape;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.draw.DrawAutoShape;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.DrawSlide;
import org.apache.poi.sl.draw.DrawTextBox;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.xmlbeans.XmlObject;
import org.practice.challenge.image.TiffConverter;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import static org.practice.challenge.image.ImageShower.showBytes;
import static org.practice.challenge.image.ImageShower.showPpt;
import static org.practice.challenge.image.ImageShower.showStream;
import static org.practice.challenge.image.ImageUtil.readBytesByBuffer;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class InputTest {
    private static final String INPUT_IMAGE = "C:\\Users\\Administrator\\Desktop\\image1.tiff";

    public static void main(String[] args) throws Exception {
        String inputFilePath1 = "C:\\Users\\Administrator\\Desktop\\866.ppt";
        SlideShow ppt = new HSLFSlideShow(new FileInputStream(inputFilePath1));

//        String inputFilePath2 = "C:\\Users\\Administrator\\Desktop\\866.ppt";
//        SlideShow ppt = new XMLSlideShow(new FileInputStream(inputFilePath2));
        FileMagic fm = FileMagic.valueOf(new FileInputStream(inputFilePath1));
        System.out.println(fm);
//        for (Object pictureDatum : ppt.getPictureData()) {
//            final PackagePart packagePart = ((XSLFPictureData)pictureDatum).getPackagePart();
//            // 强行更新图片类型，这样后续在draw的过程中就可以获取到renderer
//            packagePart.setContentType(PictureData.PictureType.PNG.contentType);
//        }
//        Slide slide = (Slide)ppt.getSlides().get(0);
//        showPpt(new DrawSlide(slide), ppt.getPageSize());



//        showBytes(readBytesByBuffer(INPUT_IMAGE));

//        final PictureShape pictureShape = (PictureShape)slide.getShapes().get(0);
//        byte[] bytes = pictureShape.getPictureData().getData();
//        final ByteArrayOutputStream os = new ByteArrayOutputStream();
//        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
//        BufferedImage bufferedImage = ImageIO.read(input);
//        ImageIO.write(bufferedImage, "png", os);

//        System.out.println(pictureShape.getPictureData().getContentType());

//        pictureShape.getPictureData().setData(TiffConverter.tiffToPng(bytes));

//        System.out.println(pictureShape.getPictureData().getContentType());
//
//        BufferedImage bufferedImage = new BufferedImage((int)pictureShape.getAnchor().getWidth(),
//                (int)pictureShape.getAnchor().getHeight(), BufferedImage.TYPE_INT_ARGB);
//        final Graphics2D graphics2D = bufferedImage.createGraphics();
//        pictureShape.draw(graphics2D, new Rectangle2D.Double(0d, 0d, pictureShape.getAnchor().getWidth(),
//                pictureShape.getAnchor().getHeight()));
//        graphics2D.dispose();
//
//        ImageIO.write(bufferedImage, "png", os);
//        bytes = os.toByteArray();
//        showBytes(bytes);

//        for (Object shape : slide.getShapes()) {
//            showPpt((Shape)shape);
//        }
    }
}
