package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandsCloneTest {

	private static void testCompoundCommandClone() {
		List<DriverCommand> commands = new ArrayList<>();
		commands.add(new SetPositionCommand(1, 2));
		commands.add(new OperateToCommand(10, 24));
		commands.add(new OperateToCommand(13, 26));
		commands.add(new OperateToCommand(-17, 23));
		commands.add(new OperateToCommand(1, 2));

		CompoundCommand command = new CompoundCommand(commands, "Compound");

		CompoundCommand clone = command.clone();

		command.addCommand(new OperateToCommand(10, 10));

		int commandSize = 0;
		for(Iterator<DriverCommand> iter = command.iterator(); iter.hasNext(); ) {
			commandSize++;
			iter.next();
		}

		int cloneSize = 0;
		for(Iterator<DriverCommand> iter = clone.iterator(); iter.hasNext(); ) {
			cloneSize++;
			iter.next();
		}

		if(commandSize == cloneSize) {
			throw new AssertionError("Compound commands sizes mustn't be equal");
		}
	}

	private static void testOperateToCommandClone() {
		OperateToCommand command = new OperateToCommand(12, 13);
		OperateToCommand clone = command.clone();
		command.setPosX(11);
		command.setPosY(14);
		if(command.getPosX()==clone.getPosX() || command.getPosY()==clone.getPosY()){
			throw new AssertionError("X and Y shouldn't be the same");
		}
	}

	private static void testSetPositionCommandClone() {
		SetPositionCommand command = new SetPositionCommand(12, 13);
		SetPositionCommand clone = command.clone();
		command.setPosX(11);
		command.setPosY(14);
		if(command.getPosX()==clone.getPosX() || command.getPosY()==clone.getPosY()){
			throw new AssertionError("X and Y shouldn't be the same");
		}
	}

	public static void main(String[] args) {
		testCompoundCommandClone();
		testOperateToCommandClone();
		testSetPositionCommandClone();
	}
}
