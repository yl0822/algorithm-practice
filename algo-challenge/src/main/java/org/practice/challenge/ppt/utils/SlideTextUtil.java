/*
 * SlideTextUtil.java
 * Copyright 2022 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package org.practice.challenge.ppt.utils;

import com.qunhe.geom.middleware.graphic.design.service.data.worker.ppt.request.ParagraphData;
import com.qunhe.geom.middleware.graphic.design.service.data.worker.ppt.request.PptTextData;
import com.qunhe.geom.middleware.graphic.design.service.data.worker.ppt.request.RunData;
import com.qunhe.geom.middleware.kigma.backend.data.element.text.Character;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;
import org.openxmlformats.schemas.presentationml.x2006.main.impl.CTShapeImpl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * @author feikong
 * @version 2022/9/22
 */
public class SlideTextUtil {

    public static void createTextShape(final XSLFSlide slide, final PptTextData textData) {
        final XSLFTextBox textBox = slide.createTextBox();
        fillBox(textBox, textData);
        // 新创建的TextBox默认自带一个paragraph
        boolean firstP = true;
        for (final ParagraphData paragraphData : textData.getParagraphDataList()) {
            final XSLFTextParagraph paragraph;
            if (firstP){
                paragraph = textBox.getTextParagraphs().get(0);
                firstP = false;
            }else {
                paragraph = textBox.addNewTextParagraph();
            }
            fillParagraph(paragraph, paragraphData);
            // 新创建的paragraph默认自带一个run
            boolean firstR = true;
            for (final RunData runData : paragraphData.getRunDataList()) {
                final XSLFTextRun textRun;
                if (firstR){
                    textRun = paragraph.getTextRuns().get(0);
                    firstR = false;
                }else {
                    textRun = paragraph.addNewTextRun();
                }
                fillRun(textRun, runData);
            }
        }
    }

    private static void fillBox(final XSLFTextBox textBox, final PptTextData data) {
        textBox.setAnchor(new Rectangle2D.Double(data.getX(), data.getY(), transWidth(data.getW()), data.getH()));
        textBox.setVerticalAlignment(transVerticalAlignment(data.getVerticalAlignment()));
        textBox.setTextAutofit(TextShape.TextAutofit.SHAPE);
        textBox.setRotation(data.getRotation());
    }

    private static void fillParagraph(final XSLFTextParagraph paragraph, final ParagraphData data) {
        paragraph.setLineSpacing(transLineSpacing(data.getLineSpacing()));
        paragraph.setIndentLevel(data.getIndentLevel());
        paragraph.setTextAlign(transTextAlign(data.getTextAlign()));
        if (StringUtils.equals(data.getListType(), Character.FontListTypeEnum.BULLETED.getValue())) {
            paragraph.setBullet(true);
            paragraph.setBulletCharacter(transBulletChar(data.getListSymbol()));
        } else if (StringUtils.equals(data.getListType(), Character.FontListTypeEnum.NUMBERED.getValue())) {
            paragraph.setBulletAutoNumber(transNumberScheme(data.getListSymbol()), data.getNumberOrder());
        }
    }

    private static void fillRun(final XSLFTextRun textRun, final RunData data) {
        textRun.setText(data.getText());
        textRun.setFontFamily(data.getFontFamily());
        textRun.setFontColor(new Color(data.getFontColor()));
        textRun.setFontSize(data.getFontSize());
        textRun.setBold(transBold(data.getFontWeight()));
        textRun.setItalic(data.isItalic());
        textRun.setUnderlined(data.isUnderline());
        textRun.setStrikethrough(data.isStrikethrough());
        textRun.setCharacterSpacing(data.getCharSpacing());
        // 透明度需要通过xml操作支持
        ((CTRegularTextRun) textRun.getXmlObject()).getRPr().getSolidFill().getSrgbClr().addNewAlpha().setVal(transAlpha(data.getAlpha()));
    }

    private static int transAlpha(final double a) {
        return (int) ((a) * 100 * 1000);
    }

    private static double transWidth(final double width){
        // 由于美间字体大部分在ppt都没有，会显示默认字体，而默认字体会偏长导致换行，所以这里在原文本框宽度基础上扩展15%，避免大部分情况。
        return width * 1.2d;
    }

    private static double transLineSpacing(final double lineSpacing){
        // setLineSpacing存在百分比和绝对值两种配置，正数为配置百分比，负数为配置绝对值
        return -lineSpacing;
    }

    private static boolean transBold(final String weight) {
        // 该值取值很多且可以自定义，所以暂定包含特定单词就设置为粗体
        return StringUtils.containsIgnoreCase(weight, "Bold")
                || StringUtils.containsIgnoreCase(weight, "Heavy");
    }

    private static VerticalAlignment transVerticalAlignment(final int va) {
        switch (va) {
            case 0:
                return VerticalAlignment.TOP;
            case 1:
                return VerticalAlignment.MIDDLE;
            case 2:
                return VerticalAlignment.BOTTOM;
            default:
                return VerticalAlignment.JUSTIFIED;
        }
    }

    private static TextParagraph.TextAlign transTextAlign(final int ta) {
        switch (ta) {
            case 0:
                return TextParagraph.TextAlign.LEFT;
            case 1:
                return TextParagraph.TextAlign.CENTER;
            case 2:
                return TextParagraph.TextAlign.RIGHT;
            default:
                return TextParagraph.TextAlign.JUSTIFY;
        }
    }

    private static String transBulletChar(final String bulletChar) {
        if (StringUtils.equals(bulletChar, Character.BulletedTypeEnum.DISC.getValue())) {
            return "●";
        } else if (StringUtils.equals(bulletChar, Character.BulletedTypeEnum.SQUARE.getValue())) {
            return "□";
        } else if (StringUtils.equals(bulletChar, Character.BulletedTypeEnum.CIRCLE.getValue())) {
            return "○";
        } else if (StringUtils.equals(bulletChar, Character.BulletedTypeEnum.DIAMOND.getValue())) {
            return "♦";
        } else {
            return "●";
        }
    }

    private static AutoNumberingScheme transNumberScheme(final String numberChar) {
        if (StringUtils.equals(numberChar, Character.NumberedTypeEnum.NUMBER.getValue())) {
            return AutoNumberingScheme.arabicPeriod;
        } else if (StringUtils.equals(numberChar, Character.NumberedTypeEnum.NUMBER_WITH_BRACKET.getValue())) {
            return AutoNumberingScheme.arabicParenBoth;
        } else if (StringUtils.equals(numberChar, Character.NumberedTypeEnum.LOWER_LETTER.getValue())) {
            return AutoNumberingScheme.alphaLcPeriod;
        } else if (StringUtils.equals(numberChar, Character.NumberedTypeEnum.UPPER_LETTER.getValue())) {
            return AutoNumberingScheme.alphaUcPeriod;
        } else if (StringUtils.equals(numberChar, Character.NumberedTypeEnum.LOWER_ROMAN.getValue())) {
            return AutoNumberingScheme.romanLcPeriod;
        } else if (StringUtils.equals(numberChar, Character.NumberedTypeEnum.UPPER_ROMAN.getValue())) {
            return AutoNumberingScheme.romanUcPeriod;
        } else {
            return AutoNumberingScheme.arabicPeriod;
        }
    }
}
