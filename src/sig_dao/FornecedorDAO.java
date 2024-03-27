/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruno_butzke
 */

package sig_dao;

import sig_classes.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FornecedorDAO {
    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
         
    ArrayList<Fornecedor> listaFor = new ArrayList<>();
	
	// Métodos C-R-U-D
    
    public int salvarFornecedor(Fornecedor forn){			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into Fornecedor values(null, ?, ?, ?, ?)");
            stm.setString(1, forn.getEmpresa());
            stm.setString(2, forn.getContato());
            stm.setString(3, forn.getFone());
            stm.setString(4, forn.getEmail());
            
            status= stm.executeUpdate();
            return status;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "FornecedorDAO(salvarFornecedor) - Erro: "+e);
            return e.getErrorCode();
        }
    }
    
    public ArrayList<Fornecedor> listarFornecedor(){		// R - RESTORE
       conexao = ModuloConexao.conector();
       String sql = "select * from fornecedor";
       try{
           stm = conexao.prepareStatement(sql);
           rs = stm.executeQuery();
           while(rs.next()) {
               Fornecedor forn = new Fornecedor();
               forn.setCod(rs.getInt("Codigo"));
               forn.setEmpresa(rs.getString("Empresa"));
               forn.setContato(rs.getString("Contato"));
               forn.setFone(rs.getString("Fone"));
               forn.setEmail(rs.getString("Email"));
               listaFor.add(forn);             
           }
           System.out.println("Fornecedores carregados com sucesso.");
       }catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "FornecedoresDAO(listarFornecedores) - Erro: "+e);
       }
       return listaFor;
    }
    
	public void alteraFornecedor(Fornecedor forn){			// U - UPDATE
            
            conexao = ModuloConexao.conector();
            try{
                stm = conexao.prepareStatement("update Fornecedor set Empresa = ?, Contato = ?,"
                        + " Fone = ?, Email = ? where codigo = ?");
                stm.setString(1, forn.getEmpresa());
                stm.setString(2, forn.getContato());
                stm.setString(3, forn.getFone());
                stm.setString(4, forn.getEmail());
                stm.setInt(5, forn.getCod());
                stm.execute();
                stm.close();
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "ForncedorDAO(alterarFornecedor) - Erro: "+e);
                       
            }
    }
    
    public void excluiForncedor(int cod){				// D - DELETE
        conexao = ModuloConexao.conector();
        try{
            stm = conexao.prepareStatement("delete from Fornecedor where codigo = ?");
            
            stm.setInt(1,cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro excluido");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "FornecedorDAO(exlcuirFornecedor) - Erro: "+e);
        }
    }

    public void excluiFornecedor(int codFor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
