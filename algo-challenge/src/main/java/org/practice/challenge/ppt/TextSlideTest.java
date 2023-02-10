package org.practice.challenge.ppt;

import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.practice.challenge.ppt.utils.SlideShowUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class TextSlideTest {

    private static final String IMG_PATH = "C:\\Users\\Administrator\\Pictures\\tokyo-tower-g4414ba44d_1920.jpg";

    private static final double RATIO = 5d;

    public static void main(String[] args) throws Exception {
        // 创建PPT
        XMLSlideShow ppt = new XMLSlideShow();
        final Dimension dimension = new Dimension();
        dimension.setSize(1920, 1080);
        ppt.setPageSize(dimension);

        XSLFSlide slide = ppt.createSlide();

        slide = ppt.createSlide();
        final XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(593, 269, 577, 86));
        textBox.setVerticalAlignment(VerticalAlignment.TOP);
        textBox.setRotation(0.0);

        XSLFTextParagraph paragraph = textBox.addNewTextParagraph();
        paragraph.setIndentLevel(0);
        paragraph.setTextAlign(TextParagraph.TextAlign.LEFT);
        paragraph.setLineSpacing(-56d);
        paragraph.setFontAlign(TextParagraph.FontAlign.AUTO);
        paragraph.setSpaceBefore(20d);


        XSLFTextRun textRun = paragraph.addNewTextRun();
        textRun.setText("非空测试普通文");
        textRun.setFontFamily("975圆体", FontGroup.LATIN);
        textRun.setFontFamily("975圆体", FontGroup.EAST_ASIAN);
        textRun.setFontColor(new Color(15864119));
        textRun.setFontSize(32d);
        textRun.setBold(true);
        textRun.setItalic(false);
        textRun.setUnderlined(false);
        textRun.setStrikethrough(false);
        textRun.setCharacterSpacing(0.0);

        ((CTRegularTextRun)textRun.getXmlObject()).getRPr().getSolidFill().getSrgbClr().addNewAlpha().setVal(52000);

        // 导出PPT
        SlideShowUtil.exportPpt(ppt);
    }
}
