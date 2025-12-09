package banco_dados;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Unidade: Auditoria Service")
class AuditoriaServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    // Antes de cada teste, redirecionamos o System.out para nossa variável
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Depois de cada teste, devolvemos o System.out ao normal
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Deve formatar log de SUCESSO corretamente")
    void deveLogarSucesso() {
        AuditoriaService service = new AuditoriaService();

        service.registrarTentativa("admin", "SELECT *", false);

        // Captura o que foi impresso
        String logGerado = outputStreamCaptor.toString();

        assertTrue(logGerado.contains("[SUCESSO]"));
        assertTrue(logGerado.contains("User: admin"));
        assertTrue(logGerado.contains("Query: SELECT *"));
    }

    @Test
    @DisplayName("Deve formatar log de BLOQUEIO corretamente")
    void deveLogarBloqueio() {
        AuditoriaService service = new AuditoriaService();

        service.registrarTentativa("hacker", "DELETE ALL", true);

        String logGerado = outputStreamCaptor.toString();

        assertTrue(logGerado.contains("[BLOQUEADO]"));
        assertTrue(logGerado.contains("User: hacker"));
    }
}