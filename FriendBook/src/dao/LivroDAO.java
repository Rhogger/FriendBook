package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Livro;

public class LivroDAO {

	private static Connection connection = null;
	private static PreparedStatement pStatement = null;

	public void cadastrarLivro(Livro livro) throws ExceptionDAO {
		String sql = "INSERT INTO livros (titulo, genero, status, id_editora, id_autor, disponibilidade) VALUE (?,?,?,?,?,?)";

		System.out.println(livro.getTitulo());
		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, livro.getTitulo());
			pStatement.setString(2, livro.getGenero());
			pStatement.setBoolean(3, livro.getStatus());
			pStatement.setInt(4, livro.getIdEditora());
			pStatement.setInt(5, livro.getIdAutor());
			pStatement.setBoolean(6, livro.getDisponibilidade());
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao cadastrar livro(a): " + e);
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

	public ArrayList<Livro> listarLivros(String pesquisa, boolean status, boolean disponibilidade) throws ExceptionDAO {
		String sql = "SELECT l.*, e.nome, a.nome FROM livros AS l INNER JOIN editora AS e ON l.id_editora = e.id INNER JOIN autor AS a ON l.id_autor = a.id WHERE l.titulo like ? AND l.status = ? AND l.disponibilidade = ? ORDER BY l.id";

		connection = null;
		pStatement = null;
		ArrayList<Livro> livros = null;

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "%" + pesquisa + "%");
			pStatement.setBoolean(2, status);
			pStatement.setBoolean(3, disponibilidade);

			ResultSet rs = pStatement.executeQuery();

			if (rs != null) {
				livros = new ArrayList<Livro>();

				while (rs.next()) {
					Livro livro = new Livro();
					livro.setId(rs.getInt("id"));
					livro.setTitulo(rs.getString("titulo"));
					livro.setGenero(rs.getString("genero"));
					livro.setStatus(rs.getBoolean("status"));
					livro.setIdEditora(rs.getInt("id_editora"));
					livro.setNomeEditora(rs.getString("e.nome"));
					livro.setIdAutor(rs.getInt("id_autor"));
					livro.setNomeAutor(rs.getString("a.nome"));
					livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
					livros.add(livro);
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao consultar livros!" + e);
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

		return livros;
	}

	public void alterarLivro(Livro livro) throws ExceptionDAO {
		Integer id = livro.getId();
		String titulo = livro.getTitulo();
		String genero = livro.getGenero();
		boolean status = livro.getStatus();
		Integer idEditora = livro.getIdEditora();
		Integer idAutor = livro.getIdAutor();

		String sql = "UPDATE livros SET titulo = ?, genero = ?', status = ?, id_editora = ?, id_autor = ? WHERE id = ?";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, titulo);
			pStatement.setString(2, genero);
			pStatement.setBoolean(3, status);
			pStatement.setInt(4, idEditora);
			pStatement.setInt(5, idAutor);
			pStatement.setInt(6, id);
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

	public void AtualizarStatusLivro(Livro livro) throws ExceptionDAO {
		Integer id = livro.getId();
		boolean status = livro.getStatus();

		String sql = "UPDATE livros SET status = ? WHERE id = ?";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setBoolean(1, status);
			pStatement.setInt(2, id);
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao atualizar status do(a) livro(a): " + e);
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

	public void AtualizarDisponibilidadeLivro(Livro livro) throws ExceptionDAO {
		Integer id = livro.getId();
		boolean disponibilidade = livro.getDisponibilidade();

		String sql = "UPDATE livros SET disponibilidade = ? WHERE id = ?";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setBoolean(1, disponibilidade);
			pStatement.setInt(2, id);
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao atualizar status do(a) livro(a): " + e);
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
		String sql = "SELECT id FROM livros ORDER BY id DESC LIMIT 1";

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
			throw new ExceptionDAO("Erro ao consultar livros!" + e);
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
