package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Registration_Fields {
    static WebDriver driver;
    public static void waituntilElementisclickble(By by, int time) { //reusable method to wait until element is clickble
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }//reusable method

    public static void waituntilElementToBeVisible(By by, int time) {//reusable method to wait until element is visible
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void clickonElement(By by) { driver.findElement(by).click(); }//reusable method from element is clickble

    public static String getText1(By by) {  //reusable method to get text from element
        return driver.findElement(by).getText();
    }

    public static void typeText(By by, String text) {//reusable method to type text in box
        driver.findElement(by).clear(); //clear existing text
        driver.findElement(by).sendKeys(text);
    }

    public static void selectfromdropdownbyvisibletext(By by, String text) {//reusable method select visible text
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public static void selectfromdropdownbyindex(By by, int n) { //reusable method select index
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(n);
    }

    public static void selefromdromdropdownbyvalue(By by, String value) { //reusable method select value
        Select select = new Select((driver.findElement(by)));
        select.selectByValue(value);
    }
    public static void JavaScriptclickonElement(By by){ //reusable method for click on element in case of other method not work
        WebElement element=driver.findElement(by);
        JavascriptExecutor javascript=(JavascriptExecutor)driver;
        javascript.executeScript("arguments[0].click();", element);

    }

    public static long timestamp() {//reusable method to get timestamp
        return (System.currentTimeMillis());

    }

    @AfterMethod
    public static void closeBrowser() {
        driver.close();
    } //reusable method to close browser

    @BeforeMethod
    public static void setUpBrowser() {
        //setting up chromedriver path
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chromedriver.exe");
        //creating chromdriver object to open google chrome browser
        driver = new ChromeDriver();
        //maximising screen
        driver.manage().window().maximize();
        //applying implicity wait of 30 sec to the driver instance
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //open url
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void VerifyUserShouldRegisterSuccessfully() {
        //waituntilElementisclickble(By.xpath("//h1[text()=\"Register\"]"), 20);
        //click on register
        clickonElement(By.linkText("Register"));
        waituntilElementisclickble(By.xpath("//input[@id=\"register-button\"]"), 20);
        //click on female
        clickonElement(By.xpath("//input[@id=\"gender-male\"]"));
        waituntilElementisclickble(By.xpath("//input[@id=\"FirstName\"]"), 80);
        //type firstname
        typeText(By.xpath("//input[@id=\"FirstName\"]"), "Anjli");
        //type lastname
        typeText(By.xpath("//input[@id=\"LastName\"]"), "Patel");
        //click on Date of birth
        clickonElement(By.xpath("//label[text()=\"Date of birth:\"]"));
        //select Dateofbirthday
        selefromdromdropdownbyvalue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "8");
        //select Dateofbirthmonth
        selectfromdropdownbyvisibletext(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "January");
        //select Dateofyear
        selefromdromdropdownbyvalue(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "2010");
        //type email id
        waituntilElementisclickble(By.xpath("//input[@id=\"Email\"]"), 30);
        String email = "text1+" + timestamp() + "@gmail.com";
        typeText(By.xpath("//input[@id=\"Email\"]"), email);
        //System.out.println("test+" + timestamp() + "gmail.com");
        //type companyname
        typeText(By.xpath("//input[@id=\"Company\"]"), "ABCLtd");
        //click on  newseller
        clickonElement(By.xpath("//input[@id=\"Newsletter\"]"));
        //type password
        typeText(By.xpath("//input[@id=\"Password\"]"), "anjali2435");
        //type confirmpassword
        typeText(By.xpath("//input[@id=\"ConfirmPassword\"]"), "anjali2435");
        //click on registerbutton
        clickonElement(By.xpath("//input[@id=\"register-button\"]"));
        //write expected result
        String expectedregistrationmessage = "Your registration completed";
        //using wait element clickble method
        waituntilElementisclickble(By.cssSelector("div.buttons > input[type='submit']"), 20);
        // path of expected result
        String actualmessage = getText1(By.xpath("//div[text()=\"Your registration completed\"]"));
        //compare expected with actual
        Assert.assertEquals(actualmessage, expectedregistrationmessage);

    }

    @Test
    public void registerUserShouldBeAbleToReferAProductToaFriendSuccesfully() {
        //click on register
        clickonElement(By.linkText("Register"));

        //using wait element clickble method
        waituntilElementisclickble(By.xpath("//input[@id=\"register-button\"]"), 20);

        //click on male
        clickonElement(By.xpath("//input[@id=\"gender-male\"]"));

        //using wait element clickble method
        waituntilElementisclickble(By.xpath("//input[@id=\"FirstName\"]"), 80);

        //type firstname
        typeText(By.xpath("//input[@id=\"FirstName\"]"), "Anjli");

        //type lastname
        typeText(By.xpath("//input[@id=\"LastName\"]"), "Patel");

        //click on Date of birth
        clickonElement(By.xpath("//label[text()=\"Date of birth:\"]"));

        //select Dateofbirthday
        selefromdromdropdownbyvalue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "8");

        //select Dateofbirthmonth
        selectfromdropdownbyvisibletext(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "January");

        //select Dateofyear
        selefromdromdropdownbyvalue(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "2010");

        //using wait element clickble method
        waituntilElementisclickble(By.xpath("//input[@id=\"Email\"]"), 30);

        //type email id
        String email = "text1+" + timestamp() + "@gmail.com";
        typeText(By.xpath("//input[@id=\"Email\"]"), email);

        //type companyname
        typeText(By.xpath("//input[@id=\"Company\"]"), "ABCLtd");

        //click on  newseller
        clickonElement(By.xpath("//input[@id=\"Newsletter\"]"));

        //type password
        typeText(By.xpath("//input[@id=\"Password\"]"), "anjali2435");

        //type confirmpassword
        typeText(By.xpath("//input[@id=\"ConfirmPassword\"]"), "anjali2435");

        //click on registerbutton
        clickonElement(By.xpath("//input[@id=\"register-button\"]"));

        //click on computer and using javascript method
      JavaScriptclickonElement(By.xpath("//ul[@class=\"top-menu notmobile\"]/li[1]/a[1]"));
      //using wait element clickble
        waituntilElementisclickble(By.xpath("//a[text()=\" Desktops \"]"),40);
        //click on desktop
        clickonElement(By.xpath("//a[text()=\" Desktops \"]"));
        //click on digital strom pc
        waituntilElementisclickble(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"), 30);
        //clickonElement(By.xpath("//div[@class=\"item-grid\"]/div[2]/div[1]/div[2]/h2/a[1]"));
        clickonElement(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"));
        //click on email a friend button
        clickonElement(By.xpath("//input[@value=\"Email a friend\"]"));
        //using wait element clickble
        waituntilElementisclickble(By.xpath("//input[@name=\"send-email\"]"), 20);
        //type friend emaiil
        typeText(By.xpath("//input[@placeholder=\"Enter friend's email.\"]"), "Text+" + timestamp() + "@gmail.com");
        //type your email address
        typeText(By.xpath("//input[@placeholder=\"Enter your email address.\"]"), "Text" + timestamp() + "@gmail.com");
        //type personal message
        typeText(By.xpath("//textarea[@name=\"PersonalMessage\"]"), "please refer products and review it");
        //click on send email
        clickonElement(By.xpath("//input[@name=\"send-email\"]"));
        //write expected result
        String expectedSentEmailSuccessMessage = "Your message has been sent.";
        //path of expected result
        String actualSentEmailSuccessMessage = getText1(By.xpath("//div[@class=\"result\"]"));
        //compare expected with actual
        Assert.assertEquals(actualSentEmailSuccessMessage, expectedSentEmailSuccessMessage, "expected match with actual");

    }

    @Test
    public void UserShouldBeAbleToAddProductToBasketSuccesfully() {
            //click on electronics category
            clickonElement(By.xpath("//ul[@class=\"top-menu notmobile\"]/li[2]/a[1]"));
            //click on cellphones
            JavaScriptclickonElement(By.xpath("//h2[@class=\"title\"]/a[contains(text(),'Cell phones')]"));
            //Click on HTC one Add to cart button
            JavaScriptclickonElement (By.xpath(("//div[@class=\"item-grid\"]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]")));
            //click  on close
            JavaScriptclickonElement(By.cssSelector("span.close"));


            //Click on HTC one mini Add to cart button
            JavaScriptclickonElement(By.xpath("//div[@class=\"item-grid\"]/div[2]/div[1]/div[2]/div[3]/div[2]/input[1]"));
            //Click on shopping cart button
            JavaScriptclickonElement(By.xpath("//span[@class='cart-label']"));
            //clickonElement(By.xpath("//span[text()=\"Shopping cart\"]"));

            //write expected result
            String expectedProductAddtocart = "HTC One M8 Android L 5.0 Lollipop";
            String expectedProductAddtocart1 = "HTC One Mini Blue";
            //wait until shopping cart product visible
            waituntilElementToBeVisible((By.xpath("//table/tbody/tr[1]/td[@class='product'][1]")),20);
            waituntilElementToBeVisible((By.xpath("//table/tbody/tr[2]/td[@class='product'][1]")),30);

            //path of product in shopping cart
            String actualProductAddtoCart = getText1(By.xpath("//table/tbody/tr[1]/td[@class='product'][1]"));
            String actualProductAddtoCart1=getText1(By.xpath("//table/tbody/tr[2]/td[@class='product'][1]"));

            //compare expected result with actual result
            Assert.assertEquals(actualProductAddtoCart, expectedProductAddtocart);
            Assert.assertEquals(actualProductAddtoCart1, expectedProductAddtocart1);
        }
    }






