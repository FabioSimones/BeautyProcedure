# BeautyProcedure – Microservices com CQRS e RabbitMQ

<img width="920" height="600" alt="image" src="https://github.com/user-attachments/assets/567254e2-6d3a-41e5-9e64-164ffd21aea6" />

Este projeto foi desenvolvido com fins **educacionais e práticos**, com o objetivo de aplicar conceitos modernos de **arquitetura de software**, **microsserviços** e **mensageria**, utilizando o padrão **CQRS (Command Query Responsibility Segregation)**.

A aplicação simula uma **API de agendamento para salões de beleza**, demonstrando a separação de responsabilidades entre comandos e consultas, comunicação assíncrona e uso de containers.

---

## 🧱 Arquitetura do Sistema

A solução é composta por **3 microsserviços independentes**, que se comunicam através do **RabbitMQ**:

- **Command Service**  
  Responsável pelas operações de escrita (criação e atualização de dados).  
  Banco de dados: **PostgreSQL**

- **Query Service**  
  Responsável pelas operações de leitura otimizadas.  
  Banco de dados: **MongoDB**

- **Sync Service**  
  Responsável pela sincronização dos dados entre os bancos, utilizando eventos publicados no RabbitMQ.

O padrão **CQRS** foi adotado para separar completamente os fluxos de **Command** e **Query**, promovendo maior escalabilidade, desacoplamento e organização do código.

---

## 🧠 CQRS (Command Query Responsibility Segregation)

CQRS é um padrão arquitetural que propõe a separação entre:

- **Commands**: operações que alteram o estado do sistema
- **Queries**: operações responsáveis apenas por consultas

Neste projeto:
- PostgreSQL é utilizado para **escrita**
- MongoDB é utilizado para **leitura**
- A replicação de dados ocorre de forma assíncrona via **RabbitMQ**

---

## 📨 Mensageria com RabbitMQ

A comunicação entre os microsserviços é realizada de forma **assíncrona**, utilizando:

- Exchanges
- Filas
- Bindings
- Routing Keys

A integração é feita através do **Spring AMQP**, garantindo comunicação desacoplada e orientada a eventos.

<img width="1500" height="600" alt="image" src="https://github.com/user-attachments/assets/8adcdd13-bba9-477b-b603-45c7d14c7a6b" />
<img width="700" height="600" alt="image" src="https://github.com/user-attachments/assets/d99321e3-b581-4b57-8569-944b8523e4cf" />
<img width="700" height="600" alt="image" src="https://github.com/user-attachments/assets/b4561368-fcf6-4e15-a938-d8d131f51eec" />


---

## 🐳 Docker & Docker Compose

Toda a infraestrutura do projeto é executada em containers Docker, garantindo isolamento e portabilidade do ambiente.

Foi criada uma pasta específica chamada **`infrastructure`**, responsável por centralizar os arquivos de infraestrutura, como:

- Arquivos `docker-compose.yml`
- Scripts SQL
- Arquivos `.sql` e `.sh`

Como containers são execuções **efêmeras**, os volumes foram configurados para garantir a persistência dos dados no host.

### ▶️ Execução do Ambiente

Com o Docker Desktop em execução, entre na pasta entitulada de **docker**, abrindo o VSCode, utilize o comando:

```bash
docker compose up -d
```

Após a inicialização, a infraestrutura foi validada utilizando o Beekeeper Studio, confirmando a correta execução e conexão com o banco de dados PostgreSQL.
<img width="550" height="600" alt="image" src="https://github.com/user-attachments/assets/2e1002cb-6637-4637-a15a-2eaad699ec1b" />

---

## 🧱 Estrutura do Projeto

O projeto segue o padrão de arquitetura em camadas, organizado da seguinte forma:

- Configuration: Configurações do projeto, incluindo RabbitMQ.
- Controllers: Camada responsável por expor os endpoints REST.
- DTOs: Objetos de transferência de dados entre as camadas.
- Entities: Representação das entidades do banco de dados.
- Exceptions: Exceções personalizadas e tratamento de erros.
- Repository: Camada de acesso e persistência de dados.
- Services: Interfaces e implementações das regras de negócio.
- Utils: Funções utilitárias reutilizáveis.

---
## 🗃️ Entidades do Sistema

As principais entidades do domínio são:

- AppointmentsEntity: Responsável por armazenar os agendamentos.
- BaseEntity: Classe base que fornece campos comuns para outras entidades, sem gerar tabela no banco.
- BeautyProceduresEntity: Representa os procedimentos estéticos oferecidos pelo salão.
- CustomerEntity: Armazena os dados dos clientes.

As entidades utilizam anotações do Spring Data JPA e Lombok, reduzindo código boilerplate e facilitando a manutenção.

---
## 🌐 APIs REST

A API foi construída seguindo boas práticas REST, incluindo:

- Versionamento de endpoints
- Uso correto de códigos HTTP
- Payloads bem definidos
- Separação clara de responsabilidades

---
## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Data MongoDB
- Spring AMQP (RabbitMQ)
- PostgreSQL
- MongoDB
- Docker
- Docker Compose
- Postman
- Maven 3.5.9

---
## 🎯 Objetivos do Projeto

- Aplicar CQRS na prática
- Trabalhar com microsserviços
- Implementar mensageria com RabbitMQ
- Integrar PostgreSQL e MongoDB
- Containerizar aplicações com Docker
- Desenvolver APIs REST escaláveis e organizadas

---
## Alguns testes no Postman:

<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/c03d9cc9-0353-4fd7-975e-f295aea6311e" />

<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/358b4d79-08b3-4fe2-8a32-10bb2230c9c9" />

<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/585a748c-143d-42c6-9b74-2d10e9c87e68" />

<img width="900" height="600" alt="image" src="https://github.com/user-attachments/assets/476df340-e49f-4d2f-a530-e40ce66d46af" />





