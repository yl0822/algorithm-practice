/*
 * FlattedShape.java
 * Copyright 2022 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package org.practice.challenge.ppt.gen;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.Shape;

import java.util.List;

/**
 * @author feikong
 * @version 2022/10/20
 */
@Data
public class FlattedShape {
    private List<Shape<?, ?>> normalShapeList;
    private List<GroupShape<?, ?>> groupShapeList;

    public static FlattedShape init() {
        final FlattedShape shape = new FlattedShape();
        shape.setNormalShapeList(Lists.newArrayList());
        shape.setGroupShapeList(Lists.newArrayList());
        return shape;
    }

    public void addNormal(final Shape<?, ?> shape) {
        this.normalShapeList.add(shape);
    }

    public void addGroup(final GroupShape<?, ?> groupShape) {
        this.groupShapeList.add(groupShape);
    }
}
