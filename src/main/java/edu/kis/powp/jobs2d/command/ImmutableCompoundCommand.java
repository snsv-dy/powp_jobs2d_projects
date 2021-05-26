package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public final class ImmutableCompoundCommand implements ICompoundCommand {
    private final CompoundCommand compoundCommand;

    public ImmutableCompoundCommand(CompoundCommand compound){
        compoundCommand = compound.clone();
    }
    public ImmutableCompoundCommand(List<DriverCommand> driverCommands, String name){
        compoundCommand = new CompoundCommand(driverCommands, name).clone();
    }

    @Override
    public void execute(Job2dDriver driver) {
        compoundCommand.execute(driver);
    }

    @Override
    public DriverCommand clone() {
        return new ImmutableCompoundCommand(compoundCommand);
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return compoundCommand.clone().iterator();
    }

    @Override
	public boolean equals(Object o) {
        return compoundCommand.equals(o);
    }

    @Override
	public String toString() {
		return compoundCommand.toString();
	}

    public CompoundCommand convertToMutableCommand(){
        return this.compoundCommand.clone();
    }
}