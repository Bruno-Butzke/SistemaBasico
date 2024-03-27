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
import sig_classes.Produto;
import sig_dao.ModuloConexao;
import sig_dao.ProdutoDAO;
import static sig_telas.FormFornecedor.listaFornecedor;

public class FormProduto extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    JTable jTblPro = new JTable();
    JScrollPane scrlTbPro = new JScrollPane(jTblPro);
    public static ArrayList<Produto> listaProduto;

    ProdutoDAO proDAO = new ProdutoDAO();

    public FormProduto() {
        super();
        this.setSize(800, 500);
        this.setResizable(false);
        this.setTitle("Cadastro de Produto");
        this.setLayout(null);

        initComponentes();
    }

    public void tblProduto() {    //Atualiza os dados da tabela Produto
        DefaultTableModel modeloPro = new DefaultTableModel(new Object[]{
            "Código",
            "Descrição",
            "Quantidade",
            "Unidade",
            "Valor"}, 0);
        for (int i = 0; i < listaProduto.size(); i++) {
            Object linhaPro[] = new Object[]{
                listaProduto.get(i).getCod(),
                listaProduto.get(i).getDescricao(),
                listaProduto.get(i).getQuant(),
                listaProduto.get(i).getUni(),
                listaProduto.get(i).getValor()};
            modeloPro.addRow(linhaPro);
        }
        jTblPro.setModel(modeloPro);
        jTblPro.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTblPro.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTblPro.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTblPro.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTblPro.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    //CRUD - RESTORE
    public void loadPro() {
        listaProduto.clear();
        try {
            listaProduto = proDAO.listarProduto();
            tblProduto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FormProduto(loadPro) - Erro: " + e);
        }
    }

    private void initComponentes() {

        conexao = ModuloConexao.conector();
        listaProduto = new ArrayList();

        JLabel lblCodPro = new JLabel("Código: ");
        lblCodPro.setBounds(10, 35, 50, 25);

        JTextField jtxtCodPro = new JTextField();
        jtxtCodPro.setBounds(10, 60, 100, 25);

        JLabel lblDscPro = new JLabel("Descrição: ");
        lblDscPro.setBounds(10, 95, 100, 25);

        JTextField jtxtDscPro = new JTextField();
        jtxtDscPro.setBounds(10, 120, 270, 25);

        JLabel lblQntPro = new JLabel("Quantidade: ");
        lblQntPro.setBounds(10, 155, 100, 25);

        JTextField jtxtQntPro = new JTextField();
        jtxtQntPro.setBounds(10, 180, 270, 25);

        JLabel lblUniPro = new JLabel("Unidade: ");
        lblUniPro.setBounds(10, 215, 100, 25);

        JTextField jtxtUniPro = new JTextField();
        jtxtUniPro.setBounds(10, 240, 270, 25);

        JLabel lblValPro = new JLabel("Valor: ");
        lblValPro.setBounds(10, 275, 100, 25);

        JTextArea jtxtValPro = new JTextArea();
        jtxtValPro.setBounds(10, 300, 270, 100);
        Border gray = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        jtxtValPro.setBorder(gray);

        scrlTbPro.setBounds(300, 60, 460, 340);
        scrlTbPro.setBorder(gray);

        loadPro();

        JButton btnNewPro = new JButton("Novo");
        btnNewPro.setBounds(20, 420, 130, 30);
        this.add(btnNewPro);

        JButton btnEdtPro = new JButton("Editar");
        btnEdtPro.setBounds(170, 420, 130, 30);
        this.add(btnEdtPro);

        JButton btnDelPro = new JButton("Excluir");
        btnDelPro.setBounds(320, 420, 130, 30);
        this.add(btnDelPro);

        JButton btnEscPro = new JButton("Cancelar");
        btnEscPro.setBounds(470, 420, 130, 30);
        this.add(btnEscPro);

        JButton btnSavPro = new JButton("Salvar");
        btnSavPro.setBounds(620, 420, 130, 30);
        this.add(btnSavPro);

        //CRUD - CREATE
        btnSavPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto pro = new Produto();
                pro.setDescricao(jtxtDscPro.getText());
                pro.setQuant(Integer.parseInt(jtxtQntPro.getText()));
                pro.setUni(jtxtUniPro.getText());
                pro.setValor(Integer.parseInt(jtxtValPro.getText()));

                if (proDAO.salvarProduto(pro) == 1) {
                    JOptionPane.showMessageDialog(null, "Dados gravados"
                            + " com sucesso na tabela produtos.");
                    loadPro();
                }
            }

        });
        //Evento Botão Editar
        btnEdtPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto pro = new Produto();
                pro.setCod(Integer.parseInt(jtxtCodPro.getText()));
                pro.setDescricao(jtxtDscPro.getText());
                pro.setQuant(Integer.parseInt(jtxtQntPro.getText()));
                pro.setUni(jtxtUniPro.getText());
                pro.setValor(Integer.parseInt(jtxtValPro.getText()));

                proDAO.alteraProduto(pro);
                loadPro();
            }
        });
        btnDelPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codPro;

                codPro = Integer.parseInt(jtxtCodPro.getText());

                proDAO.excluiProduto(codPro);
                loadPro();
                jtxtCodPro.setText("");
                jtxtDscPro.setText("");
                jtxtQntPro.setText("");
                jtxtUniPro.setText("");
                jtxtValPro.setText("");
            }
        });
        
        btnNewPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtCodPro.setText("");
                jtxtDscPro.setText("");
                jtxtQntPro.setText("");
                jtxtUniPro.setText("");
                jtxtValPro.setText("");
                
            }
        });

        add(jtxtCodPro);
        add(lblCodPro);
        add(lblDscPro);
        add(jtxtDscPro);
        add(lblQntPro);
        add(jtxtQntPro);
        add(lblUniPro);
        add(jtxtUniPro);
        add(lblValPro);
        add(jtxtValPro);
        add(scrlTbPro);

        repaint();
        jTblPro.addMouseListener(new MouseListener() {
        @Override
            public void mouseClicked(MouseEvent e) {
                
                int index = jTblPro.getSelectedRow();
                if (index >= 0 && index < listaProduto.size()) {
                    Produto pro = listaProduto.get(index);
                    jtxtCodPro.setText(String.valueOf(pro.getCod()));
                    jtxtDscPro.setText(pro.getDescricao());
                    jtxtQntPro.setText(String.valueOf(pro.getQuant()));
                    jtxtUniPro.setText(pro.getUni());
                    jtxtValPro.setText(String.valueOf(pro.getValor()));
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
