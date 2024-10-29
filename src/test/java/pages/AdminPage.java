package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AdminPage  extends BasePage{


    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']//span")
    private WebElement PIMMenu;

    @FindBy(xpath = "(//div[@role='row'])[2]//div[@role='cell'][3]")
    private WebElement getEmployeeName;


    @FindBy(css = "a[href='/web/index.php/admin/viewAdminModule']")
    private WebElement adminMenu;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addButton;

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
    private WebElement VerifyAddUserPage;


    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[2]")
    private WebElement StatusDropdown;



    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    private WebElement employeeNameInput;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    private WebElement usernameInput;

    @FindBy(xpath = "(//button[normalize-space()='Search'])[1]")
    private WebElement ClickSearchButton;


    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement passwordInput;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement confirmPasswordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(css = ".orangehrm-horizontal-padding span")
    private WebElement recordsCount;

    @FindBy(css = "input[placeholder='Search']")
    private WebElement searchInput;

    @FindBy(xpath ="(//i[@class='oxd-icon bi-trash'])[1]")
    private WebElement deleteButton;

    @FindBy(xpath = "(//button[normalize-space()='Yes, Delete'])[1]")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "(//button[normalize-space()='Reset'])[1]")
    private WebElement ResetFilterButton;

//    @FindBy(xpath = dynamicXpathEmployeeNameText)
//    private WebElement EmployeeNameText
//    ;


    private int initialRecordCount;

     String employee;
    String dynamicXpathEmployeeNameText;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void assertAdminMenuIsClickable() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(adminMenu));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.isEnabled());
    }

    public void clickAdminMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }
    public void clickPIMMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(PIMMenu)).click();
    }

    public void getNameEmployee(){
        wait.until(ExpectedConditions.visibilityOf(getEmployeeName));
        BasePage.scrolling(driver, getEmployeeName);
        employee=getEmployeeName.getText();
        System.out.println(employee);
        ;
    }


    public int getRecordsCount() {
        wait.until(ExpectedConditions.visibilityOf(recordsCount));
        String countText = recordsCount.getText();
        System.out.println(countText);
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));

    }


    public void setInitialRecordCount() {
        this.initialRecordCount = getRecordsCount();

    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void assertNewPageIsDisplayed() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(VerifyAddUserPage));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertEquals(VerifyAddUserPage.getText(),"Add User");
    }

    public void fillNewUserDetails(String username,String Pass){
//        if (employee == null || employee.isEmpty()) {
//            getNameEmployee();
//        }
        dynamicXpathEmployeeNameText = "//span[contains(text(),'" + employee + "')]";


        new BasePage(driver)
                .ClickAction(StatusDropdown);
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//span[contains(text(),'Enabled')]"))).click();

        new BasePage(driver)
                .SentKeyAction(employeeNameInput,employee)
                .ClickAction(driver.findElement(By.xpath(dynamicXpathEmployeeNameText)))
                .SentKeyAction(usernameInput,username)
                .SentKeyAction(passwordInput,Pass)
                .SentKeyAction(confirmPasswordInput,Pass)
        ;


        new BasePage(driver)
                .ClickAction(userRoleDropdown);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Admin')]"))).click();



    }

    public void clickSaveButton() {
        new BasePage(driver).ClickAction(saveButton);
    }

    public boolean verifyRecordCountIncreased() {
        int currentCount = getRecordsCount();
        return currentCount == initialRecordCount + 1;
    }

    public void searchUser(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(ClickSearchButton)).click();

    }

    public void deleteUser() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ResetFilterButton)).click();


    }

    public boolean verifyRecordCountDecreased() throws InterruptedException {
        Thread.sleep(3000);
        int currentCount = getRecordsCount();
        return currentCount == initialRecordCount;
    }}
