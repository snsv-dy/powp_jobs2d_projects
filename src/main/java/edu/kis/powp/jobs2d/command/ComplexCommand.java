package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class ComplexCommand implements DriverCommand {

    private List<DriverCommand> polecenia;


    private ComplexCommand(List<DriverCommand> polecenia) {
        this.polecenia = polecenia;
    }

    @Override
    public void execute(Job2dDriver driver) {

        for (DriverCommand i:polecenia)
        {
            i.execute(driver);
        }
    }


    public static final class Builder {

        private List<DriverCommand> polecenia = new ArrayList<>();


        public Builder add(DriverCommand command) {
            polecenia.add(command);
            return this;
        }

        public ComplexCommand build() {
            ComplexCommand commandBuilded = new ComplexCommand(new ArrayList<>(polecenia));
            return commandBuilded;
        }

    }

}

