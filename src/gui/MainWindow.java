package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.conversorFNC;



public class MainWindow extends JFrame {
	
	

	private conversorFNC model;
	private AddDataPanel dataPanel;
	private BannerPanel bannerPanel;
	private AnswerPanel answerPanel;
	private ListPanel listPanel;
	
	public MainWindow () {
		
		

		
		JPanel auxPanel1 = new JPanel();
		JPanel auxPanel2 = new JPanel();
		auxPanel1.setLayout(new BorderLayout());
		auxPanel2.setLayout(new BorderLayout());
		
		model= new conversorFNC();
		dataPanel = new AddDataPanel(this);
		bannerPanel = new BannerPanel(this);
		answerPanel = new AnswerPanel(this);
		listPanel = new ListPanel(this);
		this.setTitle("Chomsky normal form");
		this.setSize(new Dimension(800,600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(auxPanel1,BorderLayout.WEST);
		this.add(auxPanel2,BorderLayout.CENTER);
		auxPanel1.add(dataPanel,BorderLayout.NORTH);
		auxPanel1.add(listPanel,BorderLayout.CENTER);
		auxPanel2.add(bannerPanel,BorderLayout.NORTH);
		auxPanel2.add(answerPanel,BorderLayout.CENTER);
		
		
		
		
		
	}
	
	public static void main(String[]args) {
		MainWindow mw = new MainWindow();
		mw.setVisible(true);

		
	}




	public void deleteNotTerminals() {
		model.terminales();
		answerPanel.refreshList(model.getGic_inicial().retornar_producciones());
		
	}

	public void deleteNorReachables() {
		listPanel.refreshList(model.getGic_inicial().retornar_producciones());
		model.alcanzables();
		answerPanel.refreshList(model.getGic_inicial().retornar_producciones());
		
		
		
	}

	public void deleteLambdaProductions() {
		listPanel.refreshList(model.getGic_inicial().retornar_producciones());
		model.produccionesLambda();
		answerPanel.refreshList(model.getGic_inicial().retornar_producciones());
	}

	public void deleteUnitaryProductions() {
		listPanel.refreshList(model.getGic_inicial().retornar_producciones());
		model.produccionesUnitarias();
		answerPanel.refreshList(model.getGic_inicial().retornar_producciones());
		
	}

	public void finalForm() {
		listPanel.refreshList(model.getGic_inicial().retornar_producciones());
		model.produccionesChomsky();
		answerPanel.refreshList(model.getGic_inicial().retornar_producciones());
		
	}

	public void clearAll() {
		model = new conversorFNC();
		dataPanel.isAllEnabled(true);
		listPanel.clearAll();
		answerPanel.clearAll();
	}


	public void createOCL(String productions, String variables, String terminables) {
		String initialVar=variables.split("")[0];
		model.crear_GIC_inicial(variables, terminables, initialVar, productions);
		listPanel.refreshList(productions);
		listPanel.enableTerminalsButton();
		
		
	}
	
	
	
	
	
	
}
