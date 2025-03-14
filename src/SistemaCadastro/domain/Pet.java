package SistemaCadastro.domain;

public class Pet {
    private String nomePet;
    private String enderecoPet;
    private Integer idade;//**
    private Double peso;//***
    private String raça;

    public Pet() {
    }


    public Pet(String nomePet, String enderecoPet, Integer idade, Double peso, String raça) {
        this.nomePet = nomePet;
        this.enderecoPet = enderecoPet;
        this.idade = idade;
        this.peso = peso;
        this.raça = raça;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public String getEnderecoPet() {
        return enderecoPet;
    }

    public void setEnderecoPet(String enderecoPet) {
        this.enderecoPet = enderecoPet;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }
}