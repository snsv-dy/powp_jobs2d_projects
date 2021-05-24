package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class RotateFigureCommandVisitor implements ICommandVisitor{
    private final double degree;

    public RotateFigureCommandVisitor(double degree){
        this.degree = Math.toRadians(degree);
    }

    @Override
    public void visit(OperateToCommand command) {
        double pomX = Math.cos(degree) * command.getPosX() - Math.sin(degree) * command.getPosY();
        double pomY= Math.sin(degree) * command.getPosX() + Math.cos(degree) * command.getPosY();
        command.setPosX((int)(pomX));
        command.setPosY((int)(pomY));
    }

    @Override
    public void visit(SetPositionCommand command) {
        double pomX = Math.cos(degree) * command.getPosX() - Math.sin(degree) * command.getPosY();
        double pomY = Math.sin(degree) * command.getPosX() + Math.cos(degree) * command.getPosY();
        command.setPosX((int)(pomX));
        command.setPosY((int)(pomY));
    }

    @Override
    public void visit(ICompoundCommand commands) {
        Iterator<DriverCommand> iter = commands.iterator();
        iter.forEachRemaining(command -> {
            command.accept(this);
        });
    }
}
