package br.com.sicredi.simulacao.teste;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.events.Event;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class RestricaoTest {

    @BeforeClass
    public static void  setup(){RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    baseURI ="http://localhost:8080/api/v1";
    }
    @Test
    //Cenário 1: Efetuar a consulta de um CPF com restrição
    //Dado: Que o usuário esteja logado no Sistema Sicredi
    //Quando: Efetuar a consulta de um CPF com restrição
    //Então: O Http status será retornado 200 com a mensagem "O CPF 999.999.999-99 possui problemas"

    public void consultar_CPF_ComRestrição() {

            when().
                    get("/restricoes/58063164083").
            then().
                    statusCode(200).
                    body("mensagem", is("O CPF 58063164083 tem problema"));
    }

    @Test
    //Cenário 2: Efetuar a consulta de um CPF sem restrição
    //Dado: Que o usuário esteja logado no Sistema Sicredi
    //Quando: Efetuar a consulta de um CPF sem restrição
    //Então: O Http status será retornado 204

    public void consultar_CPF_SemRestrição(){

            when().
                    get("/restricoes/48587098098").
            then().
                    statusCode(204);

    }
}
