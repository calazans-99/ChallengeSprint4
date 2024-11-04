package br.com.fiap.model;

/**
 * Classe que representa um carro no sistema.
 */
public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String placa;

    /**
     * Construtor completo para a classe Carro.
     * @param id Identificador único do carro.
     * @param marca Marca do carro.
     * @param modelo Modelo do carro.
     * @param ano Ano de fabricação do carro.
     */
    public Carro(int id, String marca, String modelo, int ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    // Construtor adicional com a placa incluída, se necessário
    public Carro(int id, String marca, String modelo, int ano, String placa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    /**
     * Construtor para referências ao carro apenas pelo id.
     * @param id Identificador único do carro.
     */
    public Carro(int id) {
        this.id = id;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
