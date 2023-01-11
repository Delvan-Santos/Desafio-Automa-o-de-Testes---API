package br.com.sicredi.simulacao.teste;

import br.com.sicredi.simulacao.dominio.Dados_Simulacao;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SimulacaoTest {

    @BeforeClass
    public static void  setup(){RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    baseURI ="http://localhost:8080/api/v1";

    }

    @Test
    //Cenário 1: Efetua o cadastro de uma simulação com sucesso

    public void cadastrar_Simulação_com_Sucesso(){

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 2","93996292015","delvan@teste_2.com.br","1200","3","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(201)
                .body("nome", is("Delvan Teste 2"))
                .body("cpf", is("93996292015"));
    }

    @Test
    //Cenário 2: Efetua uma simulação informando o CPF no formato inválido

    public void cadastrar_Simulação_com_CPF_no_Formato_Inválido() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 3","8888-939.962.920-15-9999","delvan@teste_3.com.br","1300","4","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
       when().
                post("/simulacoes").
        then().
                statusCode(400);

    }

    @Test
    //Cenário 3: Efetua uma simulação deixando o nome em branco (sem preenchimento)

    public void cadastrar_Simulação_com_Nome_em_Branco() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("","64495634003","delvan@teste_4.com.br","1400","5","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400);
    }

    @Test
    //Cenário 4: Efetua uma simulação com e-mail no formato inválido

    public void cadastrar_Simulação_com_Email_no_Formato_Inválido() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 5","17677697097","delvan@","1400","6","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
      when().
                post("/simulacoes").
        then().
                statusCode(400);
    }
    @Test
    //Cenário 5: Efetua uma simulação com valor MENOR que R$ 1.000 (Ex: R$ 99 )

    public void cadastrar_Simulação_com_Valor_Menor_que_Mil_Reais() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 7","49851842095","delvan@teste_7.com.br","99","7","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400);
    }

    @Test
    //Cenário 6: Efetua uma simulação com valor MAIOR que R$ 40.000 (Ex: R$ 41.000 )

    public void cadastrar_Simulação_com_Valor_Maior_que_Quarenta_Mil_Reais() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 8","76260195044","delvan@teste_8.com.br","141000","8","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400);
    }

    @Test
    //Cenário 7: Efetua uma simulação com número de parcelas MENOR ou Igual a 1

    public void cadastrar_Simulação_com_Número_de_Parcelas_Menor_ou_Igual_a_1() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 9","57015866005","delvan@teste_9.com.br","2999","1","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400).
                body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));
    }

    @Test
    //Cenário 8: Efetua uma simulação com número de parcelas MAIOR que 48

    public void cadastrar_Simulação_com_Número_de_Parcelas_Maior_que_48() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 10","99796577070","delvan@teste_10.com.br","2100","148","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400);
    }
    @Test
    //Cenário 9: Efetua uma simulação com o campo seguro preenchido no formato inválido (Ex: diferente de true ou false)

    public void cadastrar_Simulação_com_Campo_Seguro_em_Formato_Inválido() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 11","38182366070","delvan@teste_11.com.br","1500","38","NA");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400);
    }
    @Test
    //Cenário 10: Efetua uma simulação para um mesmo CPF

        public void cadastrar_Simulação_um_Mesmo_CPF() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 12","92480843033","delvan@teste_12.com.br","5000","37","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                post("/simulacoes").
        then().
                statusCode(400).
                body("mensagem", is("CPF duplicado"));
    }
    @Test
    //Cenário 11: Efetua alteração do Email, VALOR e do número de PARCELAS de uma simulação

    public void alterar_Email_Valor_e_Parcelas_de_uma_Simulaçao() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 3.1","70090939034","delvan@teste_22.com","3300","45","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                put("/simulacoes/70090939034").
        then().
                statusCode(200).
                body("email", is("delvan@teste_22.com")).
                body("valor", is("3300")).
                body("parcelas", is("45"));
    }
    @Test
    //Cenário 12: Efetua alteração em um CPF que não possui simulação cadastrada

    public void efetuar_Alteração_em_um_CPF_que_não_Possui_Simulação_cadastrada() {

        Dados_Simulacao dados_simulacao = new Dados_Simulacao("Delvan Teste 13","27515974087","delvan@teste_13.com.br","9000","7","true");

        given().
                contentType(ContentType.JSON).
                body(dados_simulacao).
        when().
                put("/simulacoes/27515974087").
        then().
                statusCode(404).
                body("mensagem", is("CPF 27515974087 não encontrado"));
    }
    @Test
    //Cenário 13: Consultar todas Simulações Cadastradas

    public void consultar_Todas_Simulação_Cadastradas() {
        when().
               get("/simulacoes").
        then().
                statusCode(200);
    }
    @Test
    //Cenário 14: Consultar uma Simulações pelo CPF

    public void consultar_uma_Simulação_pelo_CPF() {
        when().
                get("/simulacoes/53809219061").
        then().
                statusCode(200)
                .body("cpf", is("53809219061"));
    }
    @Test
    //Cenário 15: Consultar Simulações de um CPF sem simulações cadastradas

    public void consultar_Simulação_de_CPF_sem_simulações_Cadastradas() {
        when().
                get("/simulacoes/62162609010").
        then().
                statusCode(404)
                .body("mensagem", is("CPF 62162609010 não encontrado"));

    }
    @Test
    //Cenário 16: Remover uma simulação previament cadastrada pelo seu ID

    public void remover_simulações_Cadastradas_pelo_ID() {
        when().
                delete("/simulacoes/62").
        then().
                statusCode(200)
                .body(is("OK"));

    }
    @Test
    //Cenário 17: Remover uma simulação por um ID inexistente

    public void remover_simulações_Inexistente_pelo_ID() {
        when().
                delete("/simulacoes/999").
                then().
                statusCode(404)
                .body("mensagem", is("Simulação não encontrada"));

    }
}
