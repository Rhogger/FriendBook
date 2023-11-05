package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Autor;

public class AutorDAO {

	private static Connection connection = null;
	private static PreparedStatement pStatement = null;

	public void cadastrarAutor(Autor autor) throws ExceptionDAO {
		String sql = "INSERT INTO autor (nome, cpf, status) VALUE (?,?,?)";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, autor.getNome());
			pStatement.setString(2, autor.getCpf());
			pStatement.setBoolean(3, autor.getStatus());
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao cadastrar autor(a): " + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão: " + e);
			}
		}
	}

	public ArrayList<Autor> listarAutores(String pesquisa, boolean status) throws ExceptionDAO {
		String sql = "SELECT * FROM autor WHERE nome like '%" + pesquisa + "%' AND status = true ORDER BY id";

		if (!status) {
			sql = "SELECT * FROM autor WHERE nome like '%" + pesquisa + "%' ORDER BY id";
		}

		connection = null;
		pStatement = null;
		ArrayList<Autor> autores = null;

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);

			if (rs != null) {
				autores = new ArrayList<Autor>();

				while (rs.next()) {
					Autor autor = new Autor();
					autor.setId(rs.getInt("id"));
					autor.setNome(rs.getString("nome"));
					autor.setCpf(rs.getString("cpf"));
					autor.setStatus(rs.getBoolean("status"));
					autores.add(autor);
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao consultar autores!" + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão: " + e);
			}
		}

		return autores;
	}

	public void alterarAutor(Autor autor) throws ExceptionDAO {
		Integer id = autor.getId();
		String nome = autor.getNome();
		String cpf = autor.getCpf();
		boolean status = autor.getStatus();
		String sql = "UPDATE autor SET nome = '" + nome + "', cpf = '" + cpf + "', status = " + status + " WHERE id = "
				+ id;

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao alterar cadastro: " + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão: " + e);
			}
		}
	}

	public void AtualizarStatusAutor(Autor autor) throws ExceptionDAO {
		Integer id = autor.getId();
		boolean status = autor.getStatus();

		String sql = "UPDATE autor SET status = " + status + " WHERE id = " + id;

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao atualizar status do(a) autor(a): " + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão: " + e);
			}
		}
	}

	public Integer buscarUltimoId() throws ExceptionDAO {
		String sql = "SELECT id FROM autor ORDER BY id DESC LIMIT 1";

		connection = null;
		pStatement = null;
		Integer id = 0;

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);

			if (rs != null) {

				if (rs.next() == false) {
					return 0;
				}
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao consultar autores!" + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão: " + e);
			}
		}

		return id;
	}
}
