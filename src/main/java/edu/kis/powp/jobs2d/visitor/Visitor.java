package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;

public class Visitor implements IVisitor {
    @Override
    public void visit(DriverCommand command, Job2dDriver driver) {
        command.execute(driver);
    }
}