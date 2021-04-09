package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class MacroProxyDriver implements Job2dDriver {
    private static Boolean status;
    private Job2dDriver driver;
    private static final List<DriverCommand> commands = new ArrayList<>();

    public MacroProxyDriver() {
        status = false;
    }
    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }
    public static void stopRecording() {
        status = false;
    }
    public static void startRecording() {
        status = true;
        commands.clear();
    }
    public static List<DriverCommand> getCommands() {
        return commands;
    }

    @Override
    public void setPosition(int x, int y) {
        driver.setPosition(x, y);
        if (status) {
            commands.add(new SetPositionCommand(x, y));
        }
    }
    @Override
    public void operateTo(int x, int y) {
        driver.operateTo(x, y);
        if (status) {
            commands.add(new OperateToCommand(x, y));
        }
    }
    @Override
    public String toString() {
        return driver.toString();
    }
}
