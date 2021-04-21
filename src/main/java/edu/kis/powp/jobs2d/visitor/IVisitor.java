package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public interface IVisitor {
    public void visit(OperateToCommand operation);
    public void visit(SetPositionCommand operation);
}
