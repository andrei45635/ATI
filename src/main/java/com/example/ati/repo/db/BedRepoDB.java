package com.example.ati.repo.db;

import com.example.ati.domain.Bed;
import com.example.ati.domain.BedType;
import com.example.ati.repo.IRepo;
import com.example.ati.utils.db.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedRepoDB implements IRepo<Integer, Bed> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<Bed> findAll() {
        List<Bed> beds = new ArrayList<>();
        String query = "SELECT * FROM beds";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int bedID = resultSet.getInt("bedid");
                BedType bedType = BedType.valueOf(resultSet.getString("bedtype"));
                String ventilation = resultSet.getString("ventilation");
                int pacientCNP = resultSet.getInt("pacientcnp");

                Bed bed = new Bed(bedID, bedType, ventilation, pacientCNP);
                beds.add(bed);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return beds;
    }

    @Override
    public Bed save(Bed bed) {
        String query = "INSERT INTO beds(bedid, bedtype, ventilation, pacientcnp) VALUES (?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bed.getId());
            statement.setString(2, String.valueOf(bed.getBedType()));
            statement.setString(3, bed.getVentilation());
            statement.setInt(4, bed.getPacientCNP());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bed;
    }
}
