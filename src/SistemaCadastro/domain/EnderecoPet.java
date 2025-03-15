package SistemaCadastro.domain;

public class EnderecoPet {
    private  Integer numCasaPet;
    private String ruaPet;
    private String cityPet;

    public EnderecoPet(Integer numCasaPet, String ruaPet, String cityPet) {
        this.numCasaPet = numCasaPet;
        this.ruaPet = ruaPet;
        this.cityPet = cityPet;
    }

    public Integer getNumCasaPet() {
        return numCasaPet;
    }

    public void setNumCasaPet(Integer numCasaPet) {
        this.numCasaPet = numCasaPet;
    }

    public String getRuaPet() {
        return ruaPet;
    }

    public void setRuaPet(String ruaPet) {
        this.ruaPet = ruaPet;
    }

    public String getCityPet() {
        return cityPet;
    }

    public void setCityPet(String cityPet) {
        this.cityPet = cityPet;
    }
}
