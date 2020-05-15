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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;



public class ListPanel extends JPanel implements ActionListener{
	
	public static final String DELETE_NOT_TERMINALS = "Delete not terminals";
	public static final String DELETE_NOT_REACHABLES = "Delete not reachables";
	public static final String DELETE_LAMBDA_PRODUCTIONS = "Delete lambda productions";
	public static final String DELETE_UNITARY_PRODUCTIONS = "Delete unitary productions";
	public static final String FINAL_FORM = "Final chomsky form";
	
	private MainWindow main;
	
	private JList initialProductionsList;
	private JScrollPane scrollList;
	private JButton butDeleteNotTerminals;
	private JButton butDeleteNotReachables;
	private JButton butDeleteLambdaProductions;
	private JButton butDeleteUnitaryProductions;
	private JButton butFinalForm;
	private DefaultListModel listModel;
	private JLabel labInfo;
	
	
	
	
	
	public ListPanel(MainWindow mainWindow) {
		//initialization of all attributes
		main= mainWindow;
		listModel = new DefaultListModel();
		initialProductionsList= new JList(listModel);
		scrollList= new JScrollPane();
		scrollList.setViewportView(initialProductionsList);
		scrollList.setAlignmentX(CENTER_ALIGNMENT);
		butDeleteNotTerminals = new JButton("Delete not terminals");
		butDeleteNotTerminals.addActionListener(this);
		butDeleteNotTerminals.setActionCommand(DELETE_NOT_TERMINALS);
		butDeleteNotTerminals.setHorizontalAlignment(SwingConstants.CENTER);
		butDeleteNotTerminals.setEnabled(false);
		butDeleteNotReachables = new JButton("Delete not reachables");
		butDeleteNotReachables.addActionListener(this);
		butDeleteNotReachables.setActionCommand(DELETE_NOT_REACHABLES);
		butDeleteNotReachables.setHorizontalAlignment(SwingConstants.CENTER);
		butDeleteNotReachables.setEnabled(false);
		butDeleteLambdaProductions = new JButton("Delete lambda");
		butDeleteLambdaProductions.addActionListener(this);
		butDeleteLambdaProductions.setActionCommand(DELETE_LAMBDA_PRODUCTIONS);
		butDeleteLambdaProductions.setHorizontalAlignment(SwingConstants.CENTER);
		butDeleteLambdaProductions.setEnabled(false);
		butDeleteUnitaryProductions = new JButton("Delete unitaries");
		butDeleteUnitaryProductions.addActionListener(this);
		butDeleteUnitaryProductions.setActionCommand(DELETE_UNITARY_PRODUCTIONS);
		butDeleteUnitaryProductions.setHorizontalAlignment(SwingConstants.CENTER);
		butDeleteUnitaryProductions.setEnabled(false);
		butFinalForm = new JButton("Final form");
		butFinalForm.addActionListener(this);
		butFinalForm.setActionCommand(FINAL_FORM);
		butFinalForm.setHorizontalAlignment(SwingConstants.CENTER);
		butFinalForm.setEnabled(false);
		labInfo = new JLabel("Press each button to modify G ");
		labInfo.setHorizontalAlignment(SwingConstants.CENTER);
	

		//configuration of the gui structure
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Productions of G"));
		this.setPreferredSize(new Dimension(460,105));
		
		//auxPanel1
		JPanel auxPanel1 = new JPanel();
		auxPanel1.setLayout(new BorderLayout());
		auxPanel1.setPreferredSize(new Dimension(230,0));
		auxPanel1.add(scrollList,BorderLayout.CENTER);
		
		//auxPanel2
		JPanel auxPanel2= new JPanel();
		auxPanel2.setBorder(BorderFactory.createTitledBorder(""));
		auxPanel2.setPreferredSize(new Dimension(230,0));
		auxPanel2.setLayout(new GridLayout(12,1));
		auxPanel2.add(labInfo);
		auxPanel2.add(new JLabel());
		auxPanel2.add(butDeleteNotTerminals);
		auxPanel2.add(new JLabel());
		auxPanel2.add(butDeleteNotReachables);
		auxPanel2.add(new JLabel());
		auxPanel2.add(butDeleteLambdaProductions);
		auxPanel2.add(new JLabel());
		auxPanel2.add(butDeleteUnitaryProductions);
		auxPanel2.add(new JLabel());
		auxPanel2.add(butFinalForm);
		auxPanel2.add(new JLabel());

		
		// add two auxiliary panels
		this.add(auxPanel1,BorderLayout.CENTER);
		this.add(auxPanel2,BorderLayout.EAST);
		
		
		
		
		
		
		
		
	}





	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(DELETE_NOT_TERMINALS)) {
			main.deleteNotTerminals();
			butDeleteNotTerminals.setEnabled(false);
			butDeleteNotReachables.setEnabled(true);
			
		}
		else if(command.equals(DELETE_NOT_REACHABLES)) {
			main.deleteNorReachables();
			butDeleteNotReachables.setEnabled(false);
			butDeleteLambdaProductions.setEnabled(true);
			
			
			
		}
		else if(command.equals(DELETE_LAMBDA_PRODUCTIONS)) {
			main.deleteLambdaProductions();
			butDeleteLambdaProductions.setEnabled(false);
			butDeleteUnitaryProductions.setEnabled(true);
			
		}
		else if(command.equals(DELETE_UNITARY_PRODUCTIONS)) {
			main.deleteUnitaryProductions();
			butDeleteUnitaryProductions.setEnabled(false);
			butFinalForm.setEnabled(true);
		}
		else if(command.equals(FINAL_FORM)) {
			main.finalForm();
			butFinalForm.setEnabled(false);
		}
		
		
	}


	public void refreshList(ArrayList<String> productions) {
		initialProductionsList.removeAll();
		listModel.removeAllElements();
		for (int i = 0; i < productions.size(); i++) {
			listModel.add(i,formatProduction(productions.get(i)));
		}
	}


	public void refreshList(String productions) {
		
			initialProductionsList.removeAll();
			listModel.removeAllElements();
		String[] prods = productions.split("#");
		for (int i = 0; i < prods.length; i++) {
			listModel.add(i,formatProduction(prods[i]));
		}
		
		
	}
	
	public String formatProduction(String production) {
		String[] symbols = production.split(",");
		String toReturn = symbols[0]+"-->";
		for (int i = 1; i < symbols.length; i++) {
			toReturn+=symbols[i]+"|";
		}
		return toReturn.substring(0,toReturn.length()-1);
	}





	public void clearAll() {
		initialProductionsList.removeAll();
		listModel.removeAllElements();
		
	}





	public void enableTerminalsButton() {
		butDeleteNotTerminals.setEnabled(true);
		
	}








}
