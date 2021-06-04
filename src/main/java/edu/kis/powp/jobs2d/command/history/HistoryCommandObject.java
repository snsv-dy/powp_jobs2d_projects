package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class HistoryCommandObject {
    private String name,date;
    private DriverCommand comm;

    public HistoryCommandObject(String date, String commandName, DriverCommand command) {
        this.date = date;
        this.name = commandName;
        this.comm = command;
    }

    public DriverCommand getComm() {
        return comm;
    }

    @Override
    public String toString() {
        return "At "+ date + " used " + name;
    }
}