import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.Window.Type;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
private String name, address;
private int port;
private JTextField ScriviField;
private JTextArea history;
private DefaultCaret caret;

//classe per usare UDP con TCP si usa Socket
private DatagramSocket socket;
private InetAddress iAddress;

private Thread sendThread;

	
	/**
	 * Create the frame.
	 * @param port 
	 * @param address 
	 */
	public Client(String name, String address, int port) {
		

		this.name = name;
		this.address = address;
		this.port = port;
		
		createWindow();
console("...stai cercando di collegarti alla chat...");

	boolean connect=openConnection(address);
		if(connect==false){
			System.err.println("Connection failed");
			console("connessione non riuscita");
		}
		String connection = name+" connected from "+ address+" , user: "+ port;		
	
	send(connection.getBytes());
	}

	
	private void createWindow(){
	
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setTitle("Chattela pe cazzatelle");
		//setType(Type.UTILITY);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/**GridbagLayout*/
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20,860, 10};
		gbl_contentPane.rowHeights = new int[]{40,350,45,40};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		history = new JTextArea();
		history.setEditable(false);
		/*caret=(DefaultCaret)history.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);*/
		JScrollPane scroll=new JScrollPane(history);
		history.setText("");
		GridBagConstraints gbc_txtrHistory = new GridBagConstraints();
		gbc_txtrHistory.insets = new Insets(0, 0, 5, 5);
		gbc_txtrHistory.fill = GridBagConstraints.BOTH;
		gbc_txtrHistory.gridx = 1;
		gbc_txtrHistory.gridy = 1;
		gbc_txtrHistory.gridwidth = 3;

		contentPane.add(scroll, gbc_txtrHistory);
		
		ScriviField = new JTextField();
		ScriviField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					String nuovoMessaggio=ScriviField.getText();
					send(nuovoMessaggio);
				}
			}
		});
		GridBagConstraints gbc_ScriviField = new GridBagConstraints();
		gbc_ScriviField.insets = new Insets(0, 0, 0, 5);
		gbc_ScriviField.fill = GridBagConstraints.BOTH;
		gbc_ScriviField.gridx = 1;
		gbc_ScriviField.gridy = 2;
		contentPane.add(ScriviField, gbc_ScriviField);
		ScriviField.setColumns(10);
		
		JButton SendButton = new JButton("Send");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nuovoMessaggio=ScriviField.getText();
				send(nuovoMessaggio);
				ScriviField.requestFocusInWindow();
			}
		});
		SendButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_SendButton = new GridBagConstraints();
		gbc_SendButton.insets = new Insets(0, 0, 0, 5);
		gbc_SendButton.gridx = 1;
		gbc_SendButton.gridy = 3;
		contentPane.add(SendButton, gbc_SendButton);
		
		
		setVisible(true);
		ScriviField.requestFocusInWindow();
		}


public void console(String message){
	history.append(message+"\n");
}





private boolean openConnection(String address){
	try {socket=new DatagramSocket();
		iAddress=InetAddress.getByName(address);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}catch(SocketException e){
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
}

//ATTENZIONE!!! send ha due versioni overloaddate
private void send(String message){
	if (message.equals(""))return;
	message=name+": "+message;
				console(message);
				send(message.getBytes());
				ScriviField.setText("");
				ScriviField.requestFocusInWindow();
}

private void send(final byte[] data){
	sendThread=new Thread("send"){
	public void run(){
		DatagramPacket packet=new DatagramPacket(data,data.length,iAddress,port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	};
	
	sendThread.start();
}

private String receive(){
	byte[] data=new byte[1024];
	DatagramPacket packet=new DatagramPacket(data,data.length);
	try {
		socket.receive(packet);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String message=new String(packet.getData());
	return message;
}

}
