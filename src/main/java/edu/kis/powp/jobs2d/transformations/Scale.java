package edu.kis.powp.jobs2d.transformations;


public class Scale implements TransformInterface {
    private final double xScale;
    private final double yScale;

    public Scale(double xScale, double yScale) {
        this.xScale = xScale;
        this.yScale = yScale;
    }

    @Override
    public Point transform(Point point) {
        return new Point((int) (point.getX() * xScale), (int) (point.getY() * yScale));
    }
}
