package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroClearListener implements ActionListener {
    private final DriverManager driverManager;

    public MacroClearListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.driverManager.getCurrentDriver().clearCommands();
    }
}
