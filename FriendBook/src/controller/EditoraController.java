package controller;

import java.util.ArrayList;

import dao.ExceptionDAO;
import model.Editora;

public class EditoraController {

	public boolean cadastrarEditora(String nome, boolean status) throws ExceptionDAO {

		if (nome != null && nome.length() > 0) {
			Editora editora = new Editora(nome, status);

			editora.cadastrar(editora);

			return true;
		}

		return false;
	}

	public ArrayList<Editora> consultarEditoras(String pesquisa, boolean status) throws ExceptionDAO {
		return new Editora().consultar(pesquisa, status);
	}

	public boolean alterarEditora(Integer id, String nome, boolean status) throws ExceptionDAO {

		if (nome != null && nome.length() > 0) {
			Editora editora = new Editora(id, nome, status);

			editora.editar(editora);

			return true;
		}

		return false;
	}

	public boolean atualizarEditora(Integer id, boolean status) throws ExceptionDAO {

		if (id != null) {
			Editora editora = new Editora(id, status);

			editora.alterarStatus(editora);

			return true;
		}

		return false;
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		return new Editora().buscarUltimoId();
	}
}
