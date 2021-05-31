package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.jobs2d.command.CommandImporter;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.FileOpertor;
import edu.kis.powp.jobs2d.command.json.JsonCommandImporter;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.List;

public class CommandManagerControllerController implements ICommandManagerController {

	private DriverCommandManager commandManager;
	private CommandImporter importer = new JsonCommandImporter();
	private CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);

	public CommandManagerControllerController(DriverCommandManager commandManager) {
		this.commandManager = commandManager;
	}

	@Override
	public void importCommand(String path) throws IOException {
		String fileContent = FileOpertor.loadFileContent(path);
		commandManager.setCurrentCommand(importer.importCommand(fileContent));
	}

	@Override
	public void exportCommand(String path) throws IOException {
		String commandText = importer.exportCommand((CompoundCommand) commandManager.getCurrentCommand());
		FileOpertor.writeFileContent(path,commandText);
	}

	@Override
	public void clearCommand() {
		commandManager.clearCurrentCommand();
	}

	@Override
	public void restoreCommand() {
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
}
