package pepinazo.todoist;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static junit.framework.Assert.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TodoistStepDef {
    public WebDriver driver;
    public WebDriverWait wait;
    public int numCanicas;

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
    public void iEnterMyCredentials(String usuario, String password)
    {
        driver.findElement(By.cssSelector("[href='/users/showlogin']")).click(); //click login button
        WebElement userNameTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        //asegurarse que el userNameTxt este visible
        assertTrue(userNameTxt.isDisplayed());
        System.out.println("userNameTxt es visible");

        userNameTxt.sendKeys(usuario); //enter username
        driver.findElement(By.id("password")).sendKeys(password); //search password field and enter data

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
        assertEquals("Todoist", title);
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

    @Given("I am in todist website")
    public void iAmInTodistWebsite()
    {
        iNavigateToTodoistSite();
        iEnterMyCredentials("jomarnavarro@gmail.com","Test@1234");
        iSeeTheProjectPage();
    }


    @When("I add a new project form the {string}")
    public void iAddANewProjectFormThe(String manera)
    {
        if (manera.contains("+"))
        {
            //agregar proyecto desde el icono +
            addProjectFromIcon(true);
        }
        else if (manera.contains("New Project"))
        {
            addProjectFromNewProjectLink();
        }
        else if (manera.contains("above"))
        {
            addProjectFromAbove();
        }
        else if (manera.contains("below"))
        {
            addProjectFromBelow();
        }
        else if (manera.contains("task"))
        {
            addProjectFromTask();
        }
    }

    private void addProjectFromIcon(boolean isFavorite)
    {
        //encontrar el elemento de projects css= "button[data-track='navigation|projects_panel']"
        WebElement projectsElement = driver.findElement(By.cssSelector("button[data-track='navigation|projects_panel']"));
        //poner el cursor en el icono de +
        Actions ac = new Actions(driver);
        ac.moveToElement(projectsElement).perform();
        //wait hasta que sea visible el icono + y darle click -- css= "button[data-track='navigation|projects_panel'] + div > button"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-track='navigation|projects_panel'] + div > button"))).click();
        //esperar a que se haga visible el cuadro de dialogo ---- css= "div[role='dialog']form"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='dialog']form"))).isDisplayed();
        //buscar el elemento de project name id="edit_project_modal_field_name"
        driver.findElement(By.id("edit_project_modal_field_name")).sendKeys("Project Test");
        //buscar el boton css= "button[aria-labelledby="edit_project_modal_field_color_label"]"
        driver.findElement(By.cssSelector("button[aria-labelledby='edit_project_modal_field_color_label']")).click();
        //dar click al boton y esperar a que haya muchos elementos en la lista css= "li[id*='dropdown-select']"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id*='dropdown-select']"))).click();
        //buscar elemento de la lista que contenga el color. linkText="color a escoger"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Magenta"))).click();
        //darle click a add to favorites solo si es favorito - name="is_favorite"
        if (isFavorite)
        {
            driver.findElement(By.name("is_favorite")).click();
        }
        //buscar y darle click en add linkText="Add"
        driver.findElement(By.linkText("Add")).click();
    }

    private void addProjectFromNewProjectLink()
    {
    }

    private void addProjectFromAbove()
    {
    }

    private void addProjectFromBelow()
    {
    }

    private void addProjectFromTask()
    {
    }

    @Then("The new project is listed in the end")
    public void theNewProjectIsListedInTheEnd() {
    }

    @Given("I loged in with {word} and {word}")
    public void enterWithCredentials (String userName, String password)
    {
        iNavigateToTodoistSite();
        iEnterUserAndPassword(userName, password);
        iSeeTheProjectPage();
    }


    @Given("I have {int} marbles")
    public void iHaveMarvels(int numMarbles) {
        numCanicas = numMarbles;
    }


    @When("I give away {int}")
    public void iGiveAway(int numMarbles) {
        numCanicas = numCanicas - numMarbles;
    }


    @Then("I have {int} marbles left")
    public void iHaveMarblesLeft(int numMarbles) {
        assertTrue(numCanicas == numMarbles);
    }
}
