package org.practice.challenge.ppt.data;

import lombok.Data;

/**
 * @author feikong
 * @version 2022/8/17
 */
@Data
public class PptImageData extends PptElementData {
    /**
     * 图片地址
     */
    private String url;
}
