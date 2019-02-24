# Pautas para Votação
[![Build Status](https://travis-ci.org/alex-carvalho/pauta-votacao.svg?branch=master)](https://travis-ci.org/alex-carvalho/pauta-votacao)
[![codecov](https://codecov.io/gh/alex-carvalho/pauta-votacao/branch/master/graph/badge.svg)](https://codecov.io/gh/alex-carvalho/pauta-votacao)

### Projeto com apis REST com o seguinte escopo:
- Cadastro de pautas;
- Abertura de sessão de votação em uma pauta;
- Voto unico por eleitor com valores de 'Sim'/'Não';
- Exibição dos resultado contabilizados;

### Executando o projeto
O projeto utiliza o MongoDB para armazenar os dados, é preciso ter uma instância em execução.
Caso ele não esteje executando na mesma maquina com a configuração padrão, 
é possivel definir a uri do mesmo de duas formas:

    - Parametro para a JVM:  
        -Dspring.data.mongodb.uri=mongodb://localhost:27017/pauta"
    
    - Variável de ambiente:  
        MONGO_URI=mongodb://localhost:27017/pauta

- Utilizando gradle:  
  ```gradlew bootRun```

- Utilizando diretamente o jar:  
  ```gradlew build```  
  ```java -jar ./build/libs/(jarfile)```
  
- Utilizando docker:  
  ```gradlew build```   
  ```docker build -tpauta .```   
  ```docker run -it -p8080:8080 pauta``` 

- Utilizando docker-compose com MongoDB:  
  ```docker-compose build```    
  ```docker-compose up```        


Após acessar [localhost:8080](http://localhost:8080/)

## Decisões técnicas

##### Java
    Uma das linguagens mais utilizadas no mundo, conhecida por ser robusta e facil de escalar.
##### Gradle
    Gerenciador de dependencias com dsl em groovy que permite criar scripts. 
##### Spring 
    Framework com uma comunidade muito grande, facilita a integração com diversos outros serviços,
    tornando o desenvolvimento muito mais rápido.
##### Webflux
    A programação reativa permite usufruir melhor do poder de processamento disponivel, 
    alem de ter por principios ser Resiliente, Elástica, Responsiva e Orientada a Mensagens
##### MongoDB
    Banco de dados orientado a documentos facil de escalar e com driver que suporta programação reativa.
    
## Documentação 

- Foi realizado a documentação diretamente no [swagger](http://localhost:8080/swagger-ui.html#/)