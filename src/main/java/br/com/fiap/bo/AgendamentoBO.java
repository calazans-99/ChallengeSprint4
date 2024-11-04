package br.com.fiap.bo;

import br.com.fiap.dao.AgendamentoDAO;
import br.com.fiap.model.Agendamento;
import br.com.fiap.to.AgendamentoTO;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoBO {
    private AgendamentoDAO agendamentoDAO;

    public AgendamentoBO() {
        this.agendamentoDAO = new AgendamentoDAO();
    }

    public void cadastrarAgendamento(AgendamentoTO agendamento) throws IllegalArgumentException {
        validarAgendamento(agendamento);

        if (!verificarDisponibilidade(agendamento.getDataHora())) {
            throw new IllegalArgumentException("O horário do agendamento já está ocupado.");
        }

        agendamentoDAO.adicionarAgendamento(agendamento);
    }

    public Agendamento buscarAgendamento(int id) throws IllegalArgumentException {
        Agendamento agendamento = agendamentoDAO.buscarAgendamento(id);
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não encontrado.");
        }
        return agendamento;
    }

    public void atualizarAgendamento(AgendamentoTO agendamento) throws IllegalArgumentException {
        if (agendamento.getId() <= 0) {
            throw new IllegalArgumentException("ID do agendamento inválido.");
        }

        validarAgendamento(agendamento);
        agendamentoDAO.atualizarAgendamento(agendamento);
    }

    public void deletarAgendamento(int id) throws IllegalArgumentException {
        Agendamento agendamento = agendamentoDAO.buscarAgendamento(id);
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não encontrado para exclusão.");
        }
        agendamentoDAO.deletarAgendamento(id);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoDAO.listarAgendamentos();
    }

    private boolean verificarDisponibilidade(LocalDateTime dataHora) {
        List<Agendamento> agendamentos = agendamentoDAO.listarAgendamentos();
        return agendamentos.stream().noneMatch(a -> a.getDataHora().equals(dataHora));
    }

    private void validarAgendamento(AgendamentoTO agendamento) throws IllegalArgumentException {
        if (agendamento.getDataHora() == null || agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data e hora do agendamento inválidas.");
        }
        if (agendamento.getCarro() == null) {
            throw new IllegalArgumentException("Carro é obrigatório para o agendamento.");
        }
        if (agendamento.getServico() == null) {
            throw new IllegalArgumentException("Serviço é obrigatório para o agendamento.");
        }
    }
}
