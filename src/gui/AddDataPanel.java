package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class AddDataPanel extends JPanel implements ActionListener {
	
	public static final String BUT_CREATE = "Create OCL";


	
	private MainWindow main;
	private JLabel labAddProduction;
	private JTextField txtAddProduction;
	private JButton butAdd;
	private JLabel labAddVariables;
	private JTextField txtAddVariables;
	private JLabel labAddTerminables;
	private JTextField txtAddTerminables;

    

	

	public AddDataPanel(MainWindow mainWindow) {
		//initialization of attributes
		
		main= mainWindow;
		labAddProduction = new JLabel("Add productions");
		txtAddProduction = new JTextField("");
		butAdd= new JButton("Create LIC");
		butAdd.addActionListener(this);
		butAdd.setActionCommand(BUT_CREATE);
		labAddVariables = new JLabel("Enter Variables");
		txtAddVariables = new JTextField("");
		labAddTerminables = new JLabel("Enter Terminables");
		txtAddTerminables = new JTextField("");

		//configuration of the gui structure
		
		this.setLayout(new BorderLayout());

		
		//Panel1
		JPanel auxPanel1 = new JPanel();
		auxPanel1.setBorder(BorderFactory.createTitledBorder("Productions"));
		auxPanel1.setLayout(new GridLayout(1,5));
		auxPanel1.add(labAddProduction);
		auxPanel1.add(new JLabel());
		auxPanel1.add(txtAddProduction);
		auxPanel1.add(new JLabel());
		auxPanel1.add(butAdd);
		this.add(auxPanel1,BorderLayout.NORTH);
		//Panel2 
		JPanel auxPanel2 = new JPanel();
		auxPanel2.setBorder(BorderFactory.createTitledBorder("Non terminal Symbols"));
		auxPanel2.setLayout(new GridLayout(1,5));
		auxPanel2.add(labAddVariables);
		auxPanel2.add(new JLabel());
		auxPanel2.add(txtAddVariables);
		auxPanel2.add(new JLabel());
		auxPanel2.add(new JLabel("Format : SDASB..."));

		this.add(auxPanel2,BorderLayout.CENTER);
		//panel 3		
		JPanel auxPanel3 = new JPanel();
		auxPanel3.setBorder(BorderFactory.createTitledBorder("Terminal Symbols"));
		auxPanel3.setLayout(new GridLayout(1,5));
		auxPanel3.add(labAddTerminables);
		auxPanel3.add(new JLabel());
		auxPanel3.add(txtAddTerminables);
		auxPanel3.add(new JLabel());
		auxPanel3.add(new JLabel("Format : abcde..."));

		this.add(auxPanel3,BorderLayout.SOUTH);

		
		
		
		
		
	}




	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(this.BUT_CREATE)) {
			String productions =txtAddProduction.getText();
			String variables = txtAddVariables.getText();
			String terminables = txtAddTerminables.getText();
			main.createOCL(productions,variables,terminables);
			isAllEnabled(false);
		}

	}




	public void isAllEnabled(boolean enabled) {
		if(enabled) {
			txtAddProduction.setText("");
			txtAddProduction.setEnabled(enabled);
			txtAddTerminables.setText("");
			txtAddTerminables.setEnabled(enabled);
			txtAddVariables.setText("");
			txtAddVariables.setEditable(enabled);
			butAdd.setEnabled(enabled);
		}
		else {
			txtAddProduction.setEnabled(enabled);
			txtAddTerminables.setEnabled(enabled);
			txtAddVariables.setEditable(enabled);
			butAdd.setEnabled(enabled);
		}
		
		
		
	}
	

	
	

}
