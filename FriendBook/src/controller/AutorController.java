package controller;

import java.util.ArrayList;

import dao.ExceptionDAO;
import model.Autor;

public class AutorController {

	public boolean cadastrarAutor(String nome, String cpf, boolean status) throws ExceptionDAO {

		if (nome != null && nome.length() > 0 && cpf.length() < 12) {
			Autor autor = new Autor(nome, cpf, status);

			autor.cadastrar(autor);

			return true;
		}

		return false;
	}

	public ArrayList<Autor> consultarAutores(String pesquisa, boolean status) throws ExceptionDAO {
		return new Autor().consultar(pesquisa, status);
	}

	public boolean alterarAutor(Integer id, String nome, String cpf, boolean status) throws ExceptionDAO {

		if (nome != null && nome.length() > 0) {
			Autor autor = new Autor(id, nome, cpf, status);

			autor.editar(autor);

			return true;
		}

		return false;
	}

	public boolean atualizarAutor(Integer id, boolean status) throws ExceptionDAO {

		if (id != null) {
			Autor autor = new Autor(id, status);

			autor.alterarStatus(autor);

			return true;
		}

		return false;
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		return new Autor().buscarUltimoId();
	}
}
