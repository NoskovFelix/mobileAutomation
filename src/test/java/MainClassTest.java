import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        int actualValue = 14;
        int expectedValue = MainClass.getLocalNumber();
        Assert.assertEquals("Число " + actualValue + "НЕ равно " + expectedValue,
                expectedValue, actualValue);
    }

}
