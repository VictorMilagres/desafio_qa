package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;
import utils.ConfigLoader;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Cenário 1: Login válido com perfil USER")
    public void testValidLogin() {
        loginPage.makeLogin(
            ConfigLoader.getProperty("user.valid"),
            ConfigLoader.getProperty("user.password")
        );
        assertTrue(driver.getCurrentUrl().contains("inventory.html"), "O login deveria ter redirecionado para a vitrine.");
    }

    @Test
    @DisplayName("Cenário 2: Login de Perfil sem acesso (VISITOR)")
    public void testInvalidLogin() {
        loginPage.makeLogin(
            ConfigLoader.getProperty("user.visitor"),
            ConfigLoader.getProperty("user.password")
        );
        String erro = loginPage.errorText();
        assertTrue(erro.contains("Username and password do not match"), "Deveria exibir erro de credenciais.");
    }

    @Test
    @DisplayName("Cenário 3: Bloqueio de Usuário")
    public void testBlockedUser() {
        loginPage.makeLogin(
            ConfigLoader.getProperty("user.blocked"),
            ConfigLoader.getProperty("user.password")
        );
        String erro = loginPage.errorText();
        assertTrue(erro.contains("Sorry, this user has been locked out"), "Deveria exibir mensagem de conta bloqueada.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}