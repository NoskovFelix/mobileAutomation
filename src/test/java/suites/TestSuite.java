package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.MainTestClass;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainTestClass.class
})
public class TestSuite {
}
