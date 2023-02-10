/*
 * VideoFfmpegUtil.java
 * Copyright 2022 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package org.practice.challenge.video.vtw;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author feikong
 * @version 2022/12/1
 */
@Slf4j
public class VideoFfmpegUtil extends FfmpegUtil {

    public static void picAudio2Video(PicAudioMixConfig config) {
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);
        command.add("-y");

        command.add("-loop");
        command.add("1");

        command.add("-i");
        command.add(config.getPicPath());

        if (StringUtils.isNotBlank(config.getAudioPath())){
            command.add("-i");
            command.add(config.getAudioPath());
        }

        command.add("-t");
        if (Objects.nonNull(config.getAudioDuration()) && config.getAudioDuration() > 5){
            command.add(config.getAudioDuration().toString());
        } else {
            // 默认5s
            command.add("5");
        }

        command.add("-vf");
        StringBuilder filterStr = new StringBuilder(10);
        if (StringUtils.isNotBlank(config.getScale())) {
            // 过滤器配置只能有一个选项配置，多个过滤器用,分隔组成过滤链，多个过滤链用;风格
            filterStr.append("scale=");
            filterStr.append(config.getScale());
            filterStr.append("[sc];");
        }
        if (StringUtils.isNotBlank(config.getWaterMarkPath())){
            filterStr.append("movie=");
            filterStr.append(config.getWaterMarkPath());
            filterStr.append("[wm];[wm]scale=");
            filterStr.append(config.getWaterMark() ? "iw" : "2");
            filterStr.append(":-1[swm];[sc][swm]overlay=(main_w-overlay_w):(main_h-overlay_h-26)[out]");
        }
        if (StringUtils.isNotBlank(config.getSubPath())){
            filterStr.append(",[out]subtitles=");
            filterStr.append(config.getSubPath());
            filterStr.append(":force_style='FontName=SimHei,FontSize=7'[final]");
        }
        command.add(filterStr.toString());

        command.add(config.getOutPutPath());
        commandStart(command);
    }

    public static void concatVideo(VideoConcatConfig config) {
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);

        command.add("-f");
        command.add("concat");

        command.add("-safe");
        command.add("0");

        command.add("-i");
        command.add(config.getFileTxtPath());

        command.add("-c");
        command.add("copy");

        command.add(config.getOutPutPath());
        commandStart(command);
    }

    public static void mixAudioVideo(VideoAudioMixConfig config) {
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);
        command.add("-y");
        command.add("-stream_loop");
        command.add("-1");
        command.add("-i");
        command.add(config.getAudioPath());

        command.add("-i");
        command.add(config.getVideoPath());

        if (config.getVideoWithAudio()){
            command.add("-filter_complex");
            StringBuilder filterStr = new StringBuilder(10);
            filterStr.append("[0:a]volume=");
            if (Objects.nonNull(config.getAudioVolume())){
                filterStr.append(convertVolume(config.getAudioVolume()));
            }else {
                filterStr.append("0.5");
            }
            filterStr.append("[a1];[1:a]volume=");
            if (Objects.nonNull(config.getVideoVolume())){
                filterStr.append(convertVolume(config.getVideoVolume()));
            }else {
                filterStr.append("1.5");
            }
            filterStr.append("[a2];[a1][a2]amix=inputs=2:duration=shortest");
            command.add(filterStr.toString());
        }else {
            command.add("-shortest");
        }
        command.add(config.getOutPutPath());
        commandStart(command);
    }

    private static String convertVolume(Integer volume){
        return String.format("%.2f", volume / 100d);
    }
}
