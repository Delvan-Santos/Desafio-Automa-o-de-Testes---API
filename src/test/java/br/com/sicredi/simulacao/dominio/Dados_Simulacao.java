package br.com.sicredi.simulacao.dominio;

public class Dados_Simulacao {
    private String nome;
    private String cpf;
    private String email;
    private String valor;
    private String parcelas;
    private String seguro;

    public Dados_Simulacao() {}
    public Dados_Simulacao(String nome, String cpf, String email, String valor, String parcelas, String seguro) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcelas;
        this.seguro = seguro;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getValor() {
        return valor;
    }

    public String getParcelas() {
        return parcelas;
    }

    public String getSeguro() {
        return seguro;
    }
}
