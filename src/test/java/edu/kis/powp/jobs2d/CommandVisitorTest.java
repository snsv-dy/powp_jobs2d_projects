package edu.kis.powp.jobs2d;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.ComplexDriverCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.visitor.IVisitor;
import edu.kis.powp.jobs2d.visitor.DriverCommandVisitor;

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

public class CommandVisitorTest {
    private static Job2dDriver driver = new PrintOperationDriver();

    public static void main(String[] args){
        IVisitor visitor = new DriverCommandVisitor(driver);

        List<DriverCommand> commands = new ArrayList<>();
        for(int i = 0; i < 3; ++i)
            commands.add(new OperateToCommand(i, -i));
        
        ComplexDriverCommand cdc = new ComplexDriverCommand(commands);

        cdc.accept(visitor);
    }
}