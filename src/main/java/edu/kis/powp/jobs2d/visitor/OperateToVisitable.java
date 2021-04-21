package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;

public class OperateToVisitable extends OperateToCommand implements VisitableDriverCommand{
    public OperateToVisitable(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}