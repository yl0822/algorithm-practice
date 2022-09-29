package org.practice.challenge.ppt;

import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl;
import org.practice.challenge.ppt.utils.SlideShowUtil;

import java.awt.Dimension;

/**
 * @author feikong
 * @version 2022/9/26
 */
public class VectorSlideTest {

    public static void main(String[] args) {
        XMLSlideShow ppt = new XMLSlideShow();
        final Dimension dimension = new Dimension();
        dimension.setSize(1920, 1080);
        ppt.setPageSize(dimension);

        XSLFSlide slide = ppt.createSlide();

        ((SimpleShape)slide.getShapes().get(0)).getFillColor().getRGB();

        ((PaintStyle.SolidPaint)((SimpleShape)slide.getShapes().get(0)).getFillStyle().getPaint()).getSolidColor().getColor().getRGB();

        ((CTSlideImpl)slide.getXmlObject()).getCSld().getSpTree().getSpList().get(0).getSpPr().getSolidFill().getSchemeClr().getLumModList().get(0).setVal(60000);


        // 导出PPT
        SlideShowUtil.exportPpt(ppt);
    }
}
