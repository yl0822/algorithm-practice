/*
 * AudioConcatConfig.java
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
public class VideoConcatConfig extends FfmpegConfig {
    private String fileTxtPath;
}
