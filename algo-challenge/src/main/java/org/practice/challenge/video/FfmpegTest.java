package org.practice.challenge.video;

import java.io.IOException;
import java.util.*;

/**
 * @author feikong
 * @version 2022/10/28
 */
public class FfmpegTest {

    public static void main(String[] args) throws IOException {
        String pics = "C:\\Users\\Administrator\\Desktop\\aaa\\";
        String video01 = "C:\\Users\\Administrator\\Desktop\\v001.mp4";
        String video02 = "C:\\Users\\Administrator\\Desktop\\ff_v002.mp4";
        String audio01 = "C:\\Users\\Administrator\\Desktop\\a001.wav";
        String audio02 = "C:\\Users\\Administrator\\Desktop\\a002.mp3";

        //视频播放
//        FfmpegUtils.playVideoAudio(video02);
        //视频播放并指定循环次数
//        FfmpegUtils.playVideoAudio(audio01,20);
        //视频播放并指定宽高和循环次数
//        FfmpegUtils.playVideoAudio(video01,400,700,1);
        //从视频中提取音频为mp3
//        FfmpegUtils.getAudioFromVideo(video01);
        //从视频中提取视频为无声视频
//        FfmpegUtils.getVideoFromAudio(video01);
//        无声视频+音频合并
//        FfmpegUtils.mergeSilent_VideoAudio(video01,audio02);
        //格式转换
//        FfmpegUtils.videoFormatConversion("D:\\507-#网愈云故事收藏馆.mp4","flv");
        //多视频拼接合并为一个mp4格式视频
//        List<String> video = new ArrayList<>();
//        video.add("D:\\1666938804369.mp4");
//        video.add("D:\\507-#网愈云故事收藏馆.mp4");
//        video.add("D:\\101-你也不必耿耿于怀.mp4");
        //FfmpegUtils.mergeVideos(video);
//        FfmpegUtils.mergeVideos1(video);
        //获取音频或视频信息
//        List<String> list = FfmpegUtils.videoAudioInfo("C:\\Users\\Administrator\\Desktop\\dxxz.mp3");
//        list.forEach(v -> System.out.println(v));
        //剪切视频或音频，startTime开始时间，endTime结束时间
//        FfmpegUtils.cutVideoAudio(audio02,"00:01:28","00:01:52");
        //裁剪视频尺寸大小
//        FfmpegUtils.cropVideoSize("D:\\张国荣.mp4", "600", "600", "720", "940");
        //有声视频+音频合并
//        FfmpegUtils.mergeVideoAudio("D:\\507-#网愈云故事收藏馆.mp4","D:\\ffmpegMedia\\16-这几句真假声转换太好听了.mp3");
        //视频截图，screenshotTime是截图的时间
//        FfmpegUtils.videoScreenshot("D:\\101-你也不必耿耿于怀.mp4","00:00:05");
        //视频完全截图，fps是截图的速度即多少秒截一张图
//        FfmpegUtils.videoAllScreenshot("D:\\101-你也不必耿耿于怀.mp4","1");
        //多图片+音频合并为视频。0.5则两秒播放一张，1则一秒播放一张，10则一秒播放十张（数值越小则每张图停留的越长）
        FfmpegUtils.pictureAudioMerge(pics, audio02,"0.2");
        //多音频拼接合并为一个mp3格式视频
//        List<String> audio = new ArrayList<>();
//        audio.add("D:\\ffmpegMedia\\16-这几句真假声转换太好听了.mp3");
//        audio.add("D:\\ffmpegMedia\\忽然之间.mp3");
//        audio.add("D:\\ffmpegMedia\\天空之城-李志.mp3");
//        FfmpegUtils.mergeAudios(audio);
        //绘制音频波形图保存
//        FfmpegUtils.audioWaveform("D:\\ffmpegMedia\\16-这几句真假声转换太好听了.mp3");
        //两个音频混缩合并为一个音频
//        FfmpegUtils.mergeAudios("D:\\ffmpegMedia\\16-这几句真假声转换太好听了.mp3","D:\\ffmpegMedia\\天空之城-李志.mp3");
        //两个音频混缩合并为一个音频并调整两个音频的音量大小
//        FfmpegUtils.mergeAudios("D:\\ffmpegMedia\\忽然之间.mp3","1","D:\\ffmpegMedia\\天空之城-李志.mp3","0.5");
        //两个音频混缩合并为一个音频的不同声道（即一只耳机播放一个音频）。
//        FfmpegUtils.mergeAudiosSoundtrack("D:\\ffmpegMedia\\16-这几句真假声转换太好听了.mp3","D:\\ffmpegMedia\\天空之城-李志.mp3");
    }
}
