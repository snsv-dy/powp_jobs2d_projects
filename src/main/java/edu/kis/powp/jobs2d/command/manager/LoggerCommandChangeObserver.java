package edu.kis.powp.jobs2d.command.manager;

import java.util.logging.Logger;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.observer.Subscriber;

public class LoggerCommandChangeObserver implements Subscriber {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public void update() {
		CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);
		DriverCommand command = commandsFeature.getDriverCommandManager().getCurrentCommand();
		logger.info("Current command set to: " + command.toString());
	}

	public String toString() {
		return "Logger Command Change Observer";
	}

}
