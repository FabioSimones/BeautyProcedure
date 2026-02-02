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
