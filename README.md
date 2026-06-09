# Thalassor - API RESTful para Monitoramento de Poluição Marinha

## Visão Geral

O **Thalassor** é uma solução desenvolvida para o monitoramento de focos de poluição marinha. O projeto consiste em uma API RESTful construída com **Java** e **Quarkus**, integrada a banco de dados **Oracle** e preparada para integração com um Front-End.

A aplicação permite cadastrar regiões monitoradas, usuários operadores, embarcações, focos de poluição e ordens de coleta. Além disso, o sistema associa focos de poluição a imagens orbitais públicas por meio da integração com o serviço **NASA Worldview/GIBS**, gerando uma URL de visualização da região monitorada.

## Tema da Solução

**Monitoramento de poluição marinha com apoio de imagens orbitais e gerenciamento de ordens de coleta.**

O projeto busca apoiar a identificação, classificação e acompanhamento de focos de poluição em áreas oceânicas, permitindo que operadores registrem ocorrências, consultem regiões afetadas e criem ordens de coleta para embarcações responsáveis pela limpeza.

## Integrantes

| Nome                          | RM       |
| ----------------------------- | -------- |
| Kaliel Aquino                 | RM567587 |
| Andre Sousa Matuda            | RM566733 |
| Guilherme Oliveira Feitosa    | RM566842 |
| Paulo Henrique Muniz Diedrich | RM567618 |  

## Equipe

**Nome da equipe:** Thalassor

## Links do Projeto

| Recurso            | Link                                   |
| ------------------ | -------------------------------------- |
| Backend Deploy     | https://thalassor.onrender.com         |
| Swagger da API     | https://thalassor.onrender.com/swagger |
| Front-End Deploy   | https://thalassor.vercel.app           |
| Repositório GitHub | https://github.com/If-Kaliel/thalassor |

## Tecnologias Utilizadas

| Tecnologia           | Finalidade                                  |
| -------------------- | ------------------------------------------- |
| Java 21              | Linguagem principal                         |
| Quarkus 3.15.1       | Framework backend                           |
| Jakarta REST         | Criação dos endpoints REST                  |
| JDBC                 | Comunicação manual com o banco Oracle       |
| Oracle Database FIAP | Persistência dos dados                      |
| Maven                | Gerenciamento de dependências e build       |
| Docker               | Containerização da aplicação                |
| Render               | Deploy do backend                           |
| Vercel               | Deploy do frontend                          |
| Swagger/OpenAPI      | Documentação e testes da API                |
| NASA Worldview/GIBS  | Geração de imagem orbital da região do foco |
| IntelliJ IDEA        | IDE utilizada no desenvolvimento            |

## Objetivo do Projeto

O objetivo do Thalassor é fornecer uma API RESTful capaz de registrar, consultar, atualizar e remover informações relacionadas ao monitoramento de poluição marinha.

A solução permite:

* Cadastrar usuários operadores do sistema.
* Cadastrar regiões oceânicas monitoradas.
* Registrar focos de poluição detectados.
* Calcular automaticamente o nível de risco de um foco.
* Associar uma imagem orbital ao foco usando serviço da NASA.
* Cadastrar embarcações de coleta.
* Gerenciar ordens de coleta associadas a focos, embarcações e usuários.
* Disponibilizar endpoints REST para integração com o Front-End.

## Escopo Implementado

Foram implementados os principais recursos previstos para o backend da solução:

* CRUD completo de usuários.
* CRUD completo de regiões.
* CRUD completo de focos de poluição.
* CRUD completo de embarcações.
* CRUD completo de ordens de coleta.
* Integração com Oracle Database.
* Integração com NASA Worldview/GIBS para geração de URL de imagem orbital.
* Cálculo automático de nível de risco para focos de poluição.
* Tratamento global de exceções.
* Documentação automática via Swagger.
* Deploy em nuvem no Render.
* Configuração de CORS para integração com Front-End.

## Funcionalidades Principais

### 1. Cadastro e listagem de regiões

Permite cadastrar regiões marítimas monitoradas, informando nome da região e oceano correspondente.

Exemplo de região:

```json
{
  "nomeRegiao": "Litoral Norte - São Paulo",
  "oceano": "ATLANTICO"
}
```

### 2. Cadastro e listagem de usuários

Permite cadastrar operadores do sistema, como analistas, capitães e administradores.

Perfis aceitos:

```text
ANALISTA
CAPITAO
ADMIN
```

Exemplo de usuário:

```json
{
  "nomeUsuario": "Analista Mariana",
  "email": "mariana@ecocean.com",
  "senha": "senha123",
  "perfil": "ANALISTA"
}
```

### 3. Cadastro de focos de poluição

Permite registrar focos de poluição marítima com latitude, longitude, extensão estimada, índice FDI e status.

Durante o cadastro, a API calcula automaticamente o nível de risco e gera uma URL de imagem orbital.

Exemplo de requisição:

```json
{
  "idRegiao": 1,
  "latitude": -24.12,
  "longitude": -45.38,
  "extensaoKm2": 58.75,
  "indiceFdi": 0.72,
  "statusFoco": "DETECTADO"
}
```

Exemplo de resposta:

```json
{
  "idFoco": 6,
  "idRegiao": 1,
  "latitude": -24.12,
  "longitude": -45.38,
  "extensaoKm2": 58.75,
  "indiceFdi": 0.72,
  "urlImagem": "https://wvs.earthdata.nasa.gov/api/v1/snapshot?...",
  "statusFoco": "DETECTADO",
  "dataDeteccao": "2026-06-07T22:58:36.704+00:00",
  "nivelRisco": "ALTO"
}
```

### 4. Cálculo automático de risco

O nível de risco é calculado pela camada de regra de negócio com base na extensão do foco e no índice FDI.

Classificações possíveis:

```text
BAIXO
MEDIO
ALTO
CRITICO
```

Critérios utilizados:

| Condição                                                       | Nível de Risco |
| -------------------------------------------------------------- | -------------- |
| Extensão menor que 10 km² e FDI menor que 0.35                 | BAIXO          |
| Extensão maior ou igual a 10 km² ou FDI maior ou igual a 0.35  | MEDIO          |
| Extensão maior ou igual a 50 km² ou FDI maior ou igual a 0.65  | ALTO           |
| Extensão maior ou igual a 100 km² ou FDI maior ou igual a 0.85 | CRITICO        |

### 5. Integração com NASA Worldview/GIBS

Ao cadastrar um foco de poluição, a API gera automaticamente uma URL de imagem orbital usando latitude e longitude.

A URL gerada permite visualizar a região monitorada por meio de imagens públicas da NASA.

Exemplo de URL gerada:

```text
https://wvs.earthdata.nasa.gov/api/v1/snapshot?REQUEST=GetSnapshot&TIME=2026-05-31T00%3A00%3A00Z&BBOX=-24.320000%2C-45.580000%2C-23.920000%2C-45.180000&CRS=EPSG%3A4326&LAYERS=MODIS_Terra_CorrectedReflectance_TrueColor&WRAP=day&FORMAT=image%2Fjpeg&WIDTH=1200&HEIGHT=800
```

### 6. Cadastro de embarcações

Permite cadastrar embarcações responsáveis por realizar coleta e limpeza de focos de poluição.

Status aceitos:

```text
DISPONIVEL
EM_MISSAO
MANUTENCAO
```

Exemplo:

```json
{
  "nomeEmbarcacao": "EcoWave Collector I",
  "capacidadeToneladas": 15.5,
  "statusEmbarcacao": "DISPONIVEL"
}
```

### 7. Gerenciamento de ordens de coleta

Permite criar ordens de coleta associando:

* Um foco de poluição.
* Uma embarcação.
* Um usuário responsável.

Status aceitos:

```text
PENDENTE
EM_ANDAMENTO
CONCLUIDA
CANCELADA
```

Exemplo:

```json
{
  "idFoco": 6,
  "idEmbarcacao": 1,
  "idUsuario": 1,
  "statusOrdem": "PENDENTE",
  "observacoes": "Ordem criada para recolhimento inicial do foco."
}
```

## Arquitetura do Projeto

O projeto segue uma separação em camadas:

```text
src/main/java/br/com/fiap
├── bo
├── conexoes
├── dao
├── entities
├── exceptions
├── resources
└── services
```

### Camadas

| Camada     | Responsabilidade                              |
| ---------- | --------------------------------------------- |
| entities   | Classes que representam os dados do sistema   |
| dao        | Acesso ao banco de dados usando JDBC          |
| bo         | Regras de negócio e validações                |
| resources  | Endpoints REST da API                         |
| services   | Serviços auxiliares, como integração com NASA |
| exceptions | Tratamento global de exceções                 |
| conexoes   | Classe de conexão com o Oracle                |

## Estrutura de Diretórios

```text
thalassor
├── Dockerfile
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── fiap
│   │   │               ├── bo
│   │   │               ├── conexoes
│   │   │               ├── dao
│   │   │               ├── entities
│   │   │               ├── exceptions
│   │   │               ├── resources
│   │   │               └── services
│   │   └── resources
│   │       └── application.properties
│   └── test
└── README.md
```

## Banco de Dados

O projeto utiliza banco de dados Oracle.

Principais tabelas:

```text
TB_USUARIO
TB_REGIAO
TB_FOCO_POLUICAO
TB_EMBARCACAO
TB_ORDEM_COLETA
```

### TB_USUARIO

Armazena operadores do sistema.

Campos principais:

```text
id_usuario
nm_usuario
ds_email
ds_senha
tp_perfil
dt_cadastro
```

### TB_REGIAO

Armazena regiões monitoradas.

Campos principais:

```text
id_regiao
nm_regiao
ds_oceano
```

### TB_FOCO_POLUICAO

Armazena focos de poluição.

Campos principais:

```text
id_foco
id_regiao
vl_latitude
vl_longitude
vl_extensao_km2
vl_indice_fdi
url_imagem
st_foco
dt_deteccao
ds_nivel_risco
```

### TB_EMBARCACAO

Armazena embarcações de coleta.

Campos principais:

```text
id_embarcacao
nm_embarcacao
vl_capacidade_t
st_embarcacao
```

### TB_ORDEM_COLETA

Armazena ordens de coleta.

Campos principais:

```text
id_ordem
id_foco
id_embarcacao
id_usuario
dt_abertura
dt_conclusao
st_ordem
tx_observacoes
```

## Tabela de Endpoints

### Home

| Método | URI | Descrição                         | Status esperado |
| ------ | --- | --------------------------------- | --------------- |
| GET    | `/` | Retorna informações gerais da API | 200             |

### Usuários

| Método | URI              | Descrição                  | Status esperado |
| ------ | ---------------- | -------------------------- | --------------- |
| GET    | `/usuarios`      | Lista todos os usuários    | 200             |
| GET    | `/usuarios/{id}` | Busca usuário por ID       | 200, 404        |
| POST   | `/usuarios`      | Cadastra novo usuário      | 201, 400        |
| PUT    | `/usuarios/{id}` | Atualiza usuário existente | 200, 400, 404   |
| DELETE | `/usuarios/{id}` | Remove usuário             | 204, 404        |

### Regiões

| Método | URI             | Descrição                 | Status esperado |
| ------ | --------------- | ------------------------- | --------------- |
| GET    | `/regioes`      | Lista todas as regiões    | 200             |
| GET    | `/regioes/{id}` | Busca região por ID       | 200, 404        |
| POST   | `/regioes`      | Cadastra nova região      | 201, 400        |
| PUT    | `/regioes/{id}` | Atualiza região existente | 200, 400, 404   |
| DELETE | `/regioes/{id}` | Remove região             | 204, 404        |

### Focos de Poluição

| Método | URI           | Descrição               | Status esperado |
| ------ | ------------- | ----------------------- | --------------- |
| GET    | `/focos`      | Lista todos os focos    | 200             |
| GET    | `/focos/{id}` | Busca foco por ID       | 200, 404        |
| POST   | `/focos`      | Cadastra novo foco      | 201, 400        |
| PUT    | `/focos/{id}` | Atualiza foco existente | 200, 400, 404   |
| DELETE | `/focos/{id}` | Remove foco             | 204, 404        |

### Embarcações

| Método | URI                 | Descrição                     | Status esperado |
| ------ | ------------------- | ----------------------------- | --------------- |
| GET    | `/embarcacoes`      | Lista todas as embarcações    | 200             |
| GET    | `/embarcacoes/{id}` | Busca embarcação por ID       | 200, 404        |
| POST   | `/embarcacoes`      | Cadastra nova embarcação      | 201, 400        |
| PUT    | `/embarcacoes/{id}` | Atualiza embarcação existente | 200, 400, 404   |
| DELETE | `/embarcacoes/{id}` | Remove embarcação             | 204, 404        |

### Ordens de Coleta

| Método | URI            | Descrição                | Status esperado |
| ------ | -------------- | ------------------------ | --------------- |
| GET    | `/ordens`      | Lista todas as ordens    | 200             |
| GET    | `/ordens/{id}` | Busca ordem por ID       | 200, 404        |
| POST   | `/ordens`      | Cadastra nova ordem      | 201, 400        |
| PUT    | `/ordens/{id}` | Atualiza ordem existente | 200, 400, 404   |
| DELETE | `/ordens/{id}` | Remove ordem             | 204, 404        |

## Códigos de Status Utilizados

| Código | Significado                                         |
| ------ | --------------------------------------------------- |
| 200    | Requisição realizada com sucesso                    |
| 201    | Recurso criado com sucesso                          |
| 204    | Recurso removido com sucesso, sem corpo na resposta |
| 400    | Erro de validação ou dados inválidos                |
| 404    | Recurso não encontrado                              |
| 500    | Erro interno no servidor                            |

## Tratamento de Exceções

O projeto possui uma classe global de tratamento de exceções:

```text
GlobalExceptionHandler.java
```

Ela padroniza respostas de erro em JSON.

Exemplo:

```json
{
  "erro": "Recurso não encontrado.",
  "status": 404,
  "tipo": "jakarta.ws.rs.NotFoundException"
}
```

## Regras de Negócio

### Validação de dados obrigatórios

A camada BO valida campos obrigatórios antes de chamar o DAO.

Exemplo:

* Nome da região obrigatório.
* Oceano obrigatório.
* Latitude obrigatória.
* Longitude obrigatória.
* Extensão maior que zero.
* Perfil de usuário válido.
* Status de foco válido.
* Status de embarcação válido.
* Status de ordem válido.

### Cálculo de risco

O risco do foco é calculado automaticamente com base na extensão e no índice FDI.

### Integridade entre entidades

As ordens de coleta dependem de foco, usuário e embarcação existentes no banco.

## Configuração Local

### Pré-requisitos

Instale as seguintes ferramentas:

```text
Java JDK 21
Maven
IntelliJ IDEA ou VS Code
Oracle Database ou acesso ao Oracle FIAP
Git
Docker, opcional
```

### Clonar o projeto

```bash
git clone https://github.com/If-Kaliel/thalassor.git
cd thalassor
```

### Configurar variáveis de ambiente no PowerShell

Antes de rodar localmente, configure as variáveis:

```powershell
$env:DB_USERNAME="SEU_USUARIO_ORACLE"
$env:DB_PASSWORD="SUA_SENHA_ORACLE"
$env:DB_URL="jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL"
$env:CORS_ORIGINS="http://localhost:5173,http://localhost:8080"
```

### Rodar o projeto localmente

```bash
mvn quarkus:dev
```

A API ficará disponível em:

```text
http://localhost:8080
```

Swagger local:

```text
http://localhost:8080/swagger
```

Dev UI local:

```text
http://localhost:8080/q/dev-ui
```

Observação: o Dev UI funciona apenas em ambiente de desenvolvimento. No Render, a aplicação roda em perfil de produção.

## Configuração do application.properties

O arquivo `application.properties` não deve conter credenciais reais do banco.

Exemplo:

```properties
# Swagger e OpenAPI
quarkus.swagger-ui.path=/swagger
quarkus.swagger-ui.always-include=true
quarkus.smallrye-openapi.info-title=Thalassor
quarkus.smallrye-openapi.info-version=1.0.0

# CORS
quarkus.http.cors=true
quarkus.http.cors.origins=${CORS_ORIGINS:http://localhost:5173,http://localhost:8080}
quarkus.http.cors.methods=GET,PUT,POST,DELETE,OPTIONS
quarkus.http.cors.headers=accept,authorization,content-type,x-requested-with,role
quarkus.http.cors.access-control-allow-credentials=false

# HTTP
quarkus.http.host=0.0.0.0
quarkus.http.port=${PORT:8080}
```

## Deploy no Render

O backend foi publicado no Render usando Docker.

### Variáveis de ambiente configuradas no Render

```text
DB_USERNAME=SEU_USUARIO_ORACLE
DB_PASSWORD=SUA_SENHA_ORACLE
DB_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
CORS_ORIGINS=http://localhost:5173,https://thalassor.vercel.app,https://thalassor.onrender.com
```

### Dockerfile

O projeto possui um `Dockerfile` na raiz para build e execução no Render.

```dockerfile
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/quarkus-app/lib/ ./lib/
COPY --from=build /app/target/quarkus-app/*.jar ./
COPY --from=build /app/target/quarkus-app/app/ ./app/
COPY --from=build /app/target/quarkus-app/quarkus/ ./quarkus/

EXPOSE 10000

CMD ["sh", "-c", "java -Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=${PORT:-10000} -jar quarkus-run.jar"]
```

## Integração com Front-End

O front-end deve consumir a API a partir da URL base:

```text
https://thalassor.onrender.com
```

Exemplo de chamada:

```javascript
const API_URL = "https://thalassor.onrender.com";

fetch(`${API_URL}/focos`)
  .then(response => response.json())
  .then(data => console.log(data));
```

Importante: utilizar sempre `https`, não `http`.

Correto:

```text
https://thalassor.onrender.com
```

Incorreto:

```text
http://thalassor.onrender.com
```

## Exemplos de Requisições

### Criar foco de poluição

```http
POST /focos
Content-Type: application/json
```

Body:

```json
{
  "idRegiao": 1,
  "latitude": -24.12,
  "longitude": -45.38,
  "extensaoKm2": 58.75,
  "indiceFdi": 0.72,
  "statusFoco": "DETECTADO"
}
```

### Listar focos

```http
GET /focos
```

### Buscar foco por ID

```http
GET /focos/6
```

### Atualizar foco

```http
PUT /focos/6
Content-Type: application/json
```

Body:

```json
{
  "idRegiao": 1,
  "latitude": -24.12,
  "longitude": -45.38,
  "extensaoKm2": 80.0,
  "indiceFdi": 0.78,
  "statusFoco": "EM_RECOLHIMENTO"
}
```

### Remover foco

```http
DELETE /focos/6
```

## Observações sobre Segurança

As credenciais do banco de dados não devem ser commitadas no GitHub.

O projeto utiliza variáveis de ambiente para armazenar:

```text
DB_USERNAME
DB_PASSWORD
DB_URL
CORS_ORIGINS
```

Arquivos e pastas ignorados pelo Git:

```gitignore
target/
.idea/
*.iml
.env
application-local.properties
```
Link do Front-End:

```text
https://thalassor.vercel.app
```
### Arquitetura Simplificada

```text
Front-End Vercel
       |
       v
API Quarkus no Render
       |
       v
Oracle Database FIAP

API Quarkus
       |
       v
NASA Worldview/GIBS
```

### Fluxo de Cadastro de Foco

```text
Usuário informa dados do foco no Front-End
       |
       v
Front-End envia POST /focos
       |
       v
FocoPoluicaoResource recebe a requisição
       |
       v
FocoPoluicaoBO valida dados e calcula risco
       |
       v
NasaSatelliteService gera URL da imagem orbital
       |
       v
FocoPoluicaoDAO salva no Oracle
       |
       v
API retorna foco cadastrado ao Front-End
```

## Como Importar o Projeto

### IntelliJ IDEA

1. Abrir o IntelliJ.
2. Selecionar `File > Open`.
3. Escolher a pasta do projeto `thalassor`.
4. Aguardar o Maven carregar as dependências.
5. Configurar as variáveis de ambiente na configuração de execução.
6. Rodar o projeto com `mvn quarkus:dev`.

### VS Code

1. Abrir a pasta do projeto.
2. Instalar extensões Java recomendadas.
3. Configurar variáveis de ambiente no terminal.
4. Executar `mvn quarkus:dev`.

## Considerações Finais

O Thalassor entrega uma API RESTful funcional, integrada ao banco de dados Oracle e preparada para consumo pelo Front-End. A solução implementa regras de negócio para classificação de risco ambiental, gerenciamento de focos de poluição e ordens de coleta, além de utilizar imagens orbitais públicas para contextualizar visualmente a região afetada.

A aplicação está publicada em ambiente de nuvem e pode ser testada por meio do Swagger ou pelo Front-End integrado.
