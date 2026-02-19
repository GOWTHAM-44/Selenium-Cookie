package demo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CookiesTest {
	
	private WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}
	
	@AfterMethod(alwaysRun = true)
	public void close()
	{
		if(driver != null)
		{
		driver.quit();
		}
	}
  @Test
  public void cookie() {
	  
	  driver.get("https://the-internet.herokuapp.com/");
	  
	  Cookie mycookie=new Cookie("trainerCookie","Gowtham");
	  driver.manage().addCookie(mycookie);
	  
	  Cookie fetched=driver.manage().getCookieNamed("trainerCookie");
	  Assert.assertNotNull(fetched,"Cookie was NOT added");
	  Assert.assertEquals(fetched.getValue(),"Gowtham","Cookie value mismatch!");
	  
	  System.out.println("Added cookie: "+fetched);
	  
	  Set<Cookie> all=driver.manage().getCookies();
	  
	  System.out.println("Total cookies now: "+all.size());
	  
	  for(Cookie c:all)
	  {
		  System.out.println("Cookie -> "+c.getName()+" = "+c.getValue());
	  }
	  
	  System.out.println("Domain -> "+fetched.getDomain());
	  System.out.println("cookie Path -> "+fetched.getPath());
	  System.out.println("cookie is secure -> "+fetched.isSecure());
	  System.out.println("is cookie is http only -> "+fetched.isHttpOnly());
	  System.out.println("cookie get Expiery ->"+fetched.getExpiry());
	  
	  //delete cookie by name
	  
	  driver.manage().deleteCookieNamed("trainerCookie");
	  Cookie afterDelete=driver.manage().getCookieNamed("trainerCookie");
	  Assert.assertNull(afterDelete,"Cookie was NOT deleted!");
	  System.out.println("Deleted the Cookie of trainer");
	  driver.manage().deleteAllCookies(); 
	  Assert.assertEquals(driver.manage().getCookies().size(), 0, "All cookies were not deleted");
	  System.out.println("Deleted the cookies");
	  
	  
	  
  }
}
