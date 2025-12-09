package banco_dados;

public class BancoDadosProxy implements BancoDados {

    private BancoDadosReal bancoReal;
    private String nomeBanco;

    public BancoDadosProxy(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    @Override
    public void conectar() {
        // proxy finge que conectou, mas não cria o objeto pesado ainda
        System.out.println("[Proxy] Conexão virtual iniciada.");
    }

    @Override
    public void executarQuery(String query, String usuario) throws Exception {
        // 1. SEGURANÇA: Apenas Admins podem fazer DELETE
        if (query.toUpperCase().contains("DELETE") && !usuario.equalsIgnoreCase("admin")) {
            throw new Exception("Acesso Negado: Usuário sem permissão para DELETAR.");
        }

        // 2. cria o objeto real apenas no momento do uso
        if (this.bancoReal == null) {
            this.bancoReal = new BancoDadosReal(nomeBanco);
        }

        // 3. DELEGAÇÃO
        this.bancoReal.executarQuery(query, usuario);
    }

    // aux pra testes
    BancoDadosReal getBancoReal() {
        return this.bancoReal;
    }
}