package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundCommand implements ICompoundCommand {

	private String name;
	private List<DriverCommand> driverCommands;

	public CompoundCommand(List<DriverCommand> driverCommands, String name) {
		this.driverCommands = driverCommands;
		this.name = name;
	}

	@Override
	public void execute(Job2dDriver driver) {
		driverCommands.forEach((c) -> c.execute(driver));
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return driverCommands.iterator();
	}

	@Override
	public CompoundCommand clone() {
		ArrayList<DriverCommand> list = new ArrayList<>();
		for(DriverCommand d:driverCommands){
			list.add(d.clone());
		}
		return new CompoundCommand(list, name);
	}

	@Override
	public String toString() {
		return name;
	}

	public void addCommand(DriverCommand command) {
		driverCommands.add(command);
	}
}