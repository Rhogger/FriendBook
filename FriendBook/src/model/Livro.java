package model;

import java.util.ArrayList;

import dao.ExceptionDAO;
import dao.LivroDAO;

public class Livro {
	private Integer id;
	private String titulo;
	private String genero;
	private boolean status;
	private Integer idEditora;
	private String nomeEditora;
	private Integer idAutor;
	private String nomeAutor;
	private Boolean disponibilidade;

	public Livro() {
	}

	public Livro(Integer id, boolean status) {
		this.id = id;
		this.status = status;
	}

	// OBS: boolean t serve para nada, somente para criar construtores para status e
	// disponibilidade com assinatura de construtor diferentes
	public Livro(Integer id, boolean disponibilidade, boolean t) {
		this.id = id;
		this.disponibilidade = disponibilidade;
	}

	public Livro(String titulo, String genero, boolean status, Integer idEditora, Integer idAutor,
			boolean disponibilidade) {
		this.titulo = titulo;
		this.genero = genero;
		this.status = status;
		this.idEditora = idEditora;
		this.idAutor = idAutor;
		this.disponibilidade = disponibilidade;
	}

	public Livro(Integer id, String titulo, String genero, boolean status, Integer idEditora, Integer idAutor,
			boolean disponibilidade) {
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.status = status;
		this.idEditora = idEditora;
		this.idAutor = idAutor;
		this.disponibilidade = disponibilidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(Integer idEditora) {
		this.idEditora = idEditora;
	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Boolean getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public void cadastrar(Livro livro) throws ExceptionDAO {
		new LivroDAO().cadastrarLivro(livro);
	}

	public ArrayList<Livro> consultar(String pesquisa, boolean status, boolean disponibilidade) throws ExceptionDAO {
		return new LivroDAO().listarLivros(pesquisa, status, disponibilidade);
	}

	public void editar(Livro livro) throws ExceptionDAO {
		new LivroDAO().alterarLivro(livro);
	}

	public void alterarStatus(Livro livro) throws ExceptionDAO {
		new LivroDAO().AtualizarStatusLivro(livro);
	}

	public void alterarDdisponibilidade(Livro livro) throws ExceptionDAO {
		new LivroDAO().AtualizarDisponibilidadeLivro(livro);
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		return new LivroDAO().buscarUltimoId();
	}
}