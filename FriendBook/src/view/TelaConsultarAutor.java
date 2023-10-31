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
	private javax.swing.JTextField inputPesquisar;
	private javax.swing.JLabel labelPesquisar;
	private javax.swing.JTable headerTableConsulta;
	private javax.swing.JButton buttonPesquisar;
	private javax.swing.JScrollPane tableConsulta;
//	private TelaCadastrarAutor telaCadastrarAutor = new TelaCadastrarAutor(this);
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

		labelPesquisar = new javax.swing.JLabel();
		labelPesquisar.setText("Informe o nome do Autor");
		inputPesquisar = new javax.swing.JTextField();
		inputPesquisar.setDropMode(DropMode.INSERT);
		buttonPesquisar = new javax.swing.JButton();
		buttonPesquisar.setText("Pesquisar");
		buttonPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				consultarAutor();
			}
		});
		tableConsulta = new javax.swing.JScrollPane();
		headerTableConsulta = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Autores");

		headerTableConsulta.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Nome", "Status" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class<?>[] types = new Class[] { java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, false };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		headerTableConsulta.getTableHeader().setReorderingAllowed(false);
		tableConsulta.setViewportView(headerTableConsulta);
		if (headerTableConsulta.getColumnModel().getColumnCount() > 0) {
			headerTableConsulta.getColumnModel().getColumn(2).setResizable(false);
			headerTableConsulta.getColumnModel().getColumn(2).setPreferredWidth(15);
		}

		// Crie um editor de célula personalizado para caixas de seleção
		DefaultCellEditor checkBoxEditor = new DefaultCellEditor(new JCheckBox());
		headerTableConsulta.getColumnModel().getColumn(2).setCellEditor(checkBoxEditor);

		// Configure o alinhamento central para o cabeçalho
		((DefaultTableCellRenderer) headerTableConsulta.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);

		// Mantenha o renderizador central apenas para as outras colunas, exceto a
		// coluna "Nome" (segunda coluna)
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < headerTableConsulta.getColumnModel().getColumnCount(); i++) {
			if (i == 2) {
				// Aplique o renderizador personalizado para a primeira e terceira coluna
				headerTableConsulta.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
					/**
					 * 
					 */
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
				// Mantenha o renderizador central para as outras colunas
				if (i != 1) { // Exceto a coluna "Nome"
					headerTableConsulta.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
			}
		}

		int novaAlturaDaLinha = headerTableConsulta.getRowHeight() + 2;
		headerTableConsulta.setRowHeight(novaAlturaDaLinha);

		// Configure as larguras mínima, máxima e preferida das colunas
		headerTableConsulta.getColumnModel().getColumn(0).setMinWidth(26);
		headerTableConsulta.getColumnModel().getColumn(0).setMaxWidth(26);
		headerTableConsulta.getColumnModel().getColumn(0).setPreferredWidth(26);

		headerTableConsulta.getColumnModel().getColumn(2).setMinWidth(50);
		headerTableConsulta.getColumnModel().getColumn(2).setMaxWidth(50);
		headerTableConsulta.getColumnModel().getColumn(2).setPreferredWidth(50);

		// Adicione bordas laterais
		headerTableConsulta.setShowGrid(true);
		headerTableConsulta.setGridColor(Color.LIGHT_GRAY);

		// Logica para tornar uma linha selecionável

		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCadastrarAutor telaCadastrarAutor = new TelaCadastrarAutor(TelaConsultarAutor.this);
				telaCadastrarAutor.setVisible(true);
			}
		});

		JButton buttonEditar = new JButton("Editar");
		buttonEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRows = headerTableConsulta.getSelectedRowCount();

				if (selectedRows <= 0) {
					JOptionPane.showMessageDialog(null, "Selecione pelo menos um item!");
				}
				else if (selectedRows > 1) {
					JOptionPane.showMessageDialog(null, "Só é possível editar um cadastro por vez!");
				} else {
					int selectedRow = headerTableConsulta.getSelectedRow();

					Integer id = (Integer) headerTableConsulta.getModel().getValueAt(selectedRow, 0);
					String nome = (String) headerTableConsulta.getModel().getValueAt(selectedRow, 1);
					boolean status = (boolean) headerTableConsulta.getModel().getValueAt(selectedRow, 2);

					telaEditarAutor.setVisible(true);
					telaEditarAutor.buscarAutor(id, nome, status);
				}
			}
		});

		JButton btnAlterarStatus = new JButton("Alterar Status");
		btnAlterarStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] selectedRows = headerTableConsulta.getSelectedRows();

				if (selectedRows.length < 1) {
					JOptionPane.showMessageDialog(null, "Selecione pelo menos um registro da tabela!");
				} else {
					AutorController autorController = new AutorController();

					int escolha = JOptionPane.showOptionDialog(null, "Qual status deseja definir?",
							"Alteração de Status", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Desativado", "Ativado" }, "Desativado");

					boolean status = escolha == 1;

					for (int selectedRow : selectedRows) {

						Integer id = (Integer) headerTableConsulta.getModel().getValueAt(selectedRow, 0);

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
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(36)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(labelPesquisar)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addComponent(buttonVoltar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGap(266)
								.addComponent(buttonCadastrar, GroupLayout.PREFERRED_SIZE, 95,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(buttonEditar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnAlterarStatus, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(tableConsulta, GroupLayout.PREFERRED_SIZE, 624,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.LEADING, layout.createSequentialGroup()
														.addComponent(inputPesquisar, GroupLayout.PREFERRED_SIZE, 526,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(buttonPesquisar)))
										.addPreferredGap(ComponentPlacement.RELATED))))
				.addGap(46)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(36).addComponent(labelPesquisar)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(inputPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonPesquisar))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tableConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(11)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(buttonVoltar)
								.addComponent(btnAlterarStatus).addComponent(buttonEditar)
								.addComponent(buttonCadastrar))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
	}

	private void consultarAutor() {
		String pesquisa = inputPesquisar.getText();

		DefaultTableModel tableModel = (DefaultTableModel) headerTableConsulta.getModel();
		tableModel.setRowCount(0);
		AutorController autorController = new AutorController();

		try {
			ArrayList<Autor> autores = autorController.consultarAutores(pesquisa);
			autores.forEach((Autor autor) -> {
				tableModel.addRow(new Object[] { autor.getId(), autor.getNome(), autor.getStatus() });
			});

			headerTableConsulta.setModel(tableModel);
		} catch (ExceptionDAO e) {
			Logger.getLogger(TelaConsultarAutor.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	void atualizarListaAutores() {
		consultarAutor();
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
