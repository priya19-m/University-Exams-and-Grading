package com.kce.university.main;
import com.kce.university.model.*;
import com.kce.university.service.*;
import com.kce.university.exception.*;
import java.util.*;

public class UniversityMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UniversityManagementService ums = new UniversityManagementService();
		ExamService es = new ExamService();
		ResultService rs = new ResultService();
		while(true) {
			System.out.println("University Exams & Grading Systems");
			System.out.print("1.Add Course ");
			System.out.print("2.Add Student ");
			System.out.print("3.Create Assessment ");
			System.out.print("4.Schedule Exam ");
			System.out.print("5.Record Marks ");
			System.out.print("6.Publish Grades ");
			System.out.print("7.Generate Transcript ");
			System.out.println("8.Exit ");
			System.out.print("Enter choice: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			try {
				switch(choice) {
				case 1:
					System.out.print("Enter course code: ");
					String code = sc.nextLine();
					System.out.print("Enter course name: ");
					String cname = sc.nextLine();
					ums.addCourse(new Course(code, cname));
					System.out.println("Course added");
					break;
					
				case 2:
					System.out.print("Enter student ID: ");
					int sid = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter student name: ");
					String sname = sc.nextLine();
					ums.addStudent(new Student(sid, sname));
					System.out.println("Student added");
					break;
					
				case 3:
					System.out.print("Enter assessment name: ");
					String aname = sc.nextLine();
					System.out.print("Enter weightage (%): ");
					double weight = sc.nextDouble();
                    System.out.print("Enter max marks: ");
                    double maxMarks = sc.nextDouble();
                    sc.nextLine();
                    es.createAssessment(new Assessment(aname,weight,maxMarks));
					System.out.println("Assessment created");
					break;
					
				case 4:
					System.out.print("Enter course code: ");
					String courseCode = sc.nextLine();
					Course course = ums.getCourseByCode(courseCode);
					System.out.print("Enter exam date (dd-mm-yyyy): ");
					String date = sc.nextLine();
					es.scheduleExam(new ExamSchedule(course,date));
					System.out.println("Exam scheduled");
					break;
					
				case 5:
					System.out.print("Enter student ID: ");
					int stId = sc.nextInt();
					sc.nextLine();
					Student student = ums.getStudentById(stId);
					System.out.print("Enter course code: ");
					String cc = sc.nextLine();
					Course c = ums.getCourseByCode(cc);
					System.out.print("Enter assessment name: ");
					String asname = sc.nextLine();
					
					Assessment assessment = null;
					for(Assessment a:es.getAssessments()) {
						if(a.getName().equalsIgnoreCase(asname)) {
							assessment=a;
							break;
						}
					}
					if(assessment == null) {
						throw new EntityNotFoundException("Assessment not found!");
					}

					System.out.print("Enter marks obtained: ");
					double marks = sc.nextDouble();
					sc.nextLine();
					
					es.recordMarks(new MarkEntry(student,c,assessment,marks));
					System.out.println("Marks recorded.");
					break;
				
				case 6:
					for(Student stu : ums.getAllStudents()) {
						for(Course co : ums.getAllCourses()) {
							try {
								Grade grade = rs.calculateGrade(stu, co, es.getMarkEntries());
								System.out.println(stu.getName() + " in " + co.getCourseName() + "->" + grade);
							}catch(ResultProcessingException ignored) {}
						}
					}
					break;
					
				case 7:
					System.out.print("Enter student ID: ");
					int stuId = sc.nextInt();
					sc.nextLine();
					Student st = ums.getStudentById(stuId);
					Transcript transcript = rs.generateTranscript(st, ums.getAllCourses(), es.getMarkEntries());
					System.out.println(transcript);
					break;
				case 8:
					System.out.println("Exit");
					sc.close();
					return;
					
				default:
					System.out.println("Invalid choice!");
				}
			}catch(Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

}
