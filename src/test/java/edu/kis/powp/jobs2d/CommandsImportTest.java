package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.json.JsonCommandImporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandsImportTest {
	public static final String FILENAME1 = "testCommand.json";
	private static final String FILENAME2 = "testCommand2.json";

	public static void main(String[] args) {
		ArrayList<DriverCommand> commands = new ArrayList<DriverCommand>();
		commands.add(new SetPositionCommand(0, 0));
		commands.add(new OperateToCommand(0, 100));
		commands.add(new OperateToCommand(100, 100));
		commands.add(new OperateToCommand(100, 0));
		commands.add(new OperateToCommand(0, 0));
		CompoundCommand command = new CompoundCommand(commands, "name");
		try {
			JsonCommandImporter.saveCommand(FILENAME1, command);
			CompoundCommand command2 = JsonCommandImporter.importCommand(FILENAME1);
			JsonCommandImporter.saveCommand(FILENAME2, command2);

			byte[] f1 = Files.readAllBytes(Paths.get(FILENAME1));
			new File(FILENAME1).delete();

			byte[] f2 = Files.readAllBytes(Paths.get(FILENAME2));
			new File(FILENAME2).delete();

			if (Arrays.equals(f1, f2)) {
				System.out.println("Test passed!");
			} else {
				System.err.println("Test failed!");
			}
		} catch (IOException e) {
			System.err.println("Cannot read files!");
		}
	}
}
