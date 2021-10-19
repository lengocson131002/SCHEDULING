package models;

public class Slot {
	private int id;
	private Class cl;
	private Course course;
	private Instructor instructor;
	private MeetingTime meetingTime;
	private Room room;

	public Slot(int id, Class cl, Course course) {
		super();
		this.id = id;
		this.cl = cl;
		this.course = course;
	}

	public Slot(int id, Class cl, Course course, MeetingTime meetingTime, Room room, Instructor instructor) {
		this.id = id;
		this.cl = cl;
		this.course = course;
		this.meetingTime = meetingTime;
		this.room = room;
		this.instructor = instructor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Class getCl() {
		return cl;
	}

	public void setCl(Class cl) {
		this.cl = cl;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public MeetingTime getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(MeetingTime meetingTime) {
		this.meetingTime = meetingTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "["+ cl.getName() + ", " + course.getID() + ", " +room.getNumber() + ", " + instructor.getId() 
				+ ", " + meetingTime.getOrder() + "-" + meetingTime.getSlotOrder() + "]"; 
	}

}
