package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class ScaleFigureCommandVisitor implements TransformCommandVisitor{

    private final double scale;
    private int posX;
    private int posY;
    private DriverCommand command;

    public ScaleFigureCommandVisitor(double scale){
        this.scale = scale;
    }

    private void setScaled(int x, int y){
        posX = (int) (x * scale);
        posY = (int) (y * scale);
    }

    @Override
    public void visit(SetPositionCommand command) {
        setScaled(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setScaled(command.getPosX(), command.getPosY());
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
