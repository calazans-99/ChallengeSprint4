package br.com.fiap.to;

import java.time.LocalDateTime;

/**
 * Classe de Transferência de Objeto para Agendamento.
 * Usada para transportar dados de agendamento entre camadas da aplicação.
 */
public class AgendamentoTO {
    private int id;
    private LocalDateTime dataHora;
    private CarroTO carro;
    private ServicoTO servico;

    /**
     * Construtor completo para AgendamentoTO.
     * @param id Identificador do agendamento.
     * @param dataHora Data e hora do agendamento.
     * @param carro Informações do carro associado ao agendamento.
     * @param servico Tipo de serviço agendado.
     */
    public AgendamentoTO(int id, LocalDateTime dataHora, CarroTO carro, ServicoTO servico) {
        this.id = id;
        this.dataHora = dataHora;
        this.carro = carro;
        this.servico = servico;
    }

    /**
     * Construtor vazio.
     */
    public AgendamentoTO() {}

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public CarroTO getCarro() {
        return carro;
    }

    public void setCarro(CarroTO carro) {
        this.carro = carro;
    }

    public ServicoTO getServico() {
        return servico;
    }

    public void setServico(ServicoTO servico) {
        this.servico = servico;
    }
}
