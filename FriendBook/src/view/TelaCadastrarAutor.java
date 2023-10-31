package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.AutorController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TelaCadastrarAutor extends JFrame {

	private javax.swing.JButton buttonVoltar;
	private javax.swing.JButton buttonCadastrar;
	private javax.swing.JCheckBox checkboxStatus;
	private javax.swing.JLabel labelID;
	private javax.swing.JLabel labelStatus;
	private javax.swing.JLabel labelNome;
	private javax.swing.JTextField inputID;
	private javax.swing.JTextField inputNome;

	private TelaConsultarAutor telaConsultarAutor;
	private AutorController autorController = new AutorController();

	public TelaCadastrarAutor(TelaConsultarAutor telaConsultar) {
		this.telaConsultarAutor = telaConsultar;
		initComponents();
	}

	public String buscarUltimoId() {
		Integer id = 0;

		try {
			id = autorController.buscarUltimoId();

			if (id == null) {
				id = 0;
			} else {
				id++;
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro: " + ex);
		}

		return id.toString();
	}

	private void initComponents() {

		buttonVoltar = new javax.swing.JButton();
		buttonVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		buttonCadastrar = new javax.swing.JButton();
		buttonCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nome = inputNome.getText();
				boolean status = checkboxStatus.isSelected();
				boolean sucesso;

				try {
					sucesso = autorController.cadastrarAutor(nome, status);

					if (sucesso == true) {
						telaConsultarAutor.atualizarListaAutores();
						JOptionPane.showMessageDialog(null, "Autor(a) cadastrado(a) com sucesso.");
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
		inputID.setText(buscarUltimoId());
		inputID.setEditable(false);
		inputNome = new javax.swing.JTextField();

		setTitle("Cadastrar Autor");
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		buttonVoltar.setText("Voltar");

		buttonCadastrar.setText("Cadastrar");

		checkboxStatus.setText("Ativo");

		labelID.setText("ID");

		labelStatus.setText("Status");

		labelNome.setText("Nome");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(80, 80, 80)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup()
												.addComponent(
														buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(buttonCadastrar))
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(inputID, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(inputNome,
														javax.swing.GroupLayout.PREFERRED_SIZE, 128,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addComponent(labelID)
												.addGap(38, 38, 38).addComponent(labelNome)))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(labelStatus).addComponent(checkboxStatus))))
						.addContainerGap(80, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(50, 50, 50)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelID).addComponent(labelStatus).addComponent(labelNome))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(inputID, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(checkboxStatus))
						.addGap(42, 42, 42)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(buttonVoltar).addComponent(buttonCadastrar))
						.addContainerGap(60, Short.MAX_VALUE)));

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
			java.util.logging.Logger.getLogger(TelaCadastrarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaCadastrarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaCadastrarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaCadastrarAutor.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
	}
}
