# 🏦 Plage Bank - Sistema Bancário em Java

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Concluído-success.svg)]()
[![Grade](https://img.shields.io/badge/Nota-9.0%2F10-brightgreen.svg)]()

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Modelagem UML](#modelagem-uml)
- [Regras de Negócio](#regras-de-negócio)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar](#como-executar)
- [Exemplos de Uso](#exemplos-de-uso)
- [Conceitos Aplicados](#conceitos-aplicados)
- [Desenvolvimento](#desenvolvimento)
- [Autor](#autor)
- [Licença](#licença)

---

## 📖 Sobre o Projeto

**Plage Bank** é um sistema bancário desenvolvido em Java puro, implementado via terminal/console, com foco em **Programação Orientada a Objetos (POO)**, boas práticas de desenvolvimento e aplicação rigorosa de regras de negócio.

O projeto foi desenvolvido como desafio técnico para demonstrar domínio de conceitos fundamentais de engenharia de software, incluindo encapsulamento, separação de responsabilidades, validações em múltiplas camadas e imutabilidade.

### 🎯 Objetivos

- Aplicar conceitos sólidos de POO
- Implementar regras de negócio complexas
- Garantir consistência e integridade de dados
- Demonstrar arquitetura em camadas
- Praticar código limpo e manutenível

---

## ⚙️ Funcionalidades

### 🔐 Gestão de Clientes
- [x] Cadastro de clientes com validação de CPF
- [x] Autenticação segura com senha forte
- [x] Bloqueio automático após 3 tentativas incorretas
- [x] Validação de CPF com algoritmo verificador

### 💳 Gestão de Contas
- [x] Criação de contas bancárias
- [x] Consulta de saldo em tempo real
- [x] Listagem de contas por cliente
- [x] Controle de status (ATIVA/BLOQUEADA)

### 💰 Operações Financeiras
- [x] **Depósito** - Entrada de valores na conta
- [x] **Saque** - Retirada com validação de saldo
- [x] **Transferência** - Entre contas com registro duplo
- [x] Validação de valores (positivos, não-nulos)
- [x] Prevenção de saldo negativo

### 📊 Auditoria e Histórico
- [x] Registro automático de todas transações
- [x] ID único por transação (UUID)
- [x] Timestamp de cada operação
- [x] Descrição automática contextualizada
- [x] Histórico completo e imutável

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com separação clara de responsabilidades:

```
┌─────────────────────────────────────┐
│         CAMADA VIEW                 │
│      (Interface Terminal)           │
│                                     │
│         MenuTerminal                │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│         CAMADA MODEL                │
│      (Lógica de Negócio)            │
│                                     │
│  Banco → Cliente → Conta → Transação│
└─────────────────────────────────────┘
```

### Responsabilidades por Camada

#### **View (MenuTerminal)**
- Exibição de menus formatados
- Captura de entrada do usuário
- Validação básica de formato
- Apresentação de resultados

#### **Model (Domínio)**
- Regras de negócio
- Validações complexas
- Persistência em memória
- Integridade de dados

---

## 📐 Modelagem UML

### Diagrama de Classes

```
┌──────────────┐
│    Banco     │
├──────────────┤
│ - nome       │
│ - codigo     │
├──────────────┤
│ + criarConta()      │
│ + criarCliente()    │
│ + autenticar()      │
└───────┬──────┘
        │ 1
        │
        │ *
┌───────▼────────┐          ┌────────────────┐
│    Conta       │◄─────────│   Cliente      │
├────────────────┤   1..* 1 ├────────────────┤
│ - numero       │          │ - nome         │
│ - saldo        │          │ - cpf          │
│ - status       │          │ - senha        │
├────────────────┤          ├────────────────┤
│ + sacar()      │          │ + autenticar() │
│ + depositar()  │          │ + bloquear()   │
│ + transferir() │          └────────────────┘
└───────┬────────┘
        │ 1
        │
        │ *
┌───────▼────────┐
│   Transacao    │
├────────────────┤
│ - id           │
│ - tipo         │
│ - valor        │
│ - dataHora     │
└────────────────┘
```

### Enumerações

**StatusConta**
- `ATIVA` - Operações permitidas
- `BLOQUEADA` - Apenas consultas

**TipoTransacao**
- `DEPOSITO` - Entrada de valores
- `SAQUE` - Saída de valores
- `TRANSFERENCIA_DEBITO` - Saída por transferência
- `TRANSFERENCIA_CREDITO` - Entrada por transferência

---

## 📜 Regras de Negócio

### RN01 - Autenticação
- Cliente deve fornecer CPF e senha válidos
- Após 3 tentativas incorretas, conta é bloqueada
- CPF deve ser único no sistema

### RN02 - Validação de Senha
- Mínimo 8 caracteres
- Pelo menos 1 dígito numérico
- Pelo menos 1 caractere especial ($!#@%&*)

### RN03 - Validação de CPF
- Formato: 11 dígitos
- Validação matemática dos dígitos verificadores

### RN04 - Contas
- Número de conta único e sequencial
- Saldo inicial deve ser >= 0
- Status inicial: ATIVA

### RN05 - Conta Bloqueada
- ❌ Não pode realizar saques
- ❌ Não pode realizar transferências
- ✅ Pode consultar saldo
- ✅ Pode receber depósitos
- ✅ Pode visualizar histórico

### RN06 - Operação de Saque
- Valor deve ser positivo e não-nulo
- Saldo deve ser suficiente
- Conta deve estar ATIVA
- Gera transação do tipo SAQUE

### RN07 - Operação de Depósito
- Valor deve ser positivo e não-nulo
- Não requer saldo prévio
- Gera transação do tipo DEPOSITO

### RN08 - Operação de Transferência
- Conta origem ≠ conta destino
- Ambas contas devem estar ATIVAS
- Saldo suficiente na origem
- Gera **duas transações**:
  - `TRANSFERENCIA_DEBITO` na origem
  - `TRANSFERENCIA_CREDITO` no destino

### RN09 - Transações
- Toda movimentação financeira gera transação
- Transações são **imutáveis** (atributos `final`)
- Transações não podem ser apagadas
- ID único por transação (UUID)
- Timestamp automático (LocalDateTime)

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+** - Linguagem de programação
- **Collections Framework** - ArrayList, List
- **Java Time API** - LocalDateTime para timestamps
- **UUID** - Geração de IDs únicos
- **Scanner** - Entrada de dados via terminal
- **Enums** - Tipagem forte para status e tipos

### Bibliotecas Nativas

```java
java.util.ArrayList
java.util.List
java.util.Scanner
java.util.UUID
java.time.LocalDateTime
java.time.format.DateTimeFormatter
```

---

## 📁 Estrutura do Projeto

```
plage-bank/
│
├── src/
│   ├── models/
│   │   ├── Banco.java
│   │   ├── Cliente.java
│   │   ├── Conta.java
│   │   ├── Transacao.java
│   │   ├── StatusConta.java
│   │   └── TipoTransacao.java
│   │
│   ├── view/
│   │   └── MenuTerminal.java
│   │
│   └── Main.java
│
├── README.md
└── LICENSE
```

### Descrição dos Componentes

#### **models/Banco.java**
Gerenciador central do sistema. Responsável por:
- Criar e gerenciar clientes
- Criar e gerenciar contas
- Autenticar usuários

#### **models/Cliente.java**
Representa usuários do sistema. Implementa:
- Validação de CPF com algoritmo verificador
- Validação de senha forte
- Controle de tentativas de login
- Auto-bloqueio após 3 tentativas

#### **models/Conta.java**
Gerencia operações financeiras. Garante:
- Saldo sempre não-negativo
- Validações antes de cada operação
- Registro automático de transações
- Controle de status (ativa/bloqueada)

#### **models/Transacao.java**
Registro imutável de movimentações. Características:
- Atributos `final` (imutabilidade)
- ID único (UUID)
- Timestamp preciso
- Descrição automática contextual

#### **view/MenuTerminal.java**
Interface do usuário via terminal. Fornece:
- Menus interativos
- Validação de entrada
- Feedback ao usuário
- Navegação entre funcionalidades

---

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 17 ou superior
- Terminal/Console
- IDE (opcional): IntelliJ IDEA, Eclipse, VS Code

### Compilação e Execução

#### Via Terminal

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/plage-bank.git

# 2. Navegue até o diretório
cd plage-bank/src

# 3. Compile o projeto
javac Main.java models/*.java view/*.java

# 4. Execute
java Main
```

#### Via IDE

1. Importe o projeto na IDE
2. Localize a classe `Main.java`
3. Execute o método `main`

---

## 💡 Exemplos de Uso

### Fluxo Completo

```
=== PLAGE BANK ===
1. Criar novo cliente
2. Fazer login
3. Sair do sistema
Escolha: 1

Nome completo: Thiago Carvalho
CPF: 12345678909
Senha: Senha@123

✓ Cadastro realizado com sucesso!

=== LOGIN ===
CPF: 12345678909
Senha: Senha@123

✓ Login realizado com sucesso!

=== BEM-VINDO, Thiago Carvalho ===
1. Criar conta
2. Listar minhas contas
3. Acessar conta
4. Logout
Escolha: 1

Saldo inicial: R$ 1000

✓ Conta criada com sucesso!
Número da conta: 000001

=== CONTA: 000001 | SALDO: R$ 1000.00 ===
1. Consultar saldo
2. Depositar
3. Sacar
4. Transferir
5. Ver histórico
6. Voltar
Escolha: 2

Valor a depositar: 500

✓ Depositado com sucesso!
Novo saldo: R$ 1500.00
```

---

## 🎓 Conceitos Aplicados

### Programação Orientada a Objetos

#### **Encapsulamento**
```java
private String cpf;           // Atributo privado
public String getCpf() {      // Acesso controlado
    return this.cpf;
}
```

#### **Imutabilidade**
```java
private final String id;              // Não pode ser alterado
private final LocalDateTime dataHora; // Imutável após criação
```

#### **Composição**
```java
private List<Conta> contas;      // Banco compõe Contas
private List<Transacao> historico; // Conta compõe Transações
```

#### **Fail-Fast Validation**
```java
if (!validarCpf(cpf)) {
    throw new IllegalArgumentException("CPF inválido!");
}
// Só continua se validação passar
```

### SOLID Principles

#### **Single Responsibility Principle (SRP)**
- `Banco` - Apenas gerenciamento
- `Cliente` - Apenas autenticação
- `Conta` - Apenas operações financeiras
- `Transacao` - Apenas registro

#### **Open/Closed Principle (OCP)**
- Extensível via novos tipos de transação (enum)
- Fechado para modificação nos core models

#### **Dependency Inversion Principle (DIP)**
- View depende de abstrações (Model)
- Não há dependência de implementações concretas

### Design Patterns

#### **Factory Method** (implícito)
```java
public Conta criarConta(Cliente cliente, double saldo) {
    String numero = gerarNumero();
    return new Conta(numero, cliente, saldo);
}
```

#### **Defensive Copy**
```java
public List<Transacao> getHistorico() {
    return new ArrayList<>(historico); // Retorna cópia
}
```

---

## 📊 Desenvolvimento

### Metodologia

- **Desenvolvimento Iterativo** - 3 versões até nota final
- **Code Review** - Avaliação técnica rigorosa
- **Refactoring Contínuo** - Melhorias baseadas em feedback

### Evolução do Projeto

| Versão | Nota | Status | Principais Melhorias |
|--------|------|--------|---------------------|
| v1.0 | 6.9/10 | ❌ Não Aprovado | Implementação inicial |
| v2.0 | 8.2/10 | ✅ Aprovado | Transações funcionais, validações corrigidas |
| v3.0 | 9.0/10 | ⭐ Excelente | Imutabilidade completa, código impecável |

### Métricas de Qualidade

- ✅ **0** bugs críticos
- ✅ **100%** das regras de negócio implementadas
- ✅ **10/10** aspectos de POO corretos
- ✅ **9.0/10** nota final

---

## 🧪 Testes Realizados

### Testes Funcionais

- [x] Criar cliente com CPF válido
- [x] Rejeitar CPF inválido
- [x] Rejeitar senha fraca
- [x] Bloquear após 3 tentativas
- [x] Criar conta com saldo inicial
- [x] Depositar valor positivo
- [x] Rejeitar depósito negativo
- [x] Sacar com saldo suficiente
- [x] Rejeitar saque sem saldo
- [x] Transferir entre contas
- [x] Gerar 2 transações na transferência
- [x] Registrar histórico completo
- [x] Bloquear operações em conta inativa

### Testes de Validação

- [x] Validação de CPF matemática
- [x] Validação de senha forte
- [x] Prevenção de saldo negativo
- [x] Unicidade de CPF
- [x] Unicidade de número de conta

---

## 📈 Possíveis Melhorias Futuras

### Funcionalidades
- [ ] Persistência em banco de dados (JDBC/JPA)
- [ ] Limite de crédito
- [ ] Juros e rendimentos
- [ ] Múltiplos tipos de conta (corrente, poupança)
- [ ] Extrato em PDF
- [ ] Agendamento de transferências

### Técnicas
- [ ] Testes unitários (JUnit)
- [ ] Logging (Log4j)
- [ ] Criptografia de senha (BCrypt)
- [ ] API REST (Spring Boot)
- [ ] Interface gráfica (JavaFX)
- [ ] Containerização (Docker)

---

## 👨‍💻 Autor

**Thiago Carvalho**

- GitHub: [@tayssobrt](https://github.com/tayssobrt)
- LinkedIn: [Seu LinkedIn]
- Email: [seu.email@exemplo.com]

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 🙏 Agradecimentos

- Claude (Anthropic) - Avaliação técnica e mentoria
- Comunidade Java - Documentação e melhores práticas
- Todos que contribuíram com feedback

---

## 📚 Referências

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Clean Code - Robert C. Martin](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)
- [Effective Java - Joshua Bloch](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)
- [Design Patterns](https://refactoring.guru/design-patterns)

---

<div align="center">

**Desenvolvido com ☕ e 💻 por Thiago Carvalho**

⭐ Se este projeto foi útil, considere dar uma estrela!

</div>
