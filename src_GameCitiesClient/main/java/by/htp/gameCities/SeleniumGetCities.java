package by.htp.gameCities;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumGetCities {

	private static WebDriver driver;
	private static List<WebElement> listCity;
	private static ArrayList<String> listCities;

	public SeleniumGetCities() {
		super();
	}

	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", "C://Program Files//geckodriver-v0.17.0-win64//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void getCities() {
		driver.get("http://www.americancities.ru/info/usa_cities_in_alphabetical_order/");

		listCity = driver.findElements(By.xpath("html/body/div[2]/div[2]/div[2]/table/tbody/tr/td[1]"));
		listCities = new ArrayList<String>();

		for (int i = 0; i < listCity.size(); i++) {
			listCities.add(listCity.get(i).getText());
		}

		Iterator<String> iterator = listCities.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("Количество городов в списке: " + listCities.size());
	}

	public void quitBrowser() {
		driver.quit();
	}

	public ArrayList<String> getListCities() {
		return listCities;
	}

	public static void setListCities(ArrayList<String> listCities) {
		SeleniumGetCities.listCities = listCities;
	}

}