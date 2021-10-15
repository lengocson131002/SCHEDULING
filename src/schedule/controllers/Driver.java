package schedule.controllers;

import java.util.ArrayList;

import schedule.models.Class;

public class Driver {
	public static final int POPULATION_SIZE = 9;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.9;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int NUM_OF_ELITE_SCHEDULES = 1;
	private Data data;
	private static int scheduleNumb = 0;
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.data = new Data();
		driver.printAvailableData();
		int generationNumber = 0;
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
		Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
		scheduleNumb = 0;
		System.out.println("> Generate # " + generationNumber++);
		System.out.print("  Schedule # |                           ");
		System.out.print("  Classes [dept, class, room, instructor, meeting-time]       ");
		System.out.println(
				"                                                                           | Fitness      | Conflicts");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		population.getSchedules().forEach(schedule -> {
			System.out.println("       " + driver.scheduleNumb++ + "    |  " + schedule + "    |   "
					+ String.format("%.5f", schedule.getFitness()) + "    |  " + schedule.getNumOfConflicts());
		});
		driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
		while (population.getSchedules().get(0).getFitness() != 1) {
			scheduleNumb = 0;
			System.out.println("> Generate # " + generationNumber++);
			System.out.print("  Schedule # |                           ");
			System.out.print("  Classes [dept, class, room, instructor, meeting-time]       ");
			System.out.println(
					"                                                                           | Fitness      | Conflicts");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			population = geneticAlgorithm.evolve(population).sortByFitness();
			population.getSchedules().forEach(schedule -> {
				System.out.println("       " + driver.scheduleNumb++ + "    |  " + schedule + "    |   "
						+ String.format("%.5f", schedule.getFitness()) + "    |  " + schedule.getNumOfConflicts());
			});
			driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
		}

	}

	private void printScheduleAsTable(Schedule schedule, int generation) {
		ArrayList<Class> classes = schedule.getClasses();
		System.out.println();
		System.out.format("%7s|%20s|%40s|%20s|%20s|%s\n", " Class #", "Dept", "Course (number, max # Of Students)",
				"Rom(capacity)", "Instructor (ID)", "Meetingtime (Time) ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------");
		classes.forEach(x -> {
			System.out.format(String.format("   %02d   ", x.getId()) + "|");
			System.out.format("%20s", x.getDept().getName() + "|");
			System.out.format("%40s", x.getCourse().getName() + ", " + x.getCourse().getNumber() + ", "
					+ x.getCourse().getMaxNumOfStudents() + "|");
			System.out.format("%20s", x.getRoom().getNumber() + ", " + x.getRoom().getSeatingCapacity() + "|");
			System.out.format("%20s", x.getInstructor() + ", " + x.getInstructor().getId() + "|");
			System.out.println(x.getMeetingTime().getId() + "(" + x.getMeetingTime().getTime() + ")");
		});
		if (schedule.getFitness() == 1) {
			System.out.println("> Solution found in " + (generation + 1) + " generation");
		}
	}

	private void printAvailableData() {
		System.out.println("Available Department ==> ");
		data.getDepts().forEach(dept -> {
			System.out.println("name: " + dept.getName() + ", courses: " + dept.getCourses());
		});
		System.out.println("\nAvailable Courses ==> ");
		data.getCourses().forEach(course -> {
			System.out.println("course #" + course.getNumber() + ", name:  " + course.getName() + ", max # of student: "
					+ course.getMaxNumOfStudents() + ", instructors: " + course.getInstructors());
		});
		System.out.println("\nAvailable Rooms ==> ");
		data.getRooms().forEach(room -> {
			System.out.println(
					"room #" + room.getNumber() + ", name:  " + ", max capacity: " + room.getSeatingCapacity());
		});
		System.out.println("\nAvailable Instructors ==> ");
		data.getInstructors().forEach(instructor -> {
			System.out.println("id: " + instructor.getId() + ", name:  " + instructor.getName());
		});
		System.out.println("\nAvailable Meetingtime ==> ");
		data.getMeetingTimes().forEach(meetingtime -> {
			System.out.println("id: " + meetingtime.getId() + ", name:  " + meetingtime.getTime());
		});
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------------------\n");
	}
}
