package MixProject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class getbrokenlinks {

	public static void main(String[] args) {
 {

String homePage = "https://www.nordicnaturals.com/consumers";
String url = "";
HttpURLConnection huc = null;
int respCode = 200;
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();

driver.manage().window().maximize();

driver.get(homePage);

List<WebElement> links = driver.findElements(By.tagName("a"));

Iterator<WebElement> allLinks = links.iterator();

while (allLinks.hasNext()) {

url = allLinks.next().getAttribute("href");

System.out.println(url);

if (url == null || url.isEmpty()) {
System.out.println("URL is not configured or it is empty");
continue;
}

try {
huc = (HttpURLConnection) (new URL(url).openConnection());

huc.connect();

respCode = huc.getResponseCode();

if (respCode == 400) {
System.out.println(respCode + "    is a broken link  " + url);

} else if (respCode > 400) {
System.out.println(respCode + "    is Forbidden " + url);

} else {
System.out.println(respCode + "     is a active link   " + url);
}

} catch (MalformedURLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

driver.quit();

}

	}

}
