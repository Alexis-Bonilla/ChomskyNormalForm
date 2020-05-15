package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;



public class AnswerPanel extends JPanel implements ActionListener{
	
	public static final String CREDITS = "Credits";
	public static final String CLEAR = "Clear";
	
	private MainWindow main;
	private JScrollPane listScroll;
	private JList finalProductionsList;
	private DefaultListModel listModel;
	private JButton butClear;
	private JButton butCredits;
	

	public AnswerPanel(MainWindow mainWindow) {
		//initialization of  all attributes
		main = mainWindow;
		listModel = new DefaultListModel();
		finalProductionsList = new JList(listModel);
		listScroll = new JScrollPane(finalProductionsList);
		butClear = new JButton("Clear");
		butClear.addActionListener(this);
		butClear.setActionCommand(CLEAR);
		butClear.setHorizontalAlignment(SwingConstants.CENTER);
		butCredits= new JButton("Credits");
		butCredits.addActionListener(this);
		butCredits.setActionCommand(CREDITS);
		butCredits.setHorizontalAlignment(SwingConstants.CENTER);
		
		// gui configuration
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("G'"));
		
		//Auxiliary panel 1
		JPanel auxPanel1 = new JPanel();
		auxPanel1.setLayout(new BorderLayout());
		auxPanel1.setPreferredSize(new Dimension(330,330));
		auxPanel1.add(listScroll,BorderLayout.CENTER);
		//Auxiliary panel 2
		JPanel auxPanel2 = new JPanel();
		auxPanel2.setLayout(new GridLayout(1,3));
		auxPanel2.setBorder(BorderFactory.createTitledBorder(""));
		auxPanel2.add(butClear);
		auxPanel2.add(new JLabel());
		auxPanel2.add(butCredits);
		
		//add the auxiliary panels
		this.add(auxPanel1,BorderLayout.CENTER);
		this.add(auxPanel2,BorderLayout.SOUTH);
		
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		String command= event.getActionCommand();
		if(command.equals(CLEAR)) {
			main.clearAll();
		}
		else if(command.equals(CREDITS)) {
			JOptionPane.showMessageDialog(main,"This program was created by:"+"\n" +"Alberto Alvarez Herrera"+"\n"
					+"Juan Sebastian Cardona" + "\n"+
						"Alexis Bonilla");
		}
	}


	public void getCredits() {
		JOptionPane.showMessageDialog(main,"This program was created by:"+"\n" +"Alberto Alvarez Herrera"+"\n"
				+"Juan Sebastian Cardona" + "\n"+
					"Alexis Bonilla");
		
	}


	public void clearAll() {
		finalProductionsList.removeAll();
		listModel.removeAllElements();
		
	}


	public void refreshList(ArrayList<String> productions) {
		finalProductionsList.removeAll();
		listModel.removeAllElements();
		for (int i = 0; i < productions.size(); i++) {
			listModel.addElement(productions.get(i));
		}
	}

}
