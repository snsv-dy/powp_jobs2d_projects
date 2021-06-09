package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.PaperFormats;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.jobs2d.visitor.ExceededCanvasVisitor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class TestIfCommandFits implements ActionListener {

	private Shape bounds;
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public TestIfCommandFits(Shape bounds) {
		this.bounds = bounds;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		CommandsFeature commands = FeatureManager.getFeature(CommandsFeature.class);
		ExceededCanvasVisitor visitor = new ExceededCanvasVisitor(PaperFormats.getA4());
        commands.getDriverCommandManager().getCurrentCommand().accept(visitor);

		if(visitor.getResult()){
			logger.log(new LogRecord(Level.WARNING, "Loaded command exceeds current format."));
		}else{
			logger.log(new LogRecord(Level.INFO, "Loaded command fits inside current format."));
		}
	}
}
