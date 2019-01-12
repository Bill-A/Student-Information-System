package sis;

import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        sis.report.AllTests.class,
        sis.studentinfo.AllTests.class
})

public class AllTests {
}
