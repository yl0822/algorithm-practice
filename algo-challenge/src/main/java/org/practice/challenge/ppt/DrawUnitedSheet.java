package org.practice.challenge.ppt;

import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.Slide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 为了渲染整合母版和背景统一后的背景图片，基于DrawSheet自定义实现Drawable类，改造点如下：
 * 1. 原DrawSheet只支持基于sheet构造，改造后基于slide构造，因为业务上处理是以slide为主
 * 2. 原DrawSheet渲染时不会渲染背景图，因为背景图会在普通ppt页面上进行渲染，而美间工具背景图只支持单张图片的填充，所以需要进行合并渲染。
 * 3. 原DrawSheet渲染时不会过滤占位图元，虽然DrawMasterSheet做了实现，所以这里也正好合并过来。
 *
 * @see org.apache.poi.sl.draw.DrawSheet
 * */
public class DrawUnitedSheet implements Drawable {
    /** 页面 */
    private final Slide<?,?> slide;
    /** 版式 */
    private final Sheet<?,?> layout;
    /** 母版 */
    private final Sheet<?,?> master;

    public DrawUnitedSheet(Slide<?,?> slide) {
        this.slide = slide;
        this.layout = slide.getMasterSheet();
        this.master = slide.getMasterSheet().getMasterSheet();
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        Dimension dim = layout.getSlideShow().getPageSize();
        Color whiteTrans = new Color(1f,1f,1f,0f);
        graphics.setColor(whiteTrans);
        graphics.fillRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
        
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        
        // 由于要渲染背景图，所以原来先进行母版渲染的逻辑去除掉
        
        graphics.setRenderingHint(Drawable.GROUP_TRANSFORM, new AffineTransform());
        List<Shape<?, ?>> list = new ArrayList<>(8);
        list.add(slide.getBackground());
        // layout一定存在，master在老版ppt可能为null
        if (Objects.nonNull(master)){
            list.addAll(master.getShapes());
        }
        list.addAll(layout.getShapes());

        for (Shape<?,?> shape : list) {
            if(!canDraw(shape)) {
                continue;
            }
            
            // remember the initial transform and restore it after we are done with drawing
            AffineTransform at = graphics.getTransform();

            // concrete implementations can make sense of this hint,
            // for example PSGraphics2D or PDFGraphics2D would call gsave() / grestore
            graphics.setRenderingHint(Drawable.GSAVE, true);

            // apply rotation and flipping
            Drawable drawer = drawFact.getDrawable(shape);
            drawer.applyTransform(graphics);
            // draw stuff
            drawer.draw(graphics);

            // restore the coordinate system
            graphics.setTransform(at);

            graphics.setRenderingHint(Drawable.GRESTORE, true);
        }
    }

    @Override
    public void applyTransform(Graphics2D context) {
    }

    @Override
    public void drawContent(Graphics2D context) {
    }

    /**
     * 继承自DrawMasterSheet.canDraw，目的是重载掉DrawSheet.canDraw，渲染时过滤掉占位符元素
     */
    protected boolean canDraw(Shape<?,?> shape){
        if (shape instanceof SimpleShape) {
            Placeholder ph = ((SimpleShape<?,?>)shape).getPlaceholder();
            if (ph != null) {
                return slide.getDisplayPlaceholder(ph);
            }
        }
        return slide.getFollowMasterGraphics();
    }
}
