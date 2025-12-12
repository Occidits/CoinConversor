package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoedasDAO {
    public boolean insert(Moeda moeda) {
        String sql = "INSERT INTO moedas (name, salario) VALUES (?, ?)";

        try(Connection conn = DBManager.getConexao();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, moeda.getName());
            preparedStatement.setDouble(2, moeda.getSalario());

            int affectedLines = preparedStatement.executeUpdate();
            if(affectedLines > 0){
                System.out.println("Moeda " + moeda.getName() + " inserida com Sucesso!");
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            System.err.println("Erro ao inserir a moeda no BD: " + e.getMessage());
            return false;
        }
    }
}
