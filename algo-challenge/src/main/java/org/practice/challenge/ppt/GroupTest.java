package org.practice.challenge.ppt;

import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.xslf.usermodel.*;
import org.practice.challenge.ppt.utils.SlideShowUtil;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;

import static org.practice.challenge.ppt.utils.SlideShowUtil.addPic2MediaRepo;

/**
 * @author feikong
 * @version 2022/8/12
 */
public class GroupTest {

    private static final String IMG_PATH = "C:\\Users\\Administrator\\Pictures\\iu001.jpg";

    public static void main(String[] args) throws Exception{
        XMLSlideShow ppt = new XMLSlideShow();
        XSLFPictureData pictureData = addPic2MediaRepo(ppt, IMG_PATH);
        XSLFFontInfo fontInfo = ppt.addFont(new FileInputStream("C:\\Users\\Administrator\\Desktop\\DINCond_Light.otf"));

        XSLFSlide slide = ppt.createSlide();
        XSLFPictureShape pictureShape = slide.createPicture(pictureData);

        slide = ppt.createSlide();
        XSLFTextBox textBox = slide.createTextBox();
        XSLFTextRun textRun = textBox.addNewTextParagraph().addNewTextRun();
        textRun.setText("上豆腐坎咖喱鸡饭");
        textRun.setFontFamily(fontInfo.getTypeface());


        slide = ppt.createSlide();
        XSLFGroupShape groupShape = slide.createGroup();
        groupShape.setAnchor(new Rectangle2D.Double(200, 300, 400, 600));
        groupShape.setInteriorAnchor(new Rectangle2D.Double(200, 300, 800, 1200));

        XSLFAutoShape autoShape = groupShape.createAutoShape();
        autoShape.setShapeType(ShapeType.ELLIPSE);
        autoShape.setAnchor(new Rectangle2D.Double(200, 300, 200, 300));
        autoShape.setLineColor(Color.RED);
        autoShape.setLineWidth(10);

        autoShape = groupShape.createAutoShape();
        autoShape.setShapeType(ShapeType.RECT);
        autoShape.setAnchor(new Rectangle2D.Double(500, 750, 200, 300));
        autoShape.setLineColor(Color.RED);
        autoShape.setLineWidth(10);

//        XSLFFreeformShape freeformShape = groupShape.createFreeform();
//        freeformShape.setPath(ShapeGenerator.drawPath());
//        ShapeGenerator.prettyShape(freeformShape);

        SlideShowUtil.exportPpt(ppt);
    }
}
