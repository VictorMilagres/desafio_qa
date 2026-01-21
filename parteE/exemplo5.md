## Exemplo 5 - Integridade e dados órfãos
```
SELECT a.id, a.usuario_id
FROM auditoria_login a
WHERE a.usuario_id NOT IN (SELECT id FROM usuarios);
```

### Perguntas:
1. Qual o propósito da consulta?

R: Identificar quebras de integridade referencial. A consulta busca registros na tabela de auditoria_login que fazem referência a um usuario_id que não existe mais ou nunca existiu na tabela principal de usuarios.

2. Como ela contribui para testes de integração?

R: Em testes de integração, essa consulta atua como um validador de "efeito colateral". Se o sistema permitir que um usuário seja excluído, mas não limpa ou trata os registros de auditoria, essa consulta pegará a falha que um teste funcional de UI ou API passaria sem apresentar um erro.

3. Que cenário de teste validaria isso?

R: O Teste de excluir com persistência:
1. Criar um usuário.
2. Gerar registros de auditoria para ele.
3. Excluir o usuário através da API.
4. Executar a consulta.
Resultado: A query não deve retornar nada. Se retornar o ID da auditoria do usuário deletado, o sistema tem um bug de integridade.

4. Como evitar o problema no banco?

Existem 2 formas que são as mais praticadas: 
Utilização de Chave Estrangeira, onde criar uma restrição de integridade e o banco impede que um usuário seja deletado se houver auditorias ligadas a ele.
Soft Delete: Em vez de usar DELETE, usar uma coluna deletado = true. Assim, o registro do usuário permanece no banco para manter o histórico da auditoria, mas é como se estivesse deletado para a aplicação.
