package br.com.fiap.dao;

import br.com.fiap.to.ServicoTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    // Método para adicionar um serviço ao banco de dados
    public void adicionarServico(ServicoTO servico) {
        String sql = "INSERT INTO Servico (descricao, preco) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                servico.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar serviço: " + e.getMessage());
        }
    }

    // Método para buscar um serviço pelo ID
    public ServicoTO buscarServico(int id) {
        String sql = "SELECT * FROM Servico WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ServicoTO(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço: " + e.getMessage());
        }
        return null;
    }

    // Método para atualizar um serviço no banco de dados
    public void atualizarServico(ServicoTO servico) {
        String sql = "UPDATE Servico SET descricao = ?, preco = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    // Método para deletar um serviço pelo ID
    public void deletarServico(int id) {
        String sql = "DELETE FROM Servico WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço: " + e.getMessage());
        }
    }

    // Método para listar todos os serviços do banco de dados
    public List<ServicoTO> listarServicos() {
        List<ServicoTO> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ServicoTO servico = new ServicoTO(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                );
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        }
        return servicos;
    }
}
