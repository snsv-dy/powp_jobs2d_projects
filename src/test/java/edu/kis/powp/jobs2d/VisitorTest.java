package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.visitor.DriverCommandVisitor;
import edu.kis.powp.jobs2d.visitor.IVisitor;
import edu.kis.powp.jobs2d.visitor.OperateToVisitable;
import edu.kis.powp.jobs2d.visitor.SetPositionVisitable;
import edu.kis.powp.jobs2d.visitor.VisitorDriverOperation;

/**
 * Visitor interface test.
 * 
 * @author Adrian
 */

class PrintOperationDriver implements Job2dDriver{
    @Override
    public void setPosition(int x, int y) {
        System.out.println("setPosition("+x+", "+y+")");
    }
    @Override
    public void operateTo(int x, int y) {
        System.out.println("operateTo("+x+", "+y+")");
    }
}

public class VisitorTest {
    private static Job2dDriver driver = new PrintOperationDriver();

    public static void main(String[] args){
        IVisitor visitor = new VisitorDriverOperation(driver);

        DriverCommandVisitor d = new DriverCommandVisitor();
        for(int i = 0; i < 2; ++i)
            d.addNewVisitable(new OperateToVisitable(i, i));
        
        for(int i = 0; i < 2; ++i)
            d.addNewVisitable(new SetPositionVisitable(i, i));
        
        d.accept(visitor);
    }
}