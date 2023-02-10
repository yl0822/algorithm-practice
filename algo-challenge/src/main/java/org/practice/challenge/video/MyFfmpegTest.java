package org.practice.challenge.video;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author feikong
 * @version 2022/11/2
 */
public class MyFfmpegTest {
    private static String ffplay = "D:\\ffmpeg\\bin\\ffplay.exe";
    private static String ffmpeg = "D:\\ffmpeg\\bin\\ffmpeg.exe";
    private static final String CMD_FFMPEG = System.getProperty("ffmpeg.home") + "/bin/ffmpeg.exe";
    private static String ffprobe = "D:\\ffmpeg\\bin\\ffprobe.exe";
    private static String desktopPath = "C:\\Users\\Administrator\\Desktop\\";

    public static void main(String[] args) {
//        picAudio2Video();
//        addSubtitle();
//        concatVideo();
//        concatVideo2();
//        concatVideo3();
        mixAudioVideo();
//        getAudioDuration();
    }

    public static void picAudio2Video(){
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);
        command.add("-y");

        command.add("-loop");
        command.add("1");

        command.add("-i");
        command.add("C:/Users/Administrator/Desktop/333.png");

        command.add("-i");
        command.add("C:/Users/Administrator/Desktop/333.mp3");

        command.add("-t");
        command.add("10");

        command.add("-vf");
        command.add("scale=1920:-2[sc];movie=logo02.png[wm];[sc][wm]overlay=(main_w-overlay_w):(main_h-overlay_h-26)[out]");


        command.add(desktopPath + "图片转视频_" + System.currentTimeMillis() + ".mp4");
        commandStart(command);
    }

    public static void concatVideo(){
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);

        command.add("-f");
        command.add("concat");

        command.add("-safe");
        command.add("0");

        command.add("-i");
        command.add("C:/Users/Administrator/Desktop/list.txt");

        command.add("-c");
        command.add("copy");

        command.add(desktopPath + "图片转视频_" + System.currentTimeMillis() + ".mp4");
        commandStart(command);
    }

    public static void concatVideo2(){
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);

        command.add("-i");
        command.add("C:/Users/Administrator/Desktop/111.mp4");

        command.add("-i");
        command.add("C:/Users/Administrator/Desktop/444.mp4");

//        command.add("-i");
//        command.add("C:/Users/Administrator/Desktop/333.mp4");

        command.add("-filter_complex");
        command.add("concat=n=2:v=1:a=1[v][a]");

        command.add("-map");
        command.add("[v]");
        command.add("-map");
        command.add("[a]");

        command.add(desktopPath + "图片转视频_" + System.currentTimeMillis() + ".mp4");
        commandStart(command);
    }

    public static void concatVideo3(){
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);

        command.add("-i");

        command.add("concat:111.ts|222.ts");

        command.add("-c");
        command.add("copy");

        command.add(desktopPath + "图片转视频_" + System.currentTimeMillis() + ".mp4");
        commandStart(command);
    }

    private static String convertSt(String path){
        List<String> command = new ArrayList<>(8);
        command.add(CMD_FFMPEG);

        command.add("-i");
        command.add(path);

        command.add("-c");
        command.add("copy");

        command.add("-f");
        command.add("mpegts");

        String name = desktopPath + "图片转视频_" + System.currentTimeMillis() + ".ts";
        command.add(name);
        commandStart(command);
        return name;
    }

    public static void mixAudioVideo(){
        List<String> command = new ArrayList<>(8);
        command.add(ffmpeg);
        command.add("-y");
        command.add("-stream_loop");
        command.add("-1");
        command.add("-i");
        command.add(desktopPath + "rep111.mp3");

        command.add("-i");
        command.add(desktopPath + "rep222.mp4");


//        command.add("-shortest");

        command.add("-filter_complex");
        command.add("[0:a]volume=0.5[a1];[1:a]volume=1.5[a2];[a1][a2]amix=inputs=2:duration=shortest");
        command.add(desktopPath + "音视频并轨_" + System.currentTimeMillis() + ".mp4");
        commandStart(command);
    }

    public static void addSubtitle(){
        List<String> command = new ArrayList<>(8);
        command.add(ffmpeg);
        command.add("-y");
        command.add("-i");
        command.add(desktopPath + "v2.mp4");
        command.add("-vf");
        command.add("subtitles='C\\:/Users/Administrator/Desktop/s2.srt':force_style='FontName=DejaVu Serif,Fontsize=16'");
        command.add(desktopPath + "视频合成字幕_" + System.currentTimeMillis() + ".mp4");
        commandStart(command);
    }

    public static void getAudioDuration(){
        List<String> command = new ArrayList<>(8);
        command.add(ffprobe);
        command.add("-i");
        command.add(desktopPath + "a2.wav");
        command.add("-show_format");
        command.add("-v");
        command.add("quiet");
        command.add("-print_format");
        command.add("json");
        commandInfo(command);
    }

    @SneakyThrows
    public static void commandStart(List<String> command) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ProcessBuilder builder = new ProcessBuilder();
        builder.redirectErrorStream(true);
        builder.command(command);
        Process process = builder.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("转换完成:"+stopWatch.getTime());
    }

    @SneakyThrows
    public static void commandInfo(List<String> command) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ProcessBuilder builder = new ProcessBuilder();
        builder.redirectErrorStream(true);
        builder.command(command);
        Process process = builder.start();
        FastByteArrayOutputStream fastByteArrayOutputStream = IoUtil.read(process.getInputStream());
        process.waitFor();
        String json = fastByteArrayOutputStream.toString();
        System.out.println(json);
    }
}
