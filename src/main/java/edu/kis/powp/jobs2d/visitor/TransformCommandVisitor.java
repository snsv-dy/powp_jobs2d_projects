package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public interface TransformCommandVisitor extends ICommandVisitor {

    void visitPositionCommand(PositionCommand command);

    @Override
    default void visit(SetPositionCommand command) {
        visitPositionCommand(command);
    }

    @Override
    default void visit(OperateToCommand command) {
        visitPositionCommand(command);
    }

    @Override
    default void visit(ICompoundCommand commands) {
        Iterator<DriverCommand> iter = commands.iterator();
        iter.forEachRemaining(command -> {
            command.accept(this);
        });
    }

}
