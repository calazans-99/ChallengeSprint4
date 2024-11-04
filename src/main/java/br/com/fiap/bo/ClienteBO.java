package br.com.fiap.bo;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.to.ClienteTO;

import java.util.List;

public class ClienteBO {
    private ClienteDAO clienteDAO;

    public ClienteBO() {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(ClienteTO cliente) throws Exception {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new Exception("O nome do cliente é obrigatório.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new Exception("O email do cliente é obrigatório.");
        }

        ClienteTO clienteExistente = clienteDAO.buscarPorEmail(cliente.getEmail());
        if (clienteExistente != null) {
            throw new Exception("Já existe um cliente cadastrado com este email.");
        }

        clienteDAO.adicionarCliente(cliente);
    }

    public ClienteTO buscarCliente(int id) throws Exception {
        ClienteTO cliente = clienteDAO.buscarCliente(id);
        if (cliente == null) {
            throw new Exception("Cliente não encontrado.");
        }
        return cliente;
    }

    public void atualizarCliente(ClienteTO cliente) throws Exception {
        if (cliente.getId() <= 0) {
            throw new Exception("ID do cliente inválido.");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new Exception("O nome do cliente é obrigatório.");
        }

        clienteDAO.atualizarCliente(cliente);
    }

    public void deletarCliente(int id) throws Exception {
        ClienteTO cliente = clienteDAO.buscarCliente(id);
        if (cliente == null) {
            throw new Exception("Cliente não encontrado para exclusão.");
        }
        clienteDAO.deletarCliente(id);
    }

    public List<ClienteTO> listarClientes() {
        return clienteDAO.listarClientes();
    }
}
