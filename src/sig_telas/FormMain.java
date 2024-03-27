package sig_telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FormMain extends JFrame {

    public static int x, y;

    public FormMain() {
        super();
        setBounds(100, 100, 900, 700);
        setTitle("Sistema Inteligente de Gerenciamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);

        initComponentes();
    }

    public void logout() {
        FormLogin formlogin = new FormLogin();
        formlogin.setVisible(true);
        super.setVisible(false);

    }

    private void initComponentes() {

        FormCliente frmCliente = new FormCliente();
        FormProduto frmProduto = new FormProduto();
        FormFornecedor frmFornecedor = new FormFornecedor();
        FormUsuario frmUsuario = new FormUsuario();

        //Inicia a barra de menus
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 900, 30);

        //Add Menu Cadastro
        JMenu mCadastro = new JMenu("Cadastro");

        //Add Menu Relatório
        JMenu mRelat = new JMenu("Relatório");

        JMenu mSair = new JMenu("Sair");

        //Adicionado a barra de menus ao formulario principal
        this.add(menuBar);

        //Adicionando os menus Cadastro e Relatorio a barra de menus
        menuBar.add(mCadastro);
        menuBar.add(mRelat);
        menuBar.add(mSair);

        //Cria os 3 itens de menu para o Menu Cadastro
        JMenuItem mItemCli = new JMenuItem("Cliente");
        JMenuItem mItemPro = new JMenuItem("Produto"); 
        JMenuItem mItemFor = new JMenuItem("Fornecedor"); 
        JMenuItem mItemUse = new JMenuItem("Usuario"); 
        
        mCadastro.add(mItemCli);
        mCadastro.add(mItemPro);
        mCadastro.add(mItemFor);
        mCadastro.add(mItemUse);
        

        //Cria as definiçoes do JDESKTOPpanel para receber janelas internas
        JDesktopPane jDskTop1 = new JDesktopPane();
        jDskTop1.setBounds(0, 30, 900, 670);

        this.add(jDskTop1);

        jDskTop1.setLayout(null);

        
        JMenuItem mItemLogout = new JMenuItem("Logout");
        
        mSair.add(mItemLogout);
        
        JMenuItem mItemSair = new JMenuItem("Sair");
        mSair.add(mItemSair);

        mItemSair.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mItemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        //Torna Visivel o Form principal
        this.setVisible(true);

        //Guarda as dimenses do formulario principal para ficarem centralizadas
        x = this.getWidth();
        y = this.getHeight();
        
        //evento do menu Cliente para abrir janela interna
        mItemCli.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmCliente.isVisible()) {
                    frmCliente.setVisible(true);
                    frmCliente.setClosable(true);//se o frame poder ser fechado pelo botao fechar
                    frmCliente.setIconifiable(true);//se o frame vai ser minimizado
                    frmCliente.setResizable(true);
                    
                    frmCliente.setLocation((x/2) - (frmCliente.getWidth()/2)-10,
                            (y/2)-(frmCliente.getHeight()/2)-50);
                    jDskTop1.add(frmCliente);
                }
            }
        });
              mItemPro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmProduto.isVisible()) {
                    frmProduto.setVisible(true);
                    frmProduto.setClosable(true);//se o frame poder ser fechado pelo botao fechar
                    frmProduto.setIconifiable(true);//se o frame vai ser minimizado
                    frmProduto.setResizable(true);
                    
                    frmProduto.setLocation((x/2) - (frmProduto.getWidth()/2)-10,
                            (y/2)-(frmProduto.getHeight()/2)-50);
                    jDskTop1.add(frmProduto);
                }
            }
        });
              
              mItemFor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmFornecedor.isVisible()) {
                    frmFornecedor.setVisible(true);
                    frmFornecedor.setClosable(true);//se o frame poder ser fechado pelo botao fechar
                    frmFornecedor.setIconifiable(true);//se o frame vai ser minimizado
                    frmFornecedor.setResizable(true);
                    
                    frmFornecedor.setLocation((x/2) - (frmFornecedor.getWidth()/2)-10,
                            (y/2)-(frmFornecedor.getHeight()/2)-50);
                    jDskTop1.add(frmFornecedor);
                }
            }
        });
            
              mItemUse.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmUsuario.isVisible()) {
                    frmUsuario.setVisible(true);
                    frmUsuario.setClosable(true);//se o frame poder ser fechado pelo botao fechar
                    frmUsuario.setIconifiable(true);//se o frame vai ser minimizado
                    frmUsuario.setResizable(true);
                    
                    frmUsuario.setLocation((x/2) - (frmUsuario.getWidth()/2)-10,
                            (y/2)-(frmUsuario.getHeight()/2)-50);
                    jDskTop1.add(frmUsuario);
                }
            }
        });

    }
}