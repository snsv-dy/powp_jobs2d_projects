package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

import static java.lang.Math.pow;

public class CommandStatsVisitor implements ICommandVisitor {
    private int operateToCounter = 0;
    private int setPositionCounter = 0;
    private double distance = 0;
    private int startX = 0;
    private int startY = 0;
    @Override
    public void visit(OperateToCommand command) {
        int x = command.getPosX();
        int y = command.getPosY();
        this.distance += calculateDistance(x, startX, y, startY);
        this.startX = x;
        this.startY = y;
        this.operateToCounter ++;

    }

    @Override
    public void visit(SetPositionCommand command) {
        this.startX = command.getPosX();
        this.startY = command.getPosY();
        this.setPositionCounter ++;
    }

    @Override
    public void visit(ICompoundCommand command) {
        Iterator<DriverCommand> driverCommandIterator = command.iterator();
        while (driverCommandIterator.hasNext()){
            driverCommandIterator.next().accept(this);
        }
    }

    public void resetData(){

        operateToCounter = 0;
        setPositionCounter = 0;
        distance = 0;
    }
    private double calculateDistance(int x1, int x2, int y1, int y2){
        return Math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

    public int getOperateToCounter() {
        return operateToCounter;
    }

    public double getDistance() {
        return distance;
    }

    public int getSetPositionCounter() {
        return setPositionCounter;
    }
}
