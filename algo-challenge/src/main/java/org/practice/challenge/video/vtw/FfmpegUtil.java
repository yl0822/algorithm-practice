/*
 * FfmpegUtil.java
 * Copyright 2022 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package org.practice.challenge.video.vtw;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author feikong
 * @version 2022/12/1
 */
@Slf4j
public class FfmpegUtil {
    protected static final String CMD_FFMPEG = "D:\\ffmpeg\\bin\\ffmpeg.exe";

    @SneakyThrows
    public static void commandStart(final List<String> command) {
        final ProcessBuilder builder = new ProcessBuilder();
        builder.redirectErrorStream(true);
        builder.command(command);
        final Process process = builder.start();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("FFMPEG处理中:" + line);
            }
        }
    }
}
