package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class produccionesChomsky extends chomsky {

	ArrayList<String> nuevas_variables = new ArrayList<String>();

	@Override
	public void convertir_GIC() {

		variables_para_terminales();

		producciones_forma_chomsky();

	}

	public void variables_para_terminales() {
		String patron = "[" + String.valueOf(gic_inicial.getTerminales()) + "]" + "|" + "["
				+ String.valueOf(gic_inicial.getVariables()) + "]{2}";

		LinkedHashMap<String, ArrayList<String>> producciones = (LinkedHashMap<String, ArrayList<String>>) gic_inicial
				.getProducciones().clone();

		for (String variable : gic_inicial.getProducciones().keySet()) {

			ArrayList<String> producciones_variable = gic_inicial.obtener_producciones(variable);

			ArrayList<String> nuevas_producciones_variable = new ArrayList<String>();

			for (int i = 0; i < producciones_variable.size(); i++) {

				String produccion = producciones_variable.get(i);

				for (int j = 0; j < gic_inicial.getTerminales().length && produccion.length() > 1; j++) {

					String terminal = gic_inicial.getTerminales()[j] + "";

					if (produccion.contains(terminal)) {
						produccion = produccion.replace(terminal, "T" + terminal);

						producciones.put("T" + terminal, new ArrayList<String>());

						producciones.get("T" + terminal).add(terminal);

						if (!nuevas_variables.contains("T" + terminal)) {

							nuevas_variables.add("T" + terminal);
						}
					}
				}

				nuevas_producciones_variable.add(produccion);
			}

			producciones.put(variable, nuevas_producciones_variable);
		}

		gic_inicial.setProducciones(producciones);
	}

	public void producciones_forma_chomsky() {
		ArrayList<String> n_p = new ArrayList<String>();
		LinkedHashMap<String, ArrayList<String>> p = (LinkedHashMap<String, ArrayList<String>>) gic_inicial
				.getProducciones().clone();
		int contador = 1;
		for (String variable : gic_inicial.getProducciones().keySet()) {

			ArrayList<String> producciones_variable = gic_inicial.obtener_producciones(variable);

			n_p = new ArrayList<String>();
			for (int i = 0; i < producciones_variable.size(); i++) {

				String produccion = producciones_variable.get(i);

				if (produccion.length() > 2) {

					if (!(produccion.length() == 3 && produccion.contains("T"))) {

						String pa = nuevas_variables.toString().replace(", ", "");
						boolean f = Pattern.matches(pa + "*", produccion);
						if (!(produccion.length() == 4 && f)) {

							if (produccion.startsWith("T")) {

								n_p.add(produccion.charAt(0) + "" + produccion.charAt(1) + "" + "T" + contador);
								p.put("T" + contador, new ArrayList<String>());
								p.get("T" + contador).add(produccion.substring(2, produccion.length()));
								nuevas_variables.add("T" + contador);
								contador++;
							} else {

								n_p.add(produccion.charAt(0) + "T" + contador);
								p.put("T" + contador, new ArrayList<String>());
								p.get("T" + contador).add(produccion.substring(1, produccion.length()));
								nuevas_variables.add("T" + contador);
								contador++;

							}

						} else {
							n_p.add(produccion);
						}

					} else {
						n_p.add(produccion);
					}

				} else {
					n_p.add(produccion);
				}

			}

			p.put(variable, n_p);
		}

		gic_inicial.setProducciones(p);
		eliminar_identicas();
	}

	public void eliminar_identicas() {

		
		ArrayList<String> copia_nuevas_variables = (ArrayList<String>) nuevas_variables.clone();
		for (int i = 0, j = i + 1; i < nuevas_variables.size() && j < nuevas_variables.size();) {

			String prod_1 = gic_inicial.obtener_producciones(nuevas_variables.get(i)).get(0);
			String prod_2 = gic_inicial.obtener_producciones(nuevas_variables.get(j)).get(0);

			if (prod_1.equals(prod_2)) {
				
				gic_inicial.getProducciones().remove(nuevas_variables.get(j));
				
				for (String variable : gic_inicial.getProducciones().keySet()) {
					
					
					ArrayList<String> producciones_variable = gic_inicial.obtener_producciones(variable);
					
					for (int k = 0; k < producciones_variable.size(); k++) {
						
						String prod = producciones_variable.get(k);
						
						if(prod.contains(nuevas_variables.get(j))) {
							
							String nueva_produccion = prod.replace(nuevas_variables.get(j), nuevas_variables.get(i));
							
							gic_inicial.getProducciones().get(variable).remove(prod);
							gic_inicial.getProducciones().get(variable).add(nueva_produccion);
							copia_nuevas_variables.remove(nuevas_variables.get(j));
							
						}
						
					}

				}
				 
				
			}

			if (!(j < nuevas_variables.size()-1 )) {
				i++;
				j = i + 1;
			} else {
				j++;
			}

			nuevas_variables= (ArrayList<String>) copia_nuevas_variables.clone();
			
			
		}
		
		
	}



}
