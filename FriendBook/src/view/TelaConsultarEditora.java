package view;

import model.Editora;
import dao.ExceptionDAO;
import controller.AutorController;
import controller.EditoraController;

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
 * Tela de consulta de Editoras
 *
 * @author rhogg
 */
public class TelaConsultarEditora extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputPesquisar;
	private JCheckBox checkboxStatus;

	private JScrollPane tableConsulta;
	private JTable tableConsultaModel;
	private TelaEditarEditora telaEditarEditora = new TelaEditarEditora(this);

	public TelaConsultarEditora() {
		setMaximumSize(new Dimension(900, 680));
		setMinimumSize(new Dimension(900, 680));
		getContentPane().setBackground(new Color(31, 31, 31));
		setResizable(false);
		initComponents();
	}

	public TelaConsultarEditora(TelaEditarEditora telaEditarEditora) {
		this.telaEditarEditora = telaEditarEditora;
		initComponents();
	}

	private void initComponents() {

		// Propriedades da tela
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Editoras");

		// Componentes da tela
				 JLabel labelPesquisar = new javax.swing.JLabel("Informe o nome do Autor");
				 labelPesquisar.setForeground(new Color(255, 255, 255));
				 labelPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 12));

				inputPesquisar = new javax.swing.JTextField();
				inputPesquisar.setDropMode(DropMode.INSERT);
				inputPesquisar.setBorder(null);
				inputPesquisar.setForeground(new Color(255, 255, 255));
				inputPesquisar.setBackground(new Color(91, 91, 91));
				inputPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				inputPesquisar.setPreferredSize(new Dimension(inputPesquisar.getPreferredSize().width, 26));
				inputPesquisar.setBorder(BorderFactory.createCompoundBorder(
					    inputPesquisar.getBorder(),
					    BorderFactory.createEmptyBorder(0, 8, 0, 8)
					));

				checkboxStatus = new JCheckBox("Ativo");
				checkboxStatus.setForeground(new Color(255, 255, 255));
				checkboxStatus.setBackground(new Color(31, 31, 31));
				checkboxStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				checkboxStatus.setSelected(true);

				// Botões e eventos
				JButton buttonPesquisar = new javax.swing.JButton("Pesquisar");
				buttonPesquisar.setForeground(new Color(255, 255, 255));
				buttonPesquisar.setBackground(new Color(31, 31, 31));		
				buttonPesquisar.setFont(new Font("Segoe UI", Font.BOLD, 12));
				buttonPesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						consultarEditora();
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
				tableConsulta = new javax.swing.JScrollPane();
				tableConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 12));

				tableConsultaModel = new javax.swing.JTable();
				tableConsultaModel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				tableConsultaModel.setForeground(new Color(0, 0, 0));
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
					layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addGap(41)
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tableConsulta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelPesquisar)
										.addComponent(inputPesquisar, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(buttonPesquisar)
										.addComponent(checkboxStatus)))
								.addGroup(Alignment.LEADING, layout.createSequentialGroup()
									.addComponent(buttonVoltar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addGap(420)
									.addComponent(buttonCadastrar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(buttonEditar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAlterarStatus)))
							.addGap(41))
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
							.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(buttonVoltar)
								.addComponent(btnAlterarStatus)
								.addComponent(buttonEditar)
								.addComponent(buttonCadastrar))
							.addGap(24))
				);
				getContentPane().setLayout(layout);

				pack();
			}
	
	private void consultarEditora() {
		String pesquisa = inputPesquisar.getText();
		boolean status = checkboxStatus.isSelected();

		DefaultTableModel tableModel = (DefaultTableModel) tableConsultaModel.getModel();
		tableModel.setRowCount(0);
		EditoraController editoraController = new EditoraController();

		try {
			ArrayList<Editora> editoras = editoraController.consultarEditoras(pesquisa,status);
			editoras.forEach((Editora editora) -> {
				tableModel.addRow(new Object[] { editora.getId(), editora.getNome(), editora.getStatus() });
			});

			tableConsultaModel.setModel(tableModel);
		} catch (ExceptionDAO e) {
			Logger.getLogger(TelaConsultarEditora.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void atualizarListaEditoras() {
		consultarEditora();
	}

	public void cadastrarRegistro() {
		TelaCadastrarEditora telaCadastrarEditora = new TelaCadastrarEditora(TelaConsultarEditora.this);
		telaCadastrarEditora.setVisible(true);
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

			telaEditarEditora.setVisible(true);
			telaEditarEditora.buscarEditora(id, nome, status);
		}
	}

	public void alterarStatusRegistro() {
		int[] selectedRows = tableConsultaModel.getSelectedRows();

		if (selectedRows.length < 1) {
			JOptionPane.showMessageDialog(null, "Selecione pelo menos um registro da tabela!");
		} else {
			EditoraController editoraController = new EditoraController();

			int escolha = JOptionPane.showOptionDialog(null, "Qual status deseja definir?", "Alteração de Status",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] { "Desativado", "Ativado" }, "Desativado");

			boolean status = escolha == 1;

			for (int selectedRow : selectedRows) {

				Integer id = (Integer) tableConsultaModel.getModel().getValueAt(selectedRow, 0);

				try {
					editoraController.atualizarEditora(id, status);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro: " + ex);
				} finally {
					atualizarListaEditoras();
				}
			}
		}
	}
}
