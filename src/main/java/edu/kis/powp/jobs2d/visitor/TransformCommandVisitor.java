package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface TransformCommandVisitor {

    void visit (SetPositionCommand command);

    void visit(OperateToCommand command);

    void visit(ICompoundCommand commands);

    default ICompoundCommand getCommandList(ICompoundCommand commands) {
        List<DriverCommand> modifiedCommands = new ArrayList<>();
        Iterator<DriverCommand> iterator = commands.iterator();
        while(iterator.hasNext()) {
            DriverCommand command = iterator.next();
            command.accept(this);
            command = this.getResult();
            modifiedCommands.add(command);
        }
        return new CompoundCommand(modifiedCommands, commands.toString());
    }

    DriverCommand getResult();

}
