package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class ScaleFigureCommandVisitor implements ICommandVisitor{
    private final double scale;

    public ScaleFigureCommandVisitor(double scale){
        this.scale = scale;
    }

    @Override
    public void visit(OperateToCommand command) {
        command.setPosX((int) (command.getPosX() * scale));
        command.setPosY((int) (command.getPosY() * scale));
    }

    @Override
    public void visit(SetPositionCommand command) {
        command.setPosX((int) (command.getPosX() * scale));
        command.setPosY((int) (command.getPosY() * scale));
    }

    @Override
    public void visit(ICompoundCommand commands) {
        Iterator<DriverCommand> iter = commands.iterator();
        iter.forEachRemaining(command -> {
            command.accept(this);
        });

    }
}
