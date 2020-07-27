package stepdefs.com;


import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.com.Utils;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Flights extends Utils {
    public static String prices;
    public static String fastestFlights;


    @Given("^I login website as a guest user$")
    public void i_login_website_as_a_guest_user() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src/test/java/browser_drivers/chromedriver.exe");
        driver = new ChromeDriver();

       /* System.setProperty("webdriver.gecko.driver",
                "src/test/java/geckodriver.exe");
        driver = new FirefoxDriver();*/


        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        enteringdata();
        gettingfares();
        gettingfastestFlights();

    }

    public static void enteringdata() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='FromTag']")).sendKeys("Hyderabad");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@id='ToTag']")).sendKeys("Chennai");

        driver.findElement(By.xpath("//div[@id='ORtrip']/section[2]/div/dl/dd/div/a/i")).click();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//a[contains(text(),'29')]")).click();

        Select Adults = new Select(driver.findElement(By.id("Adults")));
        Adults.selectByIndex(3);

     /*   Select Children = new Select(driver.findElement(By.id("Childrens")));
        Children.selectByIndex(3);

        Select Infants = new Select(driver.findElement(By.id("Infants")));
        Infants.selectByIndex(3);*/

        driver.findElement(By.id("SearchBtn")).click();


    }

    public static void gettingfares() {
        //List<WebElement> dd = driver.findElements(By.xpath("//*[@id='BaggageBundlingTemplate']/text()"));
        List<WebElement> dd = driver.findElements(By.xpath("//*[@id=\"BaggageBundlingTemplate\"]"));
        //*[@id="BaggageBundlingTemplate"]/text()
        System.out.println("Size is " + dd.size());
        TreeSet<String> allfares = new TreeSet<String>();
        for (WebElement elements : dd) {
            prices = elements.getText().replaceAll("[-+.^:,]", "").substring(2);
            System.out.println("Fares are :: " + prices);

            allfares.add(prices);
            // s.put(ch, baseMap.get(ch) + 1);


        }
        System.out.println("Print in ascending" + allfares);


    }

    public static void gettingfastestFlights() {
        List<WebElement> gettingfastestFlightss = driver.findElements(By.xpath("//li/table/tbody[2]/tr/th[4]"));
        System.out.println("Size is " + gettingfastestFlightss.size());
        TreeSet<String> flightTimings = new TreeSet<String>();
        for (WebElement timings : gettingfastestFlightss) {
            fastestFlights = timings.getText().replaceAll("[-+.^:,]", "");
            System.out.println("Fares are :: " + fastestFlights);

            flightTimings.add(fastestFlights);
            // s.put(ch, baseMap.get(ch) + 1);


        }
        System.out.println("Print in ascending" + flightTimings);


    }


    //public static void gettingf
}