package controller;

import java.util.ArrayList;

import dao.ExceptionDAO;
import model.Autor;

public class AutorController {

	public boolean cadastrarAutor(String nome, boolean status) throws ExceptionDAO {

		if (nome != null && nome.length() > 0) {
			Autor autor = new Autor(nome, status);

			autor.cadastrar(autor);

			return true;
		}

		return false;
	}

	public ArrayList<Autor> consultarAutores(String pesquisa, boolean status) throws ExceptionDAO {
		return new Autor().consultar(pesquisa, status);
	}

	public boolean alterarAutor(Integer id, String nome, boolean status) throws ExceptionDAO {

		if (nome != null && nome.length() > 0) {
			Autor autor = new Autor(id, nome, status);

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
