package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.history.HistoryCommandObject;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectHistoryCommandOptionListener implements ListSelectionListener {

    private DriverCommandManager commManager;
    public SelectHistoryCommandOptionListener(DriverCommandManager commManager)
    {
        this.commManager = commManager;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            HistoryCommandObject thisVal = ((JList<HistoryCommandObject>)e.getSource()).getSelectedValue();
            if (thisVal != null) {

                commManager.setCurrentCommand(thisVal.getComm());
            }
        }
    }
}