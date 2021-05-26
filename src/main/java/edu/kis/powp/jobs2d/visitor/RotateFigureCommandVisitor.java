package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.PositionCommand;

public class RotateFigureCommandVisitor implements TransformCommandVisitor{

    private final double degree;

    public RotateFigureCommandVisitor(double degree){
        this.degree = Math.toRadians(degree);
    }

    @Override
    public void visitPositionCommand(PositionCommand command){
        int newX = calculateX(command.getPosX(), command.getPosY());
        int newY = calculateY(command.getPosX(), command.getPosY());
        command.setPosX(newX);
        command.setPosY(newY);
    }

    private int calculateX(int originalX, int originalY){
        double pom = Math.cos(degree) * originalX - Math.sin(degree) * originalY;
        return (int)(pom);
    }

    private int calculateY(int originalX, int originalY){
        double pom = Math.sin(degree) * originalX + Math.cos(degree) * originalY;
        return (int)(pom);
    }

}
