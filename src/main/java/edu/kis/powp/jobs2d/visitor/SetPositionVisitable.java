package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class SetPositionVisitable extends SetPositionCommand implements VisitableDriverCommand {

    public SetPositionVisitable(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}