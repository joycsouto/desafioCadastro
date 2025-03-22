package SistemaCadastro.service;

import SistemaCadastro.ValidantionPet.Verificacao;
import SistemaCadastro.domain.EnderecoPet;
import SistemaCadastro.domain.Pet;
import SistemaCadastro.domain.SexoPet;
import SistemaCadastro.domain.TipoPet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CadastroPet {
    private static final Object NAO_INFORMADO = ("Não Informado");
    String namePet;
    int tipoPet;
    SexoPet sexoPet;
    Double idadePet;
    Double pesoPet;
    String racaPet;

    Integer numPet;
    String ruaPet;
    String cityPet;
    EnderecoPet enderecoPet = new EnderecoPet(numPet, ruaPet, cityPet);
    Verificacao verificacoes = new Verificacao();
    final String VALOR_NAO_INFORMADO = ("Não Informado");

    Scanner scanner = new Scanner(System.in);
    File[] diretorioFile = new File("petsCadastrados").listFiles();
    File filePerguntas = new File("Files/formulario.txt");

    ArrayList<Pet> listPet = new ArrayList<>();
    ArrayList<String> perguntasList = new ArrayList<>();

    String line;
    int contarNumeroPerguntas;

    ArrayList<String> petssCadastrados = new ArrayList<>();

    Pet pet = new Pet(namePet, enderecoPet, idadePet, pesoPet, racaPet, sexoPet, tipoPet);

    public void lendoFilePerguntas() {

        try (BufferedReader br = new BufferedReader(new FileReader(filePerguntas))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);

                perguntasList.add(line);

                contarNumeroPerguntas = line.length();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void respostasPet() {

        System.out.println("Responda abaixo");
        System.out.println("1_");
        verificacoes.validarNome(pet.setNomePet(namePet = scanner.nextLine()));

        System.out.println("2_");
        verificacoes.verificandotipo(pet.setTipoPet(tipoPet = scanner.nextInt()));

        System.out.println("3_");
        verificacoes.verificandoSexo(sexoPet = SexoPet.valueOf(scanner.nextLine()));

        System.out.println("4_");
        verificacoes.verificandoEndereco(enderecoPet.setNumCasaPet(scanner.nextInt()));
        verificacoes.verificandoEndereco(enderecoPet.setCityPet(scanner.nextLine()));
        verificacoes.verificandoEndereco(enderecoPet.setRuaPet(scanner.nextLine()));

        pet.setIdade(idadePet = scanner.nextDouble());

        verificacoes.verificandoIdade(idadePet = (double) scanner.nextInt());
        verificacoes.verificandoPeso(pesoPet = scanner.nextDouble());
        verificacoes.verificandoRaca(racaPet = scanner.nextLine());

        listPet.add(pet);


    }


    public void criarArquivo() {

        try {
            if (!filePerguntas.exists()) {
                boolean newFile = filePerguntas.createNewFile();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void escrevendoFile() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataFormatada = LocalDateTime.now().format(dateTimeFormatter);
        Pet pet = new Pet(namePet, new EnderecoPet(numPet, ruaPet, cityPet), idadePet, pesoPet, racaPet, sexoPet, tipoPet);

        File diretorio = new File("petsCadastrados");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        String nomeFilePet = dataFormatada + "-" + namePet.toUpperCase().replaceAll(" ", "") + ".txt";
        File userFile = new File(diretorio, nomeFilePet);
        try {
            boolean criado = userFile.createNewFile();
            System.out.println("Arquivo criado: " + criado);

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userFile, true))) {
                int linha = 1;

                // Escrevendo as informações do pet linha por linha
                bufferedWriter.write(linha++ + ". Nome do pet: " + namePet);
                bufferedWriter.newLine();
                bufferedWriter.write(linha++ + ". Idade: " + idadePet);
                bufferedWriter.newLine();
                bufferedWriter.write(linha++ + ". Peso: " + pesoPet);
                bufferedWriter.newLine();
                bufferedWriter.write(linha++ + ". Raça: " + racaPet);
                bufferedWriter.newLine();
                bufferedWriter.write(linha++ + ". Sexo: " + sexoPet);
                bufferedWriter.newLine();
                bufferedWriter.write(linha++ + ". Tipo: " + tipoPet);
                bufferedWriter.newLine();
                bufferedWriter.write(linha++ + ". Endereço: " + numPet + ", " + ruaPet + ", " + cityPet);
                bufferedWriter.newLine();

            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo: " + e.getMessage(), e);
        }
    }
    public void iniciandoCadastro(){
        lendoFilePerguntas();
        respostasPet();
        criarArquivo();
        escrevendoFile();
    }
}

