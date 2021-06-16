package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

import static java.lang.Math.pow;

public class UsageDriver implements Job2dDriver {
    private int setOperations = 0;
    private int allOperations = 0;
    private int startX = 0;
    private int startY = 0;
    private double distance = 0;
    private static final Logger logger = Logger.getLogger("global");

    @Override
    public void setPosition(int x, int y) {
        this.setOperations += 1;
        this.allOperations += 1;
        logger.info("All operation = " + allOperations + "Set operation = " + setOperations);
        startX = x;
        startY = y;

    }

    @Override
    public void operateTo(int x, int y) {
        this.distance += calculateDistance(x, startX, y, startY);
        this.allOperations += 1;
        logger.info("All operation = " + allOperations + "Distance = " + distance);
        startX = x;
        startY = y;
    }

    private double calculateDistance(int x1, int x2, int y1, int y2){
        return Math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }
}
