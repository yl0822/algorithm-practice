package org.practice.challenge.image;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawNotImplemented;
import org.apache.poi.sl.draw.DrawNothing;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author feikong
 * @version 2022/9/27
 */
public class ImageShower {

    private static final double ANCHOR_SCALE = 1.764d;

    public static void main(String[] args) {
        showFile("C:\\Users\\Administrator\\Desktop\\4444.png");
    }

    public static void showPpt(Slide slide, Dimension dimension){
        final BufferedImage bufferedImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        slide.draw(graphics2D);
        graphics2D.dispose();
        showImage(bufferedImage);
    }

    public static void showPpt(Drawable drawable, Dimension dimension){
        final BufferedImage bufferedImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        drawable.draw(graphics2D);
        graphics2D.dispose();
        showImage(bufferedImage);
    }

    public static void showPpt(Shape shape){
        final Rectangle2D anchor = shape.getAnchor();
        final int w = (int) anchor.getWidth(), h = (int) anchor.getHeight();
        final BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setTransform(AffineTransform.getTranslateInstance(-anchor.getX(), -anchor.getY()));
//        shape.draw(graphics, new Rectangle2D.Double(0d, 0d, w, h));
        shape.draw(graphics, null);
        graphics.dispose();
        showImage(bufferedImage);
    }

    public static void showPpt2(Shape shape){
        final Rectangle2D anchor = scaleAnchor(shape.getAnchor());
//        final Rectangle2D anchor = shape.getAnchor();
        final int w = (int) anchor.getWidth(), h = (int) anchor.getHeight();
        final BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setTransform(AffineTransform.getTranslateInstance(-anchor.getX(), -anchor.getY()));
//        shape.draw(graphics, new Rectangle2D.Double(0d, 0d, w, h));
//        drawShape(graphics, shape, null);
        shape.draw(graphics, anchor);
        graphics.dispose();
        showImage(bufferedImage);
    }

    public static void drawShape(Graphics2D graphics, Shape<?,?> shape, Rectangle2D bounds) {
//        Rectangle2D shapeBounds = shape.getAnchor();
        Rectangle2D shapeBounds = scaleAnchor(shape.getAnchor());
        if (shapeBounds.isEmpty() || (bounds != null && bounds.isEmpty())) {
            return;
        }

        AffineTransform txg = (AffineTransform)graphics.getRenderingHint(Drawable.GROUP_TRANSFORM);
        AffineTransform tx = new AffineTransform();
        try {
            if (bounds != null) {
                double scaleX = bounds.getWidth()/shapeBounds.getWidth();
                double scaleY = bounds.getHeight()/shapeBounds.getHeight();
                tx.translate(bounds.getCenterX(), bounds.getCenterY());
                tx.scale(scaleX, scaleY);
                tx.translate(-shapeBounds.getCenterX(), -shapeBounds.getCenterY());
            }
            graphics.setRenderingHint(Drawable.GROUP_TRANSFORM, tx);

            Drawable d;
            if (shape instanceof TableShape){
                d = new DrawTableShape((TableShape)shape);
            }else {
                d = new DrawPictureShape((PictureShape)shape);
            }
            d.applyTransform(graphics);
            d.draw(graphics);
        } finally {
            graphics.setRenderingHint(Drawable.GROUP_TRANSFORM, txg);
        }
    }

    private static Rectangle2D scaleAnchor(final Rectangle2D anchor) {
        return new Rectangle2D.Double(anchor.getX(), anchor.getY()  ,
                anchor.getWidth() * ANCHOR_SCALE, anchor.getHeight() * ANCHOR_SCALE);
    }

    public static void forceUpdateContentType(Shape shape){
        // dev design见：https://cf.qunhequnhe.com/pages/viewpage.action?pageId=80527952212
        if (!(shape instanceof XSLFPictureShape)){
            return;
        }
        XSLFPictureShape pictureShape = (XSLFPictureShape)shape;
        final boolean isTiff = pictureShape.getPictureData().getType() == PictureData.PictureType.TIFF;
        final boolean isPptx = pictureShape instanceof XSLFPictureShape;
        // 首先目前先只对tiff格式做处理，其他格式暂不扩展
        // 其次目前看起来poi只对pptx格式的图片会解析出原tiff格式，而ppt格式默认转化为png，所以不需要处理
        if (isTiff && isPptx){
            try {
                final PackagePart packagePart = pictureShape.getPictureData().getPackagePart();
                // 强行更新图片类型，这样后续在draw的过程中就可以获取到renderer
                packagePart.setContentType(PictureData.PictureType.PNG.contentType);
            } catch (final InvalidFormatException ignored) {
                // ignored
            }
        }
    }

    public static void showUrl(String url) {
        try {
            showImage(ImageIO.read(new URL(url)));
        } catch (IOException e) {
            throw new RuntimeException("数据读取失败");
        }
    }

    public static void showFile(String path) {
        try {
            showImage(ImageIO.read(new File(path)));
        } catch (IOException e) {
            throw new RuntimeException("数据读取失败");
        }
    }

    public static void showBytes(byte[] bytes){
        showStream(new ByteArrayInputStream(bytes));
    }

    public static void showStream(InputStream stream) {
        try {
            showImage(ImageIO.read(stream));
        } catch (IOException e) {
            throw new RuntimeException("数据读取失败");
        }
    }

    public static void showImage(BufferedImage image) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("PicReader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
