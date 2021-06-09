package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class RotateFigureCommandVisitor implements TransformCommandVisitor{

    private final double degree;
    private int posX;
    private int posY;
    private DriverCommand command;

    public RotateFigureCommandVisitor(double degree){
        this.degree = Math.toRadians(degree);
    }

    private void setRotated(int x, int y){
        posX = calculateX(x, y);
        posY = calculateY(x, y);
    }

    private int calculateX(int originalX, int originalY){
        double pom = Math.cos(degree) * originalX - Math.sin(degree) * originalY;
        return (int)(pom);
    }

    private int calculateY(int originalX, int originalY){
        double pom = Math.sin(degree) * originalX + Math.cos(degree) * originalY;
        return (int)(pom);
    }

    @Override
    public void visit(SetPositionCommand command) {
        setRotated(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setRotated(command.getPosX(), command.getPosY());
        this.command = new OperateToCommand(posX, posY);
    }

    @Override
    public void visit(ICompoundCommand commands) {
        this.command = this.getCommandList(commands);
    }

    @Override
    public DriverCommand getResult() {
        return this.command;
    }
}
