package org.practice.challenge.ppt.data;

import lombok.Data;

/**
 * 考虑以后可能会对图元进行全属性转换，实际图元属性由子类实现
 *
 * @author feikong
 * @version 2022/8/17
 */
@Data
public class PptElementData {
    /**
     * 图片位置和大小，单位都是像素
     * 这里不能直接用Rectangle2D，该结构存在递归，导致序列化时栈溢出
     */
    private double x;
    private double y;
    private double w;
    private double h;

    public void setAnchor(final double x, final double y, final double w, final double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

}
