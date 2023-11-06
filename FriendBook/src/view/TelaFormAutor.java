package view;

import controller.AutorController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaFormAutor extends JFrame {

	private Integer idAutor = 0;
	private String title;

	private JTextField inputID;
	private JTextField inputNome;
	private JTextField inputCpf;
	private JCheckBox checkboxStatus;

	private TelaConsultarAutor telaConsultarAutor;
	private AutorController autorController = new AutorController();

	public TelaFormAutor(TelaConsultarAutor telaConsultar, String title) {
		this.title = title;

		setMinimumSize(new Dimension(900, 680));
		setPreferredSize(new Dimension(900, 680));
		setMaximumSize(new Dimension(900, 680));
		getContentPane().setBackground(new Color(31, 31, 31));
		setResizable(false);
		this.telaConsultarAutor = telaConsultar;
		initComponents();
	}

	private void initComponents() {
		// Propriedades da tela
		setTitle(this.title + " Autor");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Componentes da tela
		JLabel labelTitulo = new JLabel(this.title + " Autor");
		labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		labelTitulo.setForeground(new Color(255, 255, 255));

		JLabel labelID = new JLabel("ID");
		labelID.setForeground(new Color(255, 255, 255));
		labelID.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelNome = new JLabel("Nome");
		labelNome.setForeground(new Color(255, 255, 255));
		labelNome.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelCpf = new JLabel("CPF");
		labelCpf.setForeground(new Color(255, 255, 255));
		labelCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelStatus = new JLabel("Status");
		labelStatus.setForeground(new Color(255, 255, 255));
		labelStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));

		inputID = new JTextField();
		inputID.setHorizontalAlignment(SwingConstants.CENTER);
		inputID.setText(buscarUltimoId());
		inputID.setEditable(false);
		inputID.setBorder(new EmptyBorder(6, 6, 6, 6));
		inputID.setForeground(new Color(255, 255, 255));
		inputID.setBackground(new Color(91, 91, 91));
		inputID.setMinimumSize(new Dimension(56, 26));
		inputID.setPreferredSize(new Dimension(56, 26));
		inputID.setMaximumSize(new Dimension(56, 26));

		inputNome = new JTextField();
		inputNome.setBorder(new EmptyBorder(6, 6, 6, 6));
		inputNome.setForeground(new Color(255, 255, 255));
		inputNome.setBackground(new Color(91, 91, 91));
		inputNome.setPreferredSize(new Dimension(inputNome.getPreferredSize().width, 26));

		inputCpf = new JTextField();
		inputCpf.setBorder(new EmptyBorder(6, 6, 6, 6));
		inputCpf.setForeground(new Color(255, 255, 255));
		inputCpf.setBackground(new Color(91, 91, 91));
		inputCpf.setPreferredSize(new Dimension(inputCpf.getPreferredSize().width, 26));

		checkboxStatus = new JCheckBox();
		checkboxStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
		checkboxStatus.setForeground(new Color(255, 255, 255));
		checkboxStatus.setBackground(new Color(31, 31, 31));
		checkboxStatus.setText("Ativo");

		// Botões e eventos
		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonVoltar.setBorder(new EmptyBorder(4, 8, 4, 8));
		buttonVoltar.setBackground(new Color(173, 27, 27));
		buttonVoltar.setFont(new Font("Segoe UI", Font.BOLD, 18));
		buttonVoltar.setForeground(new Color(255, 255, 255));
		buttonVoltar.setMinimumSize(new Dimension(80, 36));
		buttonVoltar.setPreferredSize(new Dimension(80, 36));
		buttonVoltar.setMaximumSize(new Dimension(80, 36));
		buttonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton buttonFormAction = new JButton(this.title);
		buttonFormAction.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonFormAction.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonFormAction.setBorder(new EmptyBorder(4, 8, 4, 8));
		buttonFormAction.setBackground(new Color(3, 166, 0));
		buttonFormAction.setFont(new Font("Segoe UI", Font.BOLD, 18));
		buttonFormAction.setForeground(new Color(255, 255, 255));
		buttonFormAction.setMinimumSize(new Dimension(80, 36));
		buttonFormAction.setPreferredSize(new Dimension(80, 36));
		buttonFormAction.setMaximumSize(new Dimension(80, 36));
		buttonFormAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formAction();
			}
		});

		// Componentes e configuração da Tabela
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(346).addComponent(labelTitulo))
						.addGroup(layout.createSequentialGroup().addGap(111).addGroup(layout
								.createParallelGroup(Alignment.LEADING).addComponent(checkboxStatus)
								.addComponent(labelStatus)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(labelCpf)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(inputID, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(labelID))
												.addGap(18)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(inputNome, GroupLayout.PREFERRED_SIZE, 589,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(labelNome)))
										.addComponent(inputCpf)))))
						.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addGap(248)
						.addComponent(buttonVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(124)
						.addComponent(buttonFormAction, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(248)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(56).addComponent(labelTitulo).addGap(50)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
						.addComponent(labelID).addPreferredGap(ComponentPlacement.RELATED).addComponent(inputID,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addComponent(labelNome)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(inputNome,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(26).addComponent(labelCpf).addGap(18)
				.addComponent(inputCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(26).addComponent(labelStatus).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(checkboxStatus).addPreferredGap(ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonVoltar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonFormAction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(47)));
		getContentPane().setLayout(layout);

		pack();
	}
	
	public void formAction() {
		String nome = inputNome.getText();
		String cpf = inputCpf.getText();
		boolean status = checkboxStatus.isSelected();
		boolean sucesso = false;

		if (title == "Cadastrar") {
			try {
				sucesso = autorController.cadastrarAutor(nome,cpf, status);

				if (sucesso == true) {
					telaConsultarAutor.atualizarListaAutores();
					JOptionPane.showMessageDialog(null, "Autor(a) cadastrado(a) com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos corretamente!");
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro: " + ex);
			} finally {
				if(sucesso)
				dispose();
			}
		} else if (title == "Editar") {
			try {
				sucesso = autorController.alterarAutor(idAutor, nome, cpf, status);

				if (sucesso == true) {
					telaConsultarAutor.atualizarListaAutores();
					JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos corretamente!");
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro: " + ex);
			} finally {
				if(sucesso)
				dispose();
			}
		}
	}

	public void buscarAutor(Integer id, String nome,String cpf, boolean status) {
		this.idAutor = id;
		this.inputID.setText(id.toString());
		this.inputID.setEditable(false);
		this.inputNome.setText(nome);
		this.inputCpf.setText(cpf);
		this.checkboxStatus.setSelected(status);
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

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaFormAutor.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaFormAutor.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaFormAutor.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaFormAutor.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
	}
}
