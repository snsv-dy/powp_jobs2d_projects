package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class MoveFigureCommandVisitor implements ICommandVisitor{
    private final int moveX;
    private final int moveY;

    public MoveFigureCommandVisitor(int moveX, int moveY){
        this.moveX = moveX;
        this.moveY = moveY;
    }

    @Override
    public void visit(OperateToCommand command) {
        command.setPosX(command.getPosX() + moveX);
        command.setPosY(command.getPosY() + moveY);
    }

    @Override
    public void visit(SetPositionCommand command) {
        command.setPosX(command.getPosX() + moveX);
        command.setPosY(command.getPosY() + moveY);
    }

    @Override
    public void visit(ICompoundCommand commands) {
        Iterator<DriverCommand> iter = commands.iterator();
        iter.forEachRemaining(command -> {
            command.accept(this);
        });
    }
}
