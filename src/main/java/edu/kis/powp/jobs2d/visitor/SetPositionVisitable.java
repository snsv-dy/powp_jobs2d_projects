package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;

public class SetPositionVisitable extends OperateToCommand implements Visitable {

    public SetPositionVisitable(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void accept(IVisitor visitor, Job2dDriver driver) {
        visitor.visit(this, driver);
    }
}