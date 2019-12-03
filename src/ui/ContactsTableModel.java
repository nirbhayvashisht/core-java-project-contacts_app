package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Contacts;

class ContactsTableModel extends AbstractTableModel {

	private static final int LAST_NAME_COL = 1;
	private static final int FIRST_NAME_COL = 0;
	private static final int EMAIL_COL = 2;
	private static final int SALARY_COL = 3;

	private String[] columnNames = { "First Name", "Last Name", "Email",
			"Number" };
	private List<Contacts> contacts;

	public ContactsTableModel(List<Contacts> theContacts) {
		contacts = theContacts;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return contacts.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Contacts tempContact = contacts.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempContact.getLastName();
		case FIRST_NAME_COL:
			return tempContact.getFirstName();
		case EMAIL_COL:
			return tempContact.getEmail();
		case SALARY_COL:
			return tempContact.getNumber();
		default:
			return tempContact.getFirstName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
