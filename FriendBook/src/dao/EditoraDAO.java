package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Editora;

public class EditoraDAO {

	private static Connection connection = null;
	private static PreparedStatement pStatement = null;

	public void cadastrarEditora(Editora editora) throws ExceptionDAO {
		String sql = "INSERT INTO editora (nome, status) VALUE (?,?)";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, editora.getNome());
			pStatement.setBoolean(2, editora.getStatus());
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao cadastrar editora: " + e);
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

	public ArrayList<Editora> listarEditoras(String pesquisa, boolean status) throws ExceptionDAO {
		String sql = "SELECT * FROM editora WHERE nome like ? AND status = ? ORDER BY id";

		connection = null;
		pStatement = null;
		ArrayList<Editora> editoras = null;

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "%" + pesquisa + "%");
			pStatement.setBoolean(2, status);
			ResultSet rs = pStatement.executeQuery();

			if (rs != null) {
				editoras = new ArrayList<Editora>();

				while (rs.next()) {
					Editora editora = new Editora();
					editora.setId(rs.getInt("id"));
					editora.setNome(rs.getString("nome"));
					editora.setStatus(rs.getBoolean("status"));
					editoras.add(editora);
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao consultar editoras!" + e);
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

		return editoras;
	}

	public void alterarEditora(Editora editora) throws ExceptionDAO {
		String sql = "UPDATE editora SET nome = ?, status = ? WHERE id = ?";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, editora.getNome());
			pStatement.setBoolean(2, editora.getStatus());
			pStatement.setInt(3, editora.getId());
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

	public void AtualizarStatusEditora(Editora editora) throws ExceptionDAO {
		String sql = "UPDATE editora SET status = ? WHERE id = ?";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setBoolean(1, editora.getStatus());
			pStatement.setInt(2, editora.getId());
			pStatement.execute();
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao atualizar status da editora: " + e);
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
		String sql = "SELECT id FROM editora ORDER BY id DESC LIMIT 1";

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
			throw new ExceptionDAO("Erro ao consultar editoras!" + e);
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

	public String buscarPorId(Integer id) throws ExceptionDAO {
		String sql = "SELECT nome FROM editora WHERE id = ?";

		connection = null;
		pStatement = null;

		String nome = "Sem nome";

		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			ResultSet rs = pStatement.executeQuery(sql);

			if (rs != null) {
				if (rs.next() == false) {
					return nome;
				}

				nome = rs.getString("nome");
			}

		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao consultar editora!" + e);
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

		return nome;
	}
}
