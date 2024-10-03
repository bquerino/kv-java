# Gossip Protocol Simulation with Spring Boot

## Descrição
Este projeto implementa duas versões do Gossip Protocol (algoritmo epidêmico e push-pull) utilizando Spring Boot. O objetivo do projeto é simular a disseminação de informações em um sistema distribuído, onde diferentes nós trocam informações até que todos estejam sincronizados. Ele também utiliza logs para acompanhar o progresso da disseminação.

## Algoritmos Implementados

### Epidemic Gossip

* O nó que possui a informação inicialmente propaga os dados para um vizinho aleatório.
* A disseminação continua até que todos os nós estejam sincronizados.

### Push-Pull Gossip

* O nó que possui a informação pode empurrar os dados para um vizinho aleatório ou puxar dados de um vizinho que tenha a informação.
* Este algoritmo é bidirecional e mais eficiente em certas condições.

## Tecnologias Utilizadas
* Spring Boot: Framework principal para construção da aplicação.
* SLF4J + Logback: Para geração de logs da propagação dos dados.
* Java 17+: Linguagem de programação utilizada.

## Como Executar

```bash
mvn spring-boot:run
```

### Testar os endpoints

Agora você pode acessar os endpoints para iniciar a propagação do gossip:

* **Epidemic Gossip:**
Acesse http://localhost:8080/gossip/epidemic para iniciar a propagação epidêmica de dados entre os nós.

* **Push-Pull Gossip:**
Acesse http://localhost:8080/gossip/push-pull para iniciar a propagação push-pull de dados entre os nós.

## Melhorias Futuras

* Adicionar uma interface gráfica para monitorar visualmente o progresso da propagação do gossip.
* Suporte a mais nós com configuração dinâmica.
* Simulação de falhas de nós e tolerância a falhas.

## Licença

Este projeto está licenciado sob os termos da [Licença MIT](LICENSE).
