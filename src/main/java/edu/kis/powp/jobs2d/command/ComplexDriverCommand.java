package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexDriverCommand implements ICompoundCommand {
    private List<DriverCommand> commands;
    
    public ComplexDriverCommand(List<DriverCommand> commands){
        this.commands = commands;
    }

    @Override
    public void execute(Job2dDriver driver) {
        commands.forEach(c->c.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    } 
}