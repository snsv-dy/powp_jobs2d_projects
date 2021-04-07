package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroProxyDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroToggleListener implements ActionListener {
    private DriverManager driverManager;

    public MacroToggleListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.driverManager.getCurrentDriver().toggleRecord();
    }
}
