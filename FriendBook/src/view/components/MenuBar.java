package view.components;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
    private JMenu menuItemCadastros;
    private JMenuItem menuItemCadastrosAmigos;
    private JMenuItem menuItemCadastrosAutor;
    private JMenuItem menuItemCadastrosEditora;
    private JMenuItem menuItemCadastrosLivros;
    private JMenu menuItemEmprestimos;
    private JMenuItem menuItemEmprestimoEmprestar;
    private JMenuItem menuItemEmprestimoHistorico;
    private JMenu menuItemDevolucao;
    private JMenuItem menuItemDevolucaoDevolver;
    private JMenuItem menuItemDevolucaoHistorico;
    
	public MenuBar() {
		initComponents();
	}
	
	public void initComponents() {
		menuBar = new JMenuBar();
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.setBorderPainted(false);
		
		menuItemCadastros = new JMenu("Cadastros");
		menuItemCadastros.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuItemCadastros);
		
		menuItemCadastrosAmigos = new JMenuItem("Amigos");
		menuItemCadastrosAmigos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemCadastrosAmigos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemCadastros.add(menuItemCadastrosAmigos);
		
		menuItemCadastrosAutor = new JMenuItem("Autor");
		menuItemCadastrosAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemCadastrosAutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemCadastros.add(menuItemCadastrosAutor);
		
		menuItemCadastrosEditora = new JMenuItem("Editora");
		menuItemCadastrosEditora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemCadastrosEditora.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemCadastros.add(menuItemCadastrosEditora);
		
		menuItemCadastrosLivros = new JMenuItem("Livros");
		menuItemCadastrosLivros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemCadastrosLivros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemCadastros.add(menuItemCadastrosLivros);
		
		menuItemEmprestimos = new JMenu("Empréstimo");
		menuItemEmprestimos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuItemEmprestimos);
		
		menuItemEmprestimoEmprestar = new JMenuItem("Emprestar");
		menuItemEmprestimoEmprestar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemEmprestimoEmprestar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemEmprestimos.add(menuItemEmprestimoEmprestar);
		
		menuItemEmprestimoHistorico = new JMenuItem("Histórico");
		menuItemEmprestimoHistorico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemEmprestimoHistorico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemEmprestimos.add(menuItemEmprestimoHistorico);
		
		menuItemDevolucao = new JMenu("Devolução");
		menuItemDevolucao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuItemDevolucao);
		
		menuItemDevolucaoDevolver = new JMenuItem("Devolver");
		menuItemDevolucaoDevolver.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemDevolucaoDevolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemDevolucao.add(menuItemDevolucaoDevolver);
		
		menuItemDevolucaoHistorico = new JMenuItem("Histórico");
		menuItemDevolucaoHistorico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		menuItemDevolucaoHistorico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuItemDevolucao.add(menuItemDevolucaoHistorico);
	}

}
