package org.practice.challenge.ppt;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlObject;

import java.io.FileInputStream;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class InputTest {

    public static void main(String[] args) throws Exception {
        String inputFilePath = "C:\\Users\\Administrator\\Desktop\\4444.pptx";

        // 创建PPT
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(inputFilePath));
        System.out.println(ppt);
    }
}
