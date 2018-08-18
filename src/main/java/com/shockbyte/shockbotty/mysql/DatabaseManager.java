package com.shockbyte.shockbotty.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    private static HikariDataSource dataSource;

    public static void init(String host, short port, String username, String password) {
        HikariConfig config = new HikariConfig();

        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        if (!host.equalsIgnoreCase("localhost") || port != 3306) {
            mysqlDataSource.setServerName(host);
            mysqlDataSource.setPort(port);
        }
        config.setDataSource(mysqlDataSource);

        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(5_000);
        config.setMaximumPoolSize(4);

        dataSource = new HikariDataSource(config);
    }

    public static void run(SQLTask task) {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            task.execute(connection);
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to execute MySQL task", e);
        }
    }

}
