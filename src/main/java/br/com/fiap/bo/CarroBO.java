package br.com.fiap.bo;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.model.Carro;
import br.com.fiap.to.CarroTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class CarroBO {
    private CarroDAO carroDAO;

    public CarroBO(Connection connection) {
        this.carroDAO = new CarroDAO(connection);
    }

    public void cadastrarCarro(CarroTO carro) throws IllegalArgumentException {
        validarCarro(carro);

        CarroTO carroExistente = carroDAO.buscarPorModeloEAno(carro.getModelo(), carro.getAno());
        if (carroExistente != null) {
            throw new IllegalArgumentException("Já existe um carro cadastrado com este modelo e ano.");
        }

        carroDAO.adicionarCarro(carro);
    }

    public List<Carro> listarCarros() throws SQLException {
        return carroDAO.listarCarros();
    }

    private void validarCarro(CarroTO carro) throws IllegalArgumentException {
        if (carro.getMarca() == null || carro.getMarca().isEmpty()) {
            throw new IllegalArgumentException("A marca do carro é obrigatória.");
        }
        if (carro.getModelo() == null || carro.getModelo().isEmpty()) {
            throw new IllegalArgumentException("O modelo do carro é obrigatório.");
        }
        if (carro.getAno() == 0) {
            throw new IllegalArgumentException("O ano do carro é obrigatório.");
        }

        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        if (carro.getAno() < 1886 || carro.getAno() > anoAtual) {
            throw new IllegalArgumentException("O ano do carro deve estar entre 1886 e o ano atual.");
        }
    }
}
