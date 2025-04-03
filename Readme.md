# MedicalConsult API

Esta é uma API desenvolvida com **Java Spring Boot** para gerenciamento de consultas médicas. A API permite o cadastro, listagem, atualização e exclusão de **usuários** e **consultas**.

## 🧠 Domínios principais

### User
Contém os dados de usuários, como:
- Nome
- E-mail
- CPF
- Telefone
- Data de nascimento
- Permissão (papel do usuário)
- Lista de consultas relacionadas

### Consulta
Representa uma consulta médica associada a um usuário, contendo:
- Data da consulta
- Profissional
- Especialidade

## 📦 Dependências principais
- Spring Boot
- Spring Data JPA
- Lombok
- H2 ou outro banco relacional (ex: PostgreSQL)
- Jackson (para serialização JSON)

## ✅ Testes automatizados

### Tipo de Teste
Os testes utilizados na aplicação são **Testes Unitários**. Eles são responsáveis por validar o comportamento isolado de métodos dos serviços (`UserService` e `ConsultaService`) sem acessar o banco de dados real ou outras dependências externas.

### Objetivo dos Testes
- Garantir que os métodos funcionem corretamente com entradas válidas.
- Validar a lógica de negócios sem necessidade de subir o contexto completo da aplicação.
- Simular (mockar) comportamentos dos repositórios para verificar o comportamento da camada de serviço.

### Bibliotecas utilizadas
- **JUnit 5 (Jupiter):** Framework de testes utilizado para escrever os testes.
- **Mockito:** Framework utilizado para criar *mocks* (simulações) das dependências como `UserRepository` e `ConsultaRepository`.

### Anotações utilizadas
- `@ExtendWith(MockitoExtension.class)`: Integra o Mockito ao ciclo de vida do JUnit 5.
- `@Mock`: Cria simulações (mocks) das dependências injetadas nos serviços.
- `@InjectMocks`: Injeta os mocks criados diretamente no serviço a ser testado.

### Exemplos testados
- Cadastro de usuários e consultas
- Listagem de todos os registros
- Busca por ID
- Atualização e deleção com validações

---

## 📂 Estrutura de Pacotes

```
br.com.edilsonmaria.medicalconsult
│
├── user
│   ├── domain
│   ├── repository
│   └── services
│
├── consulta
│   ├── domain
│   ├── repository
│   └── services
```

## 🚀 Como executar

```bash
./mvnw spring-boot:run
```

Ou no IntelliJ/Eclipse execute a classe principal com a anotação `@SpringBootApplication`.

## 🧪 Como executar os testes

```bash
./mvnw test
```

---

