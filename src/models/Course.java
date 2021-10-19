package models;

import java.util.ArrayList;

public class Course {
	private String ID = null;
	private String name = null;
	private int maxNumOfStudents;
	private ArrayList<Instructor> instructors;

	public Course() {
		super();
	}

	public Course(String ID, String name, int maxNumOfStudents, ArrayList<Instructor> instructors) {
		super();
		this.ID = ID;
		this.name = name;
		this.maxNumOfStudents = maxNumOfStudents;
		this.instructors = instructors;
	}

	public String getID() {
		return ID;
	}

	public void getID(String ID) {
		this.ID = ID;
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
	
	public boolean hasInstructor(String instructorID) {
		for(int i = 0 ; i < this.instructors.size(); i++) {
			if(this.instructors.get(i).getId().equalsIgnoreCase(instructorID)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
