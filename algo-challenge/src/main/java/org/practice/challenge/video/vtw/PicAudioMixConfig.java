/*
 * PicAudioSubMixConfig.java
 * Copyright 2022 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package org.practice.challenge.video.vtw;

import lombok.Data;

/**
 * @author feikong
 * @version 2022/12/1
 */
@Data
public class PicAudioMixConfig extends FfmpegConfig {
    private String picPath;
    private String audioPath;
    private String subPath;
    private Double audioDuration;
    // 分辨率，如640:480/640:-2
    private String scale;
    // 水印
    private Boolean waterMark;
    // 水印路径
    private String waterMarkPath;
}
