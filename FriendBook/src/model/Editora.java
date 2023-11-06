package model;

import java.util.ArrayList;

import dao.EditoraDAO;
import dao.ExceptionDAO;

public class Editora {
	private Integer id;
	private String nome;
	private boolean status;

	public Editora() {
	}

	public Editora(Integer id) {
		this.id = id;
	}

	public Editora(Integer id, boolean status) {
		this.id = id;
		this.status = status;
	}

	public Editora(String nome, boolean status) {
		this.nome = nome;
		this.status = status;
	}

	public Editora(Integer id, String nome, boolean status) {
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

	public void cadastrar(Editora editora) throws ExceptionDAO {
		new EditoraDAO().cadastrarEditora(editora);
	}

	public ArrayList<Editora> consultar(String pesquisa, boolean status) throws ExceptionDAO {
		return new EditoraDAO().listarEditoras(pesquisa, status);
	}

	public void editar(Editora editora) throws ExceptionDAO {
		new EditoraDAO().alterarEditora(editora);
	}

	public void alterarStatus(Editora editora) throws ExceptionDAO {
		new EditoraDAO().AtualizarStatusEditora(editora);
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		return new EditoraDAO().buscarUltimoId();
	}

	public String buscarPorId(Integer id) throws ExceptionDAO {
		return new EditoraDAO().buscarPorId(id);
	}
}