package edu.kis.powp.jobs2d.visitor;

import java.util.ArrayList;
import java.util.List;

public class DriverCommandVisitor implements VisitableDriverCommand {
    private List<VisitableDriverCommand> elements = new ArrayList<>();

    @Override
    public void accept(IVisitor visitor) {
        for(VisitableDriverCommand command : elements)
            command.accept(visitor);
    }

    public boolean addNewVisitable(VisitableDriverCommand e){
        return elements.add(e);
    }
}