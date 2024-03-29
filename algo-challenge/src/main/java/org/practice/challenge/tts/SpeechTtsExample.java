package org.practice.challenge.tts;

import com.tencent.SpeechClient;
import com.tencent.tts.model.*;
import com.tencent.tts.service.SpeechSynthesisListener;
import com.tencent.tts.service.SpeechSynthesizer;
import com.tencent.tts.utils.Ttsutils;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author feikong
 * @version 2022/10/27
 */
public class SpeechTtsExample {
    private static String codec = "pcm";
    private static int sampleRate = 16000;

    private static byte[] datas = new byte[0];


    /**
     * 语音合成
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String appId = "";
        String secretId = "";
        String secretKey = "";
        //创建SpeechSynthesizerClient实例，目前是单例
        SpeechClient client = SpeechClient.newInstance(appId, secretId, secretKey);
        //初始化SpeechSynthesizerRequest，SpeechSynthesizerRequest包含请求参数
        SpeechSynthesisRequest request = SpeechSynthesisRequest.initialize();
        request.setCodec(codec);
        //request.setSampleRate(sampleRate);
        //request.setVolume(10);
        //request.setSpeed(2f);
        request.setVoiceType(101007);
        //使用客户端client创建语音合成实例
        SpeechSynthesizer speechSynthesizer = client.newSpeechSynthesizer(request, new MySpeechSynthesizerListener());
        //执行语音合成
        String ttsText = "刻录高防御顾宇峰";
        speechSynthesizer.synthesis(ttsText);
    }


    public static class MySpeechSynthesizerListener extends SpeechSynthesisListener {

        private AtomicInteger sessionId = new AtomicInteger(0);

        @Override
        public void onComplete(SpeechSynthesisResponse response) {
            System.out.println("onComplete");
            if (response.getSuccess()) {
                //根据具体的业务选择逻辑处理
                //Ttsutils.saveResponseToFile(response.getAudio(),"./111.mp3");
                if ("pcm".equals(codec)) {
                    //pcm 转 wav
                    Ttsutils.responsePcm2Wav(sampleRate, response.getAudio(), response.getSessionId());
                }
                if ("opus".equals(codec)) {
                    //opus
                    System.out.println("OPUS:" + response.getSessionId() + " length:" + response.getAudio().length);
                }
            }
            System.out.println("结束：" + response.getSuccess() + " " + response.getCode()
                    + " " + response.getMessage() + " " + response.getEnd());
        }

        //语音合成的语音二进制数据
        @Override
        public void onMessage(byte[] data) {
            //System.out.println("onMessage:" + data.length);
            // Your own logic.
            System.out.println("onMessage length:" + data.length);
            sessionId.incrementAndGet();
        }

        @Override
        public void onFail(SpeechSynthesisResponse response) {
            System.out.println("onFail");
        }
    }
}
