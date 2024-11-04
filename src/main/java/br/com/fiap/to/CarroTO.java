package br.com.fiap.to;

/**
 * Classe de Transferência de Dados (TO) que representa um carro.
 */
public class CarroTO {
    private int id;
    private String marca;
    private String modelo;
    private int ano;

    // Construtor vazio, útil para frameworks de serialização
    public CarroTO() {}

    /**
     * Construtor completo para inicializar todos os campos do CarroTO.
     * @param id Identificador do carro.
     * @param marca Marca do carro.
     * @param modelo Modelo do carro.
     * @param ano Ano de fabricação do carro.
     */
    public CarroTO(int id, String marca, String modelo, int ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
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

    // Método toString para representação textual do objeto
    @Override
    public String toString() {
        return "CarroTO{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                '}';
    }
}
