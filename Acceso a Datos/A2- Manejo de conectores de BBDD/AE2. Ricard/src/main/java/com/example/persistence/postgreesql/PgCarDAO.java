package com.example.persistence.postgreesql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.persistence.CarDAO;
import com.example.persistence.DAOException;
import com.example.models.Car;

public class PgCarDAO implements CarDAO {

    final String GETONE = "SELECT * FROM CARS WHERE ID = ?";
    final String GETALL = "SELECT * FROM CARS";
    final String INSERT = "INSERT INTO CARS VALUES(DEFAULT, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE CARS SET REG = ?, BRAND = ?, MODEL = ?, COLOR = ? WHERE ID = ?";
    final String DELETE = "DELETE FROM CARS WHERE ID = ?";

    private static HashMap<Integer, Car> cars = new HashMap<>();

    private Connection conn;

    public PgCarDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Car> get(int id) throws DAOException {

        try (PreparedStatement statement = conn.prepareStatement(GETONE)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {

                String reg = rs.getString("REG");
                String brand = rs.getString("BRAND");
                String model = rs.getString("MODEL");
                String color = rs.getString("COLOR");

                Car car = new Car(id, reg, brand, model, color);
                return Optional.of(car);

            } else {
                throw new DAOException("That car identifier dosn't exist.");
            }

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL get operation exception", e);
        }
    }

    @Override
    public List<Car> getAll() throws DAOException {

        try (PreparedStatement statement = conn.prepareStatement(GETALL)) {

            ResultSet cursor = statement.executeQuery();
            while (cursor.next()) {

                Integer id = Integer.valueOf(cursor.getInt("id"));
                String reg = cursor.getString("reg");
                String brand = cursor.getString("brand");
                String model = cursor.getString("model");
                String color = cursor.getString("color");

                cars.put(id, new Car(id, reg, brand, model, color));
            }

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL getAll() method exception", e);
        }
        return new ArrayList<>(cars.values());
    }

    @Override
    public void insert(Car car) throws DAOException {

        try (PreparedStatement statement = conn.prepareStatement(INSERT)) {

            statement.setString(1, car.getReg());
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getColor());


            if (statement.executeUpdate() == 0) {
                throw new DAOException("0 rows inserted into the PostgreeSQL database");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void update(Car car, String[] params) throws DAOException {

        try (PreparedStatement statement = conn.prepareStatement(UPDATE)) {

            if (car.getId() == Integer.parseInt(params[0])) {
                statement.setInt(5, Integer.parseInt(params[0]));
                statement.setString(1, params[1]);
                statement.setString(2, params[2]);
                statement.setString(3, params[3]);
                statement.setString(4, params[4]);

                if (statement.executeUpdate() == 0) {
                    throw new DAOException("0 rows updated into the PostgreeSQL database");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL update exception", e);
        }
    }

    @Override
    public void delete(Car car) throws DAOException {

        try (PreparedStatement statement = conn.prepareStatement(DELETE)) {

            statement.setInt(1, car.getId());


            if (statement.executeUpdate() == 0) {
                throw new DAOException("0 rows deleted into the PostgreeSQL database");
            } 
            cars.remove(car.getId());
            

        } catch (SQLException e) {
            throw new DAOException("PostgreeSQL update exception", e);
        }
    }
}
