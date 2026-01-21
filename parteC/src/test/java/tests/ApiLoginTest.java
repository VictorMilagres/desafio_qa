package tests;

import io.restassured.response.Response;
import models.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.LoginService;
import static org.hamcrest.Matchers.*;

public class ApiLoginTest {
    private LoginService loginService;

    @BeforeEach
    public void setup() {
        loginService = new LoginService();
    }

    @Test
    @DisplayName("Cenário 1: Login com Sucesso - 200")
    public void test200() {
        LoginRequest payload = new LoginRequest("emilys", "emilyspass");
        
        Response res = loginService.makeLogin(payload);
        
        res.then()
            .statusCode(200)
            .body("accessToken", notNullValue())
            .body("firstName", equalTo("Emily"));
    }

    @Test
    @DisplayName("Cenário 2: Credenciais Inválidas - 401")
    public void test401() {
        loginService.statusTest(401).then().statusCode(401);
    }

    @Test
    @DisplayName("Cenário 3: Acesso Negado - 403")
    public void test403() {
        loginService.statusTest(403).then().statusCode(403);
    }

    @Test
    @DisplayName("Cenário 4: Usuário Bloqueado - 423")
    public void test423() {
        loginService.statusTest(423).then().statusCode(423);
    }
}