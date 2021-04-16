package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.visitor.Driver;
import edu.kis.powp.jobs2d.visitor.IVisitor;
import edu.kis.powp.jobs2d.visitor.OperateToVisitable;
import edu.kis.powp.jobs2d.visitor.SetPositionVisitable;
import edu.kis.powp.jobs2d.visitor.Visitor;

/**
 * Visitor interface test.
 * 
 * @author Adrian
 */

class VisitorT extends Visitor{
    @Override
    public void visit(DriverCommand command, Job2dDriver driver) {
        if(command instanceof OperateToCommand)
            System.out.println("Operate to execution");
        if(command instanceof SetPositionCommand)
            System.out.println("Set position execution");
        
        super.visit(command, driver);
    }
}

class EmptyDriver implements Job2dDriver{
    @Override
    public void setPosition(int x, int y) {
    }
    @Override
    public void operateTo(int x, int y) {
    }
}

public class VisitorTest {
    private static Job2dDriver driver = new EmptyDriver();

    public static void main(String[] args){
        IVisitor v = new VisitorT();

        Driver d = new Driver();
        for(int i = 0; i < 2; ++i)
            d.addNewVisitable(new OperateToVisitable(i, i));
        
        for(int i = 0; i < 2; ++i)
            d.addNewVisitable(new SetPositionVisitable(i, i));
        
        d.accept(v, driver);
    }
}