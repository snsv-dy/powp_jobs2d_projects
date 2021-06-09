package edu.kis.powp.jobs2d;

import java.awt.*;

public class PaperFormats {
    public static Shape A4_v = new Rectangle(210, 297);
    public static Shape A4_h = new Rectangle(297, 210);
    public static Shape B3_v = new Rectangle(353, 500);
    public static Shape B3_h = new Rectangle(500, 353);

    public static Shape getA4(boolean landscape){
        return landscape ? A4_h : A4_v;
    }

    public static Shape getA4(){
        return getA4(false);
    }

    public static Shape getB3(boolean landscape){
        return landscape ? B3_h : B3_v;
    }

    public static Shape getB3(){
        return getB3(false);
    }
}
