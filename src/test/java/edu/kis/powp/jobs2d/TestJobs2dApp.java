package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.jobs2d.observer.DriverNameUpdateObserver;

public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);

		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(driverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(driverFeature.getDriverManager());

		RectangleListener rectangleListener = new RectangleListener(driverFeature.getDriverManager());
		TriangleListener triangleListener = new TriangleListener(driverFeature.getDriverManager());


		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);

		application.addTest("Rectangle", rectangleListener);
		application.addTest("Triangle", triangleListener);

	}

	/**
	 * Setup test using driver commands in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);

		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());

		application.addTest("▶ Run command", new SelectRunCurrentCommandOptionListener(driverFeature.getDriverManager()));

		application.addTest("Start recording", new MacroStartListener(driverFeature.getDriverManager()));

		application.addTest("Stop recording", new MacroStopListener(driverFeature.getDriverManager()));

		application.addTest("Load recorded", new MacroLoadListener());

		application.addTest("Test if exceeds A4", new TestIfCommandFits(PaperFormats.A4_v));
		application.addTest("Test if exceeds B3", new TestIfCommandFits(PaperFormats.B3_v));
	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);
		DrawerFeature drawerFeature = FeatureManager.getFeature(DrawerFeature.class);

		Job2dDriver loggerDriver = new LoggerDriver();
		driverFeature.addDriver("Logger driver", loggerDriver);

		DrawPanelController drawerController = drawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		driverFeature.addDriver("Line Simulator", driver);
		driverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		driverFeature.addDriver("Special line Simulator", driver);


		CompositeDriver compositeDriver = new CompositeDriver();
		compositeDriver.add(loggerDriver);
		compositeDriver.add(driver);
		driverFeature.addDriver("Composite Driver", compositeDriver);
	}

	private static void setupWindows(Application application) {
		CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);

		CommandManagerWindow commandManager = new CommandManagerWindow(commandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		commandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				FeatureManager.addFeatures(new DrawerFeature(), new CommandsFeature(), new DriverFeature());
				FeatureManager.setup(app);

				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);

				app.setVisibility(true);
			}
		});
	}

}
