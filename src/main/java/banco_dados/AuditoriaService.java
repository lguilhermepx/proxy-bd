package banco_dados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditoriaService {

    // simula a escrita em um arquivo de log seguro
    public void registrarTentativa(String usuario, String query, boolean foiBloqueado) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String status = foiBloqueado ? "[BLOQUEADO]" : "[SUCESSO]";

        // formata a mensagem de log padronizada
        String logMessage = String.format("%s | %s | User: %s | Query: %s",
                timestamp, status, usuario, query);

        System.out.println(">> LOG AUDITORIA: " + logMessage);
    }
}