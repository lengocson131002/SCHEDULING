package controllers;

import java.util.ArrayList;
import models.Slot;

public class Schedule {
	private ArrayList<Slot> slots;

	private int slotNumb = 1;

	private boolean isFitnessChanged = true;

	private double fitness = -1;

	private int numOfConflicts = 0;

	private Data data;

	public Schedule(Data data) {
		this.data = data;
		slots = new ArrayList<>(data.getNumberOfClass());
		this.initialize();
	}

	private Schedule initialize() {
		data.getClasses().forEach(cl -> {
			cl.getCourses().forEach(course -> {

				// Create follow number of slot per course
				for (int i = 0; i < Driver.NUMBER_OF_SLOT_PER_WEEK; i++) {
					Slot newSlot = new Slot(slotNumb++, cl, course);
					newSlot.setMeetingTime(
							data.getMeetingTimes().get((int) (Math.random() * data.getMeetingTimes().size())));
					newSlot.setRoom(data.getRooms().get((int) (Math.random() * data.getRooms().size())));
					newSlot.setInstructor(
							course.getInstructors().get((int) (Math.random() * course.getInstructors().size())));
					slots.add(newSlot);
				}
			});
		});
		return this;
	}

	public Data getData() {
		return this.data;
	};

	public int getNumOfConflicts() {
		return this.numOfConflicts;
	}

	public ArrayList<Slot> getSlots() {
		isFitnessChanged = true;
		return this.slots;
	}

	public double getFitness() {
		if (isFitnessChanged == true) {
			fitness = calculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	private double calculateFitness() {
		System.out.println("------------------------CONFLICTS CONTENT-----------------------------------------------");
		numOfConflicts = 0;
		int[] numOfSlotsPerInstructor = new int[data.getInstructors().size()];

		slots.forEach(x -> {
			numOfSlotsPerInstructor[data.getInstructors().indexOf(x.getInstructor())]++;

			if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumOfStudents()) {
				numOfConflicts++;
				System.out.println("Conflict in capacity: " + numOfConflicts);
			}

			slots.stream().filter(y -> slots.indexOf(y) >= slots.indexOf(x)).forEach(y -> {
				if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
					if (x.getCl() == y.getCl()) {
						numOfConflicts++;
						System.out.println("Conflict in class: " + numOfConflicts);
					}
					if (x.getRoom() == y.getRoom()) {
						numOfConflicts++;
						System.out.println("Conflict in room : " + numOfConflicts);
					}
					if (x.getInstructor() == y.getInstructor()) {
						numOfConflicts++;
						System.out.println("Conflict in instructor: " + numOfConflicts);
					}
				}
			});

			slots.stream().filter(y -> slots.indexOf(y) >= slots.indexOf(x) && y.getCl() == x.getCl()).forEach(y -> {
				if (y.getCourse() == x.getCourse() && y.getInstructor() != x.getInstructor()) {
					numOfConflicts++;
					System.out.println("Conflict in number of slot per instructor: " + numOfConflicts);
				}
			});
		});

		for (int i = 0; i < numOfSlotsPerInstructor.length; i++) {
			if (numOfSlotsPerInstructor[i] > data.getInstructors().get(i).getMaxNumOfSlot()
					|| numOfSlotsPerInstructor[i] < data.getInstructors().get(i).getMinNumOfSlot()) {
				numOfConflicts++;
			}
		}
		System.out.println("-----------------------------------------------------------------------");
		return 1.0 / (numOfConflicts + 1);
	}

	@Override
	public String toString() {
		String result = new String();
		for (int i = 0; i < slots.size() - 1; i++) {
			result += slots.get(i) + ",";
		}
		result += slots.get(slots.size() - 1);
		return result;
	}

}
