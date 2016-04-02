package queryui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class QueryUI implements ActionListener {
	private JFrame frame;
	private ImagePanel queryPanel;
	private GridBagConstraints panelConstraints;
	
	private QueryBox locationField;
	private QueryBox professionField;
	private QueryBox universityField;
	private JButton submitButton;
	
	public QueryUI() {
		frame = new JFrame("Education and Salary");
		
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		BufferedImage myImage = null;
		try {
			myImage = ImageIO.read(new File("grad.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queryPanel = new ImagePanel(myImage);
		frame.setContentPane(queryPanel);
		queryPanel.setLayout(new GridBagLayout());
		
		panelConstraints = new GridBagConstraints();
		panelConstraints.gridy = 0;
		
		initQueryBoxes();
		initSubmitButton();
		
		// Show the frame
		frame.setVisible(true);
	}
	
	private void initQueryBoxes() {
		locationField = new QueryBox("Location");
		professionField = new QueryBox("Profession");
		universityField = new QueryBox("University (optional)");
		
		panelConstraints.gridy++;
		queryPanel.add(locationField, panelConstraints);
		
		panelConstraints.gridy++;
		queryPanel.add(professionField, panelConstraints);
		
		panelConstraints.gridy++;
		queryPanel.add(universityField, panelConstraints);
	
	}
	
	private void initSubmitButton() {
		submitButton = new JButton("Search");
		submitButton.addActionListener(this);
		panelConstraints.gridy++;
		queryPanel.add(submitButton, panelConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton button = (JButton) arg0.getSource();
		if (!locationField.getQuery().isEmpty() && !professionField.getQuery().isEmpty()) {
			String queryString = "average salary in " + locationField.getQuery() 
			+ " for " + professionField.getQuery();
			System.out.println(queryString);
		}
	}
}
