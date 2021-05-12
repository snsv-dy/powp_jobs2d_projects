package edu.kis.powp.jobs2d.command;

public class ComplexCommandFactory {
    public ComplexCommand drawRectangle(int x, int y, int off1, int off2) {
        return new ComplexCommand.Builder()
                .add(new SetPositionCommand(x, y)).add(new OperateToCommand(x+off1, y))
                .add(new OperateToCommand(x+off1, y+off2))
                .add(new OperateToCommand(x, y+off2))
                .add(new OperateToCommand(x, y))
                .build();
    }

    public ComplexCommand drawTriangle(int x, int y, int off1, int off2) {
        return new ComplexCommand.Builder()
                .add(new SetPositionCommand(x, y))
                .add(new OperateToCommand(x, y+off1))
                .add(new OperateToCommand(x+off2, y+off1))
                .add(new OperateToCommand(x, y))
                .build();
    }
}
