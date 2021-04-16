package edu.kis.powp.jobs2d.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class DriverVisitor implements Visitable {
    private List<Visitable> elements = new ArrayList<>();

    @Override
    public void accept(IVisitor visitor, Job2dDriver driver) {
        for(Visitable command : elements)
            command.accept(visitor, driver);
    }

    public boolean addNewVisitable(Visitable e){
        return elements.add(e);
    }
}