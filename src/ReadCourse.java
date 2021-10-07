import java.io.*;
import java.util.*;

//class to import original course data

public class ReadCourse {
//constructs main course list
	public static ArrayList<Course> courses = new ArrayList<Course>();
//method to read data from CSV
	
	public static void readData() {
		try {
			File file = new File("MyUniversityCourses (4).csv");
			Scanner scanner = new Scanner(file);
//ignores column titles
			scanner.nextLine();
//iterates through each row to build course attributes, as long as there is data, and adds it to list of courses
			while(scanner.hasNextLine()) {
				String orgcourses = scanner.nextLine();
				String [] course_attr = orgcourses.split(",");
				Course course = new Course(course_attr[0],course_attr[1],Integer.parseInt(course_attr[2]),Integer.parseInt(course_attr[3]), 
						course_attr[5], Integer.parseInt(course_attr[6]), course_attr[7]);
				courses.add(course);		
			}
			scanner.close();
//catches error and prints stack trace for debugging
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

	}
		
}