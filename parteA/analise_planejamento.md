O Candidato deve extrair cenários de teste funcionais e nagativos a partir de um sistema de login com perfis (ADMIN, USER, VISTOR), considerando redirecionamentos, mensagens de erro e tempos de carregamento de até 5 segundos.

Tarefas: 
1. Documentar pelo menos 8 cenários de teste (positivos, negativos e de segurança)
2. Classificar os tipos de teste
3. Definir quais serão automatizados na camada UI, API ou manualmente.
4. Descrever a estrutura proposta para o projeto de automação.

Resposta/Análise:
1. Cenários de Teste
| ID	| Cenário	                                     | Perfil	| Tipo	      | Resultado Esperado                                             |
| CT01	| Login com credenciais válidas e perfil ADMIN   | ADMIN	| Positivo	  | Redirecionamento para o Dashboard de Gestão em < 5s.           |
| CT02	| Login com credenciais válidas e perfil VISITOR | VISITOR	| Positivo	  | Redirecionamento para Home de Leitura (sem botões de edição).  |
| CT03	| Tentativa de acesso com senha incorreta	     | Qualquer | Negativo	  | Mensagem: "Usuário ou senha inválidos" (sem especificar qual). |
| CT04	| Bloqueio de conta após 5 tentativas falhas	 | USER	    | Segurança	  | Conta bloqueada temporariamente para evitar Brute Force.       |
| CT05	| Login com campo de e-mail em formato inválido	 | N/A	    | Negativo	  | Validação de campo (client-side) impedindo o envio do form.    |
| CT06	| Acesso direto via URL à área ADMIN sem login	 | N/A	    | Segurança	  | Redirecionamento forçado para a tela de Login.                 |
| CT07	| Performance sob latência (Login em rede lenta) | USER	    | Performance |	Garantir que o sistema não dê timeout antes dos 5s.            |
| CT08	| Injeção de SQL no campo de usuário	         | N/A	    | Segurança	  | O sistema deve tratar caracteres especiais e negar o acesso.   |

2. Classificação dos Tipos de Teste 
Para esse desafio está sendo coberto:
Testes Funcionais: Validam se o login e perfis funcionam conforme a regra de negócio.
Testes de Segurança: Focados em proteção (brute force, SQL injection, controle de acesso).
Testes de Performance: Validação do requisito de tempo de resposta (< 5s).
Testes de Usabilidade: Mensagens de erro claras e máscaras de campo.

3.Estratégia de Automação
API: Automatizar os CT01, CT02, CT03, CT04 e CT08. É mais rápido e barato validar as regras de permissão e segurança direto no endpoint de autenticação.
Interface: Automatizar apenas o "Caminho Feliz" (CT01) e as validações de campo visual (CT05). Usar para garantir que o redirecionamento visual e o tempo de carregamento perceptível estão dentro do esperado.
Manual: Testar cenários de UX complexos ou interrupções de rede muito específicas que são difíceis de simular via script de forma estável.

4. Estrutura Proposta para o Projeto de Automação
Estrutura em Java + Maven utilizando Cypress (para UI) e RestAssured (para API), organizada da seguinte forma:

src/
 ├── main/
 └── test/
      ├── java/
      │    ├── com.desafio.pages/     # Elementos da tela (Page Objects)
      │    ├── com.desafio.api/       # Requests e Payloads (RestAssured)
      │    └── com.desafio.specs/     # Os scripts de teste (JUnit/TestNG)
      └── resources/
           ├── data/                  # Massas de teste (JSON/CSV)
           └── features/              # Cenários em BDD (Gherkin)

Pilares da Estrutura:
Page Object: Isolar os seletores da tela da lógica do teste.
Hooks (@Before/@After): Para limpar cookies e resetar o estado do banco antes de cada login.
Data-Driven: Usar um arquivo JSON para rodar o mesmo teste de login com diferentes perfis (ADMIN, USER, VISITOR).
Reports: Integração com Allure Reports para gerar evidências visuais em caso de falha.