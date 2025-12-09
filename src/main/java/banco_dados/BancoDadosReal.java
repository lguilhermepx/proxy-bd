package banco_dados;

public class BancoDadosReal implements BancoDados {

    private String nomeBanco;
    public boolean conectado = false;

    public BancoDadosReal(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        conectar();
    }

    @Override
    public void conectar() {
        System.out.println("[RealDB] Conectando ao banco " + nomeBanco + "...");
        this.conectado = true;
    }

    @Override
    public void executarQuery(String query, String usuario) {
        System.out.println("[RealDB] Query executada: " + query);
    }
}