package dev.guilhermeluan.furiafanbot.util;

import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
                        .atZoneSameInstant(BRT_ZONE) // Converte para o fuso de SP
                        .format(DATE_TIME_FORMATTER); // Formata a data/hora
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

}
