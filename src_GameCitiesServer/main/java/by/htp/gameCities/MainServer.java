package by.htp.gameCities;

public class MainServer {

	public static void main(String[] args) throws InterruptedException {

		SeleniumGetCities seleniumGetCities = new SeleniumGetCities();
		seleniumGetCities.initBrowser();
		seleniumGetCities.getCities();
		seleniumGetCities.quitBrowser();

		Server server = new Server();
		server.serverClient();
	}

}