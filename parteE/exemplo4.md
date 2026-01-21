## Exemplo 4 - Erro conceitual em junção
```
SELECT u.username, a.data_evento
FROM usuarios u, auditoria_login a
WHERE u.id = a.usuario_id(+);
```

### Perguntas:
1. Qual o erro na sintaxe?
R:O erro principal é o uso do (+). No PostgreSQL as junções devem ser declaradas explicitamente no FROM (ex: LEFT JOIN), e não através de operadores especiais no WHERE.

2. Como reescrever corretamente?
R: Para o PostgreSQL e qualquer banco moderno, você deve usar a cláusula LEFT JOIN. Isso garante que todos os usuários sejam listados, mesmo aqueles que não possuem registros na tabela de auditoria.

Correção:
```
SELECT u.username, a.data_evento
FROM usuarios u
LEFT JOIN auditoria_login a ON u.id = a.usuario_id;
```
3. Que erro o PostegreSQL retornaria?
Ele retornaria: 
```
ERROR: syntax error at or near "(" ou ERROR: operator does not exist: integer = record
```