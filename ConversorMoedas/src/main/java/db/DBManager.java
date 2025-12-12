package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String NOME_ARQUIVO_DB = "moedas.db";

    private static final String URL_CONEXAO = "jdbc:sqlite:" + NOME_ARQUIVO_DB;

    public static Connection getConexao() throws SQLException{
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL_CONEXAO);

            System.out.println("Conectado com sucesso!");

            ifTableNoExists(connection);
        }catch (SQLException e){
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw e;
        }
        return connection;
    }

    public static void ifTableNoExists(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS moedas (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                " name TEXT NOT NULL, \n" +
                " salario DOUBLE NOT NULL);";

        try(java.sql.Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso!");
        }catch (Exception e){
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void close(Connection conn) {
        if(conn != null){
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
