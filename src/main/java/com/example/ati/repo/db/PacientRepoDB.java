package com.example.ati.repo.db;

import com.example.ati.domain.Pacient;
import com.example.ati.repo.IRepo;
import com.example.ati.utils.db.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacientRepoDB implements IRepo<Integer, Pacient> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Pacient> findAll() {
        List<Pacient> pacients = new ArrayList<>();
        String query = "SELECT * FROM pacients";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                int pacientID = resultSet.getInt("pacientid");
                int pacientCNP = resultSet.getInt("pacientcnp");
                int pacientAge = resultSet.getInt("pacientage");
                String premature = resultSet.getString("premature");
                String diagnostic = resultSet.getString("diagnostic");
                int severity = resultSet.getInt("severity");

                Pacient pacient = new Pacient(pacientID, pacientCNP, pacientAge, premature, diagnostic, severity);
                pacients.add(pacient);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacients;
    }

    @Override
    public Pacient save(Pacient pacient) {
        String query = "INSERT INTO pacients(pacientid, pacientcnp, pacientage, premature, diagnostic, severity) values (?, ?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pacient.getId());
            statement.setInt(2, pacient.getCNP());
            statement.setInt(3, pacient.getAge());
            statement.setString(4, pacient.getPremature());
            statement.setString(5, pacient.getDiagnostic());
            statement.setInt(6, pacient.getSeverity());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacient;
    }

    public List<Pacient> waitingPacients(){
        List<Pacient> waiting = new ArrayList<>();
        String query = "SELECT * FROM pacients WHERE pacientcnp NOT IN (SELECT pacientcnp FROM beds) ORDER BY severity DESC";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int pacientID = resultSet.getInt("pacientid");
                int pacientCNP = resultSet.getInt("pacientcnp");
                int pacientAge = resultSet.getInt("pacientage");
                String premature = resultSet.getString("premature");
                String diagnostic = resultSet.getString("diagnostic");
                int severity = resultSet.getInt("severity");

                Pacient pacient = new Pacient(pacientID, pacientCNP, pacientAge, premature, diagnostic, severity);
                waiting.add(pacient);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return waiting;
    }
}
