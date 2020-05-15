package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class GIC {

	private char[] variables;
	private char[] terminales;
	private String variable_Inicial;

	private LinkedHashMap<String, ArrayList<String>> producciones;

	public GIC(String var, String ter, String var_inicial, String prod) {

		inicializar_variables(var);
		inicializar_terminales(ter);
		variable_Inicial = var_inicial;
		producciones = new LinkedHashMap<String, ArrayList<String>>();
		inicializar_producciones(prod);

	}

	public char[] getVariables() {
		return variables;
	}

	public void setVariables(char[] variables) {
		this.variables = variables;
	}

	public char[] getTerminales() {
		return terminales;
	}

	public void setTerminales(char[] terminales) {
		this.terminales = terminales;
	}

	public String getVariable_Inicial() {
		return variable_Inicial;
	}

	public void setVariable_Inicial(String variable_Inicial) {
		this.variable_Inicial = variable_Inicial;
	}

	public LinkedHashMap<String, ArrayList<String>> getProducciones() {
		return producciones;
	}

	public void setProducciones(LinkedHashMap<String, ArrayList<String>> producciones) {
		this.producciones = producciones;
	}

	public void inicializar_variables(String var) {
		variables = new char[var.length()];
		for (int i = 0; i < var.length(); i++) {
			variables[i] = var.charAt(i);
		}
	}

	public  void inicializar_terminales(String ter) {
		terminales = new char[ter.length()+1];
		terminales[0] = '/';
		for (int i = 1; i-1 < ter.length(); i++) {
			terminales[i] = ter.charAt(i-1);
		}
		
	}

	public void inicializar_producciones(String prod) {

		String[] Gramatica_temporal = prod.split("#");

		for (int i = 0; i < Gramatica_temporal.length; i++) {

			String[] datos = Gramatica_temporal[i].split(",");

			String variable = datos[0].charAt(0)+"";

			ArrayList<String> producciones_por_variable = new ArrayList<String>();

			for (int j = 1; j < datos.length; j++) {

				producciones_por_variable.add(datos[j]);

			}

			producciones.put(variable, producciones_por_variable);

		}

	}
	
	public ArrayList<String> obtener_producciones(String variable){
		return producciones.get(variable);
	}
	
	public  ArrayList<String> retornar_producciones(){
		
		ArrayList<String> produc = new ArrayList<String>();
		
		for (String variable_llave : producciones.keySet()) {
			String produciones_variable = variable_llave+"-->"+obtener_producciones(variable_llave).
					toString().replace("[", "").replace("]", "")
					.replace(", ", "|");
			
			produc.add(produciones_variable);
		}
		
		return produc;
	}
	

}
