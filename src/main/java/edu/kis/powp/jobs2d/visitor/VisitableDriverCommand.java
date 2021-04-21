package edu.kis.powp.jobs2d.visitor;

public interface VisitableDriverCommand {
    public void accept(IVisitor visitor);
}