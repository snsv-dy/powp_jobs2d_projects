package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class MirrorFigureCommandVisitor implements ICommandVisitor{
    private final boolean mirrorHorizontal;
    private final boolean mirrorVertical;

    public MirrorFigureCommandVisitor(boolean mirrorHorizontal, boolean mirrorVertical){
        this.mirrorHorizontal = mirrorHorizontal;
        this.mirrorVertical = mirrorVertical;
    }

    @Override
    public void visit(OperateToCommand command) {
        if (mirrorHorizontal){
            command.setPosX(command.getPosX() * -1);
        }
        if(mirrorVertical){
            command.setPosY(command.getPosY() * -1);
        }
    }

    @Override
    public void visit(SetPositionCommand command) {
        if (mirrorHorizontal){
            command.setPosX(command.getPosX() * -1);
        }
        if(mirrorVertical){
            command.setPosY(command.getPosY() * -1);
        }
    }

    @Override
    public void visit(ICompoundCommand commands) {
        Iterator<DriverCommand> iter = commands.iterator();
        iter.forEachRemaining(command -> {
            command.accept(this);
        });
    }
}
