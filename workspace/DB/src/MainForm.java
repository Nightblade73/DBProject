import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controllers.BuyerController;
import Controllers.InterfaceController;
import Controllers.PartyController;
import Controllers.ProducerController;
import Controllers.ProductController;
import Controllers.SaleController;
import Tables.InterfaceTable;

public class MainForm {

	private JFrame frame;
	private Connection connection;
	private Statement statement;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<InterfaceTable> listTable = new ArrayList<InterfaceTable>();
	private String selectedTable;
	private JLabel lblFirst;
	private JLabel lblSecond;
	private JLabel lblThird;
	private JLabel lblFour;
	private JLabel lblFive;
	private JTextField txtFirst;
	private JTextField txtSecond;
	private JTextField txtThird;
	private JTextField txtFour;
	private JTextField txtFive;
	private JComboBox<Integer> comboBox;
	InterfaceController ctrl;
	private JComboBox<String> cBFirst;
	private JComboBox<String> cBSecond;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
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
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				frame.setVisible(false);
				newConnection();
			}

		});
		frame.setBounds(100, 100, 673, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 655, 26);
		frame.getContentPane().add(menuBar);

		JMenu mnConnection = new JMenu("\u041F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuBar.add(mnConnection);

		JMenuItem mntmNewConnection = new JMenuItem(
				"\u041D\u043E\u0432\u043E\u0435 \u043F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		mntmNewConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				newConnection();
			}
		});
		mnConnection.add(mntmNewConnection);

		JMenuItem mntmDisconnection = new JMenuItem(
				"\u041E\u0442\u043A\u043B\u044E\u0447\u0438\u0442\u044C\u0441\u044F");
		mntmDisconnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		mnConnection.add(mntmDisconnection);

		JMenuItem mntmExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection.close();
					statement.close();
					frame.dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		mnConnection.add(mntmExit);
		JMenu mnTables = new JMenu("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A\u0438");
		menuBar.add(mnTables);

		JMenuItem mntmNewMenuItem = new JMenuItem(
				"\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \"Sale\"");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					selectedTable = "sale";
					lblFirst.setText("Номер продукта:");
					lblSecond.setText("Номер покупателя:");
					lblThird.setText("Количество товара:");
					lblFive.setText("Дата продажи");
					lblFive.setText("Цена:");
					cBFirst.setVisible(true);
					ctrl = new ProductController();
					listTable = ctrl.loadTable(statement);
					loadComboBox(cBFirst);
					cBSecond.setVisible(true);
					ctrl = new BuyerController();
					listTable = ctrl.loadTable(statement);
					loadComboBox(cBSecond);
					lblThird.setVisible(true);
					lblFour.setVisible(true);
					lblFive.setVisible(true);
					txtThird.setVisible(true);
					txtFour.setVisible(true);
					txtFive.setVisible(true);
					ctrl = new SaleController();
					selectTable(ctrl);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		mnTables.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem(
				"\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \"Product\"");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectedTable = "product";
					lblFirst.setText("Номер производителя:");
					lblSecond.setText("Номер партии:");
					lblThird.setText("Модель:");
					lblFour.setText("Цена за штуку:");
					cBFirst.setVisible(true);
					ctrl = new ProducerController();
					listTable = ctrl.loadTable(statement);
					loadComboBox(cBFirst);
					cBSecond.setVisible(true);
					ctrl = new PartyController();
					listTable = ctrl.loadTable(statement);
					loadComboBox(cBSecond);
					lblThird.setVisible(true);
					lblFour.setVisible(true);
					lblFive.setVisible(false);
					txtThird.setVisible(true);
					txtFour.setVisible(true);
					txtFive.setVisible(false);
					ctrl = new ProductController();
					selectTable(ctrl);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mnTables.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem(
				"\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \"Buyer\"");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl = new BuyerController();
				try {
					selectTable(ctrl);
					selectedTable = "buyer";
					lblFirst.setText("Номер покупателя:");
					lblSecond.setText("Фамилия:");
					lblThird.setText("Имя:");
					lblFour.setText("Отчество:");
					lblFive.setText("Телефон");
					cBFirst.setVisible(false);
					cBSecond.setVisible(false);
					lblThird.setVisible(true);
					lblFour.setVisible(true);
					lblFive.setVisible(true);
					txtThird.setVisible(true);
					txtFour.setVisible(true);
					txtFive.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mnTables.add(mntmNewMenuItem_2);

		JMenuItem mntmproducer = new JMenuItem(
				"\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \"Producer\"");
		mntmproducer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl = new ProducerController();
				try {
					selectTable(ctrl);
					selectedTable = "producer";
					lblFirst.setText("Номер производителя:");
					lblSecond.setText("Имя производителя:");
					lblThird.setText("Электронная почта:");
					lblFour.setText("Телефон:");
					lblFive.setText("Адресс");
					cBFirst.setVisible(false);
					cBSecond.setVisible(false);
					lblThird.setVisible(true);
					lblFour.setVisible(true);
					lblFive.setVisible(true);
					txtThird.setVisible(true);
					txtFour.setVisible(true);
					txtFive.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mnTables.add(mntmproducer);

		JMenuItem mntmparty = new JMenuItem("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \"Party\"");
		mntmparty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl = new PartyController();
				try {
					selectTable(ctrl);
					selectedTable = "party";
					lblFirst.setText("Дата поставки:");
					lblSecond.setText("Имя поставщика:");
					cBFirst.setVisible(false);
					cBSecond.setVisible(false);
					lblThird.setVisible(false);
					lblFour.setVisible(false);
					lblFive.setVisible(false);
					txtThird.setVisible(false);
					txtFour.setVisible(false);
					txtFive.setVisible(false);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mnTables.add(mntmparty);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<String> list = new ArrayList<String>();
					if (selectedTable.equals("sale")) {
						list.add(cBFirst.getSelectedItem().toString().split("-")[0]);
						list.add(cBSecond.getSelectedItem().toString().split("-")[0]);
						list.add(txtThird.getText());
						list.add(txtFour.getText());
						list.add(txtFive.getText());
					} else if (selectedTable.equals("product")) {
						list.add(cBFirst.getSelectedItem().toString().split("-")[0]);
						list.add(cBSecond.getSelectedItem().toString().split("-")[0]);
						list.add(txtThird.getText());
						list.add(txtFour.getText());
					} else if (selectedTable.equals("buyer")) {
						list.add(txtFirst.getText());
						list.add(txtSecond.getText());
						list.add(txtThird.getText());
						list.add(txtFour.getText());
						list.add(txtFive.getText());
					} else if (selectedTable.equals("producer")) {
						list.add(txtFirst.getText());
						list.add(txtSecond.getText());
						list.add(txtThird.getText());
						list.add(txtFour.getText());
						list.add(txtFive.getText());
					} else if (selectedTable.equals("party")) {
						list.add(txtFirst.getText());
						list.add(txtSecond.getText());
					}
					ctrl.insertRow(statement, list);
					selectTable(ctrl);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(10, 414, 97, 25);
		frame.getContentPane().add(btnInsert);

		cBFirst = new JComboBox<String>();
		cBFirst.setBounds(163, 283, 116, 22);
		frame.getContentPane().add(cBFirst);

		cBSecond = new JComboBox<String>();
		cBSecond.setBounds(163, 305, 116, 22);
		frame.getContentPane().add(cBSecond);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 633, 231);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setBounds(263, 51, 380, 124);
		scrollPane.setViewportView(table);

		lblFirst = new JLabel("first");
		lblFirst.setBounds(12, 283, 139, 16);
		frame.getContentPane().add(lblFirst);

		lblSecond = new JLabel("second");
		lblSecond.setBounds(12, 305, 139, 16);
		frame.getContentPane().add(lblSecond);

		lblThird = new JLabel("third");
		lblThird.setBounds(12, 327, 139, 16);
		frame.getContentPane().add(lblThird);

		lblFour = new JLabel("four");
		lblFour.setBounds(12, 349, 139, 16);
		frame.getContentPane().add(lblFour);

		lblFive = new JLabel("five");
		lblFive.setBounds(12, 371, 139, 16);
		frame.getContentPane().add(lblFive);

		txtFirst = new JTextField();
		txtFirst.setText("first");
		txtFirst.setBounds(163, 283, 116, 22);
		frame.getContentPane().add(txtFirst);
		txtFirst.setColumns(10);

		txtSecond = new JTextField();
		txtSecond.setText("second");
		txtSecond.setColumns(10);
		txtSecond.setBounds(163, 305, 116, 22);
		frame.getContentPane().add(txtSecond);

		txtThird = new JTextField();
		txtThird.setText("third");
		txtThird.setColumns(10);
		txtThird.setBounds(163, 327, 116, 22);
		frame.getContentPane().add(txtThird);

		txtFour = new JTextField();
		txtFour.setText("four");
		txtFour.setColumns(10);
		txtFour.setBounds(163, 349, 116, 22);
		frame.getContentPane().add(txtFour);

		txtFive = new JTextField();
		txtFive.setText("five");
		txtFive.setColumns(10);
		txtFive.setBounds(163, 371, 116, 22);
		frame.getContentPane().add(txtFive);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrl.deleteRow(statement, (int) comboBox.getSelectedItem());
					selectTable(ctrl);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(228, 414, 97, 25);
		frame.getContentPane().add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<String> list = new ArrayList<String>();
					if (selectedTable.equals("sale")) {
						list.add(cBFirst.getSelectedItem().toString().split("-")[0]);
						list.add(cBSecond.getSelectedItem().toString().split("-")[0]);
						list.add(txtThird.getText());
						list.add(txtFour.getText());
						list.add(txtFive.getText());
					} else if (selectedTable.equals("product")) {
						list.add(cBFirst.getSelectedItem().toString().split("-")[0]);
						list.add(cBSecond.getSelectedItem().toString().split("-")[0]);
						list.add(txtThird.getText());
						list.add(txtFour.getText());
					} else if (selectedTable.equals("buyer")) {
						list.add(txtSecond.getText());
						list.add(txtThird.getText());
						list.add(txtFour.getText());
						list.add(txtFive.getText());
					} else if (selectedTable.equals("producer")) {
						list.add(txtSecond.getText());
						list.add(txtThird.getText());
						list.add(txtFour.getText());
						list.add(txtFive.getText());
					} else if (selectedTable.equals("party")) {
						list.add(txtFirst.getText());
						list.add(txtSecond.getText());
					}
					list.add(comboBox.getSelectedItem().toString());
					ctrl.updateRow(statement, list);
					selectTable(ctrl);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Ошибка: Возможно вы не выбрали элемент для обновления. Основная ошибка: "
									+ ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(119, 414, 97, 25);
		frame.getContentPane().add(btnUpdate);

		JButton btnNewSale = new JButton("New Sale");
		btnNewSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewSale();
			}
		});
		btnNewSale.setBounds(546, 414, 97, 25);
		frame.getContentPane().add(btnNewSale);

		comboBox = new JComboBox<>();
		comboBox.setBounds(308, 283, 64, 22);
		frame.getContentPane().add(comboBox);

		JLabel lblPrimaryKey = new JLabel("Id \u0437\u0430\u043F\u0438\u0441\u0438 ");
		lblPrimaryKey.setBounds(384, 278, 73, 26);
		frame.getContentPane().add(lblPrimaryKey);

	}

	public void newConnection() {
		FConnection fc = new FConnection();
		frame.setEnabled(false);
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (fc.getServerName().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Сервер не указан");
						return;
					}
					if (fc.getHost().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Хост не указан");
						return;
					}
					if (fc.getPort().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Порт не указан");
						return;
					}
					if (fc.getDBName().isEmpty()) {
						JOptionPane.showMessageDialog(null, "База данных не указана");
						return;
					}
					if (fc.getUserName().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Пользователь не указан");
						return;
					}
					String url = "jdbc:" + fc.getServerName() + "://" + fc.getHost() + ":" + fc.getPort() + "/"
							+ fc.getDBName();
					String name = fc.getUserName();
					String password = fc.getPassword();
					Class.forName("org.postgresql.Driver");
					connection = DriverManager.getConnection(url, name, password);
					statement = connection.createStatement();
					JOptionPane.showMessageDialog(null, "Подключение прошло успешно");
					fc.frameClose();
					frame.setEnabled(true);
					if (!frame.isVisible())
						frame.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
					e.printStackTrace();
				}
			}
		};
		fc.setVisible(true, al);
	}

	public void loadComboBox() {
		comboBox.removeAllItems();
		for (int i = 0; i < listTable.size(); i++) {
			comboBox.addItem(listTable.get(i).getPrimaryKey());
		}
	}

	public void loadComboBox(JComboBox<String> cB) {
		cB.removeAllItems();
		for (int i = 0; i < listTable.size(); i++) {
			cB.addItem(listTable.get(i).getInfo());
		}
	}

	public void selectTable(InterfaceController ctrl) throws SQLException {
		listTable = ctrl.loadTable(statement);
		loadComboBox();
		table.removeAll();
		DefaultTableModel dtm = new DefaultTableModel(ctrl.getRowData(statement), ctrl.getColumnName(statement));
		table = new JTable(dtm);
		frame.getContentPane().add(table);
		scrollPane.setViewportView(table);
		table.updateUI();
	}

	public void createNewSale() {
		FNewSale f = new FNewSale(statement);
		f.init();
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectTable(ctrl);
					f.frameClose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		};
		f.visible(al);
	}
}
