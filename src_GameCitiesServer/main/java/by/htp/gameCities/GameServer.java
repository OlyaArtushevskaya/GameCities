package by.htp.gameCities;

import java.util.ArrayList;
import java.util.Random;

public class GameServer {
	private String messageFromClient;
	private String messageFromServer;
	private String lastChar;

	ArrayList<String> listCitiesUsed = new ArrayList<String>();
	ArrayList<String> listCitiesStartsWithLastChar = new ArrayList<String>();
	SeleniumGetCities seleniumGetCities = new SeleniumGetCities();
	Random random = new Random();
	private String cityStartsWithLastChar;

	
	public void citiesExchange() {

		if (listCitiesUsed.contains(messageFromClient)) {
			messageFromServer = "Этот город был назван ранее. Назови другой город!";

		} else {
			listCitiesUsed.add(messageFromClient);
			seleniumGetCities.getListCities().remove(messageFromClient);

		

		lastChar = messageFromClient.toLowerCase().substring(
				messageFromClient.length() - 1);
		
		for(int i=0; i<seleniumGetCities.getListCities().size(); i++){
			if(seleniumGetCities.getListCities().get(i).toLowerCase().startsWith(lastChar)){
				
				cityStartsWithLastChar = seleniumGetCities.getListCities().get(i);
			}
		}
		if(cityStartsWithLastChar!=null){
		messageFromServer=cityStartsWithLastChar;
		listCitiesUsed.add(messageFromServer);
		seleniumGetCities.getListCities().remove(messageFromServer);
		}/*else{
			messageFromServer ="Я не могу назвать город. Игра закончена! Я проиграл!!";

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