package edu.kis.powp.jobs2d;


import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.visitor.ExceededCanvasVisitor;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExceededCanvasTest {

    @Test
    public void commandExceedsArea(){
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(0, 0));
        commands.add(new OperateToCommand(5, 0));
        commands.add(new OperateToCommand(0, -5));

        CompoundCommand complex = new CompoundCommand(commands, "somecommand");
        ExceededCanvasVisitor visitor = new ExceededCanvasVisitor(PaperFormats.getA4());
        visitor.visit(complex);

        assertTrue(visitor.exceeds);
    }

    @Test
    public void commandExceedsConvexArea(){
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(-2, -2));
        commands.add(new OperateToCommand(2, -2));
//        commands.add(new OperateToCommand(0, -5));

        CompoundCommand complex = new CompoundCommand(commands, "somecommand");
        Polygon area = new Polygon();
        area.addPoint(-3, -3);
        area.addPoint(0,0);
        area.addPoint(3, -3);
        area.addPoint(3, 3);
        area.addPoint(-3, 3);
        ExceededCanvasVisitor visitor = new ExceededCanvasVisitor(area);
        visitor.visit(complex);

        assertTrue(visitor.exceeds);
    }


    @Test
    public void commandDoesNotExceedConvexArea(){
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(-2, -2));
        commands.add(new OperateToCommand(0, 0));
        commands.add(new OperateToCommand(2, -2));
//        commands.add(new OperateToCommand(0, -5));

        CompoundCommand complex = new CompoundCommand(commands, "somecommand");
        Polygon area = new Polygon();
        area.addPoint(-3, -3);
        area.addPoint(0,0);
        area.addPoint(3, -3);
        area.addPoint(3, 3);
        area.addPoint(-3, 3);
        ExceededCanvasVisitor visitor = new ExceededCanvasVisitor(area);
        visitor.visit(complex);

        assertTrue(visitor.exceeds);
    }
}
