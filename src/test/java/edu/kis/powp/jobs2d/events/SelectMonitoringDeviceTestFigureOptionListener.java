package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.UsageMonitoringDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMonitoringDeviceTestFigureOptionListener implements ActionListener {
	private final DriverManager driverManager;

	public SelectMonitoringDeviceTestFigureOptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Job2dDriver driver = driverManager.getCurrentDriver();
		new SetPositionCommand(100, 0)
				.execute(driver);
		new OperateToCommand(100, 50)
				.execute(driver);
		new OperateToCommand(150, 50)
				.execute(driver);
		new OperateToCommand(150, 0)
				.execute(driver);
		new OperateToCommand(100, 0)
				.execute(driver);
		new SetPositionCommand(0, 0)
				.execute(driver);
		if (driver instanceof UsageMonitoringDriver) {
			UsageMonitoringDriver usageMonitoringDriver = (UsageMonitoringDriver) driver;
			if (usageMonitoringDriver.getOpDistance() != 200 || usageMonitoringDriver.getHeadDistance() != 400) {
				usageMonitoringDriver.getLogger().warning("Usage monitoring test failed!");
			}
			else{
				usageMonitoringDriver.getLogger().info("Usage monitoring test passed!");
			}
		}

	}
}
