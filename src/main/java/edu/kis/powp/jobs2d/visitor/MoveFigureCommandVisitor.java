package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MoveFigureCommandVisitor implements TransformCommandVisitor {

    private final int moveX;
    private final int moveY;
    private int posX;
    private int posY;
    private DriverCommand command;

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

    @Override
    public void visit(ICompoundCommand commands) {
        this.command = this.getCommandList(commands);
    }

    @Override
    public DriverCommand getResult() {
        return this.command;
    }
}
