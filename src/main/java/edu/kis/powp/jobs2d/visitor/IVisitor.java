package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;

public interface IVisitor {
    public void visit(DriverCommand command, Job2dDriver driver);
}

/**
 * DriverCommand - Element
 * OperateToCommand - ElementA
 * SetPositionCommand - ElementB
 * 
 * 
 */