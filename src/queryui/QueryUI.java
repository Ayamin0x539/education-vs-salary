package queryui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAQuery;

import querymanager.QueryMaker;
import querymanager.QueryManager;

public class QueryUI implements ActionListener {
	private JFrame frame;
	private ImagePanel queryPanel;
	private GridBagConstraints panelConstraints;
	
	private QueryBox locationField;
	private QueryBox professionField;
	private QueryBox universityField;
	private JButton submitButton;
	
	private WAEngine engine;
	
	public QueryUI(WAEngine engine) {
		this.engine = engine;
		
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
			final String salaryString = "average salary in " + locationField.getQuery() 
			+ " for " + professionField.getQuery();
			
			final String tuitionString = "college tuition for " + universityField.getQuery();
			
			// Make the queries
			final WAQuery salaryQuery = engine.createQuery();
			final WAQuery tuitionQuery = engine.createQuery();
			
			System.out.println("Query: " + salaryString);
			new Thread()
			{
			    public void run() {
			    	int tuition = QueryManager.parseCollegeTuitionQuery(QueryMaker.make(engine, tuitionQuery, tuitionString));
			    	System.out.println("The tuition is $" + tuition + " per year.");
					//QueryManager.parseCollegeTuitionQuery(QueryMaker.make(engine, query, tuitionString));
			    }
			}.start();
		}
		
		
	}
}
