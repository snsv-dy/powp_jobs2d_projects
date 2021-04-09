package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.MacroRecorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroStopListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MacroRecorder.stopRecording();
    }
}
