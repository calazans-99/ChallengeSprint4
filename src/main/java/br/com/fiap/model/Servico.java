package br.com.fiap.model;

public class Servico {
    private int id;
    private String descricao;
    private double preco;
    private Carro carro;

    // Construtor completo
    public Servico(int id, String descricao, double preco, Carro carro) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.carro = carro;
    }

    // Construtor com apenas id
    public Servico(int id) {
        this.id = id;
    }

    // Construtor com id, descricao e preco (para instâncias sem um carro associado)
    public Servico(int id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Construtor vazio
    public Servico() {}

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
