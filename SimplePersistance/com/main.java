package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lib.ConsoleIO;
import lib.FileIO;
import models.Employee;

public class main {

	public static String path;

	public static void main(String[] args) {
		path = ConsoleIO.promptForInput("Please enter the folder path to check", false);

//		System.out.println(FindEmployeeById(6).toString());
//		
//		System.out.println(FindEmployeeByLastName("wilcox"));
//		
//		List<Employee> k = FindAllEmployeesByLastName("wilcox");
//		for (Employee employee : k) {
//			System.out.println(employee);
//		}
//		
//		PrintSerializedDetails(path);

//		PrintAllEmployees();
		long before = System.currentTimeMillis();
		PrintSerializedDetails(path);
		System.out.println(System.currentTimeMillis() - before);
		
//		Employee person = GetSerializedEmployee(1);
//		System.out.println(person.toString());
	}

	public static void printPeopleDetails(String path) {
		File dir = new File(path);
		File[] directory = dir.listFiles();
		if (directory != null) {
			for (File emp : directory) {
				try {
					BufferedReader Buffy = new BufferedReader(new FileReader(emp));
					String line;
					while ((line = Buffy.readLine()) != null) {
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void printEmployees(String path) {
		File dir = new File(path);
		File[] directory = dir.listFiles();
		if (directory == null) {
			System.out.println("Empty Directory");
		} else {
			for (File emp : directory) {
				try {
					BufferedReader Buffy = new BufferedReader(new FileReader(emp));
					String line;
					Employee E = null;
					while ((line = Buffy.readLine()) != null) {
						String[] info = line.split(", ");
						int id = Integer.parseInt(info[0]);
						int year = Integer.parseInt(info[3]);

						E = new Employee(id, info[1], info[2], year);
					}
					System.out.println(E.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void AddEmployee(int id, String firstName, String lastName, int hireYear) {
		String longPath = path.concat("\\long");
		Employee person = new Employee(id, firstName, lastName, hireYear);
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			if (thing.getName().contains(id + ".txt")) {
				System.out.println("Employee record already exists");
				return;
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(longPath.concat("\\" + id + ".txt"));
			fw.write(person.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void DeleteEmployee(int id) {
		String longPath = path.concat("\\long");
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			if (thing.getName().contains(id + ".txt")) {
				// but why is it so easy to delete D:
				thing.delete();
			}
		}
	}

	public static void UpdateEmployee(int id, String firstName, String lastName, int hireYear) {
		String longPath = path.concat("\\long");
		Employee person = new Employee(id, firstName, lastName, hireYear);
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			if (thing.getName().contains(id + ".txt")) {
				boolean idMatch = false;
				BufferedReader Buffy;
				try {
					Buffy = new BufferedReader(new FileReader(thing));
					String line;
					Employee E = null;
					while ((line = Buffy.readLine()) != null) {
						String[] info = line.split(", ");
						int ID = Integer.parseInt(info[0]);
						if (id == ID) {
							// they're the same so you should update the file
							idMatch = true;
						}
					}
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}

				if (!idMatch) {
					System.out.println("The employee ID was invalid (can't change it bub) How about we not <3?");
				} else {
					FileWriter fw;
					try {
						fw = new FileWriter(longPath.concat("\\" + id + ".txt"));
						fw.write(person.toString());
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				return;
			}
		}
		System.out.println("Sorry, couldn't find your file");
	}

	public static void SerializeAllEmployees() {
		String longPath = path.concat("\\long");
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			try {
				BufferedReader Buffy = new BufferedReader(new FileReader(thing));
				String line;
				Employee E = null;
				int id = 0;
				while ((line = Buffy.readLine()) != null) {
					String[] info = line.split(", ");
					id = Integer.parseInt(info[0]);
					int year = Integer.parseInt(info[3]);
					E = new Employee(id, info[1], info[2], year);
				}
				FileIO.write(E, longPath.concat(" serialized\\" + id + ".ser"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static Employee GetSerializedEmployee(int id) {
		String longPath = path.concat("\\long serialized\\" + id + ".ser");
		Employee person = (Employee) FileIO.read(longPath);
		return person;
	}

	public static Employee FindEmployeeById(int id) {
		String longPath = path.concat("\\long");
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			if (thing.getName().matches(id + ".txt")) {
				BufferedReader Buffy;
				try {
					Buffy = new BufferedReader(new FileReader(thing));
					String line;
					while ((line = Buffy.readLine()) != null) {
						String[] info = line.split(", ");
						int ID = Integer.parseInt(info[0]);
						int year = Integer.parseInt(info[3]);
						return new Employee(ID, info[1], info[2], year);
					}
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("File not found");
		return null;
	}

	public static Employee FindEmployeeByLastName(String lastName) {
		String longPath = path.concat("\\long");
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			BufferedReader Buffy;
			try {
				Buffy = new BufferedReader(new FileReader(thing));
				String line;
				while ((line = Buffy.readLine()) != null) {
					String[] info = line.split(", ");
					int ID = Integer.parseInt(info[0]);
					int year = Integer.parseInt(info[3]);
					if(info[2].toLowerCase().matches(lastName.toLowerCase())) {						
						return new Employee(ID, info[1], info[2], year);
					}
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("File not found");
		return null;
	}

	public static List<Employee> FindAllEmployeesByLastName(String lastName) {
		String longPath = path.concat("\\long");
		List<Employee> list = new ArrayList<Employee>();
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			BufferedReader Buffy;
			try {
				Buffy = new BufferedReader(new FileReader(thing));
				String line;
				while ((line = Buffy.readLine()) != null) {
					String[] info = line.split(", ");
					int ID = Integer.parseInt(info[0]);
					int year = Integer.parseInt(info[3]);
					if(info[2].toLowerCase().matches(lastName.toLowerCase())) {						
						list.add(new Employee(ID, info[1], info[2], year));
					}
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	public static void PrintSerializedDetails(String path) {
		String longPath = path;
		if (!path.contains("long serialized")) {
			longPath = path.concat("\\long serialized");
		}
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			String filePath = longPath.concat("\\"+thing.getName());
			Employee person = (Employee) FileIO.read(filePath);
			System.out.println(person.toString());
		}
	}

	public static HashMap<Integer, Employee> GetAllEmployees(String path) {
		String longPath = path;
		if (!path.contains("long serialized")) {
			longPath = path.concat("\\long serialized");
		}
		HashMap<Integer, Employee> emps = new HashMap<Integer, Employee>();
		File directoy = new File(longPath);
		File[] dir = directoy.listFiles();
		for (File thing : dir) {
			String filePath = longPath.concat("\\"+thing.getName());
			Employee person = (Employee) FileIO.read(filePath);
			emps.put(person.getId(), person);
		}
		return emps;
	}

	public static void PrintAllEmployees() {
		HashMap<Integer, Employee> emps = GetAllEmployees(path);

		for (Employee employee : emps.values()) {
			System.out.println(employee.toString());
		}
	}

}
