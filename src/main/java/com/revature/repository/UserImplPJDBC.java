package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exception.NoNegativeBalanceException;
import com.revature.model.User;

public class UserImplPJDBC implements UserDAO {

	@Override
	public User getUser(long id) {
		User user = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM banking_app.checking_account WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = getUserFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private User getUserFromRS(ResultSet resultSet) throws SQLException {
		return new User(resultSet.getLong("id"), resultSet.getDouble("balance"), resultSet.getString("account_name"),
				resultSet.getString("account_password"));
	}

	@Override
	public User getUser(String name) {
		User user = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM banking_app.checking_account WHERE account_name = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, name);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = getUserFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean updateUser(User u, double n) {

		Connection conn = null;
		PreparedStatement stmt = null;

		final String query = "UPDATE banking_app.checking_account SET balance = ?;";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			if (u.getBalance() + n < 0) {
				throw new NoNegativeBalanceException();
			}
			stmt.setDouble(1, u.getBalance() + n);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (NoNegativeBalanceException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
		return true;
	}

}
