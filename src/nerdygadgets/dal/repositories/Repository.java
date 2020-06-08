package nerdygadgets.dal.repositories;

import java.util.ArrayList;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Entity;

public abstract class Repository<T extends Entity> {       
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