import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIPAddress;
	private JTextField textPort;
	private JTextField textName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 300, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textName = new JTextField();
		textName.setForeground(Color.BLACK);
		textName.setBounds(66, 36, 161, 23);
		contentPane.add(textName);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(116, 11, 62, 14);
		contentPane.add(nameLabel);
		
		textIPAddress = new JTextField();
		textIPAddress.setBounds(66, 128, 161, 23);
		contentPane.add(textIPAddress);
		textIPAddress.setColumns(10);
		
		JLabel lblIpaddress = new JLabel("IPAddress");
		lblIpaddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblIpaddress.setBounds(116, 103, 62, 14);
		contentPane.add(lblIpaddress);
		
		textPort = new JTextField();
		textPort.setColumns(10);
		textPort.setBounds(66, 220, 161, 23);
		contentPane.add(textPort);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setBounds(116, 195, 62, 14);
		contentPane.add(lblPort);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int port;
				String name=textName.getText();
				String address=textIPAddress.getText();
				if(textPort.getText()==null) port=2;
				else {
					port=Integer.parseInt(textPort.getText());
				}
				
				login(name,address,port);
			}

			
		});
		loginButton.setBounds(102, 303, 89, 23);
		contentPane.add(loginButton);
	}
	
	private void login(String name,String address, int port) {
dispose();			
new Client(name,address,port);
System.out.println(name+address+port);
			}
}
