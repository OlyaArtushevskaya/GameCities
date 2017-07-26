package by.htp.gameCities;

import java.util.ArrayList;
import java.util.Random;

public class GameClient {
	private String messageFromClient;
	private String messageFromServer;
	ArrayList<String> listCitiesUsed = new ArrayList<String>();
	ArrayList<String> listCitiesStartsWithLastChar = new ArrayList<String>();
	private static String lastChar;

	SeleniumGetCities seleniumGetCities = new SeleniumGetCities();
	Random random = new Random();
	private String cityStartsWithLastChar;

	public void randomeCity() {
		int i = random.nextInt(seleniumGetCities.getListCities().size());
		messageFromClient = seleniumGetCities.getListCities().get(i);

		listCitiesUsed.add(messageFromClient);
		seleniumGetCities.getListCities().remove(messageFromClient);
		
	}

	public void citiesExchange() {

		if (listCitiesUsed.contains(messageFromServer)) {
			messageFromClient = "Этот город был назван ранее. Назови другой город!";

		} else {
			listCitiesUsed.add(messageFromServer);
			seleniumGetCities.getListCities().remove(messageFromServer);

			lastChar = messageFromServer.toLowerCase().substring(
					messageFromServer.length() - 1);

			for (int i = 0; i < seleniumGetCities.getListCities().size(); i++) {
				if (seleniumGetCities.getListCities().get(i).toLowerCase()
						.startsWith(lastChar)) {
					
					cityStartsWithLastChar = seleniumGetCities.getListCities()
							.get(i);
				}
			}
			if(cityStartsWithLastChar!=null){
			messageFromClient = cityStartsWithLastChar;
			listCitiesUsed.add(messageFromClient);
			seleniumGetCities.getListCities().remove(messageFromClient);
			}/*else{
				messageFromClient ="Я не могу назвать город. Игра закончена! Я проиграл!!";
				
			}*/
			
		}

	}

	public String getMessageFromClient() {
		return messageFromClient;
	}

	public void setMessageFromClient(String messageFromClient) {
		this.messageFromClient = messageFromClient;
	}

	public String getMessageFromServer() {
		return messageFromServer;
	}

	public void setMessageFromServer(String messageFromServer) {
		this.messageFromServer = messageFromServer;
	}

}