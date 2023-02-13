package com.example.persistence;

import java.util.List;
import java.util.Optional;

/**
 * La popular API DAO es una clase abstracta que realiza operaciones CRUD en
 * objetos de tipo T. Haciendo facil implementar objetos DAO más específicos.
 */
public interface DAO<T> {

    /**
     * La clase Optional nos permite gestionar las NullPointerException desde la 1.8
     * Podria verse como un objeto contenedor/envoltorio del Objeto pasado como
     * parametro <T>
     * 
     * @param id el id del objeto <T> a encontrar.
     * @return un objeto encontrado por un id que puede ser nulo.
     */
    Optional<T> get(int id) throws DAOException;

    List<T> getAll() throws DAOException;

    void insert(T t) throws DAOException;

    void update(T t, String[] params) throws DAOException;

    void delete(T t) throws DAOException;

}