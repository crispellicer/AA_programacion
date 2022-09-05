package Optica.dao;

import Optica.domain.User;
import Optica.exception.UserAlreadyExistsException;
import Optica.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {this.connection=connection;}

    public void add(User user) throws SQLException, UserAlreadyExistsException {
        if (existUser(user.getUsername()))
            throw new UserAlreadyExistsException();

        String sql = "INSERT INTO USERS (USERNAME, PASSWORD, ADDRESS, EMAIL, TELEPHONE) VALUES (?, ?, ? ,? ,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getAddress());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getTelephone());

        statement.executeUpdate();
    }

    public Optional<User> getUser (String username, String password) throws SQLException, UserNotFoundException {
        if (!existUser(username))
            throw new UserNotFoundException();

        String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        User user = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, username);
        st.setString(2, password);

        ResultSet res = st.executeQuery();
        while (res.next()){
            user = new User();
            user.setUsername(res.getString("USERNAME"));
            user.setPassword(res.getString("PASSWORD"));
            user.setAddress(res.getString("ADDRESS"));
            user.setEmail(res.getString("EMAIL"));
            user.setTelephone(res.getString("TELEPHONE"));
        }
        return Optional.ofNullable(user);
    }

    public boolean delete(String username) throws SQLException, UserNotFoundException {
        if (!existUser(username))
            throw new UserNotFoundException();

        String sql = "DELETE FROM USERS WHERE USERNAME = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        int rows = statement.executeUpdate();

        return rows == 1;
    }

    public boolean modifyApp(String userName, User newUser) throws SQLException {

        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, ADDRESS = ?, EMAIL = ?, TELEPHONE = ? WHERE USERNAME = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newUser.getUsername());
        statement.setString(2, newUser.getPassword());
        statement.setString(3, newUser.getAddress());
        statement.setString(4, newUser.getEmail());
        statement.setString(5, newUser.getTelephone());
        statement.setString(6, userName);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public Optional<User> findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
        User user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User();
        }
        return Optional.ofNullable(user);
    }

    public boolean existUser(String username) throws SQLException {
        Optional<User> user = findByUsername(username);
        return user.isPresent();
    }
}
