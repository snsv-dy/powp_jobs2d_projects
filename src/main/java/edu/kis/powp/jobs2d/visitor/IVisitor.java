package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public interface IVisitor {
    public void visit(OperateToCommand command);
    public void visit(SetPositionCommand command);
    public void visit(ICompoundCommand command);
}
