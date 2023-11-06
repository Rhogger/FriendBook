package controller;

import java.util.ArrayList;

import dao.ExceptionDAO;
import model.Livro;

public class LivroController {

	public boolean cadastrarLivro(String titulo, String genero, boolean status, Integer idEditora, Integer idAutor,
			boolean disponibilidade) throws ExceptionDAO {

		if (titulo != null && titulo.length() > 0 && genero != null && idEditora != null && idAutor != null) {
			Livro livro = new Livro(titulo, genero, status, idEditora, idAutor, disponibilidade);

			livro.cadastrar(livro);
			return true;
		}

		return false;
	}

	public ArrayList<Livro> consultarLivros(String pesquisa, boolean status, boolean disponibilidade) throws ExceptionDAO {

		return new Livro().consultar(pesquisa, status, disponibilidade);
	}

	public boolean alterarLivro(Integer id, String titulo, String genero, boolean status, Integer idEditora,
			Integer idAutor, boolean disponibilidade) throws ExceptionDAO {

		if (titulo != null && titulo.length() > 0 && genero != null && idEditora != null && idAutor != null) {
			Livro livro = new Livro(id, titulo, genero, status, idEditora, idAutor, disponibilidade);

			livro.editar(livro);

			return true;
		}

		return false;
	}

	public boolean atualizarStatusLivro(Integer id, boolean status) throws ExceptionDAO {

		if (id != null) {
			Livro livro = new Livro(id, status);

			livro.alterarStatus(livro);

			return true;
		}

		return false;
	}

	public boolean atualizarDisponibilidadeLivro(Integer id, boolean disponibilidade) throws ExceptionDAO {

		if (id != null) {
			Livro livro = new Livro(id, disponibilidade, false);

			livro.alterarStatus(livro);

			return true;
		}

		return false;
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		return new Livro().buscarUltimoId();
	}
}
