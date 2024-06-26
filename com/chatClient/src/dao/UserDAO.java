package dao;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public static boolean saveMessage(int Id_group, String userId, String message) {
        String query = "INSERT INTO messages (Id_group, userID_Name, message) VALUES (?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Id_group);
            stmt.setString(2, userId);
            stmt.setString(3, message);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> getMessages() {
        List<String> messages = new ArrayList<>();
        String query = "SELECT m.message, u.username, m.timestamp FROM messages m JOIN users u ON m.user_id = u.id ORDER BY m.timestamp";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String message = String.format("[%s] %s: %s", rs.getTimestamp("timestamp"), rs.getString("username"), rs.getString("message"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
    
}
