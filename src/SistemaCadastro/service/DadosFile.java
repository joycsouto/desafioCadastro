package SistemaCadastro.service;

import SistemaCadastro.domain.EnderecoPet;
import SistemaCadastro.domain.Pet;
import SistemaCadastro.domain.SexoPet;
import SistemaCadastro.domain.TipoPet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DadosFile {

    private static final Object NAO_INFORMADO = ("Não Informado");
    String namePet;
    TipoPet tipoPet;
    SexoPet sexoPet;
    Double idadePet;
    Double pesoPet;
    String racaPet;

    Integer numPet;
    String ruaPet;
    String cityPet;
    EnderecoPet enderecoPet = new EnderecoPet(numPet, ruaPet, cityPet);

    final String VALOR_NAO_INFORMADO = ("Não Informado");

    Scanner scanner = new Scanner(System.in);
    File[] diretorioFile = new File("petsCadastrados").listFiles();
    File filePerguntas = new File("Files/formulario.txt");

    ArrayList<Pet> listPet = new ArrayList<>();
    ArrayList<String> perguntasList = new ArrayList<>();
    String[] users;
    String[] usersNow;
    String line;

    int linha = 0;
    int contarNumeroPerguntas;
    int econtrado = 0;

    ArrayList<String> petssCadastrados = new ArrayList<>();

    Pet pet = new Pet(namePet, enderecoPet, idadePet, pesoPet, racaPet, sexoPet, tipoPet);



    public void listaPets() {
        if (listPet != null) {
            for (Pet pet : listPet) {
                System.out.println(pet);
            }
        }
        if (lendoDiretorioPet() != null) {
            for (String usuarios : users) {
                if (usuarios != null) {
                    System.out.println(usuarios);

                }
            }
        }
    }

    public ArrayList lendoDiretorioPet() {
        for (File file : diretorioFile) {
            try (BufferedReader br = new BufferedReader(new FileReader((file)))) {
                while ((line = br.readLine()) != null) {
                    petssCadastrados.add(line);
                    for (int i = 0; i < petssCadastrados.size(); i++) {
                        String[] atributes = line.split("^0-9+");
Pet pet = new Pet();

                        
                    }


                    //  users = petssCadastrados.toArray(new String[line.length()]);


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return petssCadastrados;
    }

    public void adiconarPergunta() {
        scanner.nextLine();
        System.out.println("Escreva a pergunta:");
        String pergunta = scanner.nextLine();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePerguntas, true))) {
            perguntasList.add(pergunta);
            bufferedWriter.newLine();
            bufferedWriter.write(numeroPergutas() + 1 + " - " + pergunta);
            menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int numeroPergutas() {

        try (BufferedReader br = new BufferedReader(new FileReader(filePerguntas))) {
            while ((line = br.readLine()) != null) {
                linha++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return linha;
    }

    public void deletarPergunta() {
        try {

            lendoFile();

            System.out.println("Qual pergunta você quer remover? (A partir da 5ª pergunta)");
            int resposta = scanner.nextInt();
            scanner.nextLine();

            if (resposta < 5 || resposta > perguntasList.size()) {
                System.out.println("Número inválido!");
                menu();
                return;
            }


            perguntasList.remove(resposta - 1);

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePerguntas))) {
                for (String line : perguntasList) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                menu();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao escrever no arquivo: " + e.getMessage());
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerificando() {
        lendoDiretorioPet();
        for (String dadoSeparados : users) {
            String[] dados = dadoSeparados.split(",");
            //Pet pet = new Pet(dados[0], dados[1], Integer.parseInt(dados[2]), Double.parseDouble(dados[3]));
            listPet.add(pet);

        }
    }

    public void pesquisarNomePet() {
        try {
            lendoDiretorioPet();
            System.out.println("Quais critérios vc deseja procurar?Até 2" +
                    "1-Nome ou sobrenome\n" +
                    "2-Sexo\n" +
                    "3-Idade\n" +
                    "4-Peso\n" +
                    "5-Raça\n" +
                    "6-Endereço");
            Integer criterio = scanner.nextInt();
            scanner.nextLine();
            switch (criterio) {
                case 1:
                    System.out.println("Digite qual nome deseja buscar:");
                    String buscaName = scanner.nextLine();
                    //                            boolean isMarcherName1 = Pattern.matches(buscaName, petsNow.getNomePet() );
//                            boolean isMarcherName2 =  petsSistema.matches(buscaName) ;
//                            if (isMarcherName1 || isMarcherName2){
//                                System.out.println(petsNow);
//                                System.out.println(petsSistema);
                    for (String petSistema : petssCadastrados) {
                        boolean isPetFind = petSistema.toLowerCase().contains(buscaName);
                        if (isPetFind) {
                            for (int i = econtrado; i < petssCadastrados.size(); i++) {
                                if (petssCadastrados.get(i).contains(buscaName)) {
                                    System.out.println(petssCadastrados);
                                    System.out.println(petssCadastrados.get(i));
                                }
                                break;
                            }
                            econtrado++;
                        }
                        for (Pet petsNow : listPet) {
                            if (petsNow.getNomePet().toLowerCase().contains(buscaName)) {
                                System.out.println(pet.toString());
                            }
                        }

                        break;

                    }

                case 2:
                    System.out.println("Por que sexo deseja procurar:");
                    String sexoPet1 = scanner.nextLine();
                    for (String petSistema : petssCadastrados) {
                        boolean isPetFind = petSistema.toLowerCase().contains(sexoPet1.toUpperCase());
                        if (isPetFind) {
                            for (int i = econtrado; i < petssCadastrados.size(); i++) {
                                if (petssCadastrados.get(i).contains(sexoPet1)) {
                                    System.out.println(petssCadastrados);
                                    System.out.println(petssCadastrados.get(i));
                                }
                                break;
                            }
                            econtrado++;
                        }
                        for (Pet petsNow : listPet) {
                            if (petsNow.getNomePet().toLowerCase().contains(sexoPet1.toUpperCase())) {
                                System.out.println(pet.toString());
                            }
                        }

                        break;
                    }
                case 3:

            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}





