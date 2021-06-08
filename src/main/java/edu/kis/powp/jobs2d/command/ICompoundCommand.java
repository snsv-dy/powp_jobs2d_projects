package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

import edu.kis.powp.jobs2d.visitor.ICommandVisitor;
import edu.kis.powp.jobs2d.visitor.TransformCommandVisitor;

/**
 * Interface extending Job2dDriverCommand to execute more than one command.
 */
public interface ICompoundCommand extends DriverCommand {
	public Iterator<DriverCommand> iterator();
	public default void accept(ICommandVisitor visitor){
		visitor.visit(this);
	}
	public default ICompoundCommand accept(TransformCommandVisitor visitor){
		return visitor.visit(this);
	}
}
