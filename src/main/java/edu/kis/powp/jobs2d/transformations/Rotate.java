package edu.kis.powp.jobs2d.transformations;

import java.util.Random;

public class Rotate implements TransformInterface {


    public Rotate() {
    }

    @Override
    public Point transform(Point point) {

        Random random = new Random();
        int angle = random.nextInt(180);

        return new Point(
                (int) (point.getX() * Math.cos(angle) - point.getY() * Math.sin(angle)),
                (int) (point.getX() * Math.sin(angle) + point.getY() * Math.cos(angle))
        );
    }
}
