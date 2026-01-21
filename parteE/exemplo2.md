## Exemplo 2 - Identificação de erro lógico
```
SELECT * FROM usuarios WHERE bloqueado = 'false';
```

### Perguntas:
1. O que há de errado nesta consulta no PostgreSQL?

R:O erro é de incompatibilidade de tipos. No PostgreSQL, a coluna bloqueado provavelmente é do tipo boolean e na consulta o 'false' esta entre aspas, o que o banco interpreta como uma string. Assim retornando um erro.

2. Como corrigir?

R: 
```
SELECT * FROM usuarios WHERE bloqueado = false;
```
ou
```
SELECT * FROM usuarios WHERE NOT bloqueado;
```

3. Como um teste automatizado poderia detectar essa falha?

R: Sim. Ao realizar um request utilizando o endpoint que faz essa consulta o teste receberia um `500 Internal Server Error` ao invés de `status 200`.
