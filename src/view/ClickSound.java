package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ClickSound extends JFrame {

    public ClickSound() {
        JButton button = new JButton("Click Me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1");
                playSound(getClass(),  "AiBackgroundMusic.wav");
            }
        });



        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);
        this.setVisible(true);
    }

    public static void playSound(Class<?> o, String soundFileName) {
        try {
            // 获取音频文件路径
            URL soundFileURL = o.getResource("/" + soundFileName);

            if (soundFileURL == null) {
                System.err.println("Could not find the audio file.");
                return;
            }

            // 打开音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileURL);

            // 获取一个声音剪辑对象
            Clip clip = AudioSystem.getClip();

            // 打开音频输入流到剪辑
            clip.open(audioInputStream);

//            audioClip.open(audioStream);

//            // 获取音量控制
//            FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
//
//            // 设置音量大小（范围：最小值到最大值）
//            float volume = -10.0f; // 例如：降低10分贝
//            volumeControl.setValue(volume);

            // 开始播放声音
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClickSound();
    }
}

