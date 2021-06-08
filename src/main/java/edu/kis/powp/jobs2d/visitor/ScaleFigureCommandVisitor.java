package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class ScaleFigureCommandVisitor implements TransformCommandVisitor{

    private final double scale;
    private int posX;
    private int posY;

    public ScaleFigureCommandVisitor(double scale){
        this.scale = scale;
    }

    private void setScaled(int x, int y){
        posX = (int) (x * scale);
        posY = (int) (y * scale);
    }

    @Override
    public SetPositionCommand visit(SetPositionCommand command) {
        setScaled(command.getPosX(), command.getPosY());
        return new SetPositionCommand(posX, posY);
    }

    @Override
    public OperateToCommand visit(OperateToCommand command) {
        setScaled(command.getPosX(), command.getPosY());
        return new OperateToCommand(posX, posY);
    }
}
