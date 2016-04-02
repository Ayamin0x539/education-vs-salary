package queryui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QueryBox extends JPanel {
	private JTextField queryField;
	
	public QueryBox(String title) {
		super();
		queryField = new JTextField(25);
		
		// Add the label
		this.add(new JLabel(title));
		
		this.add(queryField);
	}
	
	public String getQuery() {
		
		return queryField.getText();
	}
}
