package models;

import java.util.ArrayList;

public class Class {
	private String name;
	private ArrayList<Course> courses;
	
	public Class() {};
	public Class(String name, ArrayList<Course> courses) {
		this.name = name;
		this.courses = courses;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
}
