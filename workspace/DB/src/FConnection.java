import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FConnection {

	private JFrame frame;
	private JTextField txtServerName;
	private JTextField txtPort;
	private JTextField txtDataBaseName;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextField txtHost;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FConnection window = new FConnection();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FConnection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 337);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // закрытие
																	// окна, без
																	// закрытия
																	// всего
																	// приложения
		frame.getContentPane().setLayout(null);

		txtServerName = new JTextField();
		txtServerName.setText("postgresql");
		txtServerName.setBounds(186, 22, 153, 22);
		frame.getContentPane().add(txtServerName);
		txtServerName.setColumns(10);

		JLabel lblNewLabel = new JLabel("server name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(34, 22, 116, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblPort = new JLabel("port:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPort.setBounds(34, 92, 56, 16);
		frame.getContentPane().add(lblPort);

		txtPort = new JTextField();
		txtPort.setText("5432");
		txtPort.setColumns(10);
		txtPort.setBounds(186, 92, 153, 22);
		frame.getContentPane().add(txtPort);

		JLabel lblServerName = new JLabel("data base name:");
		lblServerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblServerName.setBounds(34, 127, 140, 16);
		frame.getContentPane().add(lblServerName);

		txtDataBaseName = new JTextField();
		txtDataBaseName.setText("technicregistration");
		txtDataBaseName.setColumns(10);
		txtDataBaseName.setBounds(186, 127, 153, 22);
		frame.getContentPane().add(txtDataBaseName);

		JLabel lblUser = new JLabel("user:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUser.setBounds(34, 162, 56, 16);
		frame.getContentPane().add(lblUser);

		txtUser = new JTextField();
		txtUser.setText("1");
		txtUser.setColumns(10);
		txtUser.setBounds(186, 162, 153, 22);
		frame.getContentPane().add(txtUser);

		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(34, 197, 116, 16);
		frame.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setText("5730");
		txtPassword.setColumns(10);
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(186, 197, 153, 22);
		frame.getContentPane().add(txtPassword);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); // отмена действий, закрытие окна
			}
		});
		btnCancel.setBounds(171, 245, 97, 25);
		frame.getContentPane().add(btnCancel);

		btnOk = new JButton("Ok");
		btnOk.setBounds(70, 245, 97, 25);
		frame.getContentPane().add(btnOk);

		JLabel lblHost = new JLabel("host:");
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHost.setBounds(34, 57, 56, 16);
		frame.getContentPane().add(lblHost);

		txtHost = new JTextField();
		txtHost.setText("localhost");
		txtHost.setColumns(10);
		txtHost.setBounds(186, 57, 153, 22);
		frame.getContentPane().add(txtHost);
	}

	/// метод, через который вызываем это окно из другого окна, также передаём
	/// событие для кнопки
	public void setVisible(boolean state, ActionListener al) {
		frame.setVisible(state);
		btnOk.addActionListener(al);
	}

	/// методы, которые берут текст из формочек
	public String getServerName() {
		return txtServerName.getText();
	}

	public String getHost() {
		return txtHost.getText();
	}

	public String getPort() {
		return txtPort.getText();
	}

	public String getUserName() {
		return txtUser.getText();
	}

	public String getDBName() {
		return txtDataBaseName.getText();
	}

	@SuppressWarnings("deprecation")
	public String getPassword() {
		return txtPassword.getText();
	}

	/// метод для закрытия формочки из другой формочки
	public void frameClose() {
		frame.dispose();
	}
}
