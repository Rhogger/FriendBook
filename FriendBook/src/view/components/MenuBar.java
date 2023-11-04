package view.components;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.TelaConsultarAutor;
import view.TelaConsultarEditora;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuBar() {
		initComponents();
	}

	public JMenuBar initComponents() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.setBorderPainted(false);

		JMenu menuItemCadastros = new JMenu("Cadastros");
		menuItemCadastros.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuItemCadastros);

		JMenuItem menuItemCadastrosAmigos = new JMenuItem("Amigos");
		menuItemCadastrosAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItemCadastros.add(menuItemCadastrosAmigos);

		JMenuItem menuItemCadastrosAutor = new JMenuItem("Autor");
		menuItemCadastrosAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderTelaAutor();
			}
		});
		menuItemCadastros.add(menuItemCadastrosAutor);

		JMenuItem menuItemCadastrosEditora = new JMenuItem("Editora");
		menuItemCadastrosEditora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderTelaEditora();
			}
		});
		menuItemCadastros.add(menuItemCadastrosEditora);

		JMenuItem menuItemCadastrosLivros = new JMenuItem("Livros");
		menuItemCadastrosLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItemCadastros.add(menuItemCadastrosLivros);

		JMenu menuItemEmprestimos = new JMenu("Empréstimo");
		menuItemEmprestimos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuItemEmprestimos);

		JMenuItem menuItemEmprestimoEmprestar = new JMenuItem("Emprestar");
		menuItemEmprestimoEmprestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItemEmprestimos.add(menuItemEmprestimoEmprestar);

		JMenuItem menuItemEmprestimoHistorico = new JMenuItem("Histórico");
		menuItemEmprestimoHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItemEmprestimos.add(menuItemEmprestimoHistorico);

		JMenu menuItemDevolucao = new JMenu("Devolução");
		menuItemDevolucao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuItemDevolucao);

		JMenuItem menuItemDevolucaoDevolver = new JMenuItem("Devolver");
		menuItemDevolucaoDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItemDevolucao.add(menuItemDevolucaoDevolver);

		JMenuItem menuItemDevolucaoHistorico = new JMenuItem("Histórico");
		menuItemDevolucaoHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItemDevolucao.add(menuItemDevolucaoHistorico);

		return menuBar;
	}

	public void renderTelaAutor() {
		TelaConsultarAutor telaAutor = new TelaConsultarAutor();
		telaAutor.setVisible(true);
	}

	public void renderTelaEditora() {
		TelaConsultarEditora telaEditora = new TelaConsultarEditora();
		telaEditora.setVisible(true);
	}

}
