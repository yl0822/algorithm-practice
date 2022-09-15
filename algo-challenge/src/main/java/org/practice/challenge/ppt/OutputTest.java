package org.practice.challenge.ppt;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetColorVal;
import org.openxmlformats.schemas.presentationml.x2006.main.impl.CTShapeImpl;
import org.practice.challenge.ppt.data.PptImageData;
import org.practice.challenge.ppt.utils.SlideShowUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;

import static org.practice.challenge.ppt.utils.ShapeUtil.initEdgeSoft;
import static org.practice.challenge.ppt.utils.ShapeUtil.initEllipse;
import static org.practice.challenge.ppt.utils.ShapeUtil.initFreeShape;
import static org.practice.challenge.ppt.utils.ShapeUtil.initImgFill;
import static org.practice.challenge.ppt.utils.ShapeUtil.initOuterShadow;
import static org.practice.challenge.ppt.utils.ShapeUtil.initPolygon;
import static org.practice.challenge.ppt.utils.ShapeUtil.initRectangle;
import static org.practice.challenge.ppt.utils.ShapeUtil.initReflection;
import static org.practice.challenge.ppt.utils.ShapeUtil.initText;
import static org.practice.challenge.ppt.utils.SlideShowUtil.addPic2MediaRepo;
import static org.practice.challenge.ppt.utils.SlideUtil.createImageRelation;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class OutputTest {

    private static final String IMG_PATH = "C:\\Users\\Administrator\\Pictures\\tokyo-tower-g4414ba44d_1920.jpg";

    private static final double RATIO = 5d;

    public static void main(String[] args) throws Exception{
        // 创建PPT
        XMLSlideShow ppt = new XMLSlideShow();
        // 添加图片素材到PPT媒体库
        XSLFPictureData pictureData = ppt.addPicture(new File(IMG_PATH), PictureData.PictureType.JPEG);
        ppt.setPageSize(new Dimension(720, 600));
        // 创建PPT页面
        XSLFSlide slide = ppt.createSlide();
        // 创建图片元素
        PptImageData imageData = new PptImageData();
        imageData.setX(100);
        imageData.setY(100);
        imageData.setW(300);
        imageData.setH(200);
        imageData.setUrl("https://graphic-design-cos.kujiale.com/meijian/qhgeom/sit/renderImage/8547908132756475048/2022/08/24/166133508938540975.png");
        PptTest.createPicShape(slide, imageData);

        slide = ppt.createSlide();
        imageData.setUrl("https://graphic-design-cos.kujiale.com/meijian/qhgeom/sit/renderImage/8547908132756475048/2022/08/24/166133508938540975.png?imageMogr2/format/jpeg");
        PptTest.createPicShape(slide, imageData);

        slide = ppt.createSlide();
        imageData.setUrl("https://graphic-design-cos.kujiale.com/meijian/qhgeom/sit/renderImage/8547908132756475048/2022/08/24/166133508938540975.png?imageMogr2/format/webp");
        PptTest.createPicShape(slide, imageData);

        slide = ppt.createSlide();
        imageData.setUrl("https://graphic-design-cos.kujiale.com/meijian/qhgeom/sit/renderImage/8547908132756475048/2022/08/24/166133508938540975.png?imageMogr2/format/heif");
        PptTest.createPicShape(slide, imageData);

        slide = ppt.createSlide();
        imageData.setUrl("https://graphic-design-cos.kujiale.com/meijian/qhgeom/sit/renderImage/8547908132756475048/2022/08/24/166133508938540975.png?imageMogr2/format/tpg");
        PptTest.createPicShape(slide, imageData);

        slide = ppt.createSlide();
        imageData.setUrl("https://graphic-design-cos.kujiale.com/meijian/qhgeom/sit/renderImage/8547908132756475048/2022/08/24/166133508938540975.png?imageMogr2/format/avif");
        PptTest.createPicShape(slide, imageData);

        // 导出PPT
        SlideShowUtil.exportPpt(ppt);
    }
}
