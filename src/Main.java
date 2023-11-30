import java.util.*;

class Aluno {
    int numero;
    String nome;
    List<Double> notas;

    public Aluno(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
        this.notas = new ArrayList<>();
    }
}

class SistemaNotas {
    Stack<Aluno> alunos;
    Queue<Double> notasFila;

    public SistemaNotas() {
        this.alunos = new Stack<>();
        this.notasFila = new LinkedList<>();
    }

    public void cadastrarAluno(String nome) {
        int numero = alunos.size() + 1;
        Aluno aluno = new Aluno(numero, nome);
        alunos.push(aluno);
        System.out.println("Aluno cadastrado.");
    }

    public void cadastrarAlunosEmLote(List<String> nomes) {
        for (String nome : nomes) {
            cadastrarAluno(nome);
        }
    }

    public void cadastrarNota(int numeroAluno, double nota) {
        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.numero == numeroAluno) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado != null) {
            alunoEncontrado.notas.add(nota);
            System.out.println("Nota cadastrada.");
        } else {
            System.out.println("Aluno não cadastrado.");
        }
    }

    public void mostrarAlunosComNotas() {
        boolean algumComNotas = false;
        for (Aluno aluno : alunos) {
            if (!aluno.notas.isEmpty()) {
                algumComNotas = true;
                System.out.println("Aluno com notas: " + aluno.nome);
            }
        }

        if (!algumComNotas) {
            System.out.println("Nenhum aluno possui notas.");
        }
    }

    public void calcularMediaAluno(int numeroAluno) {
        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.numero == numeroAluno) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado != null) {
            if (!alunoEncontrado.notas.isEmpty()) {
                double soma = 0;
                for (double nota : alunoEncontrado.notas) {
                    soma += nota;
                }
                double media = soma / alunoEncontrado.notas.size();
                System.out.println("Média do aluno " + alunoEncontrado.nome + " = " + media);
            } else {
                System.out.println("Aluno sem notas.");
            }
        } else {
            System.out.println("Aluno não cadastrado.");
        }
    }

    public void listarAlunosSemNotas() {
        boolean todosComNotas = true;
        for (Aluno aluno : alunos) {
            if (aluno.notas.isEmpty()) {
                todosComNotas = false;
                System.out.println("Aluno sem notas: " + aluno.nome);
            }
        }

        if (todosComNotas) {
            System.out.println("Todos os alunos possuem notas.");
        }
    }

    public void excluirAluno() {
        if (!alunos.isEmpty()) {
            Aluno alunoExcluir = alunos.pop();
            if (!alunoExcluir.notas.isEmpty()) {
                alunos.push(alunoExcluir);
                System.out.println("Este aluno possui notas, logo, não poderá ser excluído.");
            } else {
                System.out.println("Aluno " + alunoExcluir.nome + " excluído.");
            }
        } else {
            System.out.println("Pilha vazia.");
        }
    }

    public void excluirNota() {
        if (!notasFila.isEmpty()) {
            notasFila.poll();
            System.out.println("Nota excluída.");
        } else {
            System.out.println("Fila vazia.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SistemaNotas sistemaNotas = new SistemaNotas();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar aluno em lote");
            System.out.println("3 - Cadastrar nota");
            System.out.println("4 - Mostrar alunos com notas");
            System.out.println("5 - Calcular média de um aluno");
            System.out.println("6 - Listar os nomes dos alunos sem notas");
            System.out.println("7 - Excluir aluno");
            System.out.println("8 - Excluir nota");
            System.out.println("9 - Sair");

            System.out.print("Escolha uma opção: ");
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha pendente
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do aluno: ");
                        String nomeAluno = scanner.next();
                        sistemaNotas.cadastrarAluno(nomeAluno);
                        break;
                    case 2:
                        System.out.print("Digite a quantidade de alunos a cadastrar: ");
                        int quantidadeAlunos = scanner.nextInt();
                        List<String> nomesAlunos = new ArrayList<>();
                        for (int i = 0; i < quantidadeAlunos; i++) {
                            System.out.print("Digite o nome do aluno " + (i + 1) + ": ");
                            nomesAlunos.add(scanner.next());
                        }
                        sistemaNotas.cadastrarAlunosEmLote(nomesAlunos);
                        break;
                    case 3:
                        System.out.print("Digite o número do aluno: ");
                        int numeroAluno = scanner.nextInt();
                        System.out.print("Digite a nota: ");
                        double nota = scanner.nextDouble();
                        sistemaNotas.cadastrarNota(numeroAluno, nota);
                        break;
                    case 4:
                        sistemaNotas.mostrarAlunosComNotas();
                        break;
                    case 5:
                        System.out.print("Digite o número do aluno: ");
                        int numeroAlunoCalc = scanner.nextInt();
                        sistemaNotas.calcularMediaAluno(numeroAlunoCalc);
                        break;
                    case 6:
                        sistemaNotas.listarAlunosSemNotas();
                        break;
                    case 7:
                        sistemaNotas.excluirAluno();
                        break;
                    case 8:
                        sistemaNotas.excluirNota();
                        break;
                    case 9:
                        System.out.println("Saindo do programa. Até mais!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }
    }
}
