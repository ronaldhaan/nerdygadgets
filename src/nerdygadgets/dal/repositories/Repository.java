package nerdygadgets.dal.repositories;

import java.lang.reflect.*;
import java.util.ArrayList;

import nerdygadgets.dal.Database;

public abstract class Repository<T> {       
    private Database connection;

    public Repository(Database connection) {
        this.connection = connection;
    }

    protected Database getConnection() {
        return connection;
    }

    public abstract ArrayList<T> getAll();

    public abstract T getOne(int id);

    public abstract boolean update(int id, T entity);  
    
}