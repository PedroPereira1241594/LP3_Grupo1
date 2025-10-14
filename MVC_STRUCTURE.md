# Arquitetura BAL/DAL do Projeto

Este projeto foi reestruturado seguindo a arquitetura em camadas **BAL (Business Access Layer)** e **DAL (Data Access Layer)**.

## Estrutura de Pastas

```
src/main/java/com/example/lp3_grupo1/
├── HelloApplication.java          # Classe principal da aplicação
├── controller/                    # Camada de Apresentação
│   ├── BaseController.java        # Classe base para controllers
│   ├── HelloController.java       # Controller principal
│   └── UserController.java        # Controller para gestão de usuários
├── bal/                           # Business Access Layer
│   ├── interfaces/
│   │   └── IUserBusiness.java     # Interface para lógica de negócio
│   └── UserBusiness.java          # Implementação da lógica de negócio
├── dal/                           # Data Access Layer
│   ├── interfaces/
│   │   └── IUserRepository.java   # Interface para acesso a dados
│   └── UserRepository.java        # Implementação do repositório
└── model/                         # Modelos de Dados
    ├── User.java                  # Modelo de dados do usuário
    └── WelcomeMessage.java        # Modelo para mensagens

src/main/resources/com/example/lp3_grupo1/
└── view/                          # Camada de Apresentação (Views)
    └── hello-view.fxml            # Interface gráfica principal
```

## Responsabilidades das Camadas

### DAL (Data Access Layer) - Camada de Acesso a Dados
- **Localização**: `src/main/java/com/example/lp3_grupo1/dal/`
- **Responsabilidade**: Gerenciar o acesso e persistência dos dados
- **Características**:
  - Abstrai a fonte de dados (banco, arquivo, memória, etc.)
  - Implementa operações CRUD (Create, Read, Update, Delete)
  - Não contém lógica de negócio
- **Exemplos**:
  - `IUserRepository.java`: Interface que define contratos de acesso a dados
  - `UserRepository.java`: Implementação concreta do repositório

### BAL (Business Access Layer) - Camada de Lógica de Negócio
- **Localização**: `src/main/java/com/example/lp3_grupo1/bal/`
- **Responsabilidade**: Implementar regras de negócio e validações
- **Características**:
  - Contém toda a lógica de negócio da aplicação
  - Valida dados antes de persistir
  - Coordena operações entre diferentes repositórios
  - Não conhece detalhes de apresentação
- **Exemplos**:
  - `IUserBusiness.java`: Interface que define operações de negócio
  - `UserBusiness.java`: Implementação das regras de negócio

### Controller (Camada de Apresentação)
- **Localização**: `src/main/java/com/example/lp3_grupo1/controller/`
- **Responsabilidade**: Gerenciar a interação entre usuário e sistema
- **Características**:
  - Recebe entrada do usuário
  - Chama métodos da camada BAL
  - Atualiza a interface gráfica
- **Exemplos**:
  - `HelloController.java`: Controller principal
  - `UserController.java`: Controller para gestão de usuários

### Model (Modelos de Dados)
- **Localização**: `src/main/java/com/example/lp3_grupo1/model/`
- **Responsabilidade**: Representar estruturas de dados
- **Características**:
  - Classes POJO (Plain Old Java Objects)
  - Apenas propriedades e métodos de acesso
  - Sem lógica de negócio
- **Exemplos**:
  - `User.java`: Modelo de dados do usuário
  - `WelcomeMessage.java`: Modelo para mensagens

### View (Camada de Apresentação)
- **Localização**: `src/main/resources/com/example/lp3_grupo1/view/`
- **Responsabilidade**: Interface gráfica e apresentação
- **Exemplos**:
  - `hello-view.fxml`: Interface principal da aplicação

## Vantagens da Arquitetura BAL/DAL

1. **Separação Clara de Responsabilidades**: Cada camada tem um propósito específico
2. **Desacoplamento**: Interfaces permitem trocar implementações facilmente
3. **Testabilidade**: Cada camada pode ser testada isoladamente
4. **Manutenibilidade**: Mudanças em uma camada não afetam as outras
5. **Reutilização**: Lógica de negócio pode ser reutilizada em diferentes interfaces
6. **Escalabilidade**: Facilita a adição de novas funcionalidades
7. **Flexibilidade**: Permite trocar fonte de dados sem afetar a lógica de negócio

## Fluxo de Dados na Arquitetura

```
[View/Controller] → [BAL] → [DAL] → [Data Source]
      ↑                                    ↓
      ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
```

1. **Controller** recebe entrada do usuário
2. **Controller** chama métodos da **BAL**
3. **BAL** aplica regras de negócio e validações
4. **BAL** chama métodos da **DAL** se necessário
5. **DAL** acessa a fonte de dados
6. Dados retornam pela mesma cadeia até o **Controller**
7. **Controller** atualiza a **View**

## Como Executar

A aplicação principal continua sendo executada através da classe `HelloApplication.java`, que agora utiliza a arquitetura BAL/DAL para gerenciar dados e lógica de negócio de forma estruturada.
