package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.EditoraController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class TelaEditarEditora extends JFrame {

	private javax.swing.JButton buttonVoltar;
	private javax.swing.JButton buttonEditar;
	private javax.swing.JCheckBox checkboxStatus;
	private javax.swing.JLabel labelID;
	private javax.swing.JLabel labelStatus;
	private javax.swing.JLabel labelNome;
	private javax.swing.JTextField inputID;
	private javax.swing.JTextField inputNome;

	private Integer idEditora = 0;

	private TelaConsultarEditora telaConsultarEditora;
	private EditoraController editoraController = new EditoraController();

	public TelaEditarEditora() {
		initComponents();
	}

	public TelaEditarEditora(TelaConsultarEditora telaConsultar) {
		this.telaConsultarEditora = telaConsultar;
		initComponents();
	}

	public void buscarEditora(Integer id, String nome, boolean status) {
		this.idEditora = id;
		this.inputID.setText(id.toString());
		this.inputNome.setText(nome);
		this.checkboxStatus.setSelected(status);
	}

	private void initComponents() {

		buttonVoltar = new javax.swing.JButton();
		buttonVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		buttonEditar = new javax.swing.JButton();
		buttonEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nome = inputNome.getText();
				boolean status = checkboxStatus.isSelected();
				boolean sucesso;

				try {
					sucesso = editoraController.alterarEditora(idEditora, nome, status);

					if (sucesso == true) {
						telaConsultarEditora.atualizarListaEditoras();
						JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso.");
					} else {
						JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos corretamente!");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro: " + ex);
				} finally {
					dispose();
				}
			}
		});

		checkboxStatus = new javax.swing.JCheckBox();
		labelID = new javax.swing.JLabel();
		labelStatus = new javax.swing.JLabel();
		labelNome = new javax.swing.JLabel();
		inputID = new javax.swing.JTextField();
		inputID.setEditable(false);
		inputNome = new javax.swing.JTextField();

		setTitle("Editar Editora");
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		buttonVoltar.setText("Voltar");

		buttonEditar.setText("Editar");

		checkboxStatus.setText("Ativo");

		labelID.setText("ID");

		labelStatus.setText("Status");

		labelNome.setText("Nome");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(80)
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
								.addComponent(buttonVoltar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(buttonEditar, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(inputID, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(inputNome, GroupLayout.PREFERRED_SIZE, 128,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addComponent(labelID).addGap(38)
										.addComponent(labelNome)))
								.addGap(18).addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelStatus).addComponent(checkboxStatus))))
				.addContainerGap(80, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(50)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelID)
								.addComponent(labelStatus).addComponent(labelNome))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(inputID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(inputNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(checkboxStatus))
						.addGap(42).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(buttonVoltar)
								.addComponent(buttonEditar))
						.addContainerGap(60, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
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
			java.util.logging.Logger.getLogger(TelaEditarEditora.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaEditarEditora.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaEditarEditora.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaEditarEditora.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
	}
}