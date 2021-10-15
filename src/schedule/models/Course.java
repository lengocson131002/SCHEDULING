package schedule.models;

import java.util.ArrayList;

public class Course {
	private String number = null;
	private String name = null;
	private int maxNumOfStudents;
	private ArrayList<Instructor> instructors;

	public Course() {
		super();
	}

	public Course(String number, String name, int maxNumOfStudents, ArrayList<Instructor> instructors) {
		super();
		this.number = number;
		this.name = name;
		this.maxNumOfStudents = maxNumOfStudents;
		this.instructors = instructors;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxNumOfStudents() {
		return maxNumOfStudents;
	}

	public void setMaxNumOfStudents(int maxNumOfStudents) {
		this.maxNumOfStudents = maxNumOfStudents;
	}

	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(ArrayList<Instructor> instructors) {
		this.instructors = instructors;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
