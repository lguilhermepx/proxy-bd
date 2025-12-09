package banco_dados;

public interface BancoDados {
    void conectar();
    void executarQuery(String query, String usuario) throws Exception;
}