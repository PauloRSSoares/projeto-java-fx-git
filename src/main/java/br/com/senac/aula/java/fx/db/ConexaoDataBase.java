package br.com.senac.aula.java.fx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDataBase {
    public Connection conn = null;

    public synchronized Connection getConexao() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce","postgres","postgres");
        }

        return conn;

    }
}
