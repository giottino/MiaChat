
public class ServerMain {
	private int port;

	public ServerMain(int port) {
		this.port = port;
		new Server(port);
		System.out.println(port);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port;

		if (args.length != 1) {
			System.out
					.println("dalla linea di comnado puo essere passato un solo argomento corrispondente al numero di porta :\n "
							+ "java -jar miachat.jar [port] ");
			return;
		}
		port = Integer.parseInt(args[0]);
		new ServerMain(port);
	}

}
