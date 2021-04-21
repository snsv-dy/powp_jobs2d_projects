package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDecoratorDriver;
import edu.kis.powp.jobs2d.drivers.MacroRecorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroStopListener implements ActionListener {
    private DriverManager driverManager;
    public MacroStopListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MacroRecorder.stopRecording();
        try {
            MacroDecoratorDriver mDriver = (MacroDecoratorDriver) driverManager.getCurrentDriver();
            driverManager.setCurrentDriver(mDriver.getDriver());
        } catch (ClassCastException exp) {
            //"Macro driver changed"
        }
    }
}
