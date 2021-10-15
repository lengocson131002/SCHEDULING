package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import models.Course;
import models.Class;
import models.Instructor;
import models.MeetingTime;
import models.Room;
import models.Slots;

public class Data {
	private ArrayList<Room> rooms;
	private ArrayList<Instructor> instructors;
	private ArrayList<Course> courses;
	private ArrayList<Class> classes;
	private ArrayList<MeetingTime> meetingTimes;
	private int numberOfClass = 0;
	
	public Data() {initialize();};
	private Data initialize() {
		Room room1 = new Room("001", 35);
		Room room2 = new Room("002", 25);
		Room room3 = new Room("003", 45);
		Room room4 = new Room("004", 45);
		Room room5 = new Room("005", 35);
		Room room6 = new Room("100", 30);
		Room room7 = new Room("101", 30);
		Room room8 = new Room("102", 25);
		Room room9 = new Room("103", 25);
		Room room10 = new Room("104", 45);
		Room room11 = new Room("105", 35);
		Room room12 = new Room("201", 35);
		Room room13 = new Room("202", 45);
		Room room14 = new Room("203", 35);
		Room room15 = new Room("204", 45);
		Room room16 = new Room("205", 45);
		Room room17 = new Room("206", 45);
		Room room18 = new Room("207", 45);
		Room room19 = new Room("208", 45);
		Room room20 = new Room("209", 45);
		rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9, room10,
				room11, room12, room13, room14, room15, room16, room17, room18, room19, room20));
		
		

		meetingTimes = new ArrayList<>();
		meetingTimes.add(new MeetingTime(2, Slots.SLOT_1));
		meetingTimes.add(new MeetingTime(2, Slots.SLOT_2));
		meetingTimes.add(new MeetingTime(2, Slots.SLOT_3));
		meetingTimes.add(new MeetingTime(2, Slots.SLOT_4));
		meetingTimes.add(new MeetingTime(2, Slots.SLOT_5));
		meetingTimes.add(new MeetingTime(2, Slots.SLOT_6));
		meetingTimes.add(new MeetingTime(3, Slots.SLOT_1));
		meetingTimes.add(new MeetingTime(3, Slots.SLOT_2));
		meetingTimes.add(new MeetingTime(3, Slots.SLOT_3));
		meetingTimes.add(new MeetingTime(3, Slots.SLOT_4));
		meetingTimes.add(new MeetingTime(3, Slots.SLOT_5));
		meetingTimes.add(new MeetingTime(3, Slots.SLOT_6));
		meetingTimes.add(new MeetingTime(4, Slots.SLOT_1));
		meetingTimes.add(new MeetingTime(4, Slots.SLOT_2));
		meetingTimes.add(new MeetingTime(4, Slots.SLOT_3));
		meetingTimes.add(new MeetingTime(4, Slots.SLOT_4));
		meetingTimes.add(new MeetingTime(4, Slots.SLOT_5));
		meetingTimes.add(new MeetingTime(4, Slots.SLOT_6));
		meetingTimes.add(new MeetingTime(5, Slots.SLOT_1));
		meetingTimes.add(new MeetingTime(5, Slots.SLOT_2));
		meetingTimes.add(new MeetingTime(5, Slots.SLOT_3));
		meetingTimes.add(new MeetingTime(5, Slots.SLOT_4));
		meetingTimes.add(new MeetingTime(5, Slots.SLOT_5));
		meetingTimes.add(new MeetingTime(5, Slots.SLOT_6));
		meetingTimes.add(new MeetingTime(6, Slots.SLOT_1));
		meetingTimes.add(new MeetingTime(6, Slots.SLOT_2));
		meetingTimes.add(new MeetingTime(6, Slots.SLOT_3));
		meetingTimes.add(new MeetingTime(6, Slots.SLOT_4));
		meetingTimes.add(new MeetingTime(6, Slots.SLOT_5));
		meetingTimes.add(new MeetingTime(6, Slots.SLOT_6));
	
		Instructor instructor1 = new Instructor("I1", "HungLD", 0, 30);
		Instructor instructor2 = new Instructor("I2", "PhuongLHK", 0, 30);
		Instructor instructor3 = new Instructor("I3", "HoangNT", 10, 30);
		Instructor instructor4 = new Instructor("I4", "VanVTT",  0, 30);
		Instructor instructor5 = new Instructor("I5", "BinhNN", 0, 30);
		Instructor instructor6 = new Instructor("I6", "AnNDH", 0, 30);
		Instructor instructor7 = new Instructor("I7", "HuongNTC", 0, 30);
		Instructor instructor8 = new Instructor("I8", "TrucNTT", 0, 30);
		instructors = new ArrayList<>(Arrays.asList(instructor1, instructor2, instructor3, instructor4, instructor5,
				instructor6, instructor7, instructor8));
		
		Course course1 = new Course("HCI201", "Human-Computer Interaction", 30,
				new ArrayList<>(Arrays.asList(instructor1, instructor2)));
		Course course2 = new Course("ITE302c", "Ethics in IT", 35, new ArrayList<>(Arrays.asList(instructor3)));
		Course course3 = new Course("PRN211", "Basic Cross-Platform Application Programming With .NET", 30,
				new ArrayList<>(Arrays.asList(instructor4)));
		Course course4 = new Course("SWE201c", "Introduction to Software Engineering", 45,
				new ArrayList<>(Arrays.asList(instructor3)));
		Course course5 = new Course("SWP391", "Software development project", 20,
				new ArrayList<>(Arrays.asList(instructor6, instructor7, instructor3, instructor2)));
		Course course6 = new Course("SWR302", "Sofeware Requirement", 30,
				new ArrayList<>(Arrays.asList(instructor3, instructor7, instructor4)));
		Course course7 = new Course("SWT301", "Software testing", 30,
				new ArrayList<>(Arrays.asList(instructor3, instructor8, instructor4)));
		Course course8 = new Course("SWD391", "Software Architecture and Design", 30,
				new ArrayList<>(Arrays.asList(instructor2, instructor5, instructor6)));
		courses = new ArrayList<Course>(Arrays.asList(course1, course2, course3, course4, course5, course6, course7,
				course8));
		
		Class class1 = new Class("SE1505", new ArrayList<>(Arrays.asList(course3, course5, course6, course7)));
		Class class2 = new Class("SE1414", new ArrayList<>(Arrays.asList(course1, course4)));
		Class class3 = new Class("SE1506", new ArrayList<>(Arrays.asList(course3, course5, course6, course7)));
		Class class4 = new Class("SE1410", new ArrayList<>(Arrays.asList(course1, course8)));
		Class class5 = new Class("SE1411", new ArrayList<>(Arrays.asList(course1, course8)));
		Class class6 = new Class("SE1507", new ArrayList<>(Arrays.asList(course3, course5, course6, course7)));
		classes = new ArrayList<>(Arrays.asList(class1,class2, class3, class4,class5));
		classes.forEach(x -> numberOfClass += x.getCourses().size());
		
		return this;
	}
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}
	public void setInstructors(ArrayList<Instructor> instructors) {
		this.instructors = instructors;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public ArrayList<Class> getClasses() {
		return classes;
	}
	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}
	public ArrayList<MeetingTime> getMeetingTimes() {
		return meetingTimes;
	}
	public void setMeetingTimes(ArrayList<MeetingTime> meetingTimes) {
		this.meetingTimes = meetingTimes;
	}
	public int getNumberOfClass() {
		return numberOfClass;
	}
	public void setNumberOfClass(int numberOfClass) {
		this.numberOfClass = numberOfClass;
	}
	
	
}
