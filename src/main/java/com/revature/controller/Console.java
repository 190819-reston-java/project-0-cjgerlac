package com.revature.controller;

import java.util.Arrays;
import java.util.Scanner;

import com.revature.model.User;
import com.revature.service.Services;

public class Console {

	public static Scanner sc = new Scanner(System.in);
	public static Services servicio = new Services();

	public static void accountLoginMenu() {

		boolean authenticated = false;

		while (authenticated == false) {
			Arrays.asList("Welcome to the banking app!", "Please input your account name", "or type exit to exit", "").forEach((String s) -> {
				System.out.println(s);
			});

			String userInput = sc.nextLine();
			if (userInput.equals("exit")) {
				System.exit(0);
			}
			if (servicio.verifyUserId(userInput) == true) {
				authenticated = true;
				servicio.setU(servicio.getUserDAO().getUser(userInput));
			}
		}
		passwordLoginMenu(servicio.getU());
	}

	private static void passwordLoginMenu(User u) {

		boolean authenticated = false;

		while (authenticated == false) {
			Arrays.asList("Please input your password:", "").forEach((String s) -> {
				System.out.println(s);
			});

			String userInput = sc.nextLine();
			if (servicio.verifyUserPassword(userInput) == true) {
				authenticated = true;
			}
		}
		System.out.println("Successfully logged in!");
		menu(u);
	}

	public static void menu(User u) {
		Arrays.asList("Type 1 to check your balance.", "Type 2 to make a deposit.",
				"Type 3 to make a withdrawal.", "Type 4 to logout.", "").forEach((String s) -> {
					System.out.println(s);
				});
		String userInput = sc.nextLine();

		switch (userInput) {

		case "1":
			servicio.displayBalance();
			break;

		case "2":
			System.out.println("Enter deposit amount");
			String depositInput = sc.nextLine();
			servicio.checkValidDepositAmount(depositInput);

			break;

		case "3":
			System.out.println("Enter withdrawal amount");
			String withdrawalAmount = sc.nextLine();
			servicio.withdrawalAmount(withdrawalAmount);
			break;

		case "4":
			System.out.println("Successfully logged out!");
			u = null;
			servicio = new Services();
			accountLoginMenu();
		}
		menu(u);
	}
}