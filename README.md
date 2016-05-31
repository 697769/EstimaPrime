# EstimaPrime
Repository Publish

#Índice
20/03/2016   First Commit (Repository Publish)   
20/03/2016   First Aplication Diretory Commit (Version 1.0)  
20/03/2016   Application MobileEstimaPrime Version 1.0   
25/04/2016   Application MobileEstimaPrime Version 2.0
02/05/2016   Application MobileEstimaPrime Version 2.1  

#Pendentes pra Entrega Semestral 09/06/2016
- Valor total das notas de Entrada e Saída da Empres
- Telas de Cadastros melhoradas
- Organização e estruturação do código padrão java.
- Disponibilização do .APK funcional (basico:versão beta)


#Prioritarios
- Criação de um serviço POST que recebera o EMAIL e SENHA, e retornara uma lista de empresas se validado  
- Implementação dos serviços com a classe REST do app   
- serviços que retornara os dados da empresa selecionada  

#Version 1.0
- Activity's criadas
- Utilização do LayoutLinear
- Utilização do conceito de pesos(weight) para ajustar controles nas telas
- Implementado Styles, para otimização e padronização dos botões da aplicação
- Adicionado algumas Strings
- Adicionado Cores
- Criação da Classe DBHelper (Classes para Manutenção do Banco de Dados a Nível: Modelo)
- Criação da Classe DBManager (Classe para Manutenção nas Tabelas a Nivel: Controle)
- Botões e suas respectivas intents criadas
- Botões voltar funcionando parcialmente
- Botão Selecionar Empresa (Activity Home) Retorna para Seleção de Empresas(Enterprises)
- Botão Sair (Activity Home) Retorna para Tela de Login(Main_Activity)
- Adicionada Animações para sobreposiçlão de Transições de Tela.
- Adicionado Dimenções para controle e padronização de textos
- Tratamento de E-mail nulo, mostranto Toast Message
- Tratamento de E-mail invalido, mostranto Toast Message
- Tratamento de Password nulo, mostranto Toast Message
- Tratamento de Password invalido, mostranto Toast Message
- Função de retorna do banco se o usuario existe para validar E-mail
- Função que recebe o email, e retorna a senha, para comparar com a senha digitada.

#Version 2.0   
- Implementação de Classes utilizando SQLite    
- Implementação do Desenvolvimento MVC    
- Criação dos pacotes para desenvolvimento MVC    
- Analise e modelagem do Banco de Dados   
- Verificação e integração com Banco de Dados através de um Web Service      
- Pacote REST para comunicação com webservice   
- Lista de Empresas do Banco de Dados.
- Seleção de Empresas
- Criação do Repositório para o produto EstimaPrime-webapp(site que preenchera a base de dados)
- Criação do Repositório para a ferramenta de integração dos Sistemas (web service)   
- Criação do Servico RPC para retornar se há conexão com a aplicação web service (ping)   
- Criação de um serviço de teste REST(GET) com SQLite   

#Version 2.1
- Alterações na classe DAO de Enterprise    
- Alterações na classe DAO de User  
- Usuário que esta logado passa a ser armazenado com shareed preferences  
- Aparecendo apenas as empresas de acordo com o código do usuário   
- Salvando a empresa escolhida com shareed preferences  
- Otimizações em relação ao banco de dados  
- Criação de validações e testes para realizar procedimentos como: Logar,Selecionar empresa.  
  
#Version 2.2  
- Home activity and layout changes (add type list item)   
