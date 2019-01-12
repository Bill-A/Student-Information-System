package sis.report;

import org.junit.*;
import sis.studentinfo.*;

import static org.junit.Assert.*;
import static sis.report.ReportConstant.*;


public class RosterReporterTest {
  @Test
  public void canCreateRosterReport() {
    CourseSession session =
            CourseSession.create("ENGL", "101",
            DateUtil.createDate(2003, 1, 6));

    Student studentA = new Student("Semira Allen");
    Student studentB = new Student("Joseph Smith");

    session.enroll(studentA);
    session.enroll(studentB);

    String rosterReport = new RosterReporter(session).getReport();
    assertEquals(
            RosterReporter.ROSTER_REPORT_HEADER +
                    "Semira Allen" + NEWLINE +
                    "Joseph Smith" + NEWLINE +
                    RosterReporter.ROSTER_REPORT_FOOTER +
                    "2" +
                    NEWLINE,
            rosterReport);
  }



}
