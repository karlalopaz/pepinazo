package pepinazo.todoist;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static junit.framework.Assert.*;
import java.util.concurrent.TimeUnit;

public class TodoistStepDef {
    public WebDriver driver;
    public WebDriverWait wait;



    @Before
    public void setUpTest()
    {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().setSize(new Dimension(1366, 780));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDownTest()
    {
        driver.quit();
    }


    @Given("I navigate to todoist site")
    public void iNavigateToTodoistSite()
    {
        driver.get("https://todoist.com");
        //asegurarnos que el titulo sea igual a "Todoist: The to do list to organize work & life"
        String title = driver.getTitle();
        assertEquals("Todoist: The to do list to organize work & life", title);
        System.out.println("Titulo es correcto");
    }

    @When("I enter my credentials")
    public void iEnterMyCredentials()
    {
        driver.findElement(By.cssSelector("[href=´/users/showlogin´]")).click(); //click login button
        WebElement userNameTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        //asegurarse que el userNameTxt este visible
        assertTrue(userNameTxt.isDisplayed());
        System.out.println("userNameTxt es visible");

        userNameTxt.sendKeys("jomarnavarro@gmail.com"); //enter username
        driver.findElement(By.id("password")).sendKeys("Test@1234"); //search password field and enter data

        WebElement loginButton = driver.findElement(By.cssSelector(".submit_btn"));
        ///asegurarse que el boton este habilitado
        assertTrue(loginButton.isEnabled());
        System.out.println("loginButton esta habilitado");

        loginButton.click();
    }

    @Then("I see the project page")
    public void iSeeTheProjectPage()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Inbox']")));
        //asegurar que el titulo de la pagina sea "Today: Todoist"
        String title = driver.getTitle();
        assertEquals("Today: Todoist", title);
        System.out.println("Titulo after login es correcto");
    }

    @When("I enter {word} and {word}")
    public void iEnterUserAndPassword(String usuario, String password) {
        //click login button
        driver.findElement(By.cssSelector("[href='/users/showlogin']")).click();

        WebElement userNameTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        //asegurarse que el userNameTxt este visible

        //enter username
        userNameTxt.sendKeys(usuario);
        //enter password
        driver.findElement(By.id("password")).sendKeys(password);

        //click login button
        WebElement loginButton = driver.findElement(By.cssSelector(".submit_btn"));
        ///asegurarse que el boton este habilitado
        //click al boon
        loginButton.click();
    }

}
