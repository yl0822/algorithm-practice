package org.practice.challenge.ppt;

import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileOutputStream;

import static org.practice.challenge.ppt.PptParseTest.outputFilePath;

/**
 * @author feikong
 * @version 2022/10/18
 */
public class GroupShapeTest {
    public static void main(String[] args) throws Exception {

        SlideShow ppt = new XMLSlideShow();

        Slide slide = ppt.createSlide();

        int groupLeft = 100;
        int groupTop = 50;
        int groupWidth = 350;
        int groupHeight = 300;
        int groupPadding= 10;

        GroupShape group = slide.createGroup();
        group.setInteriorAnchor(new Rectangle(groupLeft, groupTop, groupWidth, groupHeight));
        group.setAnchor(new Rectangle(groupLeft+groupPadding, groupTop+groupPadding, groupWidth-groupPadding, groupHeight-groupPadding));
//        group.setAnchor(new Rectangle(groupLeft, groupTop, groupWidth, groupHeight));
//        group.setInteriorAnchor(new Rectangle(groupLeft+groupPadding, groupTop+groupPadding, groupWidth-groupPadding, groupHeight-groupPadding));

        AutoShape shape = group.createAutoShape();
        shape.setShapeType(ShapeType.RECT);
        shape.setFillColor(Color.GREEN);
        shape.setAnchor(new Rectangle(groupLeft, groupTop, 150, 100));

        shape = group.createAutoShape();
        shape.setShapeType(ShapeType.TRIANGLE);
        shape.setFillColor(Color.RED);
        shape.setAnchor(new Rectangle(groupLeft+groupWidth-120, groupTop, 120, 100));

        shape = group.createAutoShape();
        shape.setShapeType(ShapeType.DONUT);
        shape.setFillColor(Color.YELLOW);
        shape.setAnchor(new Rectangle(groupLeft, groupTop+groupHeight-90, 90, 90));

        shape = group.createAutoShape();
        shape.setShapeType(ShapeType.ELLIPSE);
        shape.setFillColor(Color.BLUE);
        shape.setAnchor(new Rectangle(groupLeft+groupWidth-100, groupTop+groupHeight-100, 100, 100));

        FileOutputStream out = new FileOutputStream(outputFilePath);
        ppt.write(out);
        out.close();
        ppt.close();
    }
}
