package view;

import controller.AutorController;
import controller.EditoraController;
import controller.LivroController;
import model.Autor;
import model.Editora;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class TelaFormLivro extends JFrame {

	private Integer idLivro = 0;
	private Editora editora = null;
	private Autor autor = null;
	private String title;

	private JTextField inputID;
	private JTextField inputTitulo;
	@SuppressWarnings("rawtypes")
	private JComboBox selectGenero;
	@SuppressWarnings("rawtypes")
	private JComboBox selectEditora;
	@SuppressWarnings("rawtypes")
	private JComboBox selectAutor;
	private JCheckBox checkboxStatus;
	private JCheckBox checkboxDisponibilidade;

	private TelaConsultarLivro telaConsultarLivro;
	private LivroController livroController = new LivroController();

	public TelaFormLivro(TelaConsultarLivro telaConsultar, String title) {
		getContentPane().setForeground(new Color(255, 255, 255));
		this.title = title;

		setMinimumSize(new Dimension(900, 680));
		setPreferredSize(new Dimension(900, 680));
		setMaximumSize(new Dimension(900, 680));
		getContentPane().setBackground(new Color(31, 31, 31));
		setResizable(false);
		this.telaConsultarLivro = telaConsultar;
		initComponents();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
		// Propriedades da tela
		setTitle(this.title + " Livro");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Instancias do Controllers
		EditoraController editoraController = new EditoraController();
		AutorController autorController = new AutorController();

		// Componentes da tela
		JLabel labelTitulo = new JLabel(this.title + " Livro");
		labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		labelTitulo.setForeground(new Color(255, 255, 255));

		JLabel labelID = new JLabel("ID");
		labelID.setForeground(new Color(255, 255, 255));
		labelID.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelNome = new JLabel("Título");
		labelNome.setForeground(new Color(255, 255, 255));
		labelNome.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelGenero = new JLabel("Gênero");
		labelGenero.setForeground(new Color(255, 255, 255));
		labelGenero.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelEditora = new JLabel("Editora");
		labelEditora.setForeground(Color.WHITE);
		labelEditora.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelAutor = new JLabel("Autor");
		labelAutor.setForeground(Color.WHITE);
		labelAutor.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelStatus = new JLabel("Status");
		labelStatus.setForeground(new Color(255, 255, 255));
		labelStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel labelDisponibilidade = new JLabel("Disponibilidade");
		labelDisponibilidade.setForeground(Color.WHITE);
		labelDisponibilidade.setFont(new Font("Segoe UI", Font.BOLD, 14));

		inputID = new JTextField();
		inputID.setFont(new Font("Segoe UI", Font.BOLD, 12));
		inputID.setHorizontalAlignment(SwingConstants.CENTER);
		inputID.setText(buscarUltimoId());
		inputID.setEditable(false);
		inputID.setBorder(new EmptyBorder(6, 6, 6, 6));
		inputID.setForeground(new Color(255, 255, 255));
		inputID.setBackground(new Color(91, 91, 91));
		inputID.setMinimumSize(new Dimension(56, 26));
		inputID.setPreferredSize(new Dimension(56, 26));
		inputID.setMaximumSize(new Dimension(56, 26));

		inputTitulo = new JTextField();
		inputTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		inputTitulo.setBorder(new EmptyBorder(6, 6, 6, 6));
		inputTitulo.setForeground(new Color(255, 255, 255));
		inputTitulo.setBackground(new Color(91, 91, 91));
		inputTitulo.setPreferredSize(new Dimension(inputTitulo.getPreferredSize().width, 26));

		selectGenero = new JComboBox();
		selectGenero.setModel(new DefaultComboBoxModel(new String[] { "Romance", "Ficção Científica", "Fantasia",
				"Mistério", "Suspense", "Terror", "Policial", "Aventura", "História Alternativa", "Drama", "Comédia",
				"História de Amor", "Literatura Clássica", "Não Ficção", "Autobiografia", "Biografia", "Poesia",
				"Contos", "História Real", "Literatura Infantil" }));
		selectGenero.setFont(new Font("Segoe UI", Font.BOLD, 12));
		selectGenero.setForeground(new Color(255, 255, 255));
		selectGenero.setBackground(new Color(91, 91, 91));

		selectEditora = new JComboBox();
		selectEditora.setFont(new Font("Segoe UI", Font.BOLD, 12));
		selectEditora.setPreferredSize(new Dimension(30, 26));
		selectEditora.setForeground(Color.WHITE);
		selectEditora.setBackground(new Color(91, 91, 91));
		try {
			DefaultComboBoxModel selectEditoraModel = (DefaultComboBoxModel) selectEditora.getModel();
			ArrayList<Editora> editoras = editoraController.consultarEditoras("", true);
			editoras.forEach((Editora editora) -> {
				selectEditoraModel.addElement(editora.getNome());
			});

			selectEditora.setModel(selectEditoraModel);

			if (!editoras.isEmpty())
				editora = editoras.get(selectEditora.getSelectedIndex());

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro: " + ex);
		}

		selectAutor = new JComboBox();
		selectAutor.setFont(new Font("Segoe UI", Font.BOLD, 12));
		selectAutor.setForeground(Color.WHITE);
		selectAutor.setBackground(new Color(91, 91, 91));
		try {
			DefaultComboBoxModel selectAutorModel = (DefaultComboBoxModel) selectAutor.getModel();
			ArrayList<Autor> autores = autorController.consultarAutores("", true);
			autores.forEach((Autor autor) -> {
				selectAutorModel.addElement(autor.getNome());
			});

			selectAutor.setModel(selectAutorModel);

			if (!autores.isEmpty())
				autor = autores.get(selectAutor.getSelectedIndex());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro: " + ex);
		}

		checkboxDisponibilidade = new JCheckBox();
		checkboxDisponibilidade.setSelected(true);
		checkboxDisponibilidade.setText("Disponível");
		checkboxDisponibilidade.setForeground(Color.WHITE);
		checkboxDisponibilidade.setFont(new Font("Segoe UI", Font.BOLD, 12));
		checkboxDisponibilidade.setBackground(new Color(31, 31, 31));

		checkboxStatus = new JCheckBox();
		checkboxStatus.setSelected(true);
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
						.addGroup(layout.createSequentialGroup().addGap(111)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(inputID, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(labelID))
												.addGap(18)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(inputTitulo, GroupLayout.PREFERRED_SIZE, 589,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(labelNome)))
										.addComponent(labelGenero)
										.addComponent(labelEditora, GroupLayout.PREFERRED_SIZE, 49,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelAutor, GroupLayout.PREFERRED_SIZE, 49,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup().addComponent(checkboxStatus).addGap(18)
												.addComponent(checkboxDisponibilidade))
										.addGroup(layout.createSequentialGroup().addComponent(labelStatus).addGap(34)
												.addComponent(labelDisponibilidade))
										.addComponent(selectGenero, 0, 663, Short.MAX_VALUE)
										.addComponent(selectEditora, GroupLayout.PREFERRED_SIZE, 663,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(selectAutor, GroupLayout.PREFERRED_SIZE, 663,
												GroupLayout.PREFERRED_SIZE))))
						.addGap(110))
				.addGroup(layout.createSequentialGroup().addGap(248)
						.addComponent(buttonVoltar, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE).addGap(124)
						.addComponent(buttonFormAction, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(248)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(56).addComponent(labelTitulo).addGap(50)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
						.addComponent(labelID).addPreferredGap(ComponentPlacement.RELATED).addComponent(inputID,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addComponent(labelNome)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(inputTitulo,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addComponent(labelGenero).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(selectGenero, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(labelEditora, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(selectEditora, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE).addGap(13)
				.addComponent(labelAutor, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addGap(5)
				.addComponent(selectAutor, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(labelStatus)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(checkboxStatus)
										.addComponent(checkboxDisponibilidade, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(labelDisponibilidade, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
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
		if (editora != null && autor != null) {
			String titulo = inputTitulo.getText();
			String genero = (String) selectGenero.getSelectedItem();
			boolean status = checkboxStatus.isSelected();
			Integer idEditora = editora.getId();
			Integer idAutor = autor.getId();
			boolean disponibilidade = checkboxDisponibilidade.isSelected();
			boolean sucesso = false;

			if (this.title == "Cadastrar") {
				try {
					sucesso = livroController.cadastrarLivro(titulo, genero, status, idEditora, idAutor,
							disponibilidade);

					if (sucesso == true) {
						telaConsultarLivro.atualizarListaLivros();
						JOptionPane.showMessageDialog(null, "Livro(a) cadastrado(a) com sucesso.");
					} else {
						JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos corretamente!");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro: " + ex);
				} finally {
					if (sucesso)
						dispose();
				}
			} else if (this.title == "Editar") {
				try {
					sucesso = livroController.alterarLivro(idLivro, titulo, genero, status, idEditora, idAutor,
							disponibilidade);

					if (sucesso == true) {
						telaConsultarLivro.atualizarListaLivros();
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
		}else {
			if(autor == null) {
				JOptionPane.showMessageDialog(null, "Não existe cadastro para autores. É necessário criar pelo menos um!");				
			}
			
			if(editora == null) {
				JOptionPane.showMessageDialog(null, "Não existe cadastro para editoras. É necessário criar pelo menos um!");				
			}
			
			if(autor == null && editora == null) {
				JOptionPane.showMessageDialog(null, "Não existe cadastro para autores e nem editoras. É necessário criar pelo menos um de cada!");				
			}
		}
	}

	public void buscarLivro(Integer id, String titulo, String genero, boolean status, String nomeEditora,
			String nomeAutor, boolean disponibilidade) {
		this.idLivro = id;
		this.inputID.setText(id.toString());
		this.inputID.setEditable(false);
		this.inputTitulo.setText(titulo);
		this.selectGenero.setSelectedItem(genero);
		this.checkboxStatus.setSelected(status);
		this.selectEditora.setSelectedItem(nomeEditora);
		this.selectAutor.setSelectedItem(nomeAutor);
		this.checkboxStatus.setSelected(disponibilidade);
	}

	public String buscarUltimoId() {
		Integer id = 0;

		try {
			id = livroController.buscarUltimoId();

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
			java.util.logging.Logger.getLogger(TelaFormLivro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaFormLivro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaFormLivro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaFormLivro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
	}
}
