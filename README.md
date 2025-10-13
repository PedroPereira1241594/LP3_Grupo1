# 🚇 Subway2Feira - Gestão de Viagens de Metro

Projeto desenvolvido no âmbito da unidade curricular **Laboratório III (2025/2026)** do **ISEP**.  
O objetivo é implementar uma aplicação em **Java**, que permita a **gestão de viagens de metro**, utilizando **SQL Server** como sistema de base de dados e aplicando a metodologia **Scrum** ao longo do desenvolvimento.

---

## 🧭 Contexto

A empresa **Subway2Feira** pretende desenvolver uma solução digital para **gestão de viagens de metro**.  
A plataforma deve permitir aos clientes planear viagens entre estações, consultar rotas disponíveis e escolher o trajeto mais conveniente.

O sistema também disponibiliza um **backoffice** para o gestor da empresa, responsável por:
- Carregar a informação inicial de linhas, estações e ligações (via ficheiro XML validado por XSD);
- Consultar clientes e viagens realizadas.

---

## 🎯 Objetivos do Projeto

- Desenvolver uma aplicação completa de gestão de viagens de metro;
- Implementar uma arquitetura escalável e modular;
- Garantir segurança, usabilidade e desempenho;
- Aplicar práticas ágeis com **Scrum** e controlo de versão via **Git**.

---

## ⚙️ Funcionalidades Principais

### 👤 Utilizador (Cliente)
- Registo e autenticação (login/password);
- Planeamento de viagens (origem → destino);
- Visualização de diferentes trajetos possíveis (estações e linhas envolvidas);
- Escolha do trajeto preferido.

### 🧑‍💼 Gestor (Administrador)
- Upload e validação de ficheiros XML (linhas e estações);
- Gestão e visualização de clientes e viagens;
- Visualização gráfica do mapa de metro.

---

## 💻 Requisitos Técnicos

### Funcionais
- O sistema deve permitir a criação e autenticação de utilizadores;
- Calcular e apresentar trajetos possíveis entre estações;
- Suportar viagens em ambos os sentidos;
- Validar ficheiros XML com XSD.

### Não Funcionais
- Desenvolvimento em **Java**;
- Base de dados **SQL Server**;
- GUI intuitiva e responsiva;
- Separação clara entre **lógica de negócio** e **interface gráfica**;
- Passwords armazenadas **encriptadas**;
- Documentação completa em **Javadoc**;
- Testes unitários com **JUnit**;
- Utilização de **Git** com convenções de branches

---

## 🏗️ Arquitetura e Tecnologias

| Componente | Tecnologia |
|-------------|-------------|
| Linguagem   | Java 23+ |
| Base de Dados | Microsoft SQL Server |
| Interface Gráfica | JavaFX |
| Documentação | Javadoc |
| Controlo de Versão | Git |

---

## 🧩 Metodologia Scrum

O projeto segue o ciclo **Scrum** com **sprints semanais**:

- **Duração:** 14 sprints (16 semanas de projeto)  
- **Papéis:**
  - 🧠 *Team Leader* — atua como **Scrum Master** e **Product Owner**  
  - 💻 *Desenvolvedores* — responsáveis pela implementação e testes
- **Cerimónias Scrum:**
  - Sprint Planning  
  - Daily Scrum  
  - Sprint Review (Demo)  
  - Sprint Retrospective  
  - Grooming / Backlog Refinement

Cada sprint inclui entregáveis incrementais e documentação atualizada (diagramas UML e backlog).

---

