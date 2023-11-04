package model;

import java.util.ArrayList;

import dao.AutorDAO;
import dao.ExceptionDAO;

public class Autor {
	private Integer id;
	private String nome;
	private boolean status;

	public Autor() {
	}

	public Autor(Integer id) {
		this.id = id;
	}
	
	public Autor(Integer id, boolean status) {
		this.id = id;
		this.status = status;
	}

	public Autor(String nome, boolean status) {
		this.nome = nome;
		this.status = status;
	}

	public Autor(Integer id, String nome, boolean status) {
		this.id = id;
		this.nome = nome;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void cadastrar(Autor autor) throws ExceptionDAO {
		new AutorDAO().cadastrarAutor(autor);
	}

	public ArrayList<Autor> consultar(String pesquisa, boolean status) throws ExceptionDAO {
		return new AutorDAO().listarAutores(pesquisa, status);
	}

	public void editar(Autor autor) throws ExceptionDAO {
		new AutorDAO().alterarAutor(autor);
		;
	}

	public void alterarStatus(Autor autor) throws ExceptionDAO {
		new AutorDAO().AtualizarStatusAutor(autor);
		;
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		return new AutorDAO().buscarUltimoId();
	}

}