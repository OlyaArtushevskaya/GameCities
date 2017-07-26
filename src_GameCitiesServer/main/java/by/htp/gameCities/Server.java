package by.htp.gameCities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	private static final int SERVER_PORT = 8081;
	private String messageFromClient;

	Socket clientSocket;
	ServerSocket serverSocket;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;

	GameServer gameServer = new GameServer();

	public Server(String messageFromClient) {
		this.messageFromClient = messageFromClient;
	}

	public Server() {
		super();
	}

	public void serverClient() {

		try {

			Thread.sleep(5000);
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("");
			System.out.println("Сервер: Привет! Давай играть в игру \"Города\"!! Называй первый город!!");

			clientSocket = serverSocket.accept();
			
			is = clientSocket.getInputStream();
			dis = new DataInputStream(is);

			os = clientSocket.getOutputStream();
			dos = new DataOutputStream(os);
			
			boolean game = true;

			while (game) {

				messageFromClient = dis.readUTF();
				gameServer.setMessageFromClient(messageFromClient);
				System.out.println("Клиент: " + messageFromClient);
				
				if(gameServer.getMessageFromClient().equals("Этот город был назван ранее. Назови другой город!")){
					gameServer.setMessageFromServer("Я не могу назвать город. Игра закончена! Я проиграл!!");
					game = false;
					break;
				}else{
				gameServer.citiesExchange();
				}
				dos.writeUTF(gameServer.getMessageFromServer());
				Thread.sleep(500);
				System.out.println("Сервер: " + gameServer.getMessageFromServer());

				dos.flush();
				// dos.close();
				System.out.println("");

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