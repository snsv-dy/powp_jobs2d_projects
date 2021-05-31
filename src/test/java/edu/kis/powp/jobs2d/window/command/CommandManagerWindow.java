package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CommandManagerWindow extends JFrame implements WindowComponent {
	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;
	private JTextArea currentCommandField;
	private String observerListString;
	private JTextArea observerListField;
	private String selectedFilePath;

	private ICommandManagerController controller;

	public CommandManagerWindow(ICommandManagerController controller) {
		this.controller = controller;
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

//		this.commandManager = commandManager;

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

		JButton btnRunCommand = new JButton("Run command");
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);
		btnRunCommand.addActionListener(new SelectRunCurrentCommandOptionListener(driverFeature.getDriverManager()));
		content.add(btnRunCommand, c);

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

		JButton btnResetObservers = new JButton("Restore observers");
		btnResetObservers.addActionListener((ActionEvent e) -> restoreObservers());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnResetObservers, c);
	}

	private void clearCommand() {
		controller.clearCommand();
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
				controller.importCommand(selectedFilePath);
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
				controller.exportCommand(selectedFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(selectedFilePath);
		}
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(controller.getCurrentCommandString());
	}

	public void deleteObservers() {
		controller.deleteObservers();
		updateObserverListField();
	}

	private void restoreObservers() {
		controller.restoreCommand();
		updateObserverListField();
	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = controller.getObservers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
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
