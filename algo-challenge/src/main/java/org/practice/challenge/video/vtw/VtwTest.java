package org.practice.challenge.video.vtw;

/**
 * @author feikong
 * @version 2023/1/30
 */
public class VtwTest {
    private static String desktopPath = "C:\\Users\\Administrator\\Desktop\\";

    public static void main(String[] args) {
        PicAudioMixConfig config = new PicAudioMixConfig();
        config.setPicPath("C:/Users/Administrator/Desktop/111.png");
        config.setAudioPath("C:/Users/Administrator/Desktop/111.wav");
        config.setSubPath("C:/Users/Administrator/Desktop/111.srt");
        config.setAudioDuration(3.25d);
        config.setOutPutPath(desktopPath + "图片转视频_" + System.currentTimeMillis() + ".mp4");
        VideoFfmpegUtil.picAudio2Video(config);

        PicAudioMixConfig config2 = new PicAudioMixConfig();
        config2.setPicPath("C:/Users/Administrator/Desktop/222.png");
        config2.setAudioPath("C:/Users/Administrator/Desktop/222.wav");
        config2.setSubPath("C:/Users/Administrator/Desktop/222.srt");
        config2.setAudioDuration(23.55d);
        config2.setOutPutPath(desktopPath + "图片转视频_" + System.currentTimeMillis() + ".mp4");
        VideoFfmpegUtil.picAudio2Video(config2);
    }

}
