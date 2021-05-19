package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;

public class SelectClearPanelOptionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		DrawerFeature drawerFeature = FeatureManager.getFeature(DrawerFeature.class);
		drawerFeature.getDrawerController().clearPanel();
	}
}
