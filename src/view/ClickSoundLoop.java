package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ClickSoundLoop extends JFrame {

    public ClickSoundLoop() {
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


            // 这是循环播放声音
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClickSound();
    }
}
