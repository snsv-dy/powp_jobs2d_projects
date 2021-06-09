package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.jobs2d.visitor.ScaleFigureCommandVisitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectScaleFigureListener implements ActionListener {

    private DriverManager driverManager;

    public SelectScaleFigureListener (DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);
        DriverCommandManager manager = commandsFeature.getDriverCommandManager();
        manager.acceptVisitor(new ScaleFigureCommandVisitor(1.5));
    }

}