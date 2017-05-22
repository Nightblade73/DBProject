import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Controllers.BuyerController;
import Controllers.InterfaceController;
import Controllers.ProductController;
import Controllers.SaleController;
import Tables.InterfaceTable;
import Tables.Product;

public class FNewSale {

	private JFrame frame;
	private JTextArea txtrWellcome;
	private JTextField txtSurname;
	private JTextField txtName;
	private JTextField txtPatronymic;
	private JTextField txtTelephone;
	private JLabel lblSurname;
	private JLabel lblName;
	private JLabel lblPatronymic;
	private JLabel lblTelephone;
	private JList<Object> listProduct;
	private JButton btnAccept;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private JLabel lblId;
	private JComboBox<Object> comboBox;
	private Statement statement;
	private InterfaceController ctrl;
	private ArrayList<InterfaceTable> listTable = new ArrayList<InterfaceTable>();
	private JLabel label;
	private JRadioButton rdbtn;
	private JTextField textId;
	private JButton btnReadySale;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	public FNewSale(Statement s) {

		statement = s;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void init() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					@SuppressWarnings("deprecation")
					Date d1 = new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(),
							new java.util.Date().getDate());
					ctrl = new ProductController();
					listTable = ctrl.loadTable(statement);
					if (rdbtn.isSelected()) {
						txtrWellcome.setText("Ваш заказ:\nНомер товара:"
								+ listTable.get(listProduct.getSelectedIndex()).getPrimaryKey() + "\nНомер покупателя:"
								+ comboBox.getSelectedItem().toString().split("-")[0] + "\nКоличество товара:" + (int) spinner.getValue()
								+ "\nДата покупки:" + d1 + "\nСтоимость:"
								+ ((int) spinner.getValue()
										* ((Product) (listTable.get(listProduct.getSelectedIndex())))
												.getPriceforone()));
					} else {
						txtrWellcome.setText("Ваш заказ:\nНомер товара:"
								+ listTable.get(listProduct.getSelectedIndex()).getPrimaryKey() + "\nНомер покупателя:"
								+ textId.getText() + "\nКоличество товара:" + (int) spinner.getValue()
								+ "\nДата покупки:" + d1 + "\nСтоимость:"
								+ ((int) spinner.getValue()
										* ((Product) (listTable.get(listProduct.getSelectedIndex())))
												.getPriceforone()));
					}
					btnReadySale.setEnabled(true);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + ex.getMessage());
				}

			}
		});
		btnAccept.setBounds(189, 465, 97, 25);
		frame.getContentPane().add(btnAccept);

		txtSurname = new JTextField();
		txtSurname.setBounds(126, 45, 116, 22);
		frame.getContentPane().add(txtSurname);
		txtSurname.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(126, 68, 116, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtPatronymic = new JTextField();
		txtPatronymic.setColumns(10);
		txtPatronymic.setBounds(126, 92, 116, 22);
		frame.getContentPane().add(txtPatronymic);

		txtTelephone = new JTextField();
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(126, 115, 116, 22);
		frame.getContentPane().add(txtTelephone);

		lblSurname = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F:");
		lblSurname.setBounds(12, 48, 102, 16);
		frame.getContentPane().add(lblSurname);

		lblName = new JLabel("\u0418\u043C\u044F:");
		lblName.setBounds(12, 71, 102, 16);
		frame.getContentPane().add(lblName);

		lblPatronymic = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E:");
		lblPatronymic.setBounds(12, 95, 102, 16);
		frame.getContentPane().add(lblPatronymic);

		lblTelephone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D:");
		lblTelephone.setBounds(12, 118, 102, 16);
		frame.getContentPane().add(lblTelephone);

		rdbtn = new JRadioButton(
				"\u0423\u0436\u0435 \u0437\u0430\u0440\u0435\u0433\u0435\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u043D");
		rdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtn.isSelected()) {
					textId.setEnabled(false);
					txtName.setEnabled(false);
					txtPatronymic.setEnabled(false);
					txtSurname.setEnabled(false);
					txtTelephone.setEnabled(false);
					comboBox.setEnabled(true);
				} else {
					textId.setEnabled(true);
					txtName.setEnabled(true);
					txtPatronymic.setEnabled(true);
					txtSurname.setEnabled(true);
					txtTelephone.setEnabled(true);
					comboBox.setEnabled(false);
				}
			}
		});
		rdbtn.setBounds(250, 12, 173, 25);
		frame.getContentPane().add(rdbtn);

		comboBox = new JComboBox<Object>();
		comboBox.setEnabled(false);
		comboBox.setBounds(254, 45, 173, 22);
		frame.getContentPane().add(comboBox);
		loadComboBox();

		lblId = new JLabel("\u041C\u043E\u0439 id");
		lblId.setBounds(12, 16, 112, 16);
		frame.getContentPane().add(lblId);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 150, 492, 142);
		frame.getContentPane().add(scrollPane);

		listProduct = new JList<Object>(addToList());
		scrollPane.setViewportView(listProduct);

		spinner = new JSpinner();
		spinner.setBounds(393, 115, 30, 22);
		frame.getContentPane().add(spinner);

		label = new JLabel(
				"\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u0435\u0445\u043D\u0438\u043A\u0438");
		label.setBounds(254, 118, 131, 16);
		frame.getContentPane().add(label);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 305, 492, 147);
		frame.getContentPane().add(scrollPane_1);

		txtrWellcome = new JTextArea();
		scrollPane_1.setViewportView(txtrWellcome);
		txtrWellcome.setBackground(UIManager.getColor("Button.background"));
		txtrWellcome.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrWellcome.setEditable(false);

		textId = new JTextField();
		textId.setColumns(10);
		textId.setBounds(126, 13, 116, 22);
		frame.getContentPane().add(textId);

		btnReadySale = new JButton("Ready");
		btnReadySale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<String> list = new ArrayList<String>();
					@SuppressWarnings("deprecation")
					Date d1 = new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(),
							new java.util.Date().getDate());
					ctrl = new ProductController();
					listTable = ctrl.loadTable(statement);
					if (rdbtn.isSelected()) {
						list.add(String.valueOf((listTable.get(listProduct.getSelectedIndex())).getPrimaryKey()));
						list.add(comboBox.getSelectedItem().toString().split("-")[0]);
						list.add(spinner.getValue().toString());
						list.add(d1.toString());
						list.add(String.valueOf((int) spinner.getValue()
								* ((Product) (listTable.get(listProduct.getSelectedIndex()))).getPriceforone()));
						ctrl = new SaleController();
						ctrl.insertRow(statement, list);
					} else {
						ctrl = new BuyerController();
						list.add(textId.getText());
						list.add(txtSurname.getText());
						list.add(txtName.getText());
						list.add(txtPatronymic.getText());
						list.add(txtTelephone.getText());
						ctrl.insertRow(statement, list);
						ctrl = new ProductController();
						listTable = ctrl.loadTable(statement);
						list.clear();
						list.add(String.valueOf((listTable.get(listProduct.getSelectedIndex())).getPrimaryKey()));
						list.add(textId.getText());
						list.add(spinner.getValue().toString());
						list.add(d1.toString());
						list.add(String.valueOf((int) spinner.getValue()
								* ((Product) (listTable.get(listProduct.getSelectedIndex()))).getPriceforone()));
						ctrl = new SaleController();
						ctrl.insertRow(statement, list);
					}
					btnCancel.doClick();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Ошибка: " + ex.getMessage());
				}
			}
		});
		btnReadySale.setEnabled(false);
		btnReadySale.setBounds(298, 465, 97, 25);
		frame.getContentPane().add(btnReadySale);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(407, 465, 97, 25);
		frame.getContentPane().add(btnCancel);

	}

	public void loadComboBox() {
		ctrl = new BuyerController();
		try {
			listTable = ctrl.loadTable(statement);
			for (int i = 0; i < listTable.size(); i++) {
				comboBox.addItem(listTable.get(i).getInfo());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public String[] addToList() {
		ctrl = new ProductController();
		try {
			listTable = ctrl.loadTable(statement);
			String[] str = new String[listTable.size()];
			for (int i = 0; i < listTable.size(); i++) {
				str[i] = "Модель: " + ((Product) listTable.get(i)).getModel() + " Цена: "
						+ ((Product) listTable.get(i)).getPriceforone();
			}
			return str;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ошибка: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void visible(ActionListener al) {
		frame.setVisible(true);
		btnCancel.addActionListener(al);
	}

	public void frameClose() {
		frame.dispose();
	}
}
