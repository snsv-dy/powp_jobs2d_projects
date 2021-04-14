package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

import static java.lang.Math.pow;

/**
 * Line adapter - Job2dDriver with DrawPanelController object.
 */
public class LineDriverAdapter implements Job2dDriver {
	private ILine line;
	private int startX = 0, startY = 0;
	private String name;

	private static final Logger LOGGER = Logger.getLogger("global");
	private int headDistance;
	private int opDistance;

	private DrawPanelController drawController;

	public LineDriverAdapter(DrawPanelController drawController, ILine line, String name) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;
		headDistance = 0;
		opDistance = 0;
	}

	@Override
	public void setPosition(int x, int y) {
		headDistance += distanceBetweenPoints(this.startX,this.startY,x,y);
		LOGGER.info("Head distance: " + headDistance + " units");
		this.startX = x;
		this.startY = y;
	}

	@Override
	public void operateTo(int x, int y) {
		line.setStartCoordinates(this.startX, this.startY);
		opDistance += distanceBetweenPoints(this.startX,this.startY,x,y);
		this.setPosition(x, y);
		line.setEndCoordinates(x, y);
		drawController.drawLine(line);

		LOGGER.info("Op.distance: " + opDistance + " units");
	}

	@Override
	public String toString() {
		return "2d device simulator - " + name;
	}


	private int distanceBetweenPoints(int x1, int y1, int x2, int y2) {
		return (int) Math.ceil(Math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)));

	}
}
