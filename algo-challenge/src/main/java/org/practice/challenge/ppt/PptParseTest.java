package org.practice.challenge.ppt;

import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.sl.draw.binding.CTAdjPoint2D;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.CurveToCommand;
import org.apache.poi.sl.draw.geom.Formula;
import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.sl.draw.geom.PathCommand;
import org.apache.poi.sl.usermodel.Line;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.xslf.usermodel.*;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetColorVal;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.CTOuterShadowEffectImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPositiveFixedPercentageImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.impl.CTConnectorImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.impl.CTShapeImpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.function.Consumer;

/**
 * @author feikong
 * @version 2022/8/9
 */
public class PptParseTest {
    public static String outputFilePath = "C:\\Users\\Administrator\\Desktop\\test_" + System.currentTimeMillis() + ".pptx";

    public static void main(String[] args) throws Exception {
        String inputFilePath = "C:\\Users\\Administrator\\Desktop\\test_1660620805580.pptx";

        // 创建PPT
//        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(inputFilePath));
        XMLSlideShow ppt = new XMLSlideShow();
        ppt.setPageSize(new Dimension(1920, 1080));
//        ppt.getCTPresentation().addNewExtLst().addNewExt().set()

        XSLFSlide slide = ppt.createSlide();
        slide.getXmlObject().getCSld().setName("页面名称");
        slide.getBackground().setFillColor(Color.WHITE);

        XSLFConnectorShape connectorShape = ppt.createSlide().createConnector();
        connectorShape.setAnchor(new Rectangle2D.Double(1234.36, 575.44, 699.49, 0));
        connectorShape.setLineWidth(5);
        connectorShape.setRotation(120.22d);
        connectorShape.setFlipHorizontal(false);
        connectorShape.setLineColor(Color.GREEN);
//        ((CTConnectorImpl)connectorShape.getXmlObject()).getSpPr().getSolidFill().getSrgbClr().addNewAlpha().setVal(30000);
//        connectorShape.setLineDash(StrokeStyle.LineDash.DASH_DOT);
//        connectorShape.setLineHeadDecoration(LineDecoration.DecorationShape.ARROW);
//        connectorShape.setLineHeadLength(LineDecoration.DecorationSize.SMALL);
//        connectorShape.setLineTailDecoration(LineDecoration.DecorationShape.ARROW);
//        connectorShape.setLineTailWidth(LineDecoration.DecorationSize.SMALL);


        XSLFAutoShape autoShape = ppt.createSlide().createAutoShape();
        autoShape.setShapeType(ShapeType.ELLIPSE);
        autoShape.setAnchor(new Rectangle2D.Double(100, 100, 500, 300));
        autoShape.setFillColor(Color.decode("#16D9A8"));
//        ((CTShapeImpl)autoShape.getXmlObject()).getSpPr().getSolidFill().getSrgbClr().addNewAlpha().setVal(30000);

        CTEffectList effectList = ((CTShapeImpl)autoShape.getXmlObject()).getSpPr().addNewEffectLst();
        CTOuterShadowEffect shadowEffect = effectList.addNewOuterShdw();
        shadowEffect.setDist(127000);
        shadowEffect.setBlurRad(279400);
        shadowEffect.setDir(2400000);
        shadowEffect.setSx(103000);
        shadowEffect.setSy(103000);
        shadowEffect.addNewPrstClr().setVal(STPresetColorVal.RED);
        shadowEffect.addNewPrstClr().addNewBlue().setVal(0);
        shadowEffect.addNewPrstClr().addNewGreen().setVal(0);
        shadowEffect.addNewPrstClr().addNewAlpha().setVal(50000);

        CTBlurEffect blurEffect = effectList.addNewBlur();
        blurEffect.setRad(10);
        blurEffect.setGrow(true);

        CTReflectionEffect reflectionEffect = effectList.addNewReflection();
        reflectionEffect.setDist(254000);
        reflectionEffect.setEndA(295);
        reflectionEffect.setEndPos(29000);
        reflectionEffect.setBlurRad(10);
        reflectionEffect.setDir(5400000);
        reflectionEffect.setSy(-100000);
        reflectionEffect.setRotWithShape(false);

        autoShape.setLineColor(Color.RED);
//        autoShape.setText("椭圆形");
//        autoShape.setTextAutofit(TextShape.TextAutofit.NORMAL);
        autoShape.setLineWidth(22);
        autoShape.setLineDash(StrokeStyle.LineDash.LG_DASH);

        XSLFFreeformShape freeformShape = ppt.createSlide().createFreeform();
        freeformShape.setLineColor(Color.RED);
        freeformShape.setLineWidth(10);
        freeformShape.setFillColor(Color.GREEN);
        ((CTShapeImpl)freeformShape.getXmlObject()).getSpPr().getSolidFill().getSrgbClr().addNewAlpha().setVal(30000);

        FileOutputStream out = new FileOutputStream(outputFilePath);
        ppt.write(out);
        out.close();
        ppt.close();
    }

}
