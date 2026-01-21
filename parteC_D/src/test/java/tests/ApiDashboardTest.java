package tests;

import io.restassured.response.Response;
import models.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.LoginService;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;

public class ApiDashboardTest {

    private LoginService loginService;

    @BeforeEach
    public void setup() {
        loginService = new LoginService();
    }

    @Test
    @DisplayName("Cenário: Validar Integração e Carregamento Assíncrono do Dashboard")
    public void testIntegracaoDashboardComEspera() {
        LoginRequest payload = new LoginRequest("emilys", "emilyspass");
        Response loginRes = loginService.makeLogin(payload);
        String token = loginRes.jsonPath().getString("accessToken");

        await()
            .atMost(5, TimeUnit.SECONDS) // Tempo limite de 5 segundos
            .pollInterval(1, TimeUnit.SECONDS) // Intervalo de tentativa
            .untilAsserted(() -> {
                Response dashboardRes = loginService.consultDashboard(token);

                dashboardRes.then()
                    .statusCode(200)
                    .body("username", equalTo("emilys"))
                    .body("firstName", equalTo("Emily"))
                    .body("email", notNullValue());
                
                System.out.println("Integração Dashboard: OK (Assíncrono concluído)");
            });
    }
}