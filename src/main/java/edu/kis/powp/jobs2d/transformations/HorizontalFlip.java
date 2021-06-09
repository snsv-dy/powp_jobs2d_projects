package edu.kis.powp.jobs2d.transformations;

public class HorizontalFlip implements TransformInterface{

    public HorizontalFlip() {
    }

    @Override
    public Point transform(Point point) {
        return new Point(
                (int) (-point.getX()),
                (int) (point.getY())
        );
    }
}
