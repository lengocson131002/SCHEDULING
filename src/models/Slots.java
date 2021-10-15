package models;

public enum Slots {
	SLOT_1("07:00-08:30"),
	SLOT_2("08:45-10:15"),
	SLOT_3("10:30-12:00"),
	SLOT_4("12:30-14:00"),
	SLOT_5("14:15-15:45"),
	SLOT_6("16:00-17:30");
	private String time;
	Slots(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
