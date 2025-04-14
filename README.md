# Knight's Tour Problem - Benchmark and Analysis

Este projeto implementa e compara diferentes algoritmos de busca para resolução do clássico problema do Passeio do Cavalo (Knight's Tour), utilizando a linguagem Java e a biblioteca JMH (Java Microbenchmark Harness) para realizar benchmarks precisos.

---

## Sobre o problema

O Passeio do Cavalo consiste em encontrar uma sequência de movimentos de um cavalo de xadrez que percorra todas as casas de um tabuleiro sem repetir nenhuma delas. O problema pode ser modelado como um caminho Hamiltoniano em um grafo.

Mais detalhes teóricos e análises completas podem ser encontrados no artigo associado a este projeto.

---

## Algoritmos Implementados

- `Breadth-First Search (BFS)`  
  Implementação clássica da busca em largura, explorando sistematicamente todos os estados possíveis. Algoritmo altamente custoso em memória.

- `Busca Gulosa com Heurística de Warnsdorff`  
  Abordagem heurística gulosa, escolhendo o próximo movimento baseado na menor quantidade de opções futuras disponíveis. Algoritmo muito mais eficiente em tempo e memória.

---

## Resultados

Os experimentos foram realizados em tabuleiros de tamanhos 5x5, 6x6 e 8x8. As principais métricas analisadas foram:

- Tempo médio de execução (ms)
- Uso de memória normalizado (Bytes por operação)

Os resultados completos podem ser visualizados em:

- `/benchmark_results/*.csv` — Arquivos CSV gerados pelo JMH
- `/report/tabela_resultados.tex` — Tabela formatada em LaTeX
- `/report/tempo_execucao.png` — Gráfico de tempo de execução
- `/report/uso_memoria.png` — Gráfico de uso de memória

---

## Como Executar o Projeto

Pré-requisitos:
- Java 17+
- Gradle instalado

### Para executar o benchmark:

1. Vá até o arquivo `KnightTourJMHBenchmark.java` na pasta `src/main/java/io/acordi/benchmark/`.

2. Descomente as linhas que executam o benchmark e comente a chamada do método `animatePath()`.

O método `animatePath()` é responsável por exibir graficamente o caminho encontrado pelo algoritmo (modo visual). Por padrão, ele está ativo. Para rodar o benchmark, é necessário comentar ele e descomentar as linhas do benchmark.

### Executar o Benchmark:

Na pasta do projeto raíz, execute: 
```bash
./gradlew run
```

---

## Estrutura do Projeto
```
.
├── src/main/java/                  # Implementação dos algoritmos
│   ├── io/acordi/benchmark/        # Benchmarks JMH
│   ├── io/acordi/implementations/  # Algoritmos BFS e Greedy
│   ├── io/acordi/visual/           # Animação em tabuleiro do caminho encontrado
│   ├── io/acordi/entities/         # Estruturas de dados personalizadas
├── build.gradle                    # Configuração do projeto
├── benchmark_results/              # Resultados dos benchmarks em CSV
├── report/                         # Gráficos e Tabelas LaTeX
└── README.md
```

---

## Créditos

Este projeto foi desenvolvido por:

- [Pedro A. A. Soares](https://github.com/pdroacordi)

- [Érick S. Padilha](https://github.com/ErickScur)

- [Murilo W. Klug](https://github.com/Zudra)

Como parte do trabalho científico desenvolvido para o curso de Ciência da Computação do Centro Universitário Fundação Assis Gurgacz (Centro FAG).

---
## Licença 
Este projeto é de caráter educacional e científico.

Licença MIT — sinta-se livre para utilizar, modificar e distribuir este código, desde que mantidos os devidos créditos aos autores.

Para mais detalhes, consulte o arquivo [`LICENSE`](LICENSE.txt).