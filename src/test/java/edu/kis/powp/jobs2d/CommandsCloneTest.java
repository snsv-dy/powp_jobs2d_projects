package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandsCloneTest {

	@Test
	public void testCompoundCommandClone() {
		List<DriverCommand> commands = createCommandList();

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

		Assert.assertNotEquals("Compound commands sizes mustn't be equal", commandSize, cloneSize);
	}

	@Test
	public void testCompoundCommandClonedList(){
		List<DriverCommand> commands = createCommandList();
		CompoundCommand cc = new CompoundCommand(commands, "cc");
		CompoundCommand cloned = cc.clone();

		Iterator<DriverCommand> i = cc.iterator();
		Iterator<DriverCommand> i_cloned = cloned.iterator();

		while(i.hasNext() && i_cloned.hasNext()){
			DriverCommand dc = i.next();
			DriverCommand dc_cloned = i_cloned.next();
			Assert.assertNotEquals("Sklonowane obiekty nie powinny być takie same", dc, dc_cloned);
		}

	}

	@Test
	public void testOperateToCommandClone() {
		OperateToCommand command = new OperateToCommand(12, 13);
		OperateToCommand clone = command.clone();
		command.setPosX(11);
		command.setPosY(14);

		Assert.assertFalse("X and Y shouldn't be the same",
				command.getPosX()==clone.getPosX() || command.getPosY()==clone.getPosY());
	}

	@Test
	public void testSetPositionCommandClone() {
		SetPositionCommand command = new SetPositionCommand(12, 13);
		SetPositionCommand clone = command.clone();
		command.setPosX(11);
		command.setPosY(14);
		if(command.getPosX()==clone.getPosX() || command.getPosY()==clone.getPosY()){
			throw new AssertionError("X and Y shouldn't be the same");
		}

		Assert.assertFalse("X and Y shouldn't be the same",
				command.getPosX()==clone.getPosX() || command.getPosY()==clone.getPosY());
	}

	private List<DriverCommand> createCommandList(){
		List<DriverCommand> commands = new ArrayList<>();
		commands.add(new SetPositionCommand(1, 2));
		commands.add(new OperateToCommand(10, 24));
		commands.add(new OperateToCommand(13, 26));
		commands.add(new OperateToCommand(-17, 23));
		commands.add(new OperateToCommand(1, 2));
		return commands;
	}
}