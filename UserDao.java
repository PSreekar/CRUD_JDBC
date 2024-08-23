package edu.jsp.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import edu.jsp.dto.User;

public class UserDao {

	public Connection getConnection() {
		try {
			FileInputStream fileInputStream = new FileInputStream("myDb.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			Class.forName(properties.getProperty("driver"));
			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean saveUser(User user) {
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into user values(?,?,?,?,?,?);");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setLong(3, user.getPhone());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.executeUpdate();
			closeConnection(connection);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public User fetchUser(int id) {
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from User where id =?");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				User user = new User();
				user.setId(result.getInt(1));
				user.setName(result.getString(2));
				user.setPhone(result.getLong(3));
				user.setEmail(result.getString(4));
				user.setPassword(result.getString(5));
				user.setRole(result.getString(6));
				closeConnection(connection);
				return user;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateUser(int id, String col_name, String value) {
		Connection connection = getConnection();
		String qry = "update user set " + col_name + "=? where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setString(1, value);
			preparedStatement.setInt(2, id);
			closeConnection(connection);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean deleteUser(int id) {
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("delete from User where id =?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			closeConnection(connection);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
