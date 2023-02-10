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
public class VideoAudioMixConfig extends FfmpegConfig{
    private Boolean videoWithAudio;
    private String videoPath;
    private Integer videoVolume;
    private String audioPath;
    private Integer audioVolume;
}
