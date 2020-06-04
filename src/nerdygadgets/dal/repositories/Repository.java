package nerdygadgets.dal.repositories;

import nerdygadgets.dal.Database;

public abstract class Repository {       
    private Database connection;

    public Repository(Database connection) {
        this.connection = connection;
    }

    protected Database getConnection() {
        return connection;
    }
    
    
}