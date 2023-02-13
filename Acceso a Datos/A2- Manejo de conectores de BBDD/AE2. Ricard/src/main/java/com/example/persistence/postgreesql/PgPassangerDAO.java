package com.example.persistence.postgreesql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.models.Passanger;
import com.example.persistence.DAOException;
import com.example.persistence.PassengerDAO;

public class PgPassangerDAO implements PassengerDAO {

    final String GETONE = "SELECT * FROM PASSENGERS WHERE ID = ?";
    final String GETALL = "SELECT * FROM PASSENGERS";
    final String INSERT = "INSERT INTO PASSENGERS VALUES(DEFAULT, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE PASSENGERS SET CAR_ID = ?, NAME = ?, AGE = ?, WEIGHT = ? WHERE ID = ?";
    final String DELETE = "DELETE FROM PASSENGERS WHERE ID = ?";

    private static HashMap<Integer, Passanger> passengers = new HashMap<>();

    private Connection conn;

    public PgPassangerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Passanger> get(int id) throws DAOException {

        try (PreparedStatement statement = conn.prepareStatement(GETONE)) {
            statement.setInt(1, id);

            ResultSet cursor = statement.executeQuery();
            if (cursor.next()) {

                Integer carID = cursor.getInt("CAR_ID");
                String name = cursor.getString("NAME");
                Integer age = cursor.getInt("AGE");
                Integer weight = cursor.getInt("WEIGHT");

                Passanger passenger = new Passanger(id, carID, name, age, weight);
                return Optional.of(passenger);

            } else {
                throw new DAOException("That passanger identifier dosn't exist.");
            }

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL get operation exception", e);
        }
    }

    @Override
    public List<Passanger> getAll() throws DAOException {
        try (PreparedStatement statement = conn.prepareStatement(GETALL)) {

            ResultSet cursor = statement.executeQuery();
            while (cursor.next()) {

                Integer id = cursor.getInt("ID");
                Integer carID = cursor.getInt("CAR_ID");
                String name = cursor.getString("NAME");
                Integer age = cursor.getInt("AGE");
                Integer weight = cursor.getInt("WEIGHT");

                Passanger passenger = new Passanger(id, carID, name, age, weight);
                passengers.put(id, passenger);

            }

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL getAll() method exception", e);
        }
        return new ArrayList<>(passengers.values());
    }

    @Override
    public void insert(Passanger passanger) throws DAOException {
        try (PreparedStatement statement = conn.prepareStatement(INSERT)) {

            statement.setObject(1, passanger.getCar_id());
            statement.setString(2, passanger.getName());
            statement.setObject(3, passanger.getAge());
            statement.setObject(4, passanger.getWeight());

            if (statement.executeUpdate() == 0) {
                throw new DAOException("0 rows inserted into the PostgreeSQL database");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Passanger passanger, String[] params) throws DAOException {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE)) {

            if (passanger.getId() == Integer.parseInt(params[0])) {
                statement.setInt(5, Integer.parseInt(params[0]));
                statement.setInt(1, Integer.parseInt(params[1]));
                statement.setString(2, params[2]);
                statement.setInt(3, Integer.parseInt(params[3]));
                statement.setInt(4, Integer.parseInt(params[4]));
                if (statement.executeUpdate() == 0) {
                    throw new DAOException("0 rows updated into the PostgreeSQL database");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Passanger passanger) throws DAOException {
        try (PreparedStatement statement = conn.prepareStatement(DELETE)) {

            statement.setInt(1, passanger.getId());

            if (statement.executeUpdate() == 0) {
                throw new DAOException("0 rows deleted into the PostgreeSQL database");
            }

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL update exception", e);
        }
    }

}
