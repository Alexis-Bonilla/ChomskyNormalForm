package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class terminales extends chomsky {

	@Override
	public void  convertir_GIC() {
		

		ArrayList<String> terminales = new ArrayList<String>();
		String patron = "[" + String.valueOf(gic_inicial.getTerminales()) + "]";

		for (String variable_llave : gic_inicial.getProducciones().keySet()) {

			ArrayList<String> temporal = gic_inicial.obtener_producciones(variable_llave);
			boolean terminal = false;

			for (int i = 0; i < temporal.size() && !terminal; i++) {

				String produccion = temporal.get(i);
				if (Pattern.matches(patron + "*", produccion)) {
					terminales.add(variable_llave);
					terminal = true;
				}

			}

		}

		boolean agregue = false;
		do {
			agregue = false;
			for (String variable_llave : gic_inicial.getProducciones().keySet()) {

				if (!terminales.contains(variable_llave)) {

					ArrayList<String> temporal = gic_inicial.obtener_producciones(variable_llave);
					boolean terminal = false;

					for (int i = 0; i < temporal.size() && !terminal; i++) {

						String produccion = temporal.get(i);
						String nuevo_patron = "[" + String.valueOf(gic_inicial.getTerminales())
								+ String.valueOf(terminales) + "]*";
						if (Pattern.matches(nuevo_patron, produccion)) {
							terminales.add(variable_llave);
							terminal = true;
							agregue = true;
						}

					}

				}

			}

		} while (agregue);

		eliminar_variables_no_terminales(terminales);

		
	}

	public void eliminar_variables_no_terminales(ArrayList<String> terminales) {

		ArrayList<String> no_terminales = new ArrayList<String>();
		for (int i = 0; i < gic_inicial.getVariables().length; i++) {
			String variable = gic_inicial.getVariables()[i]+"";
			if (!terminales.contains(variable)) {
				no_terminales.add(variable);
				gic_inicial.getProducciones().remove(variable);
			}
		}

		for (String variable_llave : gic_inicial.getProducciones().keySet()) {

			for (int i = 0; i < gic_inicial.obtener_producciones(variable_llave).size(); i++) {

				String produccion = gic_inicial.obtener_producciones(variable_llave).get(i);

				for (int j = 0; j < no_terminales.size(); j++) {

					if (produccion.contains(no_terminales.get(j))) {
						gic_inicial.obtener_producciones(variable_llave).remove(produccion);
					}
					

				}

			}
		}

	}



}
