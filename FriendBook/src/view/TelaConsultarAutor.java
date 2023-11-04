package view;

import model.Autor;
import dao.ExceptionDAO;
import controller.AutorController;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

/**
 * Tela de consulta de Autores
 *
 * @author rhogg
 */
public class TelaConsultarAutor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputPesquisar;
	private JLabel labelPesquisar;
	private JTable tableConsultaModel;
	private JCheckBox checkboxStatus;
	private JButton buttonPesquisar;
	private JButton buttonVoltar;
	private JButton buttonCadastrar;
	private JButton buttonEditar;
	private JButton btnAlterarStatus;

	private JScrollPane tableConsulta;
	private TelaEditarAutor telaEditarAutor = new TelaEditarAutor(this);

	public TelaConsultarAutor() {
		setResizable(false);
		setSize(new Dimension(820, 640));
		initComponents();
	}

	public TelaConsultarAutor(TelaEditarAutor telaEditarAutor) {
		this.telaEditarAutor = telaEditarAutor;
		initComponents();
	}

	private void initComponents() {

		// Propriedades da tela
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Autores");

		// Componentes da tela
		labelPesquisar = new javax.swing.JLabel("Informe o nome do Autor");

		inputPesquisar = new javax.swing.JTextField();
		inputPesquisar.setDropMode(DropMode.INSERT);

		checkboxStatus = new JCheckBox("Ativo");
		checkboxStatus.setSelected(true);

		// Botões e eventos
		buttonPesquisar = new javax.swing.JButton("Pesquisar");
		buttonPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					consultarAutor();
			}
		});
		buttonPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				consultarAutor();
			}
		});

		buttonVoltar = new JButton("Voltar");
		buttonVoltar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					// Volta para a tela anterior
				}
			}
		});
		buttonVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Volta para a tela anterior
			}
		});

		buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					cadastrarRegistro();
			}
		});
		buttonCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastrarRegistro();
			}
		});

		buttonEditar = new JButton("Editar");
		buttonEditar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					editarRegistro();
			}
		});
		buttonEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarRegistro();
			}
		});

		btnAlterarStatus = new JButton("Alterar Status");
		btnAlterarStatus.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					alterarStatusRegistro();
			}
		});
		btnAlterarStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alterarStatusRegistro();
			}
		});

		// Componentes e configuração da Tabela
		tableConsulta = new javax.swing.JScrollPane();

		tableConsultaModel = new javax.swing.JTable();
		tableConsultaModel.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Status" }) {
					private static final long serialVersionUID = 1L;
					Class<?>[] types = new Class[] { java.lang.Integer.class, java.lang.String.class,
							java.lang.Boolean.class };
					boolean[] canEdit = new boolean[] { false, false, false };

					public Class<?> getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		
		tableConsultaModel.getTableHeader().setReorderingAllowed(false);

		tableConsulta.setViewportView(tableConsultaModel);
		if (tableConsultaModel.getColumnModel().getColumnCount() > 0) {
			tableConsultaModel.getColumnModel().getColumn(2).setResizable(false);
			tableConsultaModel.getColumnModel().getColumn(2).setPreferredWidth(15);
		}

		DefaultCellEditor checkBoxEditor = new DefaultCellEditor(new JCheckBox());
		tableConsultaModel.getColumnModel().getColumn(2).setCellEditor(checkBoxEditor);

		((DefaultTableCellRenderer) tableConsultaModel.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableConsultaModel.getColumnModel().getColumnCount(); i++) {
			if (i == 2) {
				tableConsultaModel.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
					private static final long serialVersionUID = 1L;

					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						if (value instanceof Boolean) {
							JCheckBox checkBox = new JCheckBox();
							checkBox.setSelected((Boolean) value);
							checkBox.setHorizontalAlignment(SwingConstants.CENTER);
							return checkBox;
						}
						return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					}
				});
			} else {
				if (i != 1) {
					tableConsultaModel.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
			}
		}

		int novaAlturaDaLinha = tableConsultaModel.getRowHeight() + 2;
		tableConsultaModel.setRowHeight(novaAlturaDaLinha);

		tableConsultaModel.getColumnModel().getColumn(0).setMinWidth(26);
		tableConsultaModel.getColumnModel().getColumn(0).setMaxWidth(26);
		tableConsultaModel.getColumnModel().getColumn(0).setPreferredWidth(26);

		tableConsultaModel.getColumnModel().getColumn(2).setMinWidth(50);
		tableConsultaModel.getColumnModel().getColumn(2).setMaxWidth(50);
		tableConsultaModel.getColumnModel().getColumn(2).setPreferredWidth(50);

		tableConsultaModel.setShowGrid(true);
		tableConsultaModel.setGridColor(Color.LIGHT_GRAY);

		// Layout da tela
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(36)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(buttonVoltar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(266)
							.addComponent(buttonCadastrar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(buttonEditar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAlterarStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(tableConsulta, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(Alignment.LEADING, layout.createSequentialGroup()
										.addComponent(labelPesquisar)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(checkboxStatus))
									.addGroup(Alignment.LEADING, layout.createSequentialGroup()
										.addComponent(inputPesquisar, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(buttonPesquisar))))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(46))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(36)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPesquisar)
						.addComponent(checkboxStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(inputPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonVoltar)
						.addComponent(btnAlterarStatus)
						.addComponent(buttonEditar)
						.addComponent(buttonCadastrar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
				tableModel.addRow(new Object[] { autor.getId(), autor.getNome(), autor.getStatus() });
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
		TelaCadastrarAutor telaCadastrarAutor = new TelaCadastrarAutor(TelaConsultarAutor.this);
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
			boolean status = (boolean) tableConsultaModel.getModel().getValueAt(selectedRow, 2);

			telaEditarAutor.setVisible(true);
			telaEditarAutor.buscarAutor(id, nome, status);
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

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaConsultarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaConsultarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaConsultarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaConsultarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaConsultarAutor().setVisible(true);
			}
		});
	}
}
