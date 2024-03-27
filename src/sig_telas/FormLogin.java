/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.sql.*;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import sig_dao.ModuloConexao;


/**
 *
 * @author bruno_butzke
 */
public class FormLogin extends JFrame {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    JTextField txtUser;
    JTextField txtPass;

    public void logar() {
        //JOptionPane.showMessageDialog(null, "Este método verifica Username/Password");
        String sql = "select * from tbuser where nm_user = ? and pw_user = ?";

        try {
            stm = conexao.prepareStatement(sql);
            stm.setString(1, txtUser.getText());
            stm.setString(2, txtPass.getText());
            rs = stm.executeQuery();

            if (rs.next()) {
                FormMain fMain = new FormMain();
                fMain.setVisible(true);

                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Usúario ou senha Inválidos");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no FormLogin " + e);
        }
    }

    public FormLogin() {
        super();
        setLocationRelativeTo(this);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponentes();

    }

    private void initComponentes() {
        JLabel lblStatusDB = new JLabel();
        lblStatusDB.setBounds(10,10,40,40);
        
        conexao = ModuloConexao.conector();
        
        if(conexao != null) {
            lblStatusDB.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/sig_icones/1.jpeg")));
            
        }else{
            lblStatusDB.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/sig_icones/2.jpeg")));
        }
        
        
        
        //Bordas utilizadas no formulário 
        Border gray = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        Border brd = BorderFactory.createRaisedBevelBorder();

        //Inagenaducuibada ao fundo do formulário
        PainelImg painel = new PainelImg("/sig_img/Garfild.jpg");
        add(painel);
       
        painel.add(lblStatusDB);
        //Botão Logar
        JButton jbtnLogin = new JButton("Logar");
        jbtnLogin.setBounds(230, 180, 100, 25);
        painel.add(jbtnLogin);

        //Painel Username
        JPanel pnlUser = new JPanel();
        pnlUser.setBorder(brd);
        pnlUser.setLayout(null);

        //Label Usarname
        JLabel lblUser = new JLabel("Usuário:");
        lblUser.setBounds(5, 7, 60, 25);
        pnlUser.add(lblUser);

        //Caixa de texto Username
        txtUser = new JTextField();
        txtUser.setBounds(100, 7, 150, 25);
        pnlUser.add(txtUser);

        //Painel Password
        JPanel pnlPass = new JPanel();
        pnlPass.setBorder(brd);
        pnlPass.setLayout(null);

        //label password
        JLabel lblPass = new JLabel("senha");
        lblPass.setBounds(5, 7, 60, 25);
        pnlPass.add(lblPass);

        //Caixa de texto Password
        txtPass = new JPasswordField();
        txtPass.setBounds(100, 7, 150, 25);
        pnlPass.add(txtPass);

        //Definição e localização dos Painéis
        pnlUser.setBounds(70, 60, 260, 40);
        pnlPass.setBounds(70, 120, 260, 40);

        //Adicionando os painéis
        painel.add(pnlUser);
        painel.add(pnlPass);

        //Evento do botão logar
        jbtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logar();
            }
        });
        
   
  
    }
   

}
