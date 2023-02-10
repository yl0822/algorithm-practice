/*
 * FfmpegConfig.java
 * Copyright 2022 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package org.practice.challenge.video.vtw;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author feikong
 * @version 2022/11/2
 */
@Data
@NoArgsConstructor
public class FfmpegConfig {
    // 合成视频文件
    private String outPutPath;
}
