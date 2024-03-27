/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_dao;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author bruno_butzke
 */
public class ModuloConexao {
    public static Connection conector(){
        Connection conexao = null;
        //Definicação do Driver
        String driver = "com.mysql.cj.jdbc.Driver";
        
        //Informação do Banco de dados
        String url = "jdbc:mysql://localhost:3306/dbtec_biblioteca_brunobutzke";
        String user = "root";
        String pass = "";
        
        // Tentativa de acesso ao banco de dados
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, pass);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão ao banco de dados: "+e);
            return null;
        }
    }
}
