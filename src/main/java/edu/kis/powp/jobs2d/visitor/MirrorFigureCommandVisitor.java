package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MirrorFigureCommandVisitor implements TransformCommandVisitor{

    private final boolean mirrorHorizontal;
    private final boolean mirrorVertical;

    public MirrorFigureCommandVisitor(boolean mirrorHorizontal, boolean mirrorVertical){
        this.mirrorHorizontal = mirrorHorizontal;
        this.mirrorVertical = mirrorVertical;
    }

    @Override
    public void visitPositionCommand(PositionCommand command){
        if (mirrorHorizontal) command.setPosX(command.getPosX() * -1);
        if (mirrorVertical) command.setPosY(command.getPosY() * -1);
    }

}
