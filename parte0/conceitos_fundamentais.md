## 1. Tipos de teste: 
### Explique a diferença entre teste de unidade, teste de integração e teste de ponta a ponta, e indique qual deles deve ser priorizado em um pipeline CI/CD.

R: O teste de unidade testa a menor parte do código possível, ou seja, uma função, um método isolado. Sendo rápido fácil de saber quando falha, pois dá pra saber exetamente em qual linha está o erro.
O teste de integração é para validar se todas as integrações estão funcionando como deveriam, por exemplo, se o request na api salva as informações no banco de dados.
O teste E2E simula o usuário final utilizando a plataforma, seja ela no navegador ou aplicação mobile.

No pipeline o interessante é priorizar os testes de unidade, pois o que esperamos são respostas rápidas que tragam segurança para seguir com a execução.

## 2. Automação e ROI:
### Cite três vantagens e três riscos da automação de testes em projetos corporativos.

R: Vantagens: 
Velocidade no Feedback: Em projetos com muitas entregas, não dá para esperar testes manuais a cada entrega onde precisa ser executado sempre um teste regressivo, então a automação acaba acelarando as entregas para minutos ao invés de horas.
Execução de Tarefas Repetitivas com mínimas falhas humanas: Nos testes manuais testes longos com prrenchimentos extensos acabam aumentando a chance de falha por cansaço de fazer tarefa repetida, a automação remove grande parte dessa falha.
Maior Cobertura de Testes: Com a automação o sistema pode ser testado em diferentes navegadores, sistemas operacionais e resoluções de tela simultaneamente, algo que demandaria muito mais tempo caso fosse feito manualmente.

Riscos:
Custo de Manutenção: Se acontecem algumas mudanças sem alinhamento do time, os testes automatizados param de funcionar. Em projetos grandes, isso pode levar a gastar mais tempo consertando testes antigos do que criando novos.
Falsa Sensação de Segurança: O teste automatizado só testa o que foi construido para testar. Portanto apesar de já termos algumas ferramentas que testar a UI, ele ainda não tem o fator humano para saber se um layout está bom, se uma imagem carregou ou não.
Investimento Inicial Elevado: Automatizar custa caro no começo, pois é necessário ferramentas, infraestrutura e profissionais.

## 3. Teste de integração em microserviços:
### Quais são os principais desafios para automação de testes de integração em uma arquitetura de microserviços? Cite dois exemplos de boas práticas para lidar com dependências externas.

R: Os principais desafios são a instabilidade dos ambientes, onde se um serviço falha invalida os testes, e a dificuldade de sincronizar massas de dados entre múltiplos bancos. Soma-se a isso a latência e falhas de rede, que geram testes instáveis, e o esforço técnico para gerenciar as diversas versões de APIs que mudam constantemente, exigindo manutenção pesada nos scripts.

Dois exemplos de boas práticas são Contract Testing, onde garante que as APIs respeitem acordos de formato de dados sem depender do sistema real, e no uso de Mocks e Stubs para simular respostas de serviços externos instáveis.

## 4. Idempotência e consistência:
### Como garantir a idempotência dos testes automatizados que interagem com um bando de dados PostgresSQL?

R: Para garantir a idempotência, podemos utilizar transações com rollback assim evitamos a persistência de dados e a implementação de limpezas antes de cada execução para garantir um estado inicial consistente. É preferivel o uso de dados dinâmicos e únicos para evitar conflitos de restrição e também utilizar containers isolados para que cada suíte de teste tenha seu próprio ambiente descartável e livre de efeitos colaterais.

## 5. Mecanismos de simulação:
### Diferencie Mock, Stub e Spy. Cite uma biblioteca Java capaz de implementar cada conceito.

R :A diferença entre eles está no nível de controle e observação: o Stub apenas fornece respostas fixas para simplificar o teste, o Mock é um objeto pré-programado para validar comportamentos, e o Spy envolve um objeto real para observar e contar chamadas sem alterar a lógica. Na prática, Stubs focam em estado, Mocks em interações e Spies em monitoramento. Em Java, a biblioteca Mockito é capaz de implementar os três conceitos.

## 6. Boas práticas de automação:
### Quais são os pricípios básicos de um bom projeto de testes automatizados em Java (estrutura de pacotes, nomeação, isolamento e reuso de código)?

R: Um bom projeto organiza-se através do Page Object onde separamos a interface da lógica de teste, garantindo melhor manutenção. O isolamento é mantido através de estados limpos em cada @BeforeEach, enquanto a nomeação dos métodos descreve claramente o comportamento que se espera. O reuso de código é feito via Base Classes e Builders, evitando a duplicação de massa de dados e configurações.

## 7. Gestão de dados de teste:
### Como você controlaria e versionaria os dados de teste usados em ambientes compartilhados (dev/test/staging)?

R: A melhor forma de controlar dados é através do versionamento de migrations para garantir consistência estrutural e o uso de Scripts de Seeding versionados no Git. Devemos  priorizar a criação de dados via APIs para garantir autonomia em ambientes compartilhados, evitando dependência de registros manuais. Além disso, o uso de dados dinâmicos previne conflitos e o uso de containers isola as execuções, impedindo que um teste interfira outro ambiente.