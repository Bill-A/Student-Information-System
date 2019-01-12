package sis.studentinfo;

import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentTest.class,
        CourseSessionTest.class,
        DateUtilTest.class
})

public class AllTests {
}
