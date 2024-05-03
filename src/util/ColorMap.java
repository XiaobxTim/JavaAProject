package util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    static Map<Integer, Color> colorMap = new HashMap<>();

    //todo: complete the method to add other color
    public static void InitialColorMap() {
        int num = 11;
        for (int i = 2, c = 0; i <= 2048; i *= 2, c ++) {
            colorMap.put(i, Color.getHSBColor((float)c/num, 1.0f, 0.8f));
        }
//        colorMap.put(2, Color.RED);
//        colorMap.put(4, Color.ORANGE);
//        colorMap.put(8, Color.YELLOW);
//        colorMap.put(16, Color.GRAY);
//        colorMap.put(32, Color.RED);
//        colorMap.put(64, Color.ORANGE);
//        colorMap.put(128, Color.YELLOW);
//        colorMap.put(256, Color.GRAY);
//        colorMap.put(512, Color.RED);
//        colorMap.put(1024, Color.ORANGE);
//        colorMap.put(2048, Color.YELLOW);
    }

    public static Color getColor(int i) {
        return colorMap.getOrDefault(i, Color.black);
    }
}
