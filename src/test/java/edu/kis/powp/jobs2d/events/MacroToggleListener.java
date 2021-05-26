package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDecoratorDriver;
import edu.kis.powp.jobs2d.drivers.MacroRecorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MacroToggleListener implements ActionListener {
	private DriverManager driverManager;
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private boolean state = false;
	public MacroToggleListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (state) {
			MacroRecorder.stopRecording();
			try {
				MacroDecoratorDriver mDriver = (MacroDecoratorDriver) driverManager.getCurrentDriver();
				driverManager.setCurrentDriver(mDriver.getDriver());
				state = !state;
			} catch (ClassCastException exp) {
				logger.log(new LogRecord(Level.WARNING, "Macro driver changed"));
			}
		} else {
			MacroDecoratorDriver mDriver = new MacroDecoratorDriver();
			mDriver.setDriver(driverManager.getCurrentDriver());
			driverManager.setCurrentDriver(mDriver);
			MacroRecorder.startRecording();
			state = !state;
		}

	}
}
