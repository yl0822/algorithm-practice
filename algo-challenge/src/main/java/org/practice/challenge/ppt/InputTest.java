package org.practice.challenge.ppt;

import com.sun.javafx.image.PixelUtils;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.draw.DrawAutoShape;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.DrawSlide;
import org.apache.poi.sl.draw.DrawTextBox;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.xmlbeans.XmlObject;

import java.awt.Color;
import java.io.FileInputStream;

import static org.practice.challenge.image.ImageShower.showBytes;
import static org.practice.challenge.image.ImageShower.showPpt;
import static org.practice.challenge.image.ImageShower.showStream;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class InputTest {

    public static void main(String[] args) throws Exception {
        String inputFilePath = "C:\\Users\\Administrator\\Desktop\\99999.pptx";

        // 创建PPT
//        SlideShow ppt = new HSLFSlideShow(new FileInputStream(inputFilePath));
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(inputFilePath));

        XSLFSlide slide = ppt.getSlides().get(0);
//        showPpt(new DrawSlide(slide));

        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof PictureShape){
                showPpt(new DrawPictureShape((PictureShape) shape));
            }else if (shape instanceof TextBox){
                showPpt(new DrawTextBox((TextBox) shape));
            }else if (shape instanceof AutoShape){
                showPpt(new DrawAutoShape((AutoShape) shape));
            }
        }


//
//        byte[] bytes = ((PictureShape) slide.getShapes().get(2)).getPictureData().getData();
//        showBytes(bytes);

        System.out.println();
    }
}
