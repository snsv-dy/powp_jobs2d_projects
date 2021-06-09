package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExceededCanvasVisitor implements ICommandVisitor{
	private Shape area;
	private boolean exceeds = false;

	public boolean getResult() {
		return exceeds;
	}
	public ExceededCanvasVisitor(Shape area){
		this.area = area;
	}

	@Override
	public void visit(OperateToCommand command) {
		exceeds = !area.contains(command.getPosX(), command.getPosY());
	}

	@Override
	public void visit(SetPositionCommand command) {
		exceeds = !area.contains(command.getPosX(), command.getPosY());
	}

	@Override
	public void visit(ICompoundCommand command) {
		Iterator<DriverCommand> iterator = command.iterator();
		exceeds = false;
		while(!exceeds && iterator.hasNext()) {
			DriverCommand c = iterator.next();
			c.accept(this);
		}
	}
}
