package test;

import org.junit.Assert;
import org.junit.Test;

import model.TimeToCleanValuation;

public class TestTimeToClean {

    TimeToCleanValuation time = new TimeToCleanValuation(2,2,300);

    @Test
    private void testTime1(){
        Assert.assertEquals(9000, time.washValuation());
    }

    @Test
    private void testTime2() {
       System.out.println(time.washValuation());
    }
}
