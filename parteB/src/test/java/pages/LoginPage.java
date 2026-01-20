package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By fieldEmail = By.id("user-name");
    private By fieldPassword = By.id("password");
    private By enterBtn = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void makeLogin(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldEmail)).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(enterBtn).click();
    }

    public String errorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}