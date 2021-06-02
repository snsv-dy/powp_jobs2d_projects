package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.jobs2d.command.CommandImporter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.List;

public interface ICommandManagerController {
	void importCommand(String fileContent, CommandImporter importer) throws IOException;
	void exportCommand(String path, CommandImporter importer) throws IOException;
	void clearCommand();
	void runCommand(DriverFeature driverFeature);
	void restoreCommand(CommandsFeature commandsFeature);
	String getCurrentCommandString();
	void deleteObservers();
	List<Subscriber> getObservers();
}
