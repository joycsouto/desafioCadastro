package SistemaCadastro.domain;

public class Pet {
    private String nomePet;
    private EnderecoPet enderecoPet;
    private Double idade;//**
    private Double peso;//***
    private String raça;

    public Pet() {
    }


    public Pet(String nomePet, EnderecoPet enderecoPet, Double idade, Double peso, String raça) {
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

    public CharSequence getEnderecoPet() {
        return (CharSequence) enderecoPet;
    }

    public void setEnderecoPet(EnderecoPet enderecoPet) {
        this.enderecoPet = enderecoPet;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
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