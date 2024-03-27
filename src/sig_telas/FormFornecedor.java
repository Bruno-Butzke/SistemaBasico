package sig_telas;

import sig_dao.ModuloConexao;
//import sig_classes.Arquivo;
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
import sig_classes.Fornecedor;
import sig_dao.ModuloConexao;
import sig_dao.FornecedorDAO;

public class FormFornecedor extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    JTable jTblFor = new JTable();
    JScrollPane scrlTbFor = new JScrollPane(jTblFor);
    public static ArrayList<Fornecedor> listaFornecedor;

    FornecedorDAO forDAO = new FornecedorDAO();

    public FormFornecedor() {
        super();
        this.setSize(800, 500);
        this.setResizable(false);
        this.setTitle("Cadastro de Fornecedor");
        this.setLayout(null);

        initComponentes();
    }

    public void tblFornecedor() {    //Atualiza os dados da tabela Fornecedor
        DefaultTableModel modeloFor = new DefaultTableModel(new Object[]{
            "Código",
            "Empresa",
            "Contato",
            "Fone",
            "Email"}, 0);
        for (int i = 0; i < listaFornecedor.size(); i++) {
            Object linhaFor[] = new Object[]{
                listaFornecedor.get(i).getCod(),
                listaFornecedor.get(i).getEmpresa(),
                listaFornecedor.get(i).getContato(),
                listaFornecedor.get(i).getFone(),
                listaFornecedor.get(i).getEmail()};
            modeloFor.addRow(linhaFor);
        }
        jTblFor.setModel(modeloFor);
        jTblFor.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTblFor.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTblFor.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTblFor.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTblFor.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    //CRUD - RESTORE
    public void loadFor() {
        listaFornecedor.clear();
        try {
            listaFornecedor = forDAO.listarFornecedor();
            tblFornecedor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FormFornecedor(loadFor) - Erro: " + e);
        }
    }

    private void initComponentes() {

        conexao = ModuloConexao.conector();
        listaFornecedor = new ArrayList();

        JLabel lblCodFor = new JLabel("Código: ");
        lblCodFor.setBounds(10, 35, 50, 25);

        JTextField jtxtCodFor = new JTextField();
        jtxtCodFor.setBounds(10, 60, 100, 25);

        JLabel lblEmpFor = new JLabel("Empresa: ");
        lblEmpFor.setBounds(10, 95, 100, 25);

        JTextField jtxtEmpFor = new JTextField();
        jtxtEmpFor.setBounds(10, 120, 270, 25);

        JLabel lblCntFor = new JLabel("Contato: ");
        lblCntFor.setBounds(10, 155, 100, 25);

        JTextField jtxtCntFor = new JTextField();
        jtxtCntFor.setBounds(10, 180, 270, 25);

        JLabel lblFnFor = new JLabel("Fone: ");
        lblFnFor.setBounds(10, 215, 100, 25);

        JTextField jtxtFnFor = new JTextField();
        jtxtFnFor.setBounds(10, 240, 270, 25);

        JLabel lblMailFor = new JLabel("Email: ");
        lblMailFor.setBounds(10, 275, 100, 25);

        JTextArea jtxtMailFor = new JTextArea();
        jtxtMailFor.setBounds(10, 300, 270, 100);
        Border gray = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        jtxtMailFor.setBorder(gray);

        scrlTbFor.setBounds(300, 60, 460, 340);
        scrlTbFor.setBorder(gray);

        loadFor();

        JButton btnNewFor = new JButton("Novo");
        btnNewFor.setBounds(20, 420, 130, 30);
        this.add(btnNewFor);

        JButton btnEdtFor = new JButton("Editar");
        btnEdtFor.setBounds(170, 420, 130, 30);
        this.add(btnEdtFor);

        JButton btnDelFor = new JButton("Excluir");
        btnDelFor.setBounds(320, 420, 130, 30);
        this.add(btnDelFor);

        JButton btnEscFor = new JButton("Cancelar");
        btnEscFor.setBounds(470, 420, 130, 30);
        this.add(btnEscFor);

        JButton btnSavFor = new JButton("Salvar");
        btnSavFor.setBounds(620, 420, 130, 30);
        this.add(btnSavFor);

        //CRUD - CREATE
        btnSavFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fornecedor forn = new Fornecedor();
                forn.setEmpresa(jtxtEmpFor.getText());
                forn.setContato(jtxtCntFor.getText());
                forn.setFone(jtxtFnFor.getText());
                forn.setEmail(jtxtMailFor.getText());

                if (forDAO.salvarFornecedor(forn) == 1) {
                    JOptionPane.showMessageDialog(null, "Dados gravados"
                            + " com sucesso na tabela Fornecedores.");
                    loadFor();
                }
            }

        });
        //Evento Botão Editar
        btnEdtFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fornecedor forn = new Fornecedor();
                forn.setCod(Integer.parseInt(jtxtCodFor.getText()));
                forn.setEmpresa(jtxtEmpFor.getText());
                forn.setContato(jtxtCntFor.getText());
                forn.setFone(jtxtFnFor.getText());
                forn.setEmail(jtxtMailFor.getText());

                forDAO.alteraFornecedor(forn);
                loadFor();
            }
        });
        btnDelFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codFor;

                codFor = Integer.parseInt(jtxtCodFor.getText());

                forDAO.excluiFornecedor(codFor);
                loadFor();
                jtxtCodFor.setText("");
                jtxtEmpFor.setText("");
                jtxtCntFor.setText("");
                jtxtFnFor.setText("");
                jtxtMailFor.setText("");
            }
        });
        
        btnNewFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtCodFor.setText("");
                jtxtEmpFor.setText("");
                jtxtCntFor.setText("");
                jtxtFnFor.setText("");
                jtxtMailFor.setText("");
                
            }
        });

        add(jtxtCodFor);
        add(lblCodFor);
        add(lblEmpFor);
        add(jtxtEmpFor);
        add(lblCntFor);
        add(jtxtCntFor);
        add(lblFnFor);
        add(jtxtFnFor);
        add(lblMailFor);
        add(jtxtMailFor);
        add(scrlTbFor);

        repaint();

        jTblFor.addMouseListener(new MouseListener() {
        @Override
            public void mouseClicked(MouseEvent e) {
                
                int index = jTblFor.getSelectedRow();
                if (index >= 0 && index < listaFornecedor.size()) {
                    Fornecedor For = listaFornecedor.get(index);
                    jtxtCodFor.setText(String.valueOf(For.getCod()));
                    jtxtEmpFor.setText(For.getEmpresa());
                    jtxtCntFor.setText(For.getContato());
                    jtxtFnFor.setText(For.getFone());
                    jtxtMailFor.setText(For.getEmail());
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
