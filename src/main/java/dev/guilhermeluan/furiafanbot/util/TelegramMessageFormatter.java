package dev.guilhermeluan.furiafanbot.util;

import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import dev.guilhermeluan.furiafanbot.client.dto.OpponentDTO;
import dev.guilhermeluan.furiafanbot.client.dto.OpponentWrapperDTO;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class TelegramMessageFormatter {
    private static final ZoneId BRT_ZONE = ZoneId.of("America/Sao_Paulo");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd/MM/yyyy - HH:mm 'BRT'", new Locale("pt", "BR"));

    /**
     * Formata uma lista de partidas futuras em uma string para mensagem do Telegram.
     * Converte o horário UTC para Horário de Brasília (BRT).
     *
     * @param matches Lista de MatchDTO representando as próximas partidas.
     * @return String formatada para o Telegram, ou mensagem de "nenhum jogo encontrado".
     */
    public static String formatNextMatches(List<MatchDTO> matches) {
        if (matches == null || matches.isEmpty()) {
            return """
                   Opa! 🐾 Dei uma olhada aqui, mas parece que não tem nenhuma partida da Furia agendada no momento. 😥

                   Fica de olho ou pergunta de novo mais tarde! Quem sabe já temos novidades. 😉

                   Enquanto isso, que tal usar o /ultimoresultado ou /ajuda?

                   #DIADEFURIA #FURIACS""";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Opa! 🐾 Fica ligado(a) nos próximos confrontos da Furia:\n");

        boolean firstMatch = true;
        for (MatchDTO match : matches) {
            if (!firstMatch) {
                sb.append("\n---\n\n");
            } else {
                sb.append("\n");
                firstMatch = false;
            }

            String formattedDateTime = "Data/Hora indisponível";
            if (match.getBeginAt() != null) {
                formattedDateTime = match.getBeginAt()
                        .atZoneSameInstant(BRT_ZONE)
                        .format(DATE_TIME_FORMATTER);
            }

            sb.append("⚫️ ").append(match.getTeam1Name()).append(" vs ").append(match.getTeam2Name()).append(" ⚪️\n");
            sb.append("🏆 Campeonato: ").append(match.getLeagueName()).append(" (").append(match.getTournamentStageName()).append(")\n");

            sb.append("🗓️ Horário: ").append(formattedDateTime).append("\n");
            sb.append("🗺️ Formato: ").append(match.getMatchType()).append("\n");
            sb.append("📺 Streams:\n");

            List<String> streams = match.getStreamUrls();
            if (streams.isEmpty()) {
                sb.append("   - Nenhuma transmissão encontrada.\n");
            } else {
                for (String streamUrl : streams) {
                    sb.append("   - ").append(streamUrl).append("\n");
                }
            }
        }

        sb.append("\n---\n");
        sb.append("\nPrepara a torcida e bora pra cima! 😉\n");
        sb.append("#DIADEFURIA #FURIACS");

        return sb.toString();
    }

    /**
     * Formata uma lista de partidas passadas em uma string para mensagem do Telegram.
     * Exibe informações básicas, o placar e o vencedor da partida.
     * Converte o horário UTC para Horário de Brasília (BRT).
     *
     * @param matches Lista de MatchDTO representando as últimas partidas.
     * @return String formatada para o Telegram, ou mensagem de "nenhum resultado encontrado".
     */
    public static String formatLastMatches(List<MatchDTO> matches) {
        if (matches == null || matches.isEmpty()) {
            return """
                   Opa! 🐾 Busquei aqui, mas não encontrei resultados recentes de partidas da Furia. 🤔

                   Pode ser que a informação ainda não esteja disponível ou não houve jogos recentemente.

                   Tente usar o /proximojogo ou /ajuda!

                   #DIADEFURIA #FURIACS""";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Opa! 🐾 Confere os últimos resultados da Furia:\n");

        boolean firstMatch = true;
        for (MatchDTO match : matches) {
            if (!firstMatch) {
                sb.append("\n---\n\n");
            } else {
                sb.append("\n");
                firstMatch = false;
            }

            String formattedDateTime = "Data indisponível";
            OffsetDateTime dateTimeToShow = match.getEndAt() != null ? match.getEndAt() : match.getBeginAt();
            if (dateTimeToShow != null) {
                formattedDateTime = dateTimeToShow
                        .atZoneSameInstant(BRT_ZONE)
                        .format(DATE_TIME_FORMATTER);
            }

            String resultLine = formattedScore(match);

            sb.append("⚫️ ").append(match.getTeam1Name()).append(" vs ").append(match.getTeam2Name()).append(" ⚪️\n");
            sb.append("🏆 Campeonato: ").append(match.getLeagueName()).append(" (").append(match.getTournamentStageName()).append(")\n");
            sb.append("🗓️ Data: ").append(formattedDateTime).append("\n");
            sb.append(resultLine).append("\n"); // Adiciona a linha com placar e/ou vencedor

        }

        sb.append("\n---\n");
        sb.append("\nContinue acompanhando a Furia! 🔥\n");
        sb.append("#DIADEFURIA #FURIACS");

        return sb.toString();
    }

    private static String formattedScore(MatchDTO match) {
        String scoreString = match.getFormattedScore();
        Optional<String> winnerNameOpt = match.getWinnerName();

        String resultLine;
        if (!"Placar indisponível".equals(scoreString)) {
            resultLine = "📊 " + scoreString;
            if (winnerNameOpt.isPresent()) {
                resultLine += " (🏆 " + winnerNameOpt.get() + ")";
            }
        } else if (winnerNameOpt.isPresent()) {
            resultLine = "🏆 Vencedor: " + winnerNameOpt.get() + " (Placar indisponível)";
        } else if ("finished".equalsIgnoreCase(match.getStatus())) {
            resultLine = "📊 Resultado: Finalizado (Detalhes indisponíveis)";
        } else {
            resultLine = "📊 Status: " + (match.getStatus() != null ? match.getStatus() : "Indisponível");
        }
        return resultLine;
    }
}
