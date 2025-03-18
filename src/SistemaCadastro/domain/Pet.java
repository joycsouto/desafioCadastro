package SistemaCadastro.domain;

public class Pet {
    private String nomePet;
    private EnderecoPet enderecoPet;
    private Double idade;//**
    private Double peso;//***
    private String raça;
    private SexoPet sexoPet;
    private TipoPet tipoPet;
    private int numPerguntas;


    public Pet() {
    }


    public Pet(String nomePet, EnderecoPet enderecoPet, Double idade, Double peso, String raça, SexoPet sexoPet, TipoPet tipoPet) {
        this.nomePet = nomePet;
        this.enderecoPet = enderecoPet;
        this.idade = idade;
        this.peso = peso;
        this.raça = raça;
        this.sexoPet = sexoPet;
        this.tipoPet = tipoPet;
    }

    @Override
    public String toString() {
        return "Pet{" +
                 nomePet + "\n" +
                enderecoPet + "\n" +
                idade + "\n" +
                peso + "\n" +
                raça + "\n" + '\'' +
                sexoPet + "\n" +
                tipoPet + "\n" +
                '}';
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

    public void getSexoPet() {
        this.sexoPet = sexoPet;
    }

    public void setSexoPet(SexoPet sexoPet) {
        this.sexoPet = sexoPet;
    }

    public TipoPet getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(TipoPet tipoPet) {
        this.tipoPet = tipoPet;
    }
}