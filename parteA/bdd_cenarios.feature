Feature: Autenticação de Usuários por Perfil
Como um usuário do sistema 
Eu quero realizar login informando minhas credenciais 
Para que eu possa acessar as funcionalidades permitidas ao meu perfil

Background: Dado que o usuário está na página de login

Scenario: Login com Perfil Administrativo (Positivo)
Quando eu insiro o e-mail de um "ADMIN" válido 
E insiro a senha correta E clico no botão "Entrar" 
Então o sistema deve me redirecionar para o "Dashboard de Gestão" em até 5 segundos 
E o menu "Configurações do Sistema" deve estar visível

Scenario: Login com Perfil Visitante (Positivo)
Quando eu insiro o e-mail de um "VISITOR" válido 
E insiro a senha correta E clico no botão "Entrar" 
Então o sistema deve me redirecionar para a "Home" em até 5 segundos 
E o botão "Editar Perfil" deve estar desabilitado

Scenario: Tentativa de Login com Senha Incorreta (Negativo)
Quando eu insiro um e-mail cadastrado 
E insiro uma senha incorreta E clico em "Entrar" 
Então devo ver a mensagem de erro "Usuário ou senha inválidos" 
E devo permanecer na página de login

Scenario: Bloqueio de Conta por Tentativas Excessivas (Segurança)
Dado que eu já realizei 4 tentativas de login com senha incorreta 
Quando eu realizo a 5ª tentativa com senha incorreta 
Então o sistema deve exibir a mensagem "Conta bloqueada por 15 minutos" 
E não deve permitir novos logins mesmo com a senha correta nesse período

Scenario: Validação de Formato de E-mail (Negativo)
Quando eu insiro o texto "email_invalido.com" no campo de e-mail 
E tento clicar no botão "Entrar" 
Então o sistema deve exibir um alerta visual "Informe um e-mail válido" 
E a requisição de login não deve ser enviada ao servidor

Scenario: Acesso Direto via URL (Segurança)
Quando eu tento acessar a URL "/admin/dashboard" sem estar logado 
Então o sistema deve me redirecionar para a página de "/login" 
E não deve carregar nenhum dado sensível da página administrativa

Scenario: Performance de Carregamento (Performance)
Quando eu realizo o login em uma conexão de rede limitada 
Então o carregamento da página inicial não deve ultrapassar o limite de 5 segundos 
E nenhum elemento da interface deve aparecer quebrado ou incompleto

Scenario: Proteção contra SQL Injection (Segurança)
Quando eu insiro ' OR '1'='1 no campo de usuário 
E insiro qualquer senha 
E clico em "Entrar" 
Então o sistema deve tratar os caracteres como texto comum 
E deve retornar a mensagem "Usuário ou senha inválidos"