package org.practice.challenge.ppt;

import org.apache.poi.hssf.usermodel.HSSFShapeTypes;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFFontInfo;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtension;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.practice.challenge.ppt.PptParseTest.outputFilePath;

/**
 * @author feikong
 * @version 2022/8/12
 */
public class SectionTest {

    public static void main(String[] args) throws Exception{
        XMLSlideShow ppt = new XMLSlideShow();

        ppt.createSlide().createTextBox().addNewTextParagraph().addNewTextRun().setText("测试PPT分节");
        ppt.createSlide().createAutoShape().setShapeType(ShapeType.DIAMOND);
        XSLFAutoShape autoShape = ppt.createSlide().createAutoShape();
        autoShape.setAnchor(new Rectangle2D.Double(234, 132, 264, 200));
        autoShape.setLineColor(Color.RED);
        autoShape.setLineWidth(10);


        ppt.getCTPresentation().getSldIdLst().getSldIdList();
        CTExtensionList extensionList = ppt.getCTPresentation().addNewExtLst();
        CTExtension extension = extensionList.addNewExt();
        extension.setUri("{521415D9-36F7-43E2-AB2F-B90AF26B5E84}");
        extension.set(XmlTest.createSection());


        FileOutputStream out = new FileOutputStream(outputFilePath);
        ppt.write(out);
        out.close();
        ppt.close();
    }
}
