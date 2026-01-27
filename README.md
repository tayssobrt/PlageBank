# 🏦 Plage Bank

## 1. Introdução

O **Plage Bank** é um sistema bancário desenvolvido para execução em **ambiente de terminal**, cujo objetivo principal é aplicar e consolidar conceitos fundamentais de **Programação Orientada a Objetos (POO)**, modelagem de domínio e definição de regras de negócio.

O projeto foi proposto como uma **atividade avaliativa semanal**, priorizando a **qualidade da modelagem**, a coerência das regras e a organização estrutural do sistema, em detrimento de aspectos visuais ou tecnológicos avançados.

---

## 2. Objetivo Geral

Desenvolver um sistema bancário simplificado que permita a realização de operações financeiras básicas, garantindo:

- Coerência do domínio
- Integridade dos dados
- Aplicação correta de princípios de POO
- Separação adequada de responsabilidades
- Clareza arquitetural

---

## 3. Objetivos Específicos

- Modelar entidades representativas do domínio bancário
- Implementar autenticação básica de usuários
- Controlar o estado das contas bancárias
- Registrar todas as movimentações financeiras
- Garantir o cumprimento rigoroso das regras de negócio

---

## 4. Conceitos e Fundamentos Utilizados

O desenvolvimento do sistema envolve os seguintes conceitos:

- Programação Orientada a Objetos (POO)
- Encapsulamento de dados
- Princípio da Responsabilidade Única
- Modelagem de Domínio
- Validação de estados e regras
- Controle de fluxo em aplicações de terminal
- Registro e histórico de operações (transações)

---

## 5. Modelagem do Sistema

O sistema foi modelado a partir de um **diagrama UML de domínio**, servindo como base para toda a implementação.

As principais entidades são:

- **Banco**  
  Responsável pela administração e gerenciamento das contas existentes.

- **Cliente**  
  Representa o usuário do sistema, contendo dados de identificação e autenticação.

- **Conta**  
  Entidade responsável pela execução das operações financeiras, manutenção do saldo e controle de estado.

- **Transação**  
  Entidade que registra toda movimentação financeira realizada no sistema, garantindo rastreabilidade.

---

## 6. Regras de Negócio

As seguintes regras de negócio foram definidas e devem ser rigorosamente respeitadas:

- O acesso ao sistema requer autenticação por CPF e senha.
- Cada conta bancária possui um status, podendo ser **ATIVA** ou **BLOQUEADA**.
- Não é permitido saldo negativo em nenhuma operação.
- Saques só podem ser realizados caso haja saldo suficiente.
- Transferências devem ocorrer entre contas distintas.
- Transferências exigem saldo suficiente na conta de origem.
- Contas bloqueadas não podem realizar operações financeiras.
- Toda operação financeira gera, obrigatoriamente, um registro de transação.
- Registros de transações não podem ser removidos ou alterados.

---

## 7. Funcionalidades Disponíveis

O sistema oferece, via terminal, as seguintes funcionalidades:

- Cadastro de clientes
- Criação de contas bancárias
- Autenticação de usuários
- Consulta de saldo
- Depósito de valores
- Saque de valores
- Transferência entre contas
- Consulta ao histórico de transações
- Encerramento da sessão

---

## 8. Escopo e Limitações

Este projeto possui caráter **estritamente acadêmico**, não contemplando:

- Uso de frameworks (ex.: Spring)
- Persistência em banco de dados
- Interface gráfica
- Integrações externas ou APIs

Toda a execução do sistema ocorre em memória, com foco exclusivo na lógica e estrutura do código.

---

## 9. Critérios de Avaliação

O projeto será avaliado considerando:

- Correta aplicação dos conceitos de POO
- Clareza e organização do código
- Fidelidade à modelagem UML proposta
- Cumprimento das regras de negócio
- Coerência arquitetural
- Tratamento adequado de estados inválidos e exceções

---

## 10. Considerações Finais

O **Plage Bank** tem como propósito principal o desenvolvimento do pensamento analítico e arquitetural do programador, indo além da simples implementação funcional, visando a construção de um sistema consistente, previsível e bem estruturado.

---
