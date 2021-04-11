package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class CompositeDrivers implements Job2dDriver {

    private List<Job2dDriver> drivers;

    public CompositeDrivers(){
        drivers = new ArrayList<>();
    }

    @Override
    public void setPosition(int x, int y) {
        drivers.forEach(e->e.setPosition(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        drivers.forEach(e->e.operateTo(x, y));
    }

    public void add(Job2dDriver driver){
        drivers.add(driver);
    }

    public List getChildren(){
        return drivers;
    }

    public void remove(Job2dDriver children){
        drivers.remove(children);
    }
}
