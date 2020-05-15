package model;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class produccionesUnitarias extends chomsky {

	@Override
	public void convertir_GIC() {
		

		LinkedHashMap<String, ArrayList<String>> unitarias = new LinkedHashMap<String, ArrayList<String>>();

		for (String variable : gic_inicial.getProducciones().keySet()) {

			unitarias.put(variable, new ArrayList<String>());
			unitarias.get(variable).add(variable);

			for (int i = 0; i < unitarias.get(variable).size(); i++) {

				ArrayList<String> producciones_variable = gic_inicial
						.obtener_producciones(unitarias.get(variable).get(i));

				for (int j = 0; j < producciones_variable.size(); j++) {

					String produccion = producciones_variable.get(j);
					String patron = "[" + String.valueOf(gic_inicial.getVariables()) + "]";

					if (produccion.length() == 1 && Pattern.matches(patron, produccion)
							&& !unitarias.get(variable).contains(produccion.charAt(0)+"")) {
						unitarias.get(variable).add(produccion);
					}
				}
			}

		}

		
		simular_producciones_unitarias(unitarias);
		
	}

	public void simular_producciones_unitarias(LinkedHashMap<String, ArrayList<String>> unitarias) {

		for (String variable_llave : gic_inicial.getProducciones().keySet()) {

			ArrayList<String> temporal = gic_inicial.obtener_producciones(variable_llave);

			ArrayList<String> unit = producciones_unitarias(variable_llave);

			for (int i = 0; i < temporal.size(); i++) {

				if (unitarias.get(variable_llave).contains(temporal.get(i))) {

					ArrayList<String> aux = producciones_unitarias(temporal.get(i));

					for (int j = 0; j < aux.size(); j++) {
						
						if(!unit.contains(aux.get(j))) {
							unit.add(aux.get(j));							
						}
					}

				}

			}
			
			gic_inicial.getProducciones().put(variable_llave, unit);

		}
	}

	public ArrayList<String> producciones_unitarias(String variable_llave) {

		ArrayList<String> producciones = (ArrayList<String>) gic_inicial.obtener_producciones(variable_llave).clone();

		for (int i = 0; i < producciones.size(); i++) {

			String produccion = producciones.get(i);

			if (Pattern.matches("[" + String.valueOf(gic_inicial.getVariables()) + "]{1}", produccion)) {
				producciones.remove(produccion);
			}
		}

		return producciones;
	}



}
