package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.jobs2d.command.CommandImporter;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.FileOpertor;
import edu.kis.powp.jobs2d.command.json.JsonCommandImporter;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.List;

public class CommandManagerController implements ICommandManagerController {

	private DriverCommandManager commandManager;

	public CommandManagerController(DriverCommandManager commandManager) {
		this.commandManager = commandManager;
	}

	@Override
	public void importCommand(String fileContent, CommandImporter importer) throws IOException {
		commandManager.setCurrentCommand(importer.importCommand(fileContent));
	}

	@Override
	public void exportCommand(String path, CommandImporter importer) throws IOException {
		String commandText = importer.exportCommand((CompoundCommand) commandManager.getCurrentCommand());
		FileOpertor.writeFileContent(path,commandText);
	}

	@Override
	public void clearCommand() {
		commandManager.clearCurrentCommand();
	}

	@Override
	public void restoreCommand(CommandsFeature commandsFeature) {
		commandsFeature.getDriverCommandManager().restoreSubscribers();
	}

	@Override
	public String getCurrentCommandString() {
		return commandManager.getCurrentCommandString();
	}

	@Override
	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
	}

	@Override
	public List<Subscriber> getObservers() {
		return commandManager.getChangePublisher().getSubscribers();
	}

	@Override
	public void runCommand(DriverFeature driverFeature) {
		new SelectRunCurrentCommandOptionListener(driverFeature.getDriverManager());
	}
}
