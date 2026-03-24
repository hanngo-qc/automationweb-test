package automationweb.automationwebtest;

import automationweb.automationwebtest.common.TestConstants;
import automationweb.automationwebtest.pages.LoginPage;
import automationweb.automationwebtest.pages.WelcomePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginPageTest extends BaseTest {
    public static final Logger logger = Logger.getLogger(LoginPageTest.class);

    private HashMap testDataSuccess = new HashMap();
    private HashMap testDataFailed = new HashMap();
    private String validEmail = "";
    private String invalidEmail = "";
    private String validPassword = "";
    private String invalidPassword = "";

    @BeforeTest(groups = {"Major","Medium", "Minor"})
    public void setUpTestData() {
        testDataSuccess = (HashMap) data.get("testDataSuccess");
        testDataFailed = (HashMap) data.get("testDataFailed");
        validEmail = testDataSuccess.get("email").toString();
        invalidEmail = testDataFailed.get("email").toString();
        validPassword = testDataSuccess.get("password").toString();
        invalidPassword = testDataFailed.get("password").toString();
    }
    @Test(groups = {"Major"})
    public void getPageTitle(){
        LoginPage loginPage= new LoginPage(driver);

        logger.info("Access Home Page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
    }

    @Test(groups = {"Major"})
    public void loginEmailSuccessfully(){
        LoginPage loginPage = new LoginPage(driver);
        WelcomePage welcomePage = new WelcomePage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccount(validEmail, validPassword);
        Assert.assertEquals(welcomePage.getPageTitle(), TestConstants.WELCOME_PAGE_TITLE);
        Assert.assertEquals(welcomePage.getLoginSuccessfullyNotify(), TestConstants.LOGIN_SUCCESS_NOTIFY);
        logger.info("User can login successfully");
    }

    @Test(groups = {"Medium"})
    public void loginFailedByNotExistedEmail() {
        LoginPage loginPage = new LoginPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccount(invalidEmail, validPassword);
        Assert.assertEquals(loginPage.getErrorEmailPasswordMessage(), TestConstants.INCORRECT_PASSWORD_MESSAGE);
        logger.info("User cannot login when entering a wrong email address");
    }

    @Test(groups = {"Medium"})
    public void loginFailedByWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccount(validEmail, invalidPassword);
        Assert.assertEquals(loginPage.getErrorEmailPasswordMessage(), TestConstants.INCORRECT_PASSWORD_MESSAGE);
        logger.info("User cannot login when entering a wrong password");
    }

    @Test(groups = {"Minor"})
    public void loginFailedByWrongEmailAndPassword() {
        LoginPage loginPage = new LoginPage(driver);

        logger.info("Access Login page");
        Assert.assertEquals(loginPage.getPageTitle(), TestConstants.LOGIN_PAGE_TITLE);
        loginPage.loginAccount(invalidEmail, invalidPassword);
        Assert.assertEquals(loginPage.getErrorEmailPasswordMessage(), TestConstants.INCORRECT_PASSWORD_MESSAGE);
        logger.info("User cannot login when entering a wrong email address or password");
    }

}
