package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class ScaleFigureCommandVisitor implements TransformCommandVisitor{

    private final double scale;

    public ScaleFigureCommandVisitor(double scale){
        this.scale = scale;
    }

    @Override
    public void visitPositionCommand(PositionCommand command){
        command.setPosX((int) (command.getPosX() * scale));
        command.setPosY((int) (command.getPosY() * scale));
    }

}
