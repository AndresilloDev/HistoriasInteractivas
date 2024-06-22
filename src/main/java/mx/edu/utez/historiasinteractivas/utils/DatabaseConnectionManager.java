package mx.edu.utez.historiasinteractivas.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/historiasinteractivasdb";
    private static final String EMAIL = "root";
    private static final String NAME = "root";
    private static final String FIRST_LAST_NAME = "root";
    private static final String LAST_LAST_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String USER = "root";

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        try {Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {throw new RuntimeException("Error", e);}

        // Pues en teoria si los datos estan en la base de datos, esto deberia jalar, pero no
        // La neta, no se porque no jala, seguire haciendo pruebas en mi compu y les aviso cuando funcione xd
        // AVISO | el nombre de la base de datos es "historiasinteractivasDB" y la base de datos esta en el drive
        // por si la quieren descargar y usar, sera un bloc de notas, para poder modificarla y que puedan descargar
        // la más reciente con los cambios hechos.


        config.setJdbcUrl(JDBC_URL);
        //config.setEmail(EMAIL);
        //config.setName(NAME);
        //config.setFirst_last_name(FIRST_LAST_NAME);
        //config.setLast_last_name(LAST_LAST_NAME);
        config.setPassword(PASSWORD);
        //config.setUser(USER);
        // Ajustes del pool
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000); // 30 seconds
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DatabaseConnectionManager() {
    // Private constructor to prevent instantiation
    }

}
