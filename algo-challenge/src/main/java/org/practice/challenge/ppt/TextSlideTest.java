package org.practice.challenge.ppt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qunhe.geom.middleware.graphic.design.service.data.worker.ppt.request.PptTextData;
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
import org.practice.challenge.ppt.utils.SlideTextUtil;

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

        String json = "{\"tp\":\"PTD\",\"x\":581.0,\"y\":511.0,\"w\":593.0,\"h\":86.0,"
                + "\"paragraphDataList\":[{\"runDataList\":[{\"text\":\"非空测试普通文本\","
                + "\"fontFamily\":\"huxiaobonanshenti\",\"fontColor\":15864119,\"fontSize\":72.0,"
                + "\"fontWeight\":\"Bold*\",\"italic\":true,\"underline\":true,\"strikethrough\":true,"
                + "\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":0,\"textAlign\":0,\"numberOrder\":0}],"
                + "\"verticalAlignment\":0,\"rotation\":-0.0,\"alpha\":1}";
        String json2 = "{\"tp\":\"PTD\",\"x\":295.0,\"y\":311.0,\"w\":250.0,\"h\":60.0,"
                + "\"paragraphDataList\":[{\"runDataList\":[{\"text\":\"输入文本\",\"fontFamily\":\"FZHTJW\","
                + "\"fontColor\":15207436,\"fontSize\":48.0,\"fontWeight\":\"Bold*\",\"italic\":false,"
                + "\"underline\":false,\"strikethrough\":false,\"charSpacing\":10.0,\"alpha\":100}],\"lineSpacing\":50.0,"
                + "\"indentLevel\":0,\"textAlign\":0,\"numberOrder\":0}],\"verticalAlignment\":0,\"rotation\":-2"
                + ".147853915324706,\"alpha\":0}";
        String json3 = "{\"tp\":\"PTD\",\"x\":1178.0,\"y\":181.0,\"w\":368.0,\"h\":344.0,"
                + "\"paragraphDataList\":[{\"runDataList\":[{\"text\":\"输入文本\",\"fontFamily\":\"SourceHanSansSC\","
                + "\"fontColor\":3355443,\"fontSize\":36.0,\"fontWeight\":\"Regular\",\"italic\":false,"
                + "\"underline\":false,\"strikethrough\":false,\"charSpacing\":0.0}],\"lineSpacing\":0.0,"
                + "\"indentLevel\":0,\"textAlign\":0,\"numberOrder\":0},{\"runDataList\":[{\"text\":\"覆盖掉\","
                + "\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,\"fontSize\":36.0,"
                + "\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,\"strikethrough\":false,"
                + "\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":0,\"textAlign\":0,"
                + "\"listType\":\"Bulleted\",\"listSymbol\":\"disc\",\"numberOrder\":0},"
                + "{\"runDataList\":[{\"text\":\"双方各看京东方\",\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,"
                + "\"fontSize\":36.0,\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,"
                + "\"strikethrough\":false,\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":1,"
                + "\"textAlign\":0,\"numberOrder\":0},{\"runDataList\":[{\"text\":\"的双方各得\","
                + "\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,\"fontSize\":36.0,"
                + "\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,\"strikethrough\":false,"
                + "\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":1,\"textAlign\":0,\"numberOrder\":0},"
                + "{\"runDataList\":[{\"text\":\"萨达双节快乐\",\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,"
                + "\"fontSize\":36.0,\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,"
                + "\"strikethrough\":false,\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":0,"
                + "\"textAlign\":0,\"numberOrder\":0},{\"runDataList\":[{\"text\":\"速度来开发及\","
                + "\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,\"fontSize\":36.0,"
                + "\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,\"strikethrough\":false,"
                + "\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":0,\"textAlign\":0,"
                + "\"listType\":\"Numbered\",\"listSymbol\":\"number\",\"numberOrder\":1},"
                + "{\"runDataList\":[{\"text\":\"东方国际\",\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,"
                + "\"fontSize\":36.0,\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,"
                + "\"strikethrough\":false,\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":0,"
                + "\"textAlign\":0,\"listType\":\"Numbered\",\"listSymbol\":\"number\",\"numberOrder\":2},"
                + "{\"runDataList\":[{\"text\":\"锁定该行发\",\"fontFamily\":\"PenroseTrial\",\"fontColor\":3355443,"
                + "\"fontSize\":36.0,\"fontWeight\":\"Regular\",\"italic\":false,\"underline\":false,"
                + "\"strikethrough\":false,\"charSpacing\":0.0}],\"lineSpacing\":0.0,\"indentLevel\":0,"
                + "\"textAlign\":0,\"listType\":\"Numbered\",\"listSymbol\":\"number\",\"numberOrder\":3}],"
                + "\"verticalAlignment\":0,\"rotation\":-0.0,\"alpha\":1}";
        PptTextData textData = new ObjectMapper().readValue(json2, PptTextData.class);

        SlideTextUtil.createTextShape(slide, textData);


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
