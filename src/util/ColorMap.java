package util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    static Map<Integer, Color> colorMap = new HashMap<>();

    //todo: complete the method to add other color
    public static void InitialColorMap() {
        /*int num = 11;
        for (int i = 2, c = 0; i <= 2048; i *= 2, c ++) {
            colorMap.put(i, Color.getHSBColor((float)c/num, 1.0f, 0.8f));
        }*/
        Color CitrineWhite=new Color(250,247,214);
        Color LightOrange=new Color(254,216,177);
        Color YellowOrange=new Color(255,174,66);
        Color RedOrange=new Color(255,83,73);
        Color PastelYellow=new Color(253,253,150);
        Color Icterine=new Color(252,247,94);
        Color Blush=new Color(222,93,131);
        Color Crimson=new Color(220,20,60);
        Color Ruddy=new Color(187,101,40);
        colorMap.put(2, Color.WHITE);
        colorMap.put(4, CitrineWhite);
        colorMap.put(8, LightOrange);
        colorMap.put(16, YellowOrange);
        colorMap.put(32, RedOrange);
        colorMap.put(64, Color.RED);
        colorMap.put(128, PastelYellow);
        colorMap.put(256, Icterine);
        colorMap.put(512, Blush);
        colorMap.put(1024, Crimson);
        colorMap.put(2048, Ruddy);
    }

    public static Color getColor(int i) {
        return colorMap.getOrDefault(i, Color.black);
    }
}
