package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MirrorFigureCommandVisitor implements TransformCommandVisitor{

    private final boolean mirrorHorizontal;
    private final boolean mirrorVertical;
    private int posX;
    private int posY;

    public MirrorFigureCommandVisitor(boolean mirrorHorizontal, boolean mirrorVertical){
        this.mirrorHorizontal = mirrorHorizontal;
        this.mirrorVertical = mirrorVertical;
    }

    private void setMirrored(int x, int y){
        if (mirrorHorizontal) {
            posX = x * -1;
        }
        else{
            posX = x;
        }
        if (mirrorVertical) {
            posY = y * -1;
        }
        else{
            posY = y;
        }
    }

    @Override
    public SetPositionCommand visit(SetPositionCommand command) {
        setMirrored(command.getPosX(), command.getPosY());
        return new SetPositionCommand(posX, posY);
    }

    @Override
    public OperateToCommand visit(OperateToCommand command) {
        setMirrored(command.getPosX(), command.getPosY());
        return new OperateToCommand(posX, posY);
    }
}
