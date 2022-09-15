package org.practice.challenge.ppt.utils;

import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.*;
import org.openxmlformats.schemas.presentationml.x2006.main.impl.CTShapeImpl;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class ShapeUtil {

    public static void shapeOrder(XSLFSimpleShape simpleShape, int order) {
        // order越大，展示优先级越高
        ((CTShapeImpl) simpleShape.getXmlObject()).getNvSpPr().getCNvPr().setId(order);
    }

    public static void initText(XSLFTextParagraph paragraph, String content) {
        paragraph.setTextAlign(TextParagraph.TextAlign.CENTER);
//        paragraph.setLineSpacing(20d);
        paragraph.setIndentLevel(1);
        paragraph.setBulletAutoNumber(AutoNumberingScheme.circleNumDbPlain, 1);

        XSLFTextRun textRun = paragraph.addNewTextRun();
        textRun.setText(content);
        textRun.setFontSize(36d);
        textRun.setFontFamily("");
        textRun.setFontColor(Color.GREEN);
        textRun.setBold(true);
        textRun.setItalic(true);
        textRun.setUnderlined(true);
        textRun.setStrikethrough(true);
//        textRun.setCharacterSpacing(10);
//        textRun.setBaselineOffset(10);
    }

    public static void initLine(XSLFSimpleShape simpleShape) {
        simpleShape.setShapeType(ShapeType.LINE);
        simpleShape.setAnchor(new Rectangle2D.Double(100, 100, 200, 0));
        visible(simpleShape);
    }

    public static void initRectangle(XSLFSimpleShape simpleShape) {
        simpleShape.setShapeType(ShapeType.RECT);
        simpleShape.setAnchor(new Rectangle2D.Double(100, 100, 200, 300));
        visible(simpleShape);
    }

    public static void initPic(XSLFSimpleShape simpleShape) {
        simpleShape.setShapeType(ShapeType.RECT);
        simpleShape.setAnchor(new Rectangle2D.Double(100, 100, 200, 300));
        visible(simpleShape);
    }

    public static void initEllipse(XSLFSimpleShape simpleShape) {
        simpleShape.setShapeType(ShapeType.ELLIPSE);
        simpleShape.setAnchor(new Rectangle2D.Double(100, 100, 300, 200));
        visible(simpleShape);
    }

    public static void initPolygon(XSLFFreeformShape freeformShape, int n){
        Path2D.Double path = new Path2D.Double();
        // 找到起点
        path.moveTo(300, 400);
        path.lineTo(0, 800);
        path.lineTo(600, 800);
        // 闭合图形
        path.closePath();
        // 正多边形需要一定的几何转换
        freeformShape.setPath(path);
        visible(freeformShape);
    }

    public static void initFreeShape(XSLFFreeformShape freeformShape){
        Path2D.Double path = new Path2D.Double();
        // 找到起点
        path.moveTo(100, 200);
        // 画贝塞尔曲线
        path.curveTo(120, 130, 240, 250, 360, 370);
        path.curveTo(150, 120, 220, 120, 220, 180);
        path.curveTo(100, 290, 200, 260, 430, 370);
        path.curveTo(50, 210, 450, 420, 120, 260);
        // 闭合图形
        path.closePath();
        freeformShape.setPath(path);
        visible(freeformShape);
    }



    public static void initOuterShadow(XSLFShape shape) {
        CTOuterShadowEffect shadowEffect = getCTEffectList(shape).addNewOuterShdw();
        shadowEffect.setBlurRad(200000);
        shadowEffect.setDist(180000);
        shadowEffect.setDir(2400000);
        shadowEffect.setSx(100000);
        shadowEffect.setSy(100000);
        shadowEffect.addNewPrstClr().setVal(STPresetColorVal.RED);
        shadowEffect.addNewPrstClr().addNewAlpha().setVal(80000);
    }

    public static void initReflection(XSLFShape shape) {
        CTReflectionEffect reflectionEffect = getCTEffectList(shape).addNewReflection();
        reflectionEffect.setDist(250000);
        reflectionEffect.setStA(70000);
        reflectionEffect.setBlurRad(10000);
        reflectionEffect.setEndPos(30000);
        // 经测试，下方阴影类型下方值固定
        reflectionEffect.setDir(5400000);
        reflectionEffect.setSy(-100000);
    }

    public static void initEdgeSoft(XSLFShape shape) {
        CTSoftEdgesEffect softEdgesEffect = getCTEffectList(shape).addNewSoftEdge();
        softEdgesEffect.setRad(30000);
    }

    public static void initSolidFill(XSLFShape shape) {
        CTSolidColorFillProperties solidFill = ((CTShapeImpl) shape.getXmlObject()).getSpPr().addNewSolidFill();
        solidFill.addNewSchemeClr().setVal(STSchemeColorVal.ACCENT_1);
    }

    public static void initGradFill(XSLFShape shape) {
        CTGradientFillProperties gradFill = ((CTShapeImpl) shape.getXmlObject()).getSpPr().addNewGradFill();
        gradFill.setPath(CTPathShadeProperties.Factory.newInstance());
    }

    public static void initImgFill(XSLFShape shape, String relationId) {
        CTBlipFillProperties blipFill = ((CTShapeImpl) shape.getXmlObject()).getSpPr().addNewBlipFill();
        blipFill.setRotWithShape(true);

        CTBlip blip = blipFill.addNewBlip();
        blip.setEmbed(relationId);
        blip.setAlphaModFixArray(getEffectAlpha(33));

        CTStretchInfoProperties stretchInfoProperties = blipFill.addNewStretch();
        stretchInfoProperties.setFillRect(CTRelativeRect.Factory.newInstance());

        CTTileInfoProperties tileInfoProperties = blipFill.addNewTile();
        tileInfoProperties.setTx(50800);
        tileInfoProperties.setTy(50800);
        tileInfoProperties.setSx(29000);
        tileInfoProperties.setSy(75000);
        tileInfoProperties.setAlgn(STRectAlignment.TR);
    }

    private static CTEffectList getCTEffectList(XSLFShape shape) {
        if (((CTShapeImpl) shape.getXmlObject()).getSpPr().isSetEffectLst()) {
            return ((CTShapeImpl) shape.getXmlObject()).getSpPr().getEffectLst();
        } else {
            return ((CTShapeImpl) shape.getXmlObject()).getSpPr().addNewEffectLst();
        }
    }


    private static CTBlurEffect[] getEffectBlur(int blur) {
        CTBlurEffect effect = CTBlurEffect.Factory.newInstance();
        effect.setRad(blur);
        effect.setGrow(true);
        return new CTBlurEffect[]{effect};
    }


    private static CTAlphaModulateFixedEffect[] getEffectAlpha(int alpha) {
        CTAlphaModulateFixedEffect effect = CTAlphaModulateFixedEffect.Factory.newInstance();
        effect.setAmt((100 - alpha) * 1000);
        return new CTAlphaModulateFixedEffect[]{effect};
    }

    private static void visible(XSLFSimpleShape simpleShape) {
        simpleShape.setLineWidth(10);
        simpleShape.setLineColor(Color.RED);
        // 这里不能addSolidFill，因为setLineWidth/setLineColor已经添加过了
        getLine(simpleShape).getSolidFill().addNewSchemeClr().setAlphaArray(getLineAlpha(33));
//        simpleShape.setLineDash(StrokeStyle.LineDash.DASH_DOT);

        // 在闭合图形下，端点样式没有意义
        simpleShape.setLineTailWidth(LineDecoration.DecorationSize.SMALL);
        simpleShape.setLineTailLength(LineDecoration.DecorationSize.SMALL);
        simpleShape.setLineTailDecoration(LineDecoration.DecorationShape.ARROW);
        simpleShape.setLineCap(StrokeStyle.LineCap.ROUND);
    }

    private static CTLineProperties getLine(XSLFShape shape) {
        return ((CTShapeImpl) shape.getXmlObject()).getSpPr().getLn();
    }

    private static CTPositiveFixedPercentage[] getLineAlpha(int alpha) {
        CTPositiveFixedPercentage effect = CTPositiveFixedPercentage.Factory.newInstance();
        effect.setVal((100 - alpha) * 1000);
        return new CTPositiveFixedPercentage[]{effect};
    }
}
