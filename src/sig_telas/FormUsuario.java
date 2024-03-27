package sig_telas;

import sig_dao.UsuarioDAO;
import sig_dao.ModuloConexao;
//import sig_classes.Arquivo;
import sig_classes.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class FormUsuario extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    JTable jTblUse = new JTable();
    JScrollPane scrlTbUse = new JScrollPane(jTblUse);
    public static ArrayList<Usuario> listaUsuario;

    UsuarioDAO useDAO = new UsuarioDAO();

    public FormUsuario() {
        super();
        this.setSize(800, 500);
        this.setResizable(false);
        this.setTitle("Cadastro de Usuários");
        this.setLayout(null);

        initComponentes();
    }

    public void tblUsuario() {    //Atualiza os dados da tabela Usuario
        DefaultTableModel modeloUse = new DefaultTableModel(new Object[]{
            "Código",
            "Nome",
            "Senha",
            "Tipo",
            "Privilégio"}, 0);
        for (int i = 0; i < listaUsuario.size(); i++) {
            Object linhaUse[] = new Object[]{
                listaUsuario.get(i).getIduser(),
                listaUsuario.get(i).getNmUser(),
                listaUsuario.get(i).getPwUser(),
                listaUsuario.get(i).getTpUser(),
                listaUsuario.get(i).getPvUser()};
            modeloUse.addRow(linhaUse);
        }
        jTblUse.setModel(modeloUse);
        jTblUse.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTblUse.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTblUse.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTblUse.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTblUse.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    //CRUD - RESTORE
    public void loadUse() {
        listaUsuario.clear();
        try {
            listaUsuario = useDAO.listarUsuario();
            tblUsuario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FormUsuario(loadUse) - Erro: " + e);
        }
    }

    private void initComponentes() {

        conexao = ModuloConexao.conector();
        listaUsuario = new ArrayList();

        JLabel lblCodUse = new JLabel("Código: ");
        lblCodUse.setBounds(10, 35, 50, 25);

        JTextField jtxtCodUse = new JTextField();
        jtxtCodUse.setBounds(10, 60, 100, 25);

        JLabel lblNomUse = new JLabel("Nome: ");
        lblNomUse.setBounds(10, 95, 50, 25);

        JTextField jtxtNomUse = new JTextField();
        jtxtNomUse.setBounds(10, 120, 270, 25);

        JLabel lblSenUse = new JLabel("Senha: ");
        lblSenUse.setBounds(10, 155, 50, 25);

        JTextField jtxtSenUse = new JTextField();
        jtxtSenUse.setBounds(10, 180, 270, 25);

        JLabel lblTpUse = new JLabel("Tipo: ");
        lblTpUse.setBounds(10, 215, 50, 25);

        JTextField jtxtTpUse = new JTextField();
        jtxtTpUse.setBounds(10, 240, 270, 25);

        JLabel lblPrvUse = new JLabel("Privilégio: ");
        lblPrvUse.setBounds(10, 275, 100, 25);

        JTextArea jtxtPrvUse = new JTextArea();
        jtxtPrvUse.setBounds(10, 300, 270, 100);
        Border gray = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        jtxtPrvUse.setBorder(gray);

        scrlTbUse.setBounds(300, 60, 460, 340);
        scrlTbUse.setBorder(gray);

        loadUse();

        JButton btnNewUse = new JButton("Novo");
        btnNewUse.setBounds(20, 420, 130, 30);
        this.add(btnNewUse);

        JButton btnEdtUse = new JButton("Editar");
        btnEdtUse.setBounds(170, 420, 130, 30);
        this.add(btnEdtUse);

        JButton btnDelUse = new JButton("Excluir");
        btnDelUse.setBounds(320, 420, 130, 30);
        this.add(btnDelUse);

        JButton btnEscUse = new JButton("Cancelar");
        btnEscUse.setBounds(470, 420, 130, 30);
        this.add(btnEscUse);

        JButton btnSavUse = new JButton("Salvar");
        btnSavUse.setBounds(620, 420, 130, 30);
        this.add(btnSavUse);

        //CRUD - CREATE
        btnSavUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario use = new Usuario();
                use.setNmUser(jtxtNomUse.getText());
                use.setPwUser(jtxtSenUse.getText());
                use.setTpUser(jtxtTpUse.getText());
                use.setPvUser(jtxtPrvUse.getText().charAt(0));

                if (useDAO.salvarUsuario(use) == 1) {
                    JOptionPane.showMessageDialog(null, "Dados gravados"
                            + " com sucesso na tabela usuario.");
                    loadUse();
                }
            }

        });
        //Evento Botão Editar
        btnEdtUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario use = new Usuario();
                use.setIduser(Integer.parseInt(jtxtCodUse.getText()));
                use.setNmUser(jtxtNomUse.getText());
                use.setPwUser(jtxtSenUse.getText());
                use.setTpUser(jtxtTpUse.getText());
                use.setPvUser(jtxtPrvUse.getText().charAt(0));

                useDAO.alteraUsuario(use);
                loadUse();
            }
        });
        btnDelUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codUse;

                codUse = Integer.parseInt(jtxtCodUse.getText());

                useDAO.excluiUsuario(codUse);
                loadUse();
                jtxtCodUse.setText("");
                jtxtNomUse.setText("");
                jtxtSenUse.setText("");
                jtxtTpUse.setText("");
                jtxtPrvUse.setText("");
            }
        });
        
        btnNewUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtCodUse.setText("");
                jtxtNomUse.setText("");
                jtxtSenUse.setText("");
                jtxtTpUse.setText("");
                jtxtPrvUse.setText("");
            }
            
        });

        add(jtxtCodUse);
        add(lblCodUse);
        add(lblNomUse);
        add(jtxtNomUse);
        add(lblSenUse);
        add(jtxtSenUse);
        add(lblTpUse);
        add(jtxtTpUse);
        add(lblPrvUse);
        add(jtxtPrvUse);
        add(scrlTbUse);

        repaint();

        jTblUse.addMouseListener(new MouseListener() {
        @Override
            public void mouseClicked(MouseEvent e) {
                
                int index = jTblUse.getSelectedRow();
                if (index >= 0 && index < listaUsuario.size()) {
                    Usuario cli = listaUsuario.get(index);
                    jtxtCodUse.setText(String.valueOf(cli.getIduser()));
                    jtxtNomUse.setText(cli.getNmUser());
                    jtxtSenUse.setText(cli.getPwUser());
                    jtxtTpUse.setText(cli.getTpUser());
                    jtxtPrvUse.setText(Character.toString(cli.getPvUser()));
                }

            }
            
            public void mousePressed(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Pressionado!!!");
            }

            public void mouseReleased(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Liberado!!!");
            }

            public void mouseEntered(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Entrou!!!");
            }

            public void mouseExited(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Saiu!!!");
            }
        });
    }

}