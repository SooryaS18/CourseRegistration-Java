import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
//course attributes
	
	private String courseName;
	private String courseId;
	private int maxStudents;
	private int numStudents;
//list of students in a specific course
	
	private ArrayList<Student> listStudents;
	private String instructor;
	private int sectNum;
	private String location;

	Course(String courseName, String courseId, int maxStudents, int numStudents, String instructor,
			int sectNum, String location){
		
		this.courseName = courseName;
		this.courseId = courseId;
		this.maxStudents= maxStudents;
		this.numStudents = numStudents;
		this.listStudents = new ArrayList<Student>();
		this.instructor= instructor;
		this.sectNum = sectNum;
		this.location = location;
	}
//getter and setter methods
	public String getcourseName(){
		return courseName;
	}
	
	public void setcourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getcourseId(){
		return courseId;
	}
	
	public void setcourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public int getmaxStudents(){
		return maxStudents;
	}
	
	public void setmaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	
	public int getnumStudents(){
		return numStudents;
	}
	
	public void setnumStudents(int numStudents) {
		this.numStudents = numStudents;
	}
	
	public ArrayList<Student> getlistStudents() {
		return listStudents;
	}

	public void setlistStudents(ArrayList<Student> listStudents) {
		this.listStudents = listStudents;
	}
	
	public String getinstructor(){
		return instructor;
	}

	public void setinstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public int getsectNum(){
		return sectNum;
	}

	public void setsectNum(int sectNum) {
		this.sectNum = sectNum;
	}
	
	public String getlocation(){
		return location;
	}

	public void setlocation(String location) {
		this.location = location;
	}

//method to add students, accepts student object as an input, and adds them to the list of students and adds 1 to the number of students
	public void addStudent(Student Stu)  {
		listStudents.add(Stu);
		numStudents++;
	}

//removes a student from the course, accepts student as an input and matches student first name. some of these are validated twice, once in the main2 class and again here
	public void dropStudent(Student S) {
		boolean validEntry = false;
		for (Student name : listStudents) {
			if(S.getfirstName().equals(name.getfirstName())) {
				validEntry = true;
			}
			else {
				System.out.println("Student not found");
			}
		}
//removes student and subtracts 1 from the number of students
		if (validEntry) {
			numStudents--;
			listStudents.remove(S);
			System.out.println("Student successfully dropped");
		}
	}

//removes all students, useful for deleting an entire course. removes errors and discrepancies in the program
	public void unenroll(Course C){
		for (Student S : C.listStudents){
			dropStudent(S);
		}
	}
	
//prints all students in the list attribute for a course

	public void getStudents(Course C) { 
		if (listStudents.isEmpty()) {
			System.out.println("There are no students enrolled in this course.");
			}
		for (Student i: C.listStudents) {
			   System.out.println(i);
			}
		}
	
	
//prints general information about a course as a string
	public void CourseInfo(Course C) {
		System.out.println("Course: "+ courseName + "\n"
							+ "Course ID: "+ C.courseId + "\n"
							+ "Instructor: "+ C.instructor + "\n"
							+ "Section Number: "+ C.sectNum + "\n"
							+ "Location: "+ C.location + "\n"
							+ "Max Capacity: "+ C.maxStudents + "\n"
							+ "Number of Enrolled Students: "+ C.numStudents + "\n");
	}
				
}

