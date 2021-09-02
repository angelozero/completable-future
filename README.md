# Completable Future 

## Future

### Parallel X Concurrent 
- _See more in [Parallel Programming vs. Concurrent Programming](https://medium.com/@sanju.skm/parallel-programming-vs-concurrent-programming-f993d3f9ceea)_
![About Concurrency x Parallelism](https://miro.medium.com/max/1050/1*S20YNBe5KqRmS08Mtn_XTw.png)



programaçao assincrona 
programaca sincrona 
pq nao apenas startar uma thread ?
    - nao teriamos acesso ao resultado executado dentro dessa thread
    - future implementado no java 5 mas melhorado no java 8
    - nao é igual ao ScheduleThreadPool, esse é executado em um determinado tempo para executar uma determinada tarefa
    - o Future vc vai executar essa tarefa em paraleo, vai continuar com o que ja estava sendo executado em seu fluxo normal e assim que houver resposta da tarefa do Future vc tera acesso a esse valor


Sobre o Future  
    Qual a diferença de se trabalhar com Future e de maneira paralela ?
        - De maneira paralela vc executa duas tarefas em cores diferentes
        - Com future vc usa apenas um nucleo
    Sem o future implementado nosso sistema iria abrir em paralelo uma outra thread e essa thread so iria se encenrrar ate obter o resultado do serviço ( api_service ) e ter uma thread parada ( ociosa ) consequentemente vc acaba dispersisando performance

Parallel Programming vs. Concurrent Programming
https://medium.com/@sanju.skm/parallel-programming-vs-concurrent-programming-f993d3f9ceea
https://miro.medium.com/max/1050/1*S20YNBe5KqRmS08Mtn_XTw.png

Basicamente 
    - Paralelismo
        - 2 cpu / 2 cores executando duas tarefas paralelas no mesmo espaço de tempo
        - lembrando que nem sempre todo o tempo otimizado é usado, o que pode ocasionar osciosidade e / ou desperdicio de energia
    - Concorrencia
        - 1 cpu / 1 core fazendo um fatiamento de tempo de cada uma das tarefas

    Exemplo -> Requisicoes Https -> Node JS


---

## Completable Future
