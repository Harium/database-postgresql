package com.harium.database.postgresql.module;

import com.harium.database.module.OrmLiteDatabaseModule;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class PostgreSQLDatabaseModule extends OrmLiteDatabaseModule {
    private final String DATABASE_NAME;
    private static final String POSTGRESQL_PREFIX = "jdbc:postgresql:";

    private final String username, password;

    public PostgreSQLDatabaseModule(String host, String databaseName, String username, String password) {
        this.DATABASE_NAME = POSTGRESQL_PREFIX + "//" + host + "/" + databaseName;
        this.username = username;
        this.password = password;
    }

    protected ConnectionSource initConnection() throws SQLException {
        return new JdbcConnectionSource(DATABASE_NAME, username, password);
    }

}
