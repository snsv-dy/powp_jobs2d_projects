package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.drivers.CompositeDrivers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeDriverTest {
    private Job2dDriver sampleDriver = new SampleDriver();
    private Job2dDriver testDriver = new TestDriver();
    private CompositeDrivers compositeDrivers = new CompositeDrivers();
    @BeforeEach
    void setUp(){
        compositeDrivers.add(sampleDriver);
        compositeDrivers.add(testDriver);
    }

    @Test
    void setToTest(){
        compositeDrivers.setPosition(1, 2);
    }

    @Test
    void operateToTest(){
        compositeDrivers.operateTo(1, 10);
    }

    @Test
    void checkSizeOfChildrenList(){
        assertEquals(2, compositeDrivers.getChildren().size());
        compositeDrivers.remove(sampleDriver);
        assertEquals(1, compositeDrivers.getChildren().size());
    }

    @Test
    void getChildrenTest(){
        List<Job2dDriver> list = compositeDrivers.getChildren();
        assertTrue((list.contains(sampleDriver) && list.contains(testDriver)));
    }




    private static class SampleDriver implements Job2dDriver{

        @Override
        public void setPosition(int i, int i1) {
            System.out.println("Sample Driver set position at pos = " + i + ", " + i1);
        }

        @Override
        public void operateTo(int i, int i1) {
            System.out.println("Sample driver operate to pos = " + i + ", " + i1);

        }
    }

    private static class TestDriver implements Job2dDriver{

        @Override
        public void setPosition(int i, int i1) {
            System.out.println("Test driver set position at pos = " + i + ", " + i1);
        }

        @Override
        public void operateTo(int i, int i1) {
            System.out.println("Test driver operate to pos = " + i + ", " + i1);
        }
    }
}
