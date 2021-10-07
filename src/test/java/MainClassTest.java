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

    @Test
    public void testGetClassNumber(){
        int expectedValue = MainClass.getClassNumber();
        Assert.assertTrue("метод getClassNumber вернул число < 45, вернулось " + expectedValue,
                expectedValue > 45);
    }

}
