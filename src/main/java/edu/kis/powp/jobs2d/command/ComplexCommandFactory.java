package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.features.DriverFeature;

public class ComplexCommandFactory {
    public static void drawRectangle(int x, int y, int off1, int off2) {
        new ComplexCommand.Builder()
                .add(new SetPositionCommand(x, y)).add(new OperateToCommand(x + off1, y))
                .add(new OperateToCommand(x + off1, y + off2))
                .add(new OperateToCommand(x, y + off2))
                .add(new OperateToCommand(x, y))
                .build()
                .execute(DriverFeature.getDriverManager().getCurrentDriver());
    }

    public static void drawTriangle(int x, int y, int off1, int off2) {
        new ComplexCommand.Builder()
                .add(new SetPositionCommand(x, y))
                .add(new OperateToCommand(x, y + off1))
                .add(new OperateToCommand(x + off2, y + off1))
                .add(new OperateToCommand(x, y))
                .build()
                .execute(DriverFeature.getDriverManager().getCurrentDriver());
    }
}
