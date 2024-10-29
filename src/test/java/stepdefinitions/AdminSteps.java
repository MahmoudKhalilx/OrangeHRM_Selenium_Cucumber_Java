package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AdminPage;
import pages.LoginPage;
import utilities.DriverManager;

public class AdminSteps {
    private final LoginPage loginPage;
    private final AdminPage adminPage;
    private final String newUsername;
    String Pass ="Test@123";

    public AdminSteps() {
        loginPage = new LoginPage(DriverManager.getDriver());
        adminPage = new AdminPage(DriverManager.getDriver());
        newUsername = "TestUser" + System.currentTimeMillis();
    }

    @Given("user navigates to OrangeHRM login page")
    public void userNavigatesToOrangeHrmLoginPage() {
       // loginPage.navigateToLoginPage();
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password){

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks on login button")
    public void clicksOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user should be logged in successfully")
    public void verifyLoginSuccess() {
        adminPage.assertAdminMenuIsClickable();
    }

    @Then("user clicks on Admin menu")
    public void clickOnAdminMenu() {
        adminPage.clickAdminMenu();
    }

    @When("user clicks on PIM menu to get Employee Name")
    public void clickOnPIMMenu() {
        adminPage.clickPIMMenu();
        adminPage.getNameEmployee();
    }

    @And("gets initial record count")
    public void getInitialRecordCount() {
        adminPage.setInitialRecordCount();
    }

    @And("clicks on Add button")
    public void clickOnAddButton() {
        adminPage.clickAddButton();
    }

    @Then("Verify Add User Is Displayed")
    public void VerifyAddUserIsDisplayed() {
        adminPage.assertNewPageIsDisplayed();
    }
    @Then("fills in the new user details")
    public void fills_in_the_new_user_details() {
        adminPage.fillNewUserDetails(newUsername,Pass);
    }

    @And("clicks on Save button")
    public void clickOnSaveButton() {
        adminPage.clickSaveButton();
    }

    @Then("verify record count increased by one")
    public void verifyRecordCountIncreased() {
        Assert.assertTrue(adminPage.verifyRecordCountIncreased(), "Record count did not increase");
    }

    @When("user searches for the new user")
    public void searchForNewUser() {
        adminPage.searchUser(newUsername);
    }

    @And("deletes the new user")
    public void deleteNewUser() {
        adminPage.deleteUser();
    }

    @Then("verify record count decreased by one")
    public void verifyRecordCountDecreased() throws InterruptedException {
        Assert.assertTrue(adminPage.verifyRecordCountDecreased(), "Record count did not decrease");
    }
}