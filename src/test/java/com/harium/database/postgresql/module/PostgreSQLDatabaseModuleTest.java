package com.harium.database.postgresql.module;

import com.harium.database.Pojo;
import com.harium.database.dao.OrmLiteBaseDAOImpl;
import com.harium.dotenv.Env;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostgreSQLDatabaseModuleTest {

    private OrmLiteBaseDAOImpl<Pojo> dao;
    private PostgreSQLDatabaseModule module;

    @Before
    public void setUp() {
        dao = new OrmLiteBaseDAOImpl<Pojo>(Pojo.class);

        String databaseName = "harium_test";
        //String host = "127.0.0.1:5432";
        String host = "127.0.0.1";
        String username = Env.get("POSTGRESQL_USERNAME");
        String password = Env.get("POSTGRESQL_PASSWORD");

        module = new PostgreSQLDatabaseModule(host, databaseName, username, password);
        module.register(dao);
        module.init(true);
    }

    @Test
    public void testGetDAO() {
        Assert.assertNotNull(module.getDAO(Pojo.class));
    }

    @Test
    public void testDAOMethods() {
        Pojo pojo = new Pojo();
        pojo.setText("1");

        Assert.assertEquals(0, dao.count());
        dao.create(pojo);
        Assert.assertEquals(1, dao.count());
        Assert.assertEquals("1", dao.queryForId(1).getText());
    }
}
