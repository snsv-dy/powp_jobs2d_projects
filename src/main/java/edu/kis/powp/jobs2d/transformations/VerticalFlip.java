package edu.kis.powp.jobs2d.transformations;

public class VerticalFlip implements TransformInterface {

    public VerticalFlip() {
    }

    @Override
    public Point transform(Point point) {
        return new Point(
                (int) (point.getX()),
                (int) (-point.getY())
        );
    }
}