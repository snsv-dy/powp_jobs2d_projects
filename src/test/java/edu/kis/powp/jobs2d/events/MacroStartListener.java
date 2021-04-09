package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.MacroProxyDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroStartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MacroProxyDriver.startRecording();
    }
}
