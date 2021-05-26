package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MoveFigureCommandVisitor implements TransformCommandVisitor {

    private final int moveX;
    private final int moveY;

    public MoveFigureCommandVisitor(int moveX, int moveY){
        this.moveX = moveX;
        this.moveY = moveY;
    }

    @Override
    public void visitPositionCommand(PositionCommand command){
        command.setPosX(command.getPosX() + moveX);
        command.setPosY(command.getPosY() + moveY);
    }

}
