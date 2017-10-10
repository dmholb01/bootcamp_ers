package com.capital.one.screens;

import java.util.Scanner;

import com.capital.one.daos.DAOUtilities;
import com.capital.one.daos.EmployeeDAO;
import com.capital.one.datamodelbeans.Users;

public class LoginScreen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean authenticated = false;
		Users newUser = new Users();
		EmployeeDAO dao = DAOUtilities.getEmployeeDao();
		Scanner scan = new Scanner(System.in);
		String name="";
		String pass="";
		
		while (!authenticated) {
			System.out.println("Please enter your username:");
			name = scan.nextLine();
			System.out.println("Please enter your password");
			pass = scan.nextLine();
			newUser = dao.loginEmployee(name, pass);
			if (newUser != null) {
				switch (newUser.getUserRoleId()) {
				case 1:
					System.out.println("Would move on to the Employee Menu Screen Now");
					System.out.println(newUser);
					// call Employee Menu Screen .run() method, passing in newUser
					authenticated = true; //will never get here if .run() method took us to next screen
					continue; // would cause to leave loop
				case 2:
					System.out.println("Would move on to the Finance Manager Menu Screen Now");
					System.out.println(newUser);
					// call Finance Manager Menu Screen .run() method, passing in newUser
					authenticated = true; //will never get here if .run() method took us to next screen
					continue; // would cause to leave loop
				default: 
					System.out.println("Something went wrong - Invalid Role ID:" + newUser.getUserRoleId());
					
				}
			} else {
				// null was returned, so authenticated remains false and will be reprompted
			}
		}

	}

}
