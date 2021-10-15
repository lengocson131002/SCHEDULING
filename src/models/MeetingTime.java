package models;

public class MeetingTime {
	private int order;
	private Slots slotOrder;
	public MeetingTime() {
	}
	public MeetingTime(int order, Slots slotOrder) {
		super();
		this.order = order;
		this.slotOrder = slotOrder;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Slots getSlotOrder() {
		return slotOrder;
	}
	public void setSlotOrder(Slots slotOrder) {
		this.slotOrder = slotOrder;
	}
	
	
	
	
}
