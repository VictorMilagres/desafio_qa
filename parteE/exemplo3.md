## Exemplo 3 - Verificação de bloqueio
```
SELECT u.username, u.tentativas,
CASE WHEN u.tentativas >= 3 THEN 'BLOQUEADO' 
ELSE 'ATIVO' END AS status
FROM usuarios u;
```

### Perguntas:
1. Descreva um cenário que resulte em "BLOQUEDO";

R: Para que um usuário apareça com o status 'BLOQUEADO' nesta consulta, ele deve passar pelo fluxo:
1. O usuário deve estar cadastrado na tabela usuarios.
2. O usuário tenta realizar o login e erra a senha por pelo menos 3 vezes.
3. A cada erro, o banco deve ser atualizado com a tentativa.

2. Como validar a consistências após o teste?

R: Tentar realizar um login com as credenciais corretas para esse usuário. O sistema deve retornar o erro de que o usuário está bloqueado.

3. Como limpar a base após o teste?

R: Pode ser feito de 2 formas:
1. Fazer um update no banco de dados, é o mais comum, já que o objetivo não é deletar o usuário, apenas desbloquear.
2. Fazer uma request na api de desbloqueie o usuário, utilizando no BeforeEcha antes de executar os testes ou no AfterEach após ter bloqueado o usuário.
