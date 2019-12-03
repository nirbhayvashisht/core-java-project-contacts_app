package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.util.List;

import core.Contacts;
import dao.AddContactsDAO;
import dao.ContactsDAO;

public class ContactsApp extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private JButton addNewContact;
	private JFrame addC;

	private ContactsDAO contactsDAO;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactsApp frame = new ContactsApp();
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
	public ContactsApp() {
		
		// create the DAO
		try {
			contactsDAO = new ContactsDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}


		setTitle("Contacts App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterFirstName = new JLabel("Enter first name");
		panel.add(lblEnterFirstName);
		
		firstNameTextField = new JTextField();
		panel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get employees for the last name

				// If first name is empty, then get all employees

				// Print out employees				
				
				try {
					String firstName = firstNameTextField.getText();

					List<Contacts> contacts = null;

					if (firstName != null && firstName.trim().length() > 0) {
						contacts = contactsDAO.searchContacts(firstName);
					} else {
						contacts = contactsDAO.getAllContacts();
					}
					
					// create the model and update the "table"
					ContactsTableModel model = new ContactsTableModel(contacts);
					
					table.setModel(model);
					

					for (Contacts temp : contacts) {
						System.out.println(temp);
					}

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ContactsApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnSearch);

		//for adding new contacts
		addNewContact = new JButton("Add");
		addNewContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addC = new JFrame("Add New Contact");
					addC.setVisible(true);

					addC.setBounds(100, 100, 450, 300);
					addC.setLayout(null);

					JTextField fname = new JTextField();
					fname.setBounds(120,10,180,27);
					addC.add(fname);
					JLabel flabel = new JLabel();
					flabel.setText("Enter first name");
					flabel.setBounds(10,10,100,30);
					addC.add(flabel);

					JTextField lname = new JTextField();
					lname.setBounds(120,50,180,27);
					addC.add(lname);
					JLabel llabel = new JLabel();
					llabel.setText("Enter last name");
					llabel.setBounds(10,50,100,30);
					addC.add(llabel);

					JTextField email = new JTextField();
					email.setBounds(120,90,180,27);
					addC.add(email);
					JLabel elabel = new JLabel();
					elabel.setText("Enter email");
					elabel.setBounds(10,90,100,30);
					addC.add(elabel);

					JTextField num = new JTextField();
					num.setBounds(120,130,180,27);
					addC.add(num);
					JLabel nlabel = new JLabel();
					nlabel.setText("Enter number");
					nlabel.setBounds(10,130,100,30);
					addC.add(nlabel);

					JButton done = new JButton("Done");
					done.setBounds(219,180,70,30);
					done.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String fn = fname.getText();
							String ln = lname.getText();
							String em = email.getText();
							String n = num.getText();
							int nmb = Integer.parseInt(n);
							try {
								AddContactsDAO obj = new AddContactsDAO();

								 obj.save(fn, ln, em, nmb);

							}catch (Exception unknown){
								System.out.println(unknown);
							}
							fname.setText("");
							lname.setText("");
							email.setText("");
							num.setText("");
						}
					});
					addC.add(done);

				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		panel.add(addNewContact);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
