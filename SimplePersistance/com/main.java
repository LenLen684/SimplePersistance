package com;

import java.io.*;

public class main {
	
	public static void main(String[] args) {
		printPeopleDetails(
				"C:\\Users\\ninja\\Documents\\Assignments\\Fall 2020\\Database 2, Electric Boogaloo\\Simple Pesistance\\People Records\\simple");
	}
	
	public static void printPeopleDetails(String path) {
		File dir = new File(path);
		File[] directory = dir.listFiles();
		if (directory == null) {
			System.out.println("Empty Directory");
		} else {
			for (File emp : directory) {
				try {
					BufferedReader Buffy = new BufferedReader(new FileReader(emp));
					String line;
					while ((line = Buffy.readLine()) != null) {
					       System.out.println(line);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void printEmployees(String path) {
		
	}

}
