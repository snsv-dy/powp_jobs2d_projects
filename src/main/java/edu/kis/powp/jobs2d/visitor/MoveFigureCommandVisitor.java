package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MoveFigureCommandVisitor extends FigureCommandVisitor {

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
    public void visit(SetPositionCommand command) {
        setMoved(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setMoved(command.getPosX(), command.getPosY());
        this.command = new OperateToCommand(posX, posY);
    }
}
