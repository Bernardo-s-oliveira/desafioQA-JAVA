#language: pt
Funcionalidade: Preenchimento do formulário
  
  Como um usuurio 
  Gostaria de cadastrar dados
  Para que eu possa preencher o formulário com sucesso
  
	Contexto:   
		Dado que estou acessando a aplicacao

  Esquema do Cenario: Deve inserir dados no formulário
    Quando informo o nome <nome>
    E o email <email>
    E a cidade/estado <cidade/estado>
    E a mensagem <mensagem>
    E seleciono enviar
    Entao o formulario foi preenchido com sucesso

    Exemplos: 
      | nome                         | email     | cidade/estado  | mensagem                        | 
      | "Bernardo Souza de Oliveira" | "b@b.com" | "Joinville/SC" | "Todos os campos preenchidos"   |
      | "Bernardo Souza"             | "b@b.com" | ""             | "cidade/estado não obrigatório" |
      | "Bernardo Oliveira"          | "b@b.com" | "Joinville/SC" | ""     											    |

  Esquema do Cenario: Nao deve enviar o formulário
    Quando informo o nome <nome>
    E o email <email>
    E a cidade/estado <cidade/estado>
    E a mensagem <mensagem>
    E seleciono enviar
    Entao sou notificado que o campo obrigatorio nao foi preenchido

    Exemplos: 
      | nome             | email     | cidade/estado  | mensagem               |
      | ""               | "b@b.com" | "Joinville/SC" | "nome não preenchido"  |
      | "Bernardo Souza" | ""        | "Joinville/SC" | "email não preenchido" |
