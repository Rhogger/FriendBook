package view;

import model.Autor;
import dao.ExceptionDAO;
import controller.AutorController;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

/**
 * Tela de consulta de Autores
 *
 * @author rhogg
 */
public class TelaConsultarAutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputPesquisar;
	private JCheckBox checkboxStatus;

	private JScrollPane tableConsulta;
	private JTable tableConsultaModel;
	private TelaFormAutor telaEditarAutor = new TelaFormAutor(this, "Editar");

	public TelaConsultarAutor() {
		setMinimumSize(new Dimension(900, 680));
		setPreferredSize(new Dimension(900, 680));
		setMaximumSize(new Dimension(900, 680));
		getContentPane().setBackground(new Color(31, 31, 31));
		setResizable(false);
		initComponents();
	}

	public TelaConsultarAutor(TelaFormAutor telaEditarAutor) {
		this.telaEditarAutor = telaEditarAutor;
		initComponents();
	}

	private void initComponents() {
		// Propriedades da tela
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Autores");

		// Componentes da tela
		JLabel labelPesquisar = new JLabel("Informe o nome do Autor");
		labelPesquisar.setForeground(new Color(255, 255, 255));
		labelPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		inputPesquisar = new JTextField();
		inputPesquisar.setDropMode(DropMode.INSERT);
		inputPesquisar.setBorder(null);
		inputPesquisar.setForeground(new Color(255, 255, 255));
		inputPesquisar.setBackground(new Color(91, 91, 91));
		inputPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputPesquisar.setPreferredSize(new Dimension(inputPesquisar.getPreferredSize().width, 26));
		inputPesquisar.setBorder(BorderFactory.createCompoundBorder(inputPesquisar.getBorder(),
				BorderFactory.createEmptyBorder(0, 8, 0, 8)));

		checkboxStatus = new JCheckBox("Ativo");
		checkboxStatus.setForeground(new Color(255, 255, 255));
		checkboxStatus.setBackground(new Color(31, 31, 31));
		checkboxStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkboxStatus.setSelected(true);

		// Botões e eventos
		JButton buttonPesquisar = new JButton("Pesquisar");
		buttonPesquisar.setForeground(new Color(255, 255, 255));
		buttonPesquisar.setBackground(new Color(31, 31, 31));
		buttonPesquisar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarAutor();
			}
		});

		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.setForeground(new Color(255, 255, 255));
		buttonVoltar.setBackground(new Color(31, 31, 31));
		buttonVoltar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.setForeground(new Color(255, 255, 255));
		buttonCadastrar.setBackground(new Color(31, 31, 31));
		buttonCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarRegistro();
			}
		});

		JButton buttonEditar = new JButton("Editar");
		buttonEditar.setForeground(new Color(255, 255, 255));
		buttonEditar.setBackground(new Color(31, 31, 31));
		buttonEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistro();
			}
		});

		JButton btnAlterarStatus = new JButton("Alterar Status");
		btnAlterarStatus.setForeground(new Color(255, 255, 255));
		btnAlterarStatus.setBackground(new Color(31, 31, 31));
		btnAlterarStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAlterarStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarStatusRegistro();
			}
		});

		// Componentes e configuração da Tabela
		tableConsulta = new JScrollPane();
		tableConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		tableConsultaModel = new JTable();
		tableConsultaModel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tableConsultaModel.setForeground(new Color(0, 0, 0));
		tableConsultaModel.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "CPF", "Status" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class[] { Integer.class, String.class, String.class, Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		tableConsultaModel.getTableHeader().setReorderingAllowed(false);

		tableConsulta.setViewportView(tableConsultaModel);

		DefaultCellEditor checkBoxEditor = new DefaultCellEditor(new JCheckBox());
		tableConsultaModel.getColumnModel().getColumn(3).setCellEditor(checkBoxEditor);

		((DefaultTableCellRenderer) tableConsultaModel.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableConsultaModel.getColumnModel().getColumnCount(); i++) {

			if (i != 3 && i != 1) {
				tableConsultaModel.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		}

		int novaAlturaDaLinha = tableConsultaModel.getRowHeight() + 2;
		tableConsultaModel.setRowHeight(novaAlturaDaLinha);

		tableConsultaModel.getColumnModel().getColumn(0).setMinWidth(26);
		tableConsultaModel.getColumnModel().getColumn(0).setMaxWidth(26);
		tableConsultaModel.getColumnModel().getColumn(0).setPreferredWidth(26);
		tableConsultaModel.getColumnModel().getColumn(0).setResizable(false);
		
		tableConsultaModel.getColumnModel().getColumn(2).setMinWidth(150);
		tableConsultaModel.getColumnModel().getColumn(2).setMaxWidth(150);
		tableConsultaModel.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableConsultaModel.getColumnModel().getColumn(2).setResizable(false);

		tableConsultaModel.getColumnModel().getColumn(3).setMinWidth(50);
		tableConsultaModel.getColumnModel().getColumn(3).setMaxWidth(50);
		tableConsultaModel.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableConsultaModel.getColumnModel().getColumn(3).setResizable(false);

		tableConsultaModel.setShowGrid(true);
		tableConsultaModel.setGridColor(Color.LIGHT_GRAY);

		// Layout da tela
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(41)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tableConsulta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(labelPesquisar)
										.addComponent(inputPesquisar, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(buttonPesquisar)
										.addComponent(checkboxStatus)))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
								.addComponent(buttonVoltar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGap(420)
								.addComponent(buttonCadastrar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(buttonEditar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnAlterarStatus)))
				.addGap(41)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(36)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelPesquisar)
						.addComponent(checkboxStatus))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(inputPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPesquisar))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE).addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(buttonVoltar)
						.addComponent(btnAlterarStatus).addComponent(buttonEditar).addComponent(buttonCadastrar))
				.addGap(24)));
		getContentPane().setLayout(layout);

		pack();
	}

	private void consultarAutor() {
		String pesquisa = inputPesquisar.getText();
		boolean status = checkboxStatus.isSelected();

		DefaultTableModel tableModel = (DefaultTableModel) tableConsultaModel.getModel();
		tableModel.setRowCount(0);
		AutorController autorController = new AutorController();

		try {
			ArrayList<Autor> autores = autorController.consultarAutores(pesquisa, status);
			autores.forEach((Autor autor) -> {
				tableModel.addRow(new Object[] { autor.getId(), autor.getNome(), autor.getCpf(), autor.getStatus() });
			});

			tableConsultaModel.setModel(tableModel);
		} catch (ExceptionDAO e) {
			Logger.getLogger(TelaConsultarAutor.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void atualizarListaAutores() {
		consultarAutor();
	}

	public void cadastrarRegistro() {
		TelaFormAutor telaCadastrarAutor = new TelaFormAutor(TelaConsultarAutor.this, "Cadastrar");
		telaCadastrarAutor.setVisible(true);
	}

	public void editarRegistro() {
		int selectedRows = tableConsultaModel.getSelectedRowCount();

		if (selectedRows <= 0) {
			JOptionPane.showMessageDialog(null, "Selecione pelo menos um item!");
		} else if (selectedRows > 1) {
			JOptionPane.showMessageDialog(null, "Só é possível editar um cadastro por vez!");
		} else {
			int selectedRow = tableConsultaModel.getSelectedRow();

			Integer id = (Integer) tableConsultaModel.getModel().getValueAt(selectedRow, 0);
			String nome = (String) tableConsultaModel.getModel().getValueAt(selectedRow, 1);
			String cpf = (String) tableConsultaModel.getModel().getValueAt(selectedRow, 2);
			boolean status = (boolean) tableConsultaModel.getModel().getValueAt(selectedRow, 3);

			telaEditarAutor.setVisible(true);
			telaEditarAutor.buscarAutor(id, cpf, nome, status);
		}
	}

	public void alterarStatusRegistro() {
		int[] selectedRows = tableConsultaModel.getSelectedRows();

		if (selectedRows.length < 1) {
			JOptionPane.showMessageDialog(null, "Selecione pelo menos um registro da tabela!");
		} else {
			AutorController autorController = new AutorController();

			int escolha = JOptionPane.showOptionDialog(null, "Qual status deseja definir?", "Alteração de Status",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] { "Desativado", "Ativado" }, "Desativado");

			boolean status = escolha == 1;

			for (int selectedRow : selectedRows) {

				Integer id = (Integer) tableConsultaModel.getModel().getValueAt(selectedRow, 0);

				try {
					autorController.atualizarAutor(id, status);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro: " + ex);
				} finally {
					atualizarListaAutores();
				}
			}
		}
	}
}
