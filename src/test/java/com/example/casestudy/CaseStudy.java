package com.example.casestudy;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.List;

public class CaseStudy {

    @Test
    public void FlightPage(){

        //Open Browser
        System.setProperty("webdriver.chrome.driver", "/Users/busebalyemez/Documents/selenium libraries/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        sleep();

        //Open Test Page
        String url = "https://www.enuygun.com/";
        driver.get(url);
        System.out.println("Page is Opened");

        //RoundTrip Checkbox
        WebElement RoundTrip = driver.findElement(By.xpath("//div[text()='Gidiş-dönüş']"));
        RoundTrip.click();

        //Select Origin Location and Destination Location
        List<WebElement> LocationBoxes = driver.findElements(By.xpath("//label[@class='sc-dZoequ elXgMz']"));
        LocationBoxes.get(0).click();
        sleep();
        LocationBoxes.get(0).sendKeys("antalya");
        sleep();
        //LocationBoxes.get(0).sendKeys(Keys.ARROW_DOWN);
        LocationBoxes.get(0).sendKeys(Keys.ENTER);
        sleep();
        sleep();

        LocationBoxes.get(1).click();
        sleep();
        LocationBoxes.get(1).sendKeys("istanbul");
        sleep();
        LocationBoxes.get(1).sendKeys(Keys.ARROW_DOWN);
        LocationBoxes.get(1).sendKeys(Keys.ENTER);
        sleep();

        //Departure Date Select
        WebElement DepartureDate = driver.findElement(By.xpath("//div[text()='Gidiş Tarihi']"));
        DepartureDate.click();
        sleep();

        //Click the Forward Arrow Button
        WebElement ForwardButton = driver.findElement(By.xpath("//button[@data-testid='enuygun-homepage-flight-departureDate-month-forward-button']"));
        ForwardButton.click();
        sleep();

        //Select Date
        List<WebElement> dateList = driver.findElements(By.xpath("//div[@class='sc-kBRoID ceBmsY']/div[@class='sc-fKMpNL khCWdk']/button[contains(text(),'20')]"));
        dateList.get(1).click();
        sleep();

        //Return Date Select
        WebElement ReturnDate = driver.findElement(By.xpath("//div[text()='Dönüş Tarihi']"));
        ReturnDate.click();
        sleep();

        //Click the Forward Arrow Button
        WebElement ForwardButtonTwo = driver.findElement(By.xpath("//button[@data-testid='enuygun-homepage-flight-returnDate-month-forward-button']"));
        ForwardButtonTwo.click();
        sleep();

        //Return date
        List<WebElement> ReturnDateList = driver.findElements(By.xpath("//div[@class='sc-kBRoID ceBmsY']/div[@class='sc-fKMpNL khCWdk']/button[contains(text(),'3')]"));
        ReturnDateList.get(0).click();
        sleep();

        //Transit Filter 'Aktarmasız'
        WebElement TransitFilter = driver.findElement(By.xpath("//div[contains(text(),'Aktarmasız')]"));
        TransitFilter.click();
        sleep();

        //Passenger and Cabin
        WebElement PassengerandCabin = driver.findElement(By.xpath("//div[@data-testid='enuygun-homepage-flight-selectPassengerAndCabin']"));
        PassengerandCabin.click();
        sleep();

        //Select Guest item and select cabin class, Click Okay Button
        List<WebElement> GuestCount = driver.findElements(By.xpath("//button[@class='undefined stepper-module_counterStepper__AHN37 stepper-module_primary__qQQ9r stepper-module_primary-filled__AnPrI']"));
        GuestCount.get(0).click();
        sleep();

        WebElement CabinClass = driver.findElement(By.xpath("//button[@class='sc-klVQfs iDxDFf cabinClassBtn ']"));
        CabinClass.click();

        WebElement OkayButton = driver.findElement(By.xpath("//button[@class='sc-klVQfs dScLyI']"));
        OkayButton.click();
        sleep();

        //Click Submit Button
        WebElement SubmitButton = driver.findElement(By.xpath("//div[@class='sc-gEvEer kitgym']"));
        SubmitButton.click();

        //New Page Url verification
        String ExpectedUrl = "https://www.enuygun.com/ucak-bileti/antalya-havalimani-istanbul-sabiha-gokcen-havalimani-ayt-saw/?gidis=20.03.2024&donus=03.05.2024&yetiskin=2&sinif=business&save=1&aktarmasiz=1&geotrip=domestic&trip=domestic";
        String ActualUrl = driver.getCurrentUrl();
        Assert.assertEquals(ActualUrl, ExpectedUrl, "Actual Url is not the same expected url!!");

        //New Page Title verification
        String expectedTitle = "20 Mar 2024 - 03 May 2024 Antalya İstanbul ucuz uçak bileti - Antalya İstanbul uçak bileti fiyatları - ENUYGUN";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        //Header component
        boolean headerComponent = driver.findElement(By.xpath("//div[@class='searchFormGraphic col tr']")).isDisplayed();
        //WebElement headerComponent = driver.findElement(By.xpath("//div[@class='form-header ']"));
        Assert.assertTrue(headerComponent, "Header component is not visible");

        //class="form-header active" icerisindeki Antalya-Istanbul text verification
        List<WebElement> Locationinfo = driver.findElements(By.xpath("//strong[@class='graphic-strong']"));
        System.out.println(Locationinfo.get(1).getText());
        String expectedText = "Antalyaİstanbul";
        String actualText = Locationinfo.get(1).getText();
        Assert.assertEquals(actualText, expectedText, "Actual text is not the same as Antalyaİstanbul!");
        sleep();

        driver.quit();
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}






