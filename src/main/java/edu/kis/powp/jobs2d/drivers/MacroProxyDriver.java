package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class MacroProxyDriver implements Job2dDriver {
    private Boolean status;
    private Job2dDriver driver;
    private final List<DriverCommand> commands = new ArrayList<>();

    public MacroProxyDriver() {
        this.status = false;
    }
    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }
    public void toggleRecord() {
        status = !status;
    }
    public void clearCommands() {
        commands.clear();
    }
    public List<DriverCommand> getCommands() {
        return commands;
    }

    @Override
    public void setPosition(int i, int i1) {
        driver.setPosition(i, i1);
        if (status) {
            commands.add(new SetPositionCommand(i, i1));
        }
    }
    @Override
    public void operateTo(int i, int i1) {
        driver.operateTo(i, i1);
        if (status) {
            commands.add(new OperateToCommand(i, i1));
        }
    }
    @Override
    public String toString() {
        return driver.toString();
    }
}
