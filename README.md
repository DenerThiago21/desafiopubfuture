# desafiopubfuture

Para execução do projeto.
- Requisitos:
  * SGBD Postgres
  * Insomnia 

1. Fazer o Clone do Projeto;
  1.1.  Acessar o diretorio resources e dentro do arquivo application.properties digitar os dados de usuario e senha para o banco de dados instalado;

2. importar o arquivo Insomnia_2022-01-16 para o insomnia e executar as tarefas dentro do codigo;

3. Endpoints:
 1 Conta:
  - POST: http://localhost:8080/contas -> (cria uma conta)
  - PUT: http://localhost:8080/contas/{id} -> (edita uma conta, onde {id} é o identificador da conta a ser editada) 
  - DELETE: http://localhost:8080/contas/{id} -> (remove uma conta, onde {id} é o identificador da conta a ser editada)
  - GET: http://localhost:8080/contas -> (retorna todas as contas cadastradas no banco de dados)
  - PUT: http://localhost:8080/contas/{idRemetente}/{idDestinatario} -> (faz transferencia de valores entre as contas, onde {idRemetente} é o identificador da conta a que vai transferir dinheiro e {idDestinatario} é o identificador da conta que irá receber o dinheiro)
  - GET: http://localhost:8080/contas/saldoTotal -> (retorna o saldo total)

 2 Despesa:
  - POST: http://localhost:8080/despesas/{conta_id} -> (cria uma despesa, onde {conta_id} é o identificador da conta em que a despesa estará vinculada)
  - PUT: http://localhost:8080/despesas/{id}/{conta_id} -> (edita uma despesa, onde {id} é o identificador da conta e {conta_id} é o identificador da conta em que a despesa está vinculada)
  - DELETE: http://localhost:8080/despesas/{id} -> (remove uma despesa, onde {id} é o identificador da despesa a ser removida)
  - GET: http://localhost:8080/despesas -> (retorna todas as despesas cadastradas)

 3 Receita:
  - POST: http://localhost:8080/receitas/{id} -> (cria uma receita {conta_id} é o identificador da conta em que a receitaá vinculada)
  - PUT: http://localhost:8080/receitas/{id}/{conta_id} -> (edita uma receita, onde {id} é o identificador da conta e {conta_id} é o identificador da conta em que a receita está vinculada)
  - DELETE: http://localhost:8080/receitas/{id} -> (remove uma receita, onde {id} é o identificador da receita a ser removida)
  - GET: http://localhost:8080/receitas -> (retorna todas as receitas cadastradas)
 

