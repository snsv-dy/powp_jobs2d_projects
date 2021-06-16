package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.visitor.CommandStatsVisitor;
import edu.kis.powp.observer.Subscriber;
import edu.kis.powp.jobs2d.window.command.CommandManagerWindow;


import java.util.List;

public class CommandStatsObserver implements Subscriber {
    private DriverCommandManager driverCommandManager;
    private CommandStatsVisitor commandStatsVisitor;
    private CommandManagerWindow commandManagerWindow;
    private int operateToCounter = 0;
    private int setPositionCounter = 0;
    private double distance = 0;
    private String commandName;
    public CommandStatsObserver(DriverCommandManager driverCommandManager, CommandManagerWindow commandManagerWindow){
        this.commandStatsVisitor= new CommandStatsVisitor();
        this.driverCommandManager = driverCommandManager;
        this.commandManagerWindow = commandManagerWindow;
    }

    @Override
    public String toString(){
       return "CommandStatObserver ";
    }

    public String getData(){
        return "Name of command = " + commandName + " \noperateTo commands = " + operateToCounter + " setTo commands = " + setPositionCounter +
                " distance = " + distance;
    }
    @Override
    public void update() {
        DriverCommand driverCommand= driverCommandManager.getCurrentCommand();
        commandName = driverCommand.toString();
        driverCommand.accept(commandStatsVisitor);
        operateToCounter = commandStatsVisitor.getOperateToCounter();
        setPositionCounter = commandStatsVisitor.getSetPositionCounter();
        distance = commandStatsVisitor.getDistance();
        commandManagerWindow.updateCommandStatsField(this.getData());
        commandStatsVisitor.resetData();


    }
}
