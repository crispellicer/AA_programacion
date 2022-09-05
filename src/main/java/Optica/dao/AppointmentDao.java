package Optica.dao;

import Optica.domain.Appointment;
import Optica.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AppointmentDao {
    private Connection connection;

    public AppointmentDao(Connection connection) {
        this.connection = connection;
    }

    public void addAppointment(User user) throws SQLException {
        String orderSql = "INSERT INTO APPOINTMENTS (USERNAME,DATE,REASON,CREATION_DATE) VALUES (?,?,?)";

        PreparedStatement orderStatement = connection.prepareStatement(orderSql);
        orderStatement.setDate(1, Date.valueOf(LocalDate.now()));
        orderStatement.setString(2, user.getUsername());
        orderStatement.setDate(3, Date.valueOf(LocalDate.now()));
        orderStatement.executeUpdate();
    }

    public void delete(int idAppointment) throws SQLException {
        String orderSql = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID=?";
        PreparedStatement orderStatement = connection.prepareStatement(orderSql);
        orderStatement.setInt(1, idAppointment);
        orderStatement.executeUpdate();
    }

    public ArrayList<Appointment> getUserAppointments(String username) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS WHERE USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ArrayList<Appointment> appointments = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(resultSet.getInt("APPOINTMENT_ID"));
            appointment.setUsername(resultSet.getString("USERNAME"));
            appointment.setDate(resultSet.getDate("DATE"));
            appointment.setCreationDate(resultSet.getDate("CREATION_DATE"));
            appointments.add(appointment);
        }
        return appointments;

    }

    public Appointment getAppointment(int id) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS  WHERE APPOINTMENT_ID =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        Appointment appointment =null;
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            appointment = new Appointment();
            appointment.setId(resultSet.getInt("APPOINTMENT_ID"));
            appointment.setUsername(resultSet.getString("USERNAME"));
            appointment.setDate(resultSet.getDate("DATE"));
            appointment.setCreationDate(resultSet.getDate("CREATION_DATE"));
        }
        return appointment;
    }
}
