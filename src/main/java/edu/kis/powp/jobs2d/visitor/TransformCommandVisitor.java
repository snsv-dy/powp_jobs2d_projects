package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface TransformCommandVisitor {

    SetPositionCommand visit (SetPositionCommand command);

    OperateToCommand visit(OperateToCommand command);

    default CompoundCommand visit(ICompoundCommand commands) {
        List<DriverCommand> modifiedCommands = new ArrayList<>();
        Iterator<DriverCommand> iterator = commands.iterator();
        while(iterator.hasNext()) {
            DriverCommand command = iterator.next();
            command = command.accept(this);
            modifiedCommands.add(command);
        }
        return new CompoundCommand(modifiedCommands, commands.toString());
    }

}
