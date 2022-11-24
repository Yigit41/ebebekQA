package patikadev;

import java.time.Duration;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {

	private WebDriver driver;
	Log log = new Log();
	
	@Before
	public void setUp() throws Exception {
		log.info("Testing Started");
		String url = "https://www.e-bebek.com/";
		driver = new ChromeDriver();
		driver.get(url);
		log.info("Homepage Opened");
	}

	@After
	public void tearDown() {
		log.info("Test Ended");
		driver.quit();

	}

	@Test
	public void ebebekTest() throws Exception {

		WebElement searchElement = driver.findElement(By.id("txtSearchBox"));

		searchElement.click();
		log.info("Clicked on search bar");
		searchElement.sendKeys("biberon");
		log.info("Typed in \"biberon\"");
		searchElement.sendKeys(Keys.ENTER);
		log.info("Word searched");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div/eb-product-list-item[1]/div/eb-generic-link/a")));
		
		WebElement firstProduct = driver.findElement(By.xpath("//div/eb-product-list-item[1]/div/eb-generic-link/a"));
		String productLink = firstProduct.getAttribute("href");

		driver.get(productLink);
		log.info("Clicked on first product");
		
		WebElement element = driver.findElement(By.id("addToCartBtn"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		log.info("Clicked on add to cart button");
		
		wait.until(ExpectedConditions.presenceOfElementLocated((By.id("btnShowCart"))));
		driver.findElement(By.id("btnShowCart")).click();
		log.info("Clicked show cart button");
		try {
			WebDriverWait cartListWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			cartListWait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div/eb-cart-item-list"))));
			log.info("Test Successful. The Product Has Been Added To The Cart");
		} catch (Exception e) {
			log.warn("Test Failed. The Product Not Found In Cart");

		}

	}

}
