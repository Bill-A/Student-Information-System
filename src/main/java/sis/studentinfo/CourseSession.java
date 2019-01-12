package sis.studentinfo;

import java.util.*;

/**
 * Representation of a single-semester session of a specific University course.
 *
 * @author Bill Allen
 */

public class CourseSession implements Comparable<CourseSession> {
  private static int count;
  private String department;
  private String number;
  private ArrayList<Student> students = new ArrayList<Student>();
  private Date startDate;
  private int numberOfCredits;
  static final String NEWLINE =
          System.getProperty("line.separator");
  static final String ROSTER_REPORT_HEADER =
          "Student" + NEWLINE +
          "-------" + NEWLINE;
  static final String ROSTER_REPORT_FOOTER =
          NEWLINE + "# students = ";

  private CourseSession(String department, String number, Date startDate) {
    this.department = department;
    this.number = number;
    this.startDate = startDate;
  }

  private static void incrementCount() {
    ++count;
  }

  public static int getCount() {
    return count;
  }

  public static void resetCount() {
    count = 0;
  }

  public static CourseSession create(String department, String number, Date startDate) {
    incrementCount();
    return new CourseSession(department, number, startDate);
  }

  public String getDepartment() {

    return department;
  }

  public String getNumber() {

    return number;
  }

  public int getNumberOfStudents() {

    return students.size();
  }

  public ArrayList<Student> getAllStudents(){
    return students;
  }

  /**
   * @param student
   */
  public void enroll(Student student) {
    student.addCredits(numberOfCredits);
    students.add(student);
  }


  public Student get(int index) {

    return students.get(index);
  }

  public Date getEndDate() {
    final int sessionLength = 16;
    final int daysInWeek = 7;
    final int daysFromFridayToMonday = 3;
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(startDate);
    int numberOfDays = sessionLength * daysInWeek - daysFromFridayToMonday;
    calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
    return calendar.getTime();
  }

  public Date getStartDate() {

    return startDate;
  }

  public void setNumberOfCredits(int credits) {
    this.numberOfCredits = credits;
  }


  @Override
  public int compareTo(CourseSession that) {

    int compare =
            this.getDepartment().compareTo(that.getDepartment());

    if (compare ==0)
      compare = this.getNumber().compareTo(that.getNumber());
    return compare;
  }
}