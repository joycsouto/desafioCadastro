package SistemaCadastro.ValidantionPet;

import SistemaCadastro.domain.EnderecoPet;
import SistemaCadastro.domain.SexoPet;
import SistemaCadastro.domain.TipoPet;


import java.util.Scanner;
import java.util.regex.Pattern;

public class Verificacao {
    Scanner scanner = new Scanner(System.in);


    private String VALOR_NAO_INFORMADO = ("Não Informado");

    public void validarNome(String nomePet) {
        String padraoNomePet = "([\\p{L}]+)\s([\\p{L}]+)";
        boolean isPadraoNomePet = Pattern.matches(padraoNomePet, nomePet);
        Object nome = nomePet == null ? VALOR_NAO_INFORMADO : nomePet;
        if (!isPadraoNomePet) {//Se o nome não for um padrão ele joga a exeção
            throw new RuntimeException("O nome deve ter sobrenome e não pode conter caracteres especiais.");
        }
    }


    public void verificandoSexo(SexoPet sexoPet) {
        if (sexoPet.getValor() == 1) {
            sexoPet.setSexo(sexoPet.getSexo());
           // Object sexo = sexoPet == null || sexoPet == " " ? VALOR_NAO_INFORMADO : sexoPet;
        } else if (sexoPet.getValor() == 2) {
            sexoPet.setSexo(sexoPet.getSexo());
        }
        else throw new RuntimeException("Valor Invalído");
    }

    public void verificandotipo(TipoPet tipoPet) {
        if (tipoPet.getValor() == 1) {
            tipoPet.setValor(tipoPet.getValor());
        } else if (tipoPet.getValor() == 2) {
            tipoPet.setValor(tipoPet.getValor());
        }
    }

    public void verificandoPeso(Double pesoPet) {
        Object peso = pesoPet == null || pesoPet == 0 ? VALOR_NAO_INFORMADO : pesoPet;
        if (pesoPet < 0.5 || pesoPet > 60) {
            throw new RuntimeException("Peso Inválido");
        }

    }

    public void verificandoIdade(Double IdadePet) {
        Object idade = IdadePet == null || IdadePet == 0 ? VALOR_NAO_INFORMADO : IdadePet;
        if (IdadePet > 20 || IdadePet < 0) {
            throw new RuntimeException("Idade Inválida");
        }
    }
    public void verificandoRaca(String racaPet){
        Object raca = racaPet == null ? VALOR_NAO_INFORMADO : racaPet;
        String padraoRaca = "[\\p{L}]";
        boolean isPadraoRaca = Pattern.matches(padraoRaca, racaPet);
        if (isPadraoRaca){
            //pet.setRaca(racaPet);
        }
    }
    public  void verificandoEndereco(EnderecoPet enderecoPet){
      if (enderecoPet.getNumCasaPet() == 0){
          enderecoPet.setNumCasaPet(Integer.valueOf(VALOR_NAO_INFORMADO));
      }
    }

}
