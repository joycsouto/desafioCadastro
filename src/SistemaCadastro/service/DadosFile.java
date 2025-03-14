package SistemaCadastro.service;

import SistemaCadastro.domain.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DadosFile {

    String name;
    String email;
    Integer age;
    Double height;
    Scanner scanner = new Scanner(System.in);
    File[] diretorioFile = new File("Files").listFiles();
    File filePerguntas = new File("Files/formulario.txt");
    ArrayList<Pet> listPet = new ArrayList<>();
    ArrayList<String> perguntasList = new ArrayList<>();
    String line;
    int linha = 0;
    String[] users;
    ArrayList<String> usuariosCadastrados = new ArrayList<>();

    Pet pet = new Pet();
    public void menu() {
        int resposta;
        try {
            System.out.println("1 - Cadastrar um novo pet\n" +
                    "2 - Alterar os dados do pet cadastrado\n\n" +
                    "3 - Deletar um pet cadastrado\n\n" +
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
                cadastrarUser();
                menu();
                break;
            case 2:
                usuariosSistema();
                menu();
                break;
            case 3:
                adiconarPergunta();

                break;
            case 4:
                deletarPergunta();

                break;
            case 5:
                pesquisarNome();

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

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void cadastrarUser() {
        lendoFile();
        scanner.nextLine();
        System.out.println("Responda abaixo");
        name = scanner.nextLine();
        pet.setName(name);
        String padraoNomePet = "([\\p{L}]+)\s([\\p{L}]+)";
        boolean isPadraoNomePet = Pattern.matches(padraoNomePet,name);

        if (!isPadraoNomePet) {
            throw new RuntimeException("O nome deve ter sobrenome e não pode conter caracteres especiais.");
        }

        String padraoEmail = "[a-zA-Z0-9]+@gmail.com";
        //  Pattern pattern2 = Pattern.compile(padraoEmail);
        // user.setEmail(scanner.nextLine());
        email += pet.setEmail(scanner.nextLine());
        boolean isPadrao = Pattern.matches(padraoEmail, email);
        if (!isPadrao) {
            throw new RuntimeException("Seu email precisa ter @gmail.com");
        }
        if (listPet.contains(email)) {
            throw new RuntimeException("Esse email ja existe");
        }


        age = pet.setAge(scanner.nextInt());
        if (age < 18) {
            throw new RuntimeException("A idade precisa ser apartir de 18 anos.");

        }


        String padraoVirgula = "(\\d{1},\\d{2})";
        height = pet.setHeight(scanner.nextDouble());
        boolean isMarcher = Pattern.matches(padraoVirgula, height.toString());
        if (!isMarcher) {
            throw new RuntimeException("O número precisa ser separado por vírgula");
        }


        listPet.add(new Pet(name, email, age, height));
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

        File diretorio = new File("Files");
        File userFile = new File(diretorio, name.toUpperCase().trim() + ".txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userFile, true))) {
            System.out.println(userFile.createNewFile());
            bufferedWriter.write(pet.toString());
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void usuariosSistema() {
        if (listPet != null) {
            for (Pet pet : listPet) {
                System.out.println(pet);
            }
        }
        if (userCadastrados() != null) {
            for (String usuarios : users) {
                if (usuarios != null) {
                    System.out.println(usuarios);
                }
            }
        }
    }

    public String[] userCadastrados() {

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
        userCadastrados();
        for (String dadoSeparados : users) {
            String[] dados = dadoSeparados.split(",");
            Pet pet = new Pet(dados[0], dados[1], Integer.parseInt(dados[2]), Double.parseDouble(dados[3]));
            listPet.add(pet);

        }
    }

    public void pesquisarNome() {
        try {
            userCadastrados();
            System.out.println("Pesquise por nome,email ou idade:");
            String pesquisa = scanner.next();
            for (Pet usuarios : listPet) {
                boolean isMarcher = Pattern.matches(pesquisa, usuarios.getNomePet());
                boolean isMarcher2 = Pattern.matches(pesquisa, usuarios.getEnderecoPet());
                boolean isMarcher3 = Pattern.matches(pesquisa, usuarios.getAge().toString());
                if (isMarcher || isMarcher2 || isMarcher3) {
                    System.out.println(usuarios);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Usuário Inexistente, tente novamente", e);
        }
    }


}

