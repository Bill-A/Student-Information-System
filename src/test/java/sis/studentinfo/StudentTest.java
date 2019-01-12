package sis.studentinfo;

import org.junit.*;

import static org.junit.Assert.*;

public class StudentTest {
  private static final double GRADE_TOLERANCE = 0.05;

  @Test
  public void canCreate() {
    final String firstStudentName = "Jane Doe";
    final String secondStudentName = "Joe Blow";

    Student firstStudent = new Student(firstStudentName);
    assertEquals(firstStudentName, firstStudent.getName());

    Student secondStudent = new Student(secondStudentName);
    assertEquals(secondStudentName, secondStudent.getName());

    assertEquals(firstStudentName, firstStudent.getName());
  }

  @Test
  public void isFullTime() {
    Student student = new Student("Jane Doe");
    assertFalse(student.isFullTime());
  }

  @Test
  public void canCreateCredits() {
    Student student = new Student("Jane Doe");
    assertEquals(0, student.getCredits());
    student.addCredits(3);
    assertEquals(3, student.getCredits());
    student.addCredits(4);
    assertEquals(7, student.getCredits());
  }

  @Test
  public void canCheckStudentStatus() {
    Student student = new Student("Jane Doe");
    assertEquals(0, student.getCredits());
    assertFalse(student.isFullTime());

    student.addCredits(3);
    assertEquals(3, student.getCredits());
    assertFalse(student.isFullTime());

    student.addCredits(4);
    assertEquals(7, student.getCredits());
    assertFalse(student.isFullTime());

    student.addCredits(5);
    assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
    assertTrue(student.isFullTime());

  }

  @Test
  public void isInState() {
    Student student = new Student("Jane Doe");
    assertFalse(student.isInState());
    student.setState(Student.IN_STATE);
    assertTrue(student.isInState());
    student.setState("MD");
    assertFalse(student.isInState());
  }

  @Test
  public void canCalculateGPA() {
    Student student = new Student("a");
    assertGPA(student, 0.0);
    student.addGrade(Student.Grade.A);
    assertGPA(student, 4.0);
    student.addGrade(Student.Grade.B);
    assertGPA(student, 3.5);
    student.addGrade(Student.Grade.C);
    assertGPA(student, 3.0);
    student.addGrade(Student.Grade.D);
    assertGPA(student, 2.5);
    student.addGrade(Student.Grade.F);
    assertGPA(student, 2.0);
  }

  @Test
  public void canCalculateGPAHonorStudents() {
    assertGPA(createHonorsStudent(), 0.0);
    assertGPA(createHonorsStudent(Student.Grade.A), 5.0);
    assertGPA(createHonorsStudent(Student.Grade.B), 4.0);
    assertGPA(createHonorsStudent(Student.Grade.C), 3.0);
    assertGPA(createHonorsStudent(Student.Grade.D), 2.0);
    assertGPA(createHonorsStudent(Student.Grade.F), 0.0);
  }

  private void assertGPA(Student student, double expectedGPA) {
    assertEquals(expectedGPA, student.getGPA(), GRADE_TOLERANCE);
  }

  private Student createHonorsStudent(Student.Grade grade) {
    Student student = createHonorsStudent();
    student.addGrade(grade);
    return student;
  }

  private Student createHonorsStudent() {
    Student student = new Student("a");
    student.setGradingStrategy(new HonorsGradingStrategy());
    return student;
  }

}
