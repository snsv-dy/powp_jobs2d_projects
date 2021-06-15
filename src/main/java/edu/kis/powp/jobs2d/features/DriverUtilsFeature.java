package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.observer.DriverNameUpdateObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DriverUtilsFeature implements Feature{
    private Application app;

    @Override
    public void setup(Application application) {
        app = application;
        app.addComponentMenu(DriverUtilsFeature.class, "Drivers utils");
    }

    public void addUtilsCheckBox(String name, Job2dDriver driver, DriverManager driverManager) {
        ActionListener listener = (ActionEvent e)->{
            if(((JCheckBoxMenuItem)e.getSource()).getState())
                driverManager.addUtilsDriver(driver);
            else
                driverManager.removeUtilsDriver(driver);
        };
        app.addComponentMenuElementWithCheckBox(DriverUtilsFeature.class, name, listener, false);
    }
}
