package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.visitor.ICommandVisitor;
import edu.kis.powp.jobs2d.visitor.TransformCommandVisitor;

/**
 * DriverCommand interface.
 */
public interface DriverCommand {

	/**
	 * Execute command on driver.
	 *
	 * @param driver driver.
	 */
	public void execute(Job2dDriver driver);
	public void accept(ICommandVisitor visitor);
	public DriverCommand accept(TransformCommandVisitor visitor);
	public DriverCommand clone();
}
