package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelayedSlider {
    private JSlider slider;
    private Timer timer;
    private int delay = 500; // 延时0.5秒
    private int targetValue;

    public DelayedSlider() {
        JFrame frame = new JFrame("Delayed Slider Example");
        slider = new JSlider(0, 100, 50);

        // 创建一个Timer，每次延时后执行一次ActionListener
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(targetValue);
                timer.stop(); // 停止计时器
            }
        });
        timer.setRepeats(false); // 确保计时器只执行一次

        // 添加滑块监听器
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!timer.isRunning()) {
                    timer.start(); // 启动计时器
                } else {
                    timer.restart(); // 如果计时器已经在运行，重置计时器
                }
                targetValue = slider.getValue(); //
                System.out.println(targetValue);
            }
        });

        frame.add(slider);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DelayedSlider();
            }
        });
    }
}

