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
        Assert.assertTrue("Метод getClassNumber вернул число < 45, вернулось " + expectedValue,
                expectedValue > 45);
    }

    @Test
    public void testGetClassString(){
        String expectedStr = MainClass.getClassString();
        Assert.assertTrue("Строка 'Hello, world' НЕ содержит подстрок 'Hello' или 'hello'",
                expectedStr.contains("hello") || expectedStr.contains("Hello"));
    }

}
