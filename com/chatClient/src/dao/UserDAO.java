package dao;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {
	//kiểm tra người dùng tồn tại chưa bằng cách so sánh mật khẩu vs csdl
    public boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT password FROM user WHERE user_name = ?";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            }
        }
        return false;
    }

    public boolean registerUser(String username, String password) throws SQLException {
        String checkQuery = "SELECT * FROM user WHERE user_name = ?";
        String insertQuery = "INSERT INTO user (user_name, password) VALUES (?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                return false; // Username already exists
            } else {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                insertStatement.setString(1, username);
                insertStatement.setString(2, hashedPassword);
                insertStatement.executeUpdate();
                return true;
            }
        }
    }
    
}
