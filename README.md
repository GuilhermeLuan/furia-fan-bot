# Furia Fan Bot 🐾

[![Language](https://img.shields.io/github/languages/top/GuilhermeLuan/furia-fan-bot)](https://github.com/GuilhermeLuan/furia-fan-bot)

Um bot para Telegram desenvolvido em Java com Spring Boot que informa sobre as próximas partidas e últimos resultados do time de CS (Counter-Strike) da Furia Esports. Os dados são obtidos em tempo real através da API da [PandaScore](https://pandascore.co/).

## Funcionalidades ✨

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

*   **`/ajuda`**: Apresenta uma mensagem com a lista de comandos disponíveis e uma breve descrição de cada um.

**Características Adicionais:**

*   **Fonte de Dados Confiável:** Utiliza a API REST v2 da PandaScore.
*   **Fuso Horário Localizado:** Datas e horas são sempre apresentadas no fuso horário de Brasília (America/Sao_Paulo).
*   **Respostas Formatadas:** As mensagens enviadas ao usuário são formatadas com emojis e estilo para uma melhor experiência no Telegram.

## Tecnologias Utilizadas 🛠️

*   **Linguagem Principal:** Java 21
*   **Framework:** Spring Boot 3+
*   **Interação com Telegram:** Biblioteca [TelegramBots for Spring Boot](https://github.com/rubenlagus/TelegramBots) (`telegrambots-spring-boot-starter`)
*   **Cliente HTTP:** OpenFeign para consumir a API da PandaScore.
*   **Build e Dependências:** Maven
*   **API Externa:** PandaScore API v2

## Pré-requisitos 📋

Para executar este projeto localmente, você precisará ter instalado:

*   JDK 21 ou superior.
*   Maven 3.6+ .
*   Uma conta no Telegram.
*   Um **Token de Bot do Telegram**: Crie um bot e obtenha o token falando com o [@BotFather](https://t.me/BotFather) no Telegram.
*   Um **Token de API da PandaScore**: Registre-se e obtenha seu token de acesso no [site oficial da PandaScore](https://pandascore.co/).

## Configuração ⚙️

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/GuilhermeLuan/furia-fan-bot.git
    cd furia-fan-bot
    ```
2.  **Configure as credenciais:**
    *   Copie o arquivo `.envTemplate` para `.env`:
    *   Adicione suas credenciais no arquivo `.env`:

    ```properties
    TELEGRAM_BOT_USERNAME=
    TELEGRAM_BOT_TOKEN=
    TELEGRAM_CREATOR_ID=
    PANDA_SCORE_API_TOKEN=
    ```

## Instalação e Execução 🚀

1.  **Compile o projeto usando Maven:**
    ```bash
    mvn clean install -DskipTests
    ```
    *(O `-DskipTests` é opcional, para pular a execução de testes durante o build)*

2.  **Execute a aplicação Spring Boot:**
    ```bash
    mvn spring-boot:run
    ```

3.  Após a inicialização bem-sucedida, seu bot estará online e pronto para receber comandos no Telegram!

## Como Usar o Bot no Telegram 📱

1.  Abra o aplicativo Telegram.
2.  Procure pelo `username` do seu bot.
3.  Inicie uma conversa com ele.
4.  Envie um dos comandos disponíveis:
    *   `/proximojogo`
    *   `/ultimoresultado`
    *   `/ajuda`

    O bot responderá com as informações solicitadas, formatadas como nos exemplos das seções anteriores.

## Estrutura do Projeto (Visão Geral) 📁

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── dev/guilhermeluan/furiafanbot/
│   │   │       ├── FuriaFanBotApplication.java  # Classe principal Spring Boot
│   │   │       ├── bot/                     # Lógica e componentes do Telegram
│   │   │       │   ├── command/             # Implementações dos comandos
│   │   │       │   └── FuriaBot.java        # Classe que estende TelegramLongPollingBot
│   │   │       ├── client/                  # Cliente para API externa (PandaScore)
│   │   │       │   ├── dto/                 # Data Transfer Objects (MatchDTO, etc.)
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

## Agradecimentos 🙏

*   À [PandaScore](https://pandascore.co/) pela API robusta de dados de eSports.
*   À equipe por trás da biblioteca [TelegramBots](https://github.com/rubenlagus/TelegramBots).
*   A toda a torcida da Furia! **#DIADEFURIA** ⚫️⚪️

---
*Desenvolvido com ☕ por Guilherme Luan*
```