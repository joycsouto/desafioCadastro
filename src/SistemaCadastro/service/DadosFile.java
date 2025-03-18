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
    File[] diretorioFile = new File("Files").listFiles();
    File filePerguntas = new File("Files/formulario.txt");

    ArrayList<Pet> listPet = new ArrayList<>();
    ArrayList<String> perguntasList = new ArrayList<>();
    String[] users;
    String[] usersNow;
    String line;

    int linha = 0;
    int contarNumeroPerguntas;

    ArrayList<String> usuariosCadastrados = new ArrayList<>();

    Pet pet = new Pet(namePet, enderecoPet, idadePet, pesoPet, racaPet, sexoPet, tipoPet);

    public void menu() {
        int resposta;
        try {
            System.out.println("1 - Cadastrar um novo pet\n" +
                    "2 - Alterar os dados do pet cadastrado\n" +
                    "3 - Deletar um pet cadastrado\n" +
                    "4 - Listar todos os pets cadastrados\n" +
                    "5 -  Listar pets por algum critério(idade, nome, raça)\n" +
                    "6 -   Sair" +
                    "Escolha uma opção:");
            resposta = scanner.nextInt();
            //    resposta = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite um número.");
            menu();
            return;
        }
        switch (resposta) {
            case 1:
                cadastrarPet();
                menu();
                break;
            case 2:
                petsNoSistema();
                menu();
                break;
            case 3:
                adiconarPergunta();

                break;
            case 4:
                deletarPergunta();

                break;
            case 5:
                pesquisarNomePet();

                break;
            case 6:
                System.out.println("Saindo");
                break;
            default:
                System.out.println("Opção inválida");
                menu();
        }
    }

    public void lendoFile() {

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

    public void cadastrarPet() {

        lendoFile();
        scanner.nextLine();
        System.out.println("Responda abaixo");
        namePet = scanner.nextLine();
        String padraoNomePet = "([\\p{L}]+)\s([\\p{L}]+)";
        boolean isPadraoNomePet = Pattern.matches(padraoNomePet, namePet);
        Object nome = namePet == null ? NAO_INFORMADO : namePet;
        if (!isPadraoNomePet) {
            throw new RuntimeException("O nome deve ter sobrenome e não pode conter caracteres especiais.");
        }

        try {
            String tipoPetUser = scanner.nextLine().toUpperCase();
            tipoPet = TipoPet.valueOf(tipoPetUser);
            if (tipoPet.name().isEmpty()) {
                tipoPet = TipoPet.valueOf(VALOR_NAO_INFORMADO);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        try {

            String tipoSexoPetUser = scanner.nextLine().toUpperCase();
            sexoPet = SexoPet.valueOf(tipoSexoPetUser);
            Object sexo = tipoPet == null ? NAO_INFORMADO : sexoPet;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        try {
            numPet = scanner.nextInt();
            ruaPet = scanner.next();
            cityPet = scanner.next();

            Object num = numPet == null || numPet == 0 ? NAO_INFORMADO : numPet;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


        try {
            scanner.nextLine();
            idadePet = scanner.nextDouble();
            Object idade = idadePet == null || idadePet == 0 ? NAO_INFORMADO : idadePet;
            if (idadePet > 20 || idadePet < 0) {
                throw new RuntimeException("Idade Inválida");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


        try {
            pesoPet = scanner.nextDouble();
            Object peso = pesoPet == null || pesoPet == 0 ? NAO_INFORMADO : pesoPet;
            if (pesoPet < 0.5 || pesoPet > 60) {
                throw new RuntimeException("Peso Inválido");

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        try {
            racaPet = scanner.next();
            Object raca = racaPet == null ? NAO_INFORMADO : racaPet;
            String padraoRaca = "[\\p{L}]";
            boolean isPadraoRaca = Pattern.matches(padraoRaca, racaPet);
            if (isPadraoRaca) {
                throw new RuntimeException("Digte apenas letras.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


        Pet pet = new Pet(namePet, new EnderecoPet(numPet, ruaPet, cityPet), idadePet, pesoPet, racaPet, sexoPet, tipoPet);
        listPet.add(pet);
        criarArquivo();


    }

    public void criarArquivo() {

        try {

            boolean newFile = filePerguntas.createNewFile();

            escrevendoFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void escrevendoFile() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataFormatada = LocalDateTime.now().format(dateTimeFormatter);

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

                bufferedWriter.write(pet.toString());
                bufferedWriter.newLine();

            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo: " + e.getMessage(), e);
        }
    }

    public void petsNoSistema() {
        if (listPet != null) {
            for (Pet pet : listPet) {
                System.out.println(pet);
            }
        }
        if (petsCadastrados() != null) {
            for (String usuarios : users) {
                if (usuarios != null) {
                    System.out.println(usuarios);

                }
            }
        }
    }

    public String[] petsCadastrados() {

        for (File file : diretorioFile) {
            if (!file.getName().equals("formulario.txt")) {
                try (BufferedReader br = new BufferedReader(new FileReader((file)))) {
                    while ((line = br.readLine()) != null) {

                        usuariosCadastrados.add(line);
                        users = usuariosCadastrados.toArray(new String[line.length()]);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
        return users;
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
        petsCadastrados();
        for (String dadoSeparados : users) {
            String[] dados = dadoSeparados.split(",");
            //Pet pet = new Pet(dados[0], dados[1], Integer.parseInt(dados[2]), Double.parseDouble(dados[3]));
            listPet.add(pet);

        }
    }

    public void pesquisarNomePet() {
        try {

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
//                            }
                            for (Pet petsNow : listPet) {
                                if (petsNow.getNomePet().toLowerCase().contains(buscaName)) {
                                    System.out.println(petsNow);
                                }
                            }
                            for (String petSistema : petsCadastrados()) {
                                if (petSistema.toLowerCase().contains(buscaName)) {
                                    System.out.println(petSistema);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Digite qual sexo deseja buscar:");
                            String buscaSexo = scanner.nextLine();
//                    boolean isMarcherSexo2 =  petsSistema.matches(buscaSexo) ;
//                    if (listPet.contains(buscaSexo)|| isMarcherSexo2){
//                        System.out.println(petsNow);
//                        System.out.println(petsSistema);
//                    }


//                boolean isMarcher2 = Pattern.matches(pesquisa, usuarios.getEnderecoPet());
//                boolean isMarcher3 = Pattern.matches(pesquisa, usuarios.getIdade().toString());
//                if (isMarcher || isMarcher2 || isMarcher3) {
//                    System.out.println(usuarios);
//                }
                    }}
         catch (RuntimeException e) {
            throw new RuntimeException("Usuário Inexistente, tente novamente", e);
        }
    }


}

