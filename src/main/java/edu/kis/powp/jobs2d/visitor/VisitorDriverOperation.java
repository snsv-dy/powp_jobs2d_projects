package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class VisitorDriverOperation implements IVisitor {
    private Job2dDriver driver;
    public VisitorDriverOperation(Job2dDriver driver){
        this.driver = driver;
    }

    @Override
    public void visit(OperateToCommand operation) {
        operation.execute(driver);
    }

    @Override
    public void visit(SetPositionCommand operation) {
        operation.execute(driver);
    }
}