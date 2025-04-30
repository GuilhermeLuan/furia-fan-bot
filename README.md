# Furia Fan Bot 🐾

[![Language](https://img.shields.io/github/languages/top/GuilhermeLuan/furia-fan-bot)](https://github.com/GuilhermeLuan/furia-fan-bot)

Um bot para Telegram desenvolvido em Java com Spring Boot que informa sobre as próximas partidas, últimos resultados e a lineup atual do time de CS (Counter-Strike) da Furia. Os dados são obtidos em tempo real através da API da [PandaScore](https://pandascore.co/).

## Funcionalidades 

O bot responde aos seguintes comandos no Telegram:

*   **`/proximojogo`**: Exibe informações detalhadas sobre a(s) próxima(s) partida(s) agendada(s) da Furia, incluindo:
    *   Times envolvidos
    *   Campeonato e Fase
    *   Data e Hora (convertido para Horário de Brasília - BRT)
    *   Formato da partida (MD1, MD3, etc.)
    *   Links disponíveis para as transmissões (Streams).

*   **`/ultimoresultado`**: Mostra um resumo das últimas partidas concluídas da Furia, contendo:
    *   Times envolvidos
    *   Campeonato e Fase
    *   Data da partida (BRT)
    *   Placar final
    *   Time vencedor.

*   **`/lineup`**: Mostra a escalação atual de jogadores *ativos* da Furia, incluindo:
    *   Nickname do jogador
    *   Nome completo (se disponível)
    *   Idade
    *   Data de nascimento.

*   **`/ajuda`**: Apresenta uma mensagem com a lista de comandos disponíveis e uma breve descrição de cada um.

**Características Adicionais:**

*   **Fonte de Dados Confiável:** Utiliza a API REST v2 da PandaScore.
*   **Fuso Horário Localizado:** Datas e horas são sempre apresentadas no fuso horário de Brasília (America/Sao_Paulo).
*   **Respostas Formatadas:** As mensagens enviadas ao usuário são formatadas com emojis e estilo para uma melhor experiência no Telegram.
*   **Utilização de Cache (Redis Cache)**: Utilizei Cache para melhorar o tempo de resposta para o usuario. 

## Pré-requisitos 📋

Para executar este projeto localmente, você precisará ter instalado:

*   Java JDK 21.
*   Uma conta no Telegram.
*   Docker.
*   Um **Token de Bot do Telegram**: Crie um bot e obtenha o token falando com o [@BotFather](https://t.me/BotFather) no Telegram.
*   Um **Token de API da PandaScore**: Registre-se e obtenha seu token de acesso no [site oficial da PandaScore](https://pandascore.co/).

## Configuração ⚙️

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/GuilhermeLuan/furia-fan-bot.git
    cd furia-fan-bot
    ```
2.  **Crie e preencha o arquivo `.env`:**
    *   Na raiz do projeto, localize o arquivo `.envTemplate`.
    *   **Renomeie** este arquivo para `.env`.
    *   Abra o arquivo `.env` recém-criado e **adicione os valores** para as seguintes variáveis:

    ```dotenv
    # =======================================
    # Configurações do Bot Telegram
    # =======================================
    TELEGRAM_BOT_USERNAME=SEU_BOT_USERNAME
    TELEGRAM_BOT_TOKEN=SEU_TELEGRAM_BOT_TOKEN
    TELEGRAM_CREATOR_ID=SEU_ID (OPCIONAL)

    # =======================================
    # Configurações da API PandaScore
    # =======================================
    PANDA_SCORE_API_TOKEN=SEU_PANDASCORE_API_TOKEN

    # =======================================
    # Configurações Redis Cache
    # =======================================
    REDIS_PORT=PORTA_REDIS
    REDIS_PASSWORD=SENHA_REDIS
    REDIS_USERNAME=USERNAME_REDIS
    ```
    *   **Importante:** Substitua os placeholders pelos seus valores reais.
    *   ⚠️ **Nota:** O Spring Boot **não lê** arquivos `.env` automaticamente. Você precisa carregar essas variáveis no seu ambiente *antes* de executar a aplicação. Veja a próxima seção.

3.  **Carregando Variáveis de Ambiente (do `.env`)**

    Para que o Spring Boot reconheça as variáveis definidas no seu arquivo `.env` localmente, você precisa carregá-las no ambiente de execução. Escolha **uma** das seguintes opções:

    *   **Opção A: Usando Plugin de IDE (Recomendado para Desenvolvimento)**
        *   **IntelliJ IDEA:** Instale o plugin ".env files support" ou "EnvFile". Vá em `Run` -> `Edit Configurations...`, selecione a configuração da sua aplicação Spring Boot, e na seção "EnvFile" ou similar, adicione o seu arquivo `.env`. O IDE carregará as variáveis antes de iniciar a aplicação.
        *   **Outras IDEs:** Procure por plugins ou configurações equivalentes que permitam carregar arquivos `.env` nas configurações de execução/debug.

    *   **Opção B: Usando Linha de Comando (Linux/macOS/Git Bash)**
        *   Abra seu terminal na raiz do projeto.
        *   Execute o seguinte comando para exportar as variáveis do `.env` para a sessão atual do terminal:
          ```bash
          export $(grep -v '^#' .env | xargs)
          ```
        *   **Após** executar o comando acima, na **mesma sessão** do terminal, execute a aplicação:
          ```bash
          ./mvnw clean install
          docker compose up -d
          ./mvnw spring-boot:run
          ```

    *   **Em Produção/Deploy:** Em ambientes de produção (Heroku, Docker, AWS, etc.), você normalmente configura as variáveis de ambiente diretamente na plataforma de hospedagem, sem usar um arquivo `.env`.

## Instalação e Execução 

1.  **Compile o projeto usando Maven:**
    ```bash
    ./mvnw clean install -DskipTests
    ```
2.  **Certifique-se de ter carregado as variáveis de ambiente** usando um dos métodos da seção "Carregando Variáveis de Ambiente (.env)".
3.  **Execute a aplicação Spring Boot:**
    *   Se usou a Opção B (linha de comando), execute na mesma sessão do terminal:
      ```bash
      ./mvnw clean install
      docker compose up -d
      ./mvnw spring-boot:run
      ```
    *   Se usou a Opção A (IDE), apenas execute a aplicação pela configuração do IDE.

4.  Após a inicialização bem-sucedida, seu bot estará online!

## Como Usar o Bot no Telegram

1.  Abra o aplicativo Telegram.
2.  Procure pelo `username` do seu bot (que você configurou em `.env`).
3.  Inicie uma conversa com ele.
4.  Envie um dos comandos disponíveis:
    *   `/proximojogo` - Para ver as próximas partidas.
    *   `/ultimoresultado` - Para ver os últimos resultados.
    *   `/lineup` - Para ver a escalação atual do time.
    *   `/ajuda` - Para ver a lista de comandos novamente.

    O bot responderá com as informações solicitadas, formatadas como nos exemplos das seções anteriores.

## Estrutura do Projeto (Visão Geral) 

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── dev/guilhermeluan/furiafanbot/
│   │   │       ├── FuriaFanBotApplication.java  # Classe principal Spring Boot
│   │   │       ├── bot/                     # Lógica e componentes do Telegram
│   │   │       │   ├── command/             # Implementações dos comandos (/proximojogo, /lineup, etc.)
│   │   │       │   └── FuriaBot.java        # Classe que estende TelegramLongPollingBot
│   │   │       ├── client/                  # Cliente para API externa (PandaScore)
│   │   │       │   ├── dto/                 # Data Transfer Objects (MatchDTO, TeamDTO, PlayerDTO, etc.)
│   │   │       │   └── PandaScoreClient.java # Interface/Implementação do cliente HTTP
│   │   │       ├── config/                  # Configurações do Spring (Beans, WebClient)
│   │   │       ├── formatter/               # Classes utilitárias para formatar mensagens
│   │   │       │   └── TelegramMessageFormatter.java
│   │   │       └── service/                 # Lógica de negócio da aplicação
│   │   │           └── MatchInfoService.java
│   │   └── resources/
│   │       ├── application.properties       # Arquivo de configuração principal
│   │       └── application.properties.example # Exemplo de configuração
│   └── test/                            # Testes unitários e de integração
├── .gitignore                           # Arquivos ignorados pelo Git
├── mvnw                                 # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                             # Maven Wrapper (Windows)
├── pom.xml                              # Arquivo de configuração do Maven
└── README.md                            # Este arquivo
```


## Agradecimentos 

*   À [PandaScore](https://pandascore.co/) pela API robusta de dados de eSports.
*   À equipe por trás da biblioteca [TelegramBots](https://github.com/rubenlagus/TelegramBots).

---
*Desenvolvido com ☕ por Guilherme Luan*
