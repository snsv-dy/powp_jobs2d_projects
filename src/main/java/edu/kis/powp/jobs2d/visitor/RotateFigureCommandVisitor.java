package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class RotateFigureCommandVisitor implements TransformCommandVisitor{

    private final double degree;
    private int posX;
    private int posY;

    public RotateFigureCommandVisitor(double degree){
        this.degree = Math.toRadians(degree);
    }

    private void setRotated(int x, int y){
        posX = calculateX(x, y);
        posY = calculateY(x, y);
    }

    private int calculateX(int originalX, int originalY){
        double pom = Math.cos(degree) * originalX - Math.sin(degree) * originalY;
        return (int)(pom);
    }

    private int calculateY(int originalX, int originalY){
        double pom = Math.sin(degree) * originalX + Math.cos(degree) * originalY;
        return (int)(pom);
    }

    @Override
    public SetPositionCommand visit(SetPositionCommand command) {
        setRotated(command.getPosX(), command.getPosY());
        return new SetPositionCommand(posX, posY);
    }

    @Override
    public OperateToCommand visit(OperateToCommand command) {
        setRotated(command.getPosX(), command.getPosY());
        return new OperateToCommand(posX, posY);
    }
}
