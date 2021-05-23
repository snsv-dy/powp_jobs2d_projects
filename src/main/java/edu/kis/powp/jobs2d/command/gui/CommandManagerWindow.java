package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandImporter;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.FileOpertor;
import edu.kis.powp.jobs2d.command.json.JsonCommandImporter;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class CommandManagerWindow extends JFrame implements WindowComponent {
	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;
	private DriverCommandManager commandManager;
	private JTextArea currentCommandField;
	private String observerListString;
	private JTextArea observerListField;
	private String selectedFilePath;
	private CommandImporter importer = new JsonCommandImporter();

	public CommandManagerWindow(DriverCommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		JButton btnImportCommand = new JButton("Import command");
		btnImportCommand.addActionListener((ActionEvent e) -> this.importCommand());
		content.add(btnImportCommand, c);

		JButton btnExportCommand = new JButton("Export command");
		btnExportCommand.addActionListener((ActionEvent e) -> this.exportCommand());
		content.add(btnExportCommand, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearObservers, c);
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}

	private void importCommand() {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Choose Json file");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Json files", "json");
		fileChooser.addChoosableFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFilePath = fileChooser.getSelectedFile().getAbsoluteFile().toString();
			try {
				String fileContent = FileOpertor.loadFileContent(selectedFilePath);
				commandManager.setCurrentCommand(importer.importCommand(fileContent));
				updateCurrentCommandField();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(selectedFilePath);
		}
	}

	private void exportCommand() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Json files", "json");
		fileChooser.addChoosableFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(null);

//		int returnValue = fileChooser.showOpenDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			selectedFilePath = fileChooser.getSelectedFile().getAbsoluteFile().toString();
			try {
				String commandText = importer.exportCommand((CompoundCommand) commandManager.getCurrentCommand());
				FileOpertor.writeFileContent(selectedFilePath,commandText);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(selectedFilePath);
		}
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
		this.updateObserverListField();
	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}

	public String getSelectedFilePath() {
		return selectedFilePath;
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
