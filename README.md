# Agregador de Investimentos - API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring_boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Sobre o Projeto
Este é um projeto desenvolvido exclusivamente para **fins de estudo e aprimoramento técnico**. 

O objetivo principal deste repositório é consolidar os conhecimentos no desenvolvimento Back-end utilizando o ecossistema Java (Spring Boot) e a construção de testes unitários.

## ⚙️ Funcionalidades Desenvolvidas
A aplicação implementa um CRUD (Create, Read, Update, Delete) completo para a entidade de Usuários (`User`), contendo:
* **Criação:** Cadastro de novos usuários no sistema.
* **Leitura:** Listagem de todos os usuários e busca por ID.
* **Atualização:** Modificação segura de dados do usuário utilizando DTOs.
* **Exclusão:** Remoção de usuários do banco de dados.

## 🧪 Cobertura de Testes Unitários
Como a resiliência das entregas é o foco deste estudo, a camada de serviços (`UserService`) foi rigorosamente testada utilizando **JUnit 5 e Mockito**. 

As práticas de engenharia de testes aplicadas incluem:
* Estruturação rigorosa baseada no padrão **AAA (Arrange, Act, Assert)**.
* Uso de **Mocks** para isolar a camada de repositório, garantindo testes rápidos e sem dependência de banco de dados.
* Implementação de **ArgumentCaptors** para interceptar e auditar de forma purista os dados exatos que transitam entre os serviços e o banco.
* Validação profunda para evitar falsos positivos nas mutações de estado e nas chamadas de métodos (`verify`).

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java
* **Framework:** Spring Boot (Spring Data JPA)
* **Testes:** JUnit 5, Mockito
* **Gerenciador de Dependências:** Maven
* **Infraestrutura:** Docker e Docker Compose (para orquestração do banco de dados)
