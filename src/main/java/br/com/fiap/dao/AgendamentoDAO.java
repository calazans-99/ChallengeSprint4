package br.com.fiap.dao;

import br.com.fiap.model.Agendamento;
import br.com.fiap.model.Carro;
import br.com.fiap.model.Servico;
import br.com.fiap.to.AgendamentoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    // Método para adicionar um agendamento ao banco de dados
    public void adicionarAgendamento(AgendamentoTO agendamento) {
        String sql = "INSERT INTO Agendamento (data_hora, carro_id, servico_id) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, Timestamp.valueOf(agendamento.getDataHora()));
            stmt.setInt(2, agendamento.getCarro().getId());
            stmt.setInt(3, agendamento.getServico().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                agendamento.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar agendamento: " + e.getMessage());
        }
    }

    // Método para buscar um agendamento pelo ID
    public Agendamento buscarAgendamento(int id) {
        String sql = "SELECT * FROM Agendamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Agendamento(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        new Carro(rs.getInt("carro_id")), // Usando apenas o ID do carro
                        new Servico(rs.getInt("servico_id")) // Usando apenas o ID do serviço
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar agendamento: " + e.getMessage());
        }
        return null;
    }

    // Método para atualizar um agendamento no banco de dados
    public void atualizarAgendamento(AgendamentoTO agendamento) {
        String sql = "UPDATE Agendamento SET data_hora = ?, carro_id = ?, servico_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(agendamento.getDataHora()));
            stmt.setInt(2, agendamento.getCarro().getId());
            stmt.setInt(3, agendamento.getServico().getId());
            stmt.setInt(4, agendamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar agendamento: " + e.getMessage());
        }
    }

    // Método para deletar um agendamento pelo ID
    public void deletarAgendamento(int id) {
        String sql = "DELETE FROM Agendamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar agendamento: " + e.getMessage());
        }
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> listarAgendamentos() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        new Carro(rs.getInt("carro_id")), // Usando apenas o ID do carro
                        new Servico(rs.getInt("servico_id")) // Usando apenas o ID do serviço
                );
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }
}
