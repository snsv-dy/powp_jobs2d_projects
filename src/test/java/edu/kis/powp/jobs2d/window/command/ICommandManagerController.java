package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.List;

public interface ICommandManagerController {
	void importCommand(String path) throws IOException;
	void exportCommand(String path) throws IOException;
	void clearCommand();
	void restoreCommand();
	String getCurrentCommandString();
	void deleteObservers();
	List<Subscriber> getObservers();
}
