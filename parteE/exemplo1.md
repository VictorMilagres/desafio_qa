## Exemplo 1 - Propósito e correção
```
SELECT u.username, COUNT(a.id) AS total_logins
FROM usuarios u
LEFT JOIN auditoria_login a ON a.usuario_id = u.id AND a.sucesso = true
WHERE u.perfil = 'ADMIN'
GROUP BY u.username
HAVING COUNT(a.id) > 5
ORDER BY total_logins DESC;
```

### Perguntas:
1. Qual o propósito dessa consulta?
R: Identificar usuários administradores ativos e frequentes. A consulta busca o nome de usuário apenas de quem possui o perfil 'ADMIN'. Conta quantos logins realizados com sucesso cada um dos administradores realizou. Filtra os resultados para mostrar apenas os que tiveram mais de 5 logins com sucesso e apresenta a lista do mais ativo para o menos ativo.

2. Há erros lógicos ou sintáticos?
R: No lugar do LEFT JOIN com a condição a.sucesso = true dentro do ON, usuários 'ADMIN' que nunca logaram (ou nunca logaram com sucesso) aparecerão com total_logins = 0. No entanto, a cláusula HAVING COUNT(a.id) > 5 excluirá esses usuários de qualquer maneira. Se o objetivo é visualizar somente quem tem logins, um INNER JOIN seria mais performático. 

3. Que tipo de cenário de teste você derivaria a partir dela?

| Cenário              | Descrição                                                            | Resultado Esperado                          |
| Limite               | Usuário ADMIN com exatamente 5 logins de sucesso.                    | Não deve aparecer (o filtro é > 5).         |
| Sucesso              | Usuário ADMIN com 6 logins de sucesso.                               | Deve aparecer com total 6.                  |
| Filtro de Perfil     | Usuário 'USER' comum com 10 logins de sucesso.                       | Não deve aparecer (filtro de perfil ADMIN). |
| Filtro de Status     | Usuário ADMIN com 10 tentativas de login, mas todas sucesso = false. | Não deve aparecer (contagem será 0).        |
| Integridade de Dados | Usuário ADMIN sem nenhum registro na tabela de auditoria.            | Não deve aparecer.                          |
| Ordenação            | Dois usuários ADMINs com 10 e 15 logins respectivamente.             | O de 15 logins deve aparecer primeiro.      |