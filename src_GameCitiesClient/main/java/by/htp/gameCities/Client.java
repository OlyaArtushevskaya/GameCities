package by.htp.gameCities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static final int SERVER_PORT = 8081;
	private static final String SERVER_HOST = "localhost";
	private static InetAddress ip;

	private String messageFromServer;
	Socket clientSocket;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;

	GameClient gameClient = new GameClient();

	public Client() {
		super();
	}

	public void clientServer() {

		try {

			Thread.sleep(5000);
			ip = InetAddress.getByName(SERVER_HOST);
			clientSocket = new Socket(ip, SERVER_PORT);
			System.out.println("");
			System.out.println("Клиент: Привет! Отлично!! Я готов начать игру!!");

			os = clientSocket.getOutputStream();
			dos = new DataOutputStream(os);

			is = clientSocket.getInputStream();
			dis = new DataInputStream(is);
			gameClient.randomeCity();
			boolean game = true;

			while (game) {
				dos.writeUTF(gameClient.getMessageFromClient());
				Thread.sleep(500);
				System.out.println("Клиент: " + gameClient.getMessageFromClient());
				dos.flush();
				// dos.close();

				messageFromServer = dis.readUTF();
				gameClient.setMessageFromServer(messageFromServer);
				System.out.println("Сервер: " + messageFromServer);
				System.out.println("");
				if(gameClient.getMessageFromServer().equals("Этот город был назван ранее. Назови другой город!")){
					gameClient.setMessageFromClient("Я не могу назвать город. Игра закончена! Я проиграл!!");
					game = false;
					break;
				}else{
					gameClient.citiesExchange();
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}