package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroProxyDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MacroRecordListener implements ActionListener {
    private DriverManager driverManager;

    public MacroRecordListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        List<DriverCommand> commands = driverManager.getCurrentDriver().getCommands();
        if (!commands.isEmpty()) {
            manager.setCurrentCommand(commands, "Recorded Macro");
            DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
            command.execute(driverManager.getCurrentDriver());
            manager.clearCurrentCommand();
        }
    }
}
