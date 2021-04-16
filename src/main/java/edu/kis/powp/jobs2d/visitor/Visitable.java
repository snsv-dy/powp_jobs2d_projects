package edu.kis.powp.jobs2d.visitor;
import edu.kis.powp.jobs2d.Job2dDriver;

public interface Visitable {
    public void accept(IVisitor visitor, Job2dDriver driver);
}