package br.com.fiap.dao;

import br.com.fiap.model.Carro;
import br.com.fiap.to.CarroTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    public CarroDAO(Connection connection) {}

    // Método para adicionar um carro ao banco de dados
    public void adicionarCarro(CarroTO carro) {
        String sql = "INSERT INTO Carro (marca, modelo, ano) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAno());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                carro.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar carro: " + e.getMessage());
        }
    }

    // Método para buscar um carro pelo ID
    public Carro buscarCarro(int id) {
        String sql = "SELECT * FROM Carro WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Carro(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carro: " + e.getMessage());
        }
        return null;
    }

    // Método para atualizar um carro no banco de dados
    public void atualizarCarro(Carro carro) {
        String sql = "UPDATE Carro SET marca = ?, modelo = ?, ano = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAno());
            stmt.setInt(4, carro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar carro: " + e.getMessage());
        }
    }

    // Método para deletar um carro pelo ID
    public void deletarCarro(int id) {
        String sql = "DELETE FROM Carro WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar carro: " + e.getMessage());
        }
    }

    // Método para listar todos os carros do banco de dados
    public List<Carro> listarCarros() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM Carro";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano")
                );
                carros.add(carro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
        }
        return carros;
    }

    // Método para buscar por modelo e ano (implementação a ser feita)
    public CarroTO buscarPorModeloEAno(String modelo, int ano) {
        String sql = "SELECT * FROM Carro WHERE modelo = ? AND ano = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            stmt.setInt(2, ano);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CarroTO(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carro por modelo e ano: " + e.getMessage());
        }
        return null;
    }
}
