package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import models.Employee;

public class main {
	
	public static void main(String[] args) {
		printEmployees(
				"C:\\Users\\shact\\Documents\\GitHub\\SimplePersistance\\people\\simple");
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
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
