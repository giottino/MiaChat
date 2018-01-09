import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable {

	private int port;
	private DatagramSocket socket;

	private Thread serverThread, manageThread,sendThread,receiveThread;
	private boolean running=false;

	public Server(int port) {
		super();
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		serverThread = new Thread(this, "server");
		serverThread.start();
	}

	public void run(){
		running=true;
		System.out.println("la porta usata dal server è "+port);
		manageClient();
		receive();
	}
	
	private void manageClient(){
		manageThread=new Thread("manage"){
			public void run(){
				while(running){
					
				}
				
			}
			
		};
		manageThread.start();
	}

	private void receive(){
		receiveThread=new Thread("receive"){
			public void run(){
			while(running){
				byte[] data=new byte[1024];
				DatagramPacket packet=new DatagramPacket(data,data.length);
				try {
					socket.receive(packet);
					//packet.getAddress();
					//packet.getPort();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
			
		};
		receiveThread.start();
	}
}
