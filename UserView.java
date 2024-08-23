package edu.jsp.view;

import java.util.Scanner;

import edu.jsp.dao.UserDao;
import edu.jsp.dto.User;

public class UserView {

	private static UserDao dao = new UserDao();

	public static void saveUser(Scanner scanner) {
		User user = new User();
		System.out.println("enter the id");
		user.setId(scanner.nextInt());
		System.out.println("enter the name");
		user.setName(scanner.next());
		System.out.println("enter the email");
		user.setEmail(scanner.next());
		System.out.println("enter the password");
		user.setPassword(scanner.next());
		System.out.println("enter the phone");
		user.setPhone(scanner.nextLong());
		System.out.println("enter the role");
		user.setRole(scanner.next());
		if (dao.saveUser(user))
			System.out.println("user object is saved");
		else
			System.out.println("user object is not saved");
	}

	public static void fetchUser(Scanner scanner) {
		System.out.println("enter the id ");
		int id = scanner.nextInt();
		User user = dao.fetchUser(id);
		if (user != null) {
			System.out.println("user id " + user.getId());
			System.out.println("user name " + user.getName());
			System.out.println("user password " + user.getPassword());
			System.out.println("user email " + user.getEmail());
			System.out.println("user role " + user.getRole());
			System.out.println("user phone " + user.getPhone());
		} else
			System.out.println("sorry user is not found");

	}

	public static void deleteUser(Scanner scanner) {
		System.out.println("enter the id ");
		int id = scanner.nextInt();
		boolean result = dao.deleteUser(id);
		if (result)
			System.out.println("user account succcessfully deleted");
		else
			System.out.println("sorry user is not found");
	}

	public static void updateUser(Scanner scanner) {
		System.out.println("enter the id ");
		int id = scanner.nextInt();
		System.out.println("what you need to update");
		System.out.println("1.name");
		System.out.println("2.email");
		System.out.println("3.password");
		System.out.println("4.role");
		System.out.println("5.phone");
		String col_name = "";
		String value = "";
		switch (scanner.nextInt()) {
		case 1 -> {
			col_name = "name";
			System.out.println("enter the new name ");
			value = scanner.next();
		}
		case 2 -> {
			col_name = "email";
			System.out.println("enter the new email ");
			value = scanner.next();
		}
		case 3 -> {
			col_name = "password";
			System.out.println("enter the new password");
			value = scanner.next();
		}
		case 4 -> {
			col_name = "role";
			System.out.println("enter new role");
			value = scanner.next();
		}
		case 5 -> {
			col_name = "phone";
			System.out.println("enter new phone number");
			value = scanner.next();
		}
		}
		System.out.println();
		int result = dao.updateUser(id, col_name, value);
		if (result != 0)
			System.out.println(result + " records updated");
		else
			System.out.println(result + " records updated");
	}

	public static int welcome(Scanner scanner) {
		System.out.println("1.Save user");
		System.out.println("2.Update user");
		System.out.println("3.Fetch User");
		System.out.println("4.Delete User");
		System.out.println("5.exit User");
		return scanner.nextInt();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			switch (welcome(scanner)) {
			case 1 -> saveUser(scanner);
			case 2 -> updateUser(scanner);
			case 3 -> fetchUser(scanner);
			case 4 -> deleteUser(scanner);
			case 5 -> System.exit(0);
			}
		}

	}
}
