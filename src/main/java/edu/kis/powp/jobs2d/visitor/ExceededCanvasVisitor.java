package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExceededCanvasVisitor implements ICommandVisitor{

    private Shape area;
    public boolean exceeds = false;

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
        while(!exceeds && iterator.hasNext()) {
            DriverCommand c = iterator.next();
            c.accept(this);
        }
    }
}
