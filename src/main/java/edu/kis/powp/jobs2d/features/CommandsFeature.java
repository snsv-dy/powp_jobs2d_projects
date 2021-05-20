package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature implements Feature {

	private DriverCommandManager commandManager;

	public void setup(Application application) {
		commandManager = new DriverCommandManager();

		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
	}

	/**
	 * Get manager of application driver command.
	 * 
	 * @return plotterCommandManager.
	 */
	public DriverCommandManager getDriverCommandManager() {
		return commandManager;
	}
}
