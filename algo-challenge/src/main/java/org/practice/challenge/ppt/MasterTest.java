package org.practice.challenge.ppt;

import org.apache.poi.hslf.usermodel.HSLFPictureShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawSlide;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.practice.challenge.ppt.gen.FlattedShape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.practice.challenge.image.ImageShower.forceUpdateContentType;
import static org.practice.challenge.image.ImageShower.showImage;
import static org.practice.challenge.image.ImageShower.showPpt;
import static org.practice.challenge.image.ImageShower.showPpt2;

/**
 * @author feikong
 * @version 2022/10/9
 */
public class MasterTest {

    public static void main(String[] args) throws Exception{
        String inputFilePath = "C:\\Users\\Administrator\\Desktop\\xxxx.pptx";;
        SlideShow ppt = new XMLSlideShow(new FileInputStream(inputFilePath));
        System.out.println(ppt.getSlides().size());
    }

    public static SlideShow getSlideShow(final String pptUrl) throws IOException {
        try (final InputStream stream = new URL(pptUrl).openStream()) {
            try {
                return new XMLSlideShow(stream);
            } catch (final Exception e) {
                try (final InputStream stream2 = new URL(pptUrl).openStream()) {
                    return new HSLFSlideShow(new HSLFSlideShowImpl(stream2));
                }
            }
        }
    }
}