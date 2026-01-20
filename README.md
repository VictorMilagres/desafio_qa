# Desafio QA

## Parte 0

A pasta `parte0` contém todas as respostas das perguntas Parte 0 - Teorias e Conceitos Fundamentais.

## Parte A
A pasta `parteA` contém toda a Análise e Planejamento de Testes.


## Parte B - Automação UI (Selenium)

Este projeto contém a automação de cenários críticos da tela de login, utilizando **Java**, **Selenium WebDriver** e o padrão **Page Object Model**.

### Pré-requisitos
* **Java JDK 11** ou superior instalado.
* **Maven** instalado e configurado no PATH.

### Configuração
1. Clone o repositório:
   `git clone git@github.com:VictorMilagres/desafio_qa.git`
2. Navegue até a pasta:
   `cd parteB`
3. Instale as dependências:
   `mvn clean install`

### Como rodar os testes
Você pode rodar todos os testes pelo terminal com o comando:
```
mvn test
```

### Cenários Automatizados:
Cenário 1: Login válido com perfil USER
Cenário 2: Login de Perfil sem acesso (VISITOR)
Cenário 3: Bloqueio de Usuário