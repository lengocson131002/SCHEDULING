package models;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import controllers.Data;
import controllers.Driver;
import controllers.GeneticAlgorithm;
import controllers.Population;
import controllers.Schedule;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.util.stream.IntStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.LineBorder;

public class Schedule_View extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tableSchedule;
	private DefaultTableModel scheduleModel;
	private DefaultTableModel classModel;
	private DefaultTableModel roomModel;
	private DefaultTableModel instructorModel;
	private DefaultTableModel courseModel;
	public static final int POPULATION_SIZE = 9;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.9;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int NUM_OF_ELITE_SCHEDULES = 1;
	public static final int NUMBER_OF_SLOT_PER_WEEK = 3;
	private static Data data;
	private static GeneticAlgorithm geneticAlgorithm;
	private static Population population;
	private JLabel currentClass;
	private static boolean loaded = false;

	private static JLabel statusLoading;
	private JTable tableClasses;
	private JTable tableInstructors;
	private JTable tableCourses;
	private JTable tableRooms;

	class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

		public MultiLineCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		data = new Data();
		geneticAlgorithm = new GeneticAlgorithm(data);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Schedule_View frame = new Schedule_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		population = new Population(Driver.POPULATION_SIZE, data).sortByFitness();

		// LOAD schedule
		while (population.getSchedules().get(0).getFitness() != 1) {
			population = geneticAlgorithm.evolve(population).sortByFitness();
		}

		// Loaded
		loaded = true;
		resetStatus();
	}

	private static void resetStatus() {
		statusLoading.setText("DONE");
	}

	/**
	 * Create the frame.
	 */
	public Schedule_View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1469, 788);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);

		MultiLineCellRenderer render = new MultiLineCellRenderer();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1436, 735);
		contentPane.add(tabbedPane);

		JPanel panelClass = new JPanel();
		tabbedPane.addTab("Classes", null, panelClass, null);
		panelClass.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(52, 33, 666, 639);
		panelClass.add(scrollPane_1);

		tableClasses = new JTable();
		tableClasses.setEnabled(false);
		tableClasses.setRowHeight(50);
		classModel = new DefaultTableModel();
		classModel.setColumnIdentifiers(new Object[] { "CLASS NAME", "COURSES" });
		tableClasses.setModel(classModel);
		tableClasses.setDefaultRenderer(Object.class, centerRender);
		JTableHeader classHeader = tableClasses.getTableHeader();
		classHeader.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane_1.setViewportView(tableClasses);
		loadClasses();

		JPanel panelnstructor = new JPanel();
		tabbedPane.addTab("Instructors", null, panelnstructor, null);
		panelnstructor.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(49, 34, 892, 620);
		panelnstructor.add(scrollPane_2);

		tableInstructors = new JTable();
		tableInstructors.setEnabled(false);
		tableInstructors.setRowHeight(50);
		instructorModel = new DefaultTableModel();
		instructorModel.setColumnIdentifiers(new Object[] { "INSTRUCTOR ID", "INSTRUCTOR NAME", "COURSES",
				"MIN NUMBER OF SLOT", "MAX NUMBER OF SLOT" });
		tableInstructors.setModel(instructorModel);
		JTableHeader instructorHeader = tableInstructors.getTableHeader();
		instructorHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_2.setViewportView(tableInstructors);
		for (int i = 0; i < instructorModel.getColumnCount(); i++) {
			if (i == 2) {
				tableInstructors.getColumnModel().getColumn(i).setPreferredWidth(300);
			}
			tableInstructors.getColumnModel().getColumn(i).setCellRenderer(centerRender);

		}
		loadInstructor();

		JPanel panelCourse = new JPanel();
		tabbedPane.addTab("Courses", null, panelCourse, null);
		panelCourse.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(51, 33, 969, 589);
		panelCourse.add(scrollPane_3);

		tableCourses = new JTable();
		scrollPane_3.setViewportView(tableCourses);
		tableCourses.setEnabled(false);
		tableCourses.setRowHeight(50);
		courseModel = new DefaultTableModel();
		courseModel.setColumnIdentifiers(
				new Object[] { "COURSE ID", "COURSE NAME", "MAX NUMBER OF STUDENT", "INSTRUCTORS" });
		tableCourses.setModel(courseModel);
		JTableHeader courseHeader = tableCourses.getTableHeader();
		courseHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		for (int i = 0; i < courseModel.getColumnCount(); i++) {
			if (i == 1) {
				tableCourses.getColumnModel().getColumn(i).setPreferredWidth(300);
			} else if (i == 3) {
				tableCourses.getColumnModel().getColumn(i).setPreferredWidth(100);
			}
			tableCourses.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}
		loadCourses();

		JPanel panelRoom = new JPanel();
		tabbedPane.addTab("Rooms", null, panelRoom, null);
		panelRoom.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(45, 40, 474, 630);
		panelRoom.add(scrollPane_4);

		tableRooms = new JTable();
		tableRooms.setEnabled(false);
		tableRooms.setRowHeight(50);
		roomModel = new DefaultTableModel();
		roomModel.setColumnIdentifiers(new Object[] { "ROOM NAME", "CAPACITY" });
		JTableHeader roomHeader = tableCourses.getTableHeader();
		roomHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		tableRooms.setModel(roomModel);
		for (int i = 0; i < roomModel.getColumnCount(); i++) {
			tableRooms.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}

		scrollPane_4.setViewportView(tableRooms);
		loadRooms();

		JPanel Schedule = new JPanel();
		tabbedPane.addTab("Schedule", null, Schedule, null);
		Schedule.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollPane.setBounds(113, 61, 1131, 623);
		Schedule.add(scrollPane);

		tableSchedule = new JTable();
		tableSchedule.setRowSelectionAllowed(false);
		tableSchedule.setRowHeight(100);
		tableSchedule.setEditingRow(-1);
		tableSchedule.setEditingColumn(-1);

		scheduleModel = new DefaultTableModel();
		scheduleModel.setColumnIdentifiers(
				new Object[] { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY" });

		resetTableSchedule();
		JTableHeader headerSchedule = tableSchedule.getTableHeader();
		headerSchedule.setFont(new Font("Tahoma", Font.BOLD, 13));
		tableSchedule.setModel(scheduleModel);
		scrollPane.setViewportView(tableSchedule);

		for (int i = 0; i < 7; i++) {
			tableSchedule.getColumnModel().getColumn(i).setCellRenderer(render);
		}

		tableSchedule.setDefaultRenderer(String.class, render);
		JComboBox<String> classesDropList = new JComboBox<>();
		classesDropList.setBounds(1254, 61, 154, 31);
		Schedule.add(classesDropList);
		data.getClasses().forEach(x -> {
			classesDropList.addItem(x.getName());
		});

		JButton viewScheduleButton = new JButton("VIEW");
		viewScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loaded) {
					loadSchedule((String) classesDropList.getSelectedItem());
				}
			}
		});
		viewScheduleButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		viewScheduleButton.setBounds(1285, 117, 93, 31);
		Schedule.add(viewScheduleButton);

		statusLoading = new JLabel("LOADING...");
		statusLoading.setFont(new Font("Tahoma", Font.BOLD, 13));
		statusLoading.setVerticalAlignment(SwingConstants.BOTTOM);
		statusLoading.setBounds(1254, 642, 100, 24);
		Schedule.add(statusLoading);

		JLabel lblSlot_6 = new JLabel("SLOT 1");
		lblSlot_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSlot_6.setOpaque(true);
		lblSlot_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlot_6.setForeground(SystemColor.menu);
		lblSlot_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSlot_6.setBackground(SystemColor.controlDkShadow);
		lblSlot_6.setBounds(13, 81, 115, 100);
		Schedule.add(lblSlot_6);

		JLabel lblSlot_3 = new JLabel("SLOT 5");
		lblSlot_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSlot_3.setOpaque(true);
		lblSlot_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlot_3.setForeground(SystemColor.menu);
		lblSlot_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSlot_3.setBackground(SystemColor.controlDkShadow);
		lblSlot_3.setBounds(13, 481, 100, 100);
		Schedule.add(lblSlot_3);

		JLabel lblSlot_1 = new JLabel("SLOT 3");
		lblSlot_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSlot_1.setOpaque(true);
		lblSlot_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlot_1.setForeground(SystemColor.menu);
		lblSlot_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSlot_1.setBackground(SystemColor.controlDkShadow);
		lblSlot_1.setBounds(13, 281, 100, 100);
		Schedule.add(lblSlot_1);

		JLabel lblSlot_4 = new JLabel("SLOT 6");
		lblSlot_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSlot_4.setOpaque(true);
		lblSlot_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlot_4.setForeground(SystemColor.menu);
		lblSlot_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSlot_4.setBackground(SystemColor.controlDkShadow);
		lblSlot_4.setBounds(13, 581, 100, 100);
		Schedule.add(lblSlot_4);

		JLabel lblNewLabel_1 = new JLabel("CLASS: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(519, 10, 93, 41);
		Schedule.add(lblNewLabel_1);

		JLabel lblSlot = new JLabel("SLOT 2");
		lblSlot.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSlot.setOpaque(true);
		lblSlot.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlot.setForeground(SystemColor.menu);
		lblSlot.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSlot.setBackground(SystemColor.controlDkShadow);
		lblSlot.setBounds(13, 181, 100, 100);
		Schedule.add(lblSlot);

		currentClass = new JLabel("");
		currentClass.setHorizontalAlignment(SwingConstants.CENTER);
		currentClass.setOpaque(true);
		currentClass.setBackground(new Color(135, 206, 235));
		currentClass.setFont(new Font("Tahoma", Font.BOLD, 20));
		currentClass.setBounds(622, 10, 154, 41);
		Schedule.add(currentClass);

		JLabel lblSlot_2 = new JLabel("SLOT 4");
		lblSlot_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSlot_2.setOpaque(true);
		lblSlot_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlot_2.setForeground(SystemColor.menu);
		lblSlot_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSlot_2.setBackground(SystemColor.controlDkShadow);
		lblSlot_2.setBounds(13, 381, 100, 100);
		Schedule.add(lblSlot_2);
	}

	private void loadRooms() {
		for (int i = 0; i < data.getRooms().size(); i++) {
			Room currRoom = data.getRooms().get(i);
			roomModel.addRow(new String[] { currRoom.getNumber(), currRoom.getSeatingCapacity() + "" });

		}
	}

	private void loadCourses() {
		for (int i = 0; i < data.getCourses().size(); i++) {
			String classes = new String();
			Course currCourse = data.getCourses().get(i);
			for (int j = 0; j < data.getClasses().size(); j++) {
				Class currClass = data.getClasses().get(j);
				if (currClass.hasCourse(currCourse.getID())) {
					classes += currClass.getName() + ", ";
				}
			}
			courseModel.addRow(new String[] { currCourse.getID(), currCourse.getName(),
					currCourse.getMaxNumOfStudents() + " ", classes });
		}
	}

	private void loadInstructor() {
		int numOfInstructor = data.getInstructors().size();
		for (int i = 0; i < numOfInstructor; i++) {
			String courses = new String();
			int numOfCourses = data.getCourses().size();
			Instructor currInstructor = data.getInstructors().get(i);
			for (int j = 0; j < numOfCourses; j++) {
				Course currCourse = data.getCourses().get(j);
				if (currCourse.hasInstructor(currInstructor.getId())) {
					courses += currCourse.getID() + ", ";
				}
			}
			instructorModel.addRow(new String[] { currInstructor.getId(), currInstructor.getName(), courses,
					currInstructor.getMinNumOfSlot() + "", currInstructor.getMaxNumOfSlot() + "" });

		}
	}

	private void loadClasses() {
		int numOfClasses = data.getClasses().size();
		for (int i = 0; i < numOfClasses; i++) {
			String courses = new String();
			Class currClass = data.getClasses().get(i);
			int numOfCourses = data.getClasses().get(i).getCourses().size();
			for (int j = 0; j < numOfCourses; j++) {
				if (j == currClass.getCourses().size() - 1) {
					courses += currClass.getCourses().get(j).getID();
				} else {
					courses += currClass.getCourses().get(j).getID() + ", ";
				}
			}
			classModel.addRow(new String[] { currClass.getName(), courses });
		}
	}

	private void resetTableSchedule() {
		scheduleModel.setRowCount(0);
		for (int i = 0; i < 6; i++) {
			scheduleModel.addRow(new String[] { "", "", "", "", "", "", "" });
		}
	}

	private void loadSchedule(String className) {
		resetTableSchedule();
		currentClass.setText(className);
		Schedule schedule = population.getSchedules().get(0);
		schedule.getSlots().forEach(slot -> {
			if (slot.getCl().getName().equalsIgnoreCase(className)) {
				int col = slot.getMeetingTime().getOrder() - 2;
				int row = 0;
				Slots s = slot.getMeetingTime().getSlotOrder();
				switch (s) {
				case SLOT_1:
					row = 0;
					break;
				case SLOT_2:
					row = 1;
					break;
				case SLOT_3:
					row = 2;
					break;
				case SLOT_4:
					row = 3;
					break;
				case SLOT_5:
					row = 4;
					break;
				case SLOT_6:
					row = 5;
					break;
				}
				String content = String.format("%s\n%s\n%s\n%s", slot.getCourse().getName(), slot.getInstructor(),
						"Room " + slot.getRoom().getNumber(), "(" + s.getTime() + ")");
				scheduleModel.setValueAt(content, row, col);
			}
		});
	}
}
