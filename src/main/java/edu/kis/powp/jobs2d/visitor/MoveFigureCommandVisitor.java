package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MoveFigureCommandVisitor implements TransformCommandVisitor {

    private final int moveX;
    private final int moveY;
    private int posX;
    private int posY;

    public MoveFigureCommandVisitor(int moveX, int moveY){
        this.moveX = moveX;
        this.moveY = moveY;
    }

    private void setMoved(int x, int y){
        posX = x + moveX;
        posY = y + moveY;
    }

    @Override
    public SetPositionCommand visit(SetPositionCommand command) {
        setMoved(command.getPosX(), command.getPosY());
        return new SetPositionCommand(posX, posY);
    }

    @Override
    public OperateToCommand visit(OperateToCommand command) {
        setMoved(command.getPosX(), command.getPosY());
        return new OperateToCommand(posX, posY);
    }
}
