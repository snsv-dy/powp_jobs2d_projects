package edu.kis.powp.jobs2d.transformations;

public class Rotate implements TransformInterface {
    private final int angle;

    public Rotate(int angle) {
        this.angle = angle;
    }

    @Override
    public Point transform(Point point) {
        return new Point(
                (int) (point.getX() * Math.cos(angle) - point.getY() * Math.sin(angle)),
                (int) (point.getX() * Math.sin(angle) + point.getY() * Math.cos(angle))
        );
    }
}
