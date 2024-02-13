>O projeto foi desenvolvido afim de aprimorar novos conhecimentos afins de segurança de autenticação e autorizalçao de uma API.

### Desenvolvimento

O projeto segue apenas para fins de estudos e aplicação do mesmo, segue abaixo oque foi ultilizado:

- Java
- Spring boot
- Spring Security
- JWT
- JPA
- H2
- Postgres


## Funcionalidade
a api tem como objetivo permitir que um usuário consiga se registrar com os seguintes atributos:
- login
- senha
- nome
- endereço
- função ("ADM", "USER")

após o registro o usuário poderá se autenticar com login e senha. se o cadastro constar na base de dados o usuário receberá um token de acesso que por hora o dara acesso as seguintes funcionalidades:

os usuários com perfil de adm poderam:

alterar de qualquer usuário os seguintes atributos:

- nome
- senha
- endereço
- segredo para trocar a senha
- função
- deletar qualquer usuário

alem de consuirem ver todos os usuários cadstrados.


os usuários com perfil de user poderam alterar somente seus atributos:

- nome
- senha
- endereço
- segredo para trocar a senha


