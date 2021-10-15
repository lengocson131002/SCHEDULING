package schedule.controllers;

import java.util.ArrayList;
import schedule.models.Class;

public class Schedule{
	private ArrayList<Class> classes;
	
	private int classNumb = 1;
	
	private boolean isFitnessChanged = true;
	
	private double fitness = -1;
	
	private int numOfConflicts = 0;
	
	private Data data;

	public Schedule(Data data) {
		this.data = data;
		classes = new ArrayList<>(data.getNumberOfClass());
		this.initialize();
	}

	private Schedule initialize() {
		data.getDepts().forEach(dept -> {
			dept.getCourses().forEach(course -> {
				Class newClass = new Class(classNumb++, dept, course);
				newClass.setMeetingTime(
						data.getMeetingTimes().get((int) (Math.random() * data.getMeetingTimes().size())));
				newClass.setRoom(data.getRooms().get((int) (Math.random() * data.getRooms().size())));
				newClass.setInstructor(course.getInstructors().get((int)(Math.random()* course.getInstructors().size())));
				classes.add(newClass);
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
	
	public ArrayList<Class> getClasses(){
		isFitnessChanged = true;
		return this.classes;
	}
	
	public double getFitness() {
		if(isFitnessChanged == true)  {
			fitness = calculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}
	
	private double calculateFitness() {
		numOfConflicts = 0;
		classes.forEach(x -> {
			if(x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumOfStudents()) {
				 numOfConflicts++;
			}
			classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
				if(x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
					if(x.getRoom() == y.getRoom()) numOfConflicts++;
					if(x.getInstructor() == y.getInstructor()) numOfConflicts++;
				}
			});
		});
		return 1.0 /(numOfConflicts + 1);
	}

	@Override
	public String toString() {
		String result = new String();
		for(int i = 0; i < classes.size() - 1; i++) {
			result += classes.get(i) + ",";
		}
		result += classes.get(classes.size() - 1);
		return result;
	}

}
