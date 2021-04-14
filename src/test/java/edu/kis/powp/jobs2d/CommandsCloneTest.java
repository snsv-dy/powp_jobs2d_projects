package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandsCloneTest {

	@Test
	public void operateToCommandCloneEqualityTest() {
		OperateToCommand command = new OperateToCommand(1, 2);
		OperateToCommand clone = command.clone();

		Assert.assertNotSame(command, clone);
		Assert.assertEquals(command, clone);
	}

	@Test
	public void operateToCommandCloneNotInterfereTest() {
		OperateToCommand command = new OperateToCommand(1, 2);
		OperateToCommand clone = command.clone();

		clone.setPosX(3);
		clone.setPosY(4);

		Assert.assertEquals(command.getPosX(), 1);
		Assert.assertEquals(command.getPosY(), 2);
	}

	@Test
	public void setPositionCommandCloneEqualityTest() {
		SetPositionCommand command = new SetPositionCommand(1, 2);
		SetPositionCommand clone = command.clone();

		Assert.assertNotSame(command, clone);
		Assert.assertEquals(command, clone);
	}

	@Test
	public void setPositionCommandCloneNotInterfereTest() {
		SetPositionCommand command = new SetPositionCommand(1, 2);
		SetPositionCommand clone = command.clone();

		clone.setPosX(3);
		clone.setPosY(4);

		Assert.assertEquals(command.getPosX(), 1);
		Assert.assertEquals(command.getPosY(), 2);
	}

	@Test
	public void compoundCommandCloneEqualityTest() {
		List<DriverCommand> commands = createCommandsList();
		CompoundCommand command = new CompoundCommand(commands, "command");
		CompoundCommand clone = command.clone();

		Assert.assertNotSame(command, clone);
		Assert.assertEquals(command, clone);
	}

	@Test
	public void compoundCommandCloneNotInterfereTest() {
		List<DriverCommand> commandsList = createCommandsList();
		CompoundCommand command = new CompoundCommand(commandsList, "command");
		CompoundCommand clone = command.clone();

		clone.addCommand(new OperateToCommand(0, 0));

		List<DriverCommand> currentCommandsList = new ArrayList<>();
		command.iterator().forEachRemaining(currentCommandsList::add);
		Assert.assertEquals(commandsList, currentCommandsList);
	}

	private List<DriverCommand> createCommandsList(){
		List<DriverCommand> commands = new ArrayList<>();
		commands.add(new SetPositionCommand(1, 2));
		commands.add(new OperateToCommand(3, 4));
		commands.add(new OperateToCommand(5, 6));
		commands.add(new OperateToCommand(7, 8));
		commands.add(new OperateToCommand(9, 10));
		return commands;
	}
}