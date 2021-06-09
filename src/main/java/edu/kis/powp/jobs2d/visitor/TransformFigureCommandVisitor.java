package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.transformations.Point;
import edu.kis.powp.jobs2d.transformations.TransformInterface;

public class TransformFigureCommandVisitor extends FigureCommandVisitor{

    private TransformInterface transformation;

    public TransformFigureCommandVisitor(TransformInterface transform){
        this.transformation = transform;
    }

    @Override
    public void visit(SetPositionCommand command) {
        Point pom = transformation.transform(new Point(command.getPosX(), command.getPosY()));
        this.command = new SetPositionCommand(pom.getX(), pom.getY());
    }

    @Override
    public void visit(OperateToCommand command) {
        Point pom = transformation.transform(new Point(command.getPosX(), command.getPosY()));
        this.command = new OperateToCommand(pom.getX(), pom.getY());
    }
}
