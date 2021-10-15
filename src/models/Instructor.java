package models;

public class Instructor {
	private String id;
	private String name;
	private int minNumOfSlot;
	private int maxNumOfSlot;

	public Instructor() {
	}

	public Instructor(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Instructor(String id, String name, int minNumOfSlots, int maxNumOfSlots) {
		this.id = id;
		this.name = name;
		this.minNumOfSlot = minNumOfSlots;
		this.maxNumOfSlot = maxNumOfSlots;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinNumOfSlot() {
		return minNumOfSlot;
	}

	public void setMinNumOfSlot(int minNumOfSlot) {
		this.minNumOfSlot = minNumOfSlot;
	}

	public int getMaxNumOfSlot() {
		return maxNumOfSlot;
	}

	public void setMaxNumOfSlot(int maxNumOfSlot) {
		this.maxNumOfSlot = maxNumOfSlot;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
