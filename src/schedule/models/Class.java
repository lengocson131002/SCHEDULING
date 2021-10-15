package schedule.models;

public class Class {
	private int id;
	private Department dept;
	private Course course;
	private Instructor instructor;
	private MeetingTime meetingTime;
	private Room room;

	public Class(int id, Department dept, Course course) {
		super();
		this.id = id;
		this.dept = dept;
		this.course = course;
	}

	public Class(int id, Department dept, Course course, MeetingTime meetingTime, Room room, Instructor instructor) {
		super();
		this.id = id;
		this.dept = dept;
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

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
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
		return "["+ dept.getName() + ", " + course.getNumber() + ", " +room.getNumber() + ", " + instructor.getId() 
				+ ", " + meetingTime.getId() + "]"; 
	}

}
