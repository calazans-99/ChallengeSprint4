package br.com.fiap.bo;

import br.com.fiap.dao.ServicoDAO;
import br.com.fiap.to.ServicoTO;

import java.sql.SQLException;
import java.util.List;

public class ServicoBO {
    private ServicoDAO servicoDAO;

    public ServicoBO() {
        this.servicoDAO = new ServicoDAO();
    }

    public void cadastrarServico(ServicoTO servico) throws Exception {
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new Exception("A descrição do serviço é obrigatória.");
        }
        if (servico.getPreco() <= 0) {
            throw new Exception("O preço do serviço deve ser maior que zero.");
        }

        servicoDAO.adicionarServico(servico);
    }

    public ServicoTO buscarServico(int id) throws Exception {
        ServicoTO servico = servicoDAO.buscarServico(id);
        if (servico == null) {
            throw new Exception("Serviço não encontrado.");
        }
        return servico;
    }

    public void atualizarServico(ServicoTO servico) throws Exception {
        if (servico.getId() <= 0) {
            throw new Exception("ID do serviço inválido.");
        }
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new Exception("A descrição do serviço é obrigatória.");
        }

        servicoDAO.atualizarServico(servico);
    }

    public void deletarServico(int id) throws Exception {
        ServicoTO servico = servicoDAO.buscarServico(id);
        if (servico == null) {
            throw new Exception("Serviço não encontrado para exclusão.");
        }
        servicoDAO.deletarServico(id);
    }

    public List<ServicoTO> listarServicos() throws SQLException {
        return servicoDAO.listarServicos();
    }
}
