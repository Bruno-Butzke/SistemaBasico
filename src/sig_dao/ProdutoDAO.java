/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruno_butzke
 */

package sig_dao;

import sig_classes.Produto;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
         
    ArrayList<Produto> listaPro = new ArrayList<>();
	
	// Métodos C-R-U-D
    
    public int salvarProduto(Produto pro){			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into produto values(null, ?, ?, ?, ?)");
            stm.setString(1, pro.getDescricao());
            stm.setInt(2, pro.getQuant());
            stm.setString(3, pro.getUni());
            stm.setInt(4, pro.getValor());
            
            status= stm.executeUpdate();
            return status;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ProdutoDAO(salvarProduto) - Erro: "+e);
            return e.getErrorCode();
        }
    }
    
    public ArrayList<Produto> listarProduto(){		// R - RESTORE
       conexao = ModuloConexao.conector();
       String sql = "select * from produto";
       try{
           stm = conexao.prepareStatement(sql);
           rs = stm.executeQuery();
           while(rs.next()) {
               Produto pro = new Produto();
               pro.setCod(rs.getInt("Codigo"));
               pro.setDescricao(rs.getString("Descricao"));
               pro.setQuant(rs.getInt("Quant"));
               pro.setUni(rs.getString("Uni"));
               pro.setValor(rs.getInt("Valor"));
               listaPro.add(pro);             
           }
           System.out.println("Produtos carregados com sucesso.");
       }catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "ProdutoDAO(listarProduto) - Erro: "+e);
       }
       return listaPro;
    }
    
	public void alteraProduto(Produto pro){			// U - UPDATE
            
            conexao = ModuloConexao.conector();
            try{
                stm = conexao.prepareStatement("update Produto set Descricao = ?, Quant = ?,"
                        + " uni = ?, Valor = ? where codigo = ?");
                stm.setString(1, pro.getDescricao());
                stm.setInt(2, pro.getQuant());
                stm.setString(3, pro.getUni());
                stm.setInt(4, pro.getValor());
                stm.setInt(5, pro.getCod());
                stm.execute();
                stm.close();
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "ProdutoDAO(alterarProduto) - Erro: "+e);
                       
            }
    }
    
    public void excluiProduto(int cod){				// D - DELETE
        conexao = ModuloConexao.conector();
        try{
            stm = conexao.prepareStatement("delete from Produto where codigo = ?");
            
            stm.setInt(1,cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro excluido");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ProdutoDAO(exlcuirProduto) - Erro: "+e);
        }
    }
}
