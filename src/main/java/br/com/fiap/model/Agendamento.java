package br.com.fiap.model;

import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private LocalDateTime dataHora;
    private Carro carro;
    private Servico servico;

    public Agendamento(int id, LocalDateTime dataHora, Carro carro, Servico servico) {
        this.id = id;
        this.dataHora = dataHora;
        this.carro = carro;
        this.servico = servico;
    }

    public Agendamento() {}

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

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
