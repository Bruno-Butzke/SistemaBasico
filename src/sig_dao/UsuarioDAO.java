/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruno_butzke
 */

package sig_dao;

import sig_classes.Usuario;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
         
    ArrayList<Usuario> listaUse = new ArrayList<>();
	
	// Métodos C-R-U-D
    
    public int salvarUsuario(Usuario use){			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into tbuser values(null, ?, ?, ?, ?)");
            stm.setString(1, use.getNmUser());
            stm.setString(2, use.getPwUser());
            stm.setString(3, use.getTpUser());
            stm.setString(4, Character.toString(use.getPvUser()));
            
            status= stm.executeUpdate();
            return status;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO(salvarUsuario) - Erro: "+e);
            return e.getErrorCode();
        }
    }
    
    public ArrayList<Usuario> listarUsuario(){		// R - RESTORE
       conexao = ModuloConexao.conector();
       String sql = "select * from tbuser";
       try{
           stm = conexao.prepareStatement(sql);
           rs = stm.executeQuery();
           while(rs.next()) {
               Usuario use = new Usuario();
               use.setIduser(rs.getInt("id_user"));
               use.setNmUser(rs.getString("nm_user"));
               use.setPwUser(rs.getString("pw_user"));
               use.setTpUser(rs.getString("tp_user"));
               use.setPvUser(rs.getString("pv_user").charAt(0));
               listaUse.add(use);             
           }
           System.out.println("Usuários carregados com sucesso.");
       }catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "UsuarioDAO(listarUsuario) - Erro: "+e);
       }
       return listaUse;
    }
    
	public void alteraUsuario(Usuario use){			// U - UPDATE
            
            conexao = ModuloConexao.conector();
            try{
                stm = conexao.prepareStatement("update tbuser set nm_user = ?, pw_user = ?,"
                        + " tp_user = ?, pv_user = ? where id_user = ?");
                stm.setString(1, use.getNmUser());
                stm.setString(2, use.getPwUser());
                stm.setString(3, use.getTpUser());
                stm.setString(4, Character.toString(use.getPvUser()));
                stm.setInt(5, use.getIduser());
                stm.execute();
                stm.close();
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "UsuarioDAO(alterarUsuario) - Erro: "+e);
                       
            }
    }
    
    public void excluiUsuario(int cod){				// D - DELETE
        conexao = ModuloConexao.conector();
        try{
            stm = conexao.prepareStatement("delete from tbuser where id_user = ?");
            
            stm.setInt(1,cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro excluido");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "UsuarioDAO(exlcuirUsuario) - Erro: "+e);
        }
    }
}
