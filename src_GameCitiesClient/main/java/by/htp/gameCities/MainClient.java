package by.htp.gameCities;

public class MainClient {

	public static void main(String[] args) throws InterruptedException {

		SeleniumGetCities seleniumGetCities = new SeleniumGetCities();
		seleniumGetCities.initBrowser();
		seleniumGetCities.getCities();
		seleniumGetCities.quitBrowser();

		Client client = new Client();
		client.clientServer();
	}

}