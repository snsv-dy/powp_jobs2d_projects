package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {

    private List<DriverCommand> commandList;


    private ComplexCommand(List<DriverCommand> commandList) {
        this.commandList = commandList;
    }

    @Override
    public void execute(Job2dDriver driver) {

        for (DriverCommand i: commandList)
        {
            i.execute(driver);
        }
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commandList.iterator();
    }

    public static final class Builder {

        private List<DriverCommand> commandList = new ArrayList<>();


        public Builder add(DriverCommand command) {
            commandList.add(command);
            return this;
        }

        public ComplexCommand build() {
            ComplexCommand commandBuilded = new ComplexCommand(new ArrayList<>(commandList));
            return commandBuilded;
        }

    }

}

