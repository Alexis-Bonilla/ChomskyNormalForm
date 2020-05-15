package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class alcanzables extends chomsky {

	@Override
	public void convertir_GIC() {
		

		ArrayList<String> alcanzables = new ArrayList<String>();

		alcanzables.add(gic_inicial.getVariable_Inicial());

		for (int i = 0; i < alcanzables.size(); i++) {

			String variable = alcanzables.get(i);
			ArrayList<String> temporal = gic_inicial.obtener_producciones(variable);

			for (int j = 0; j < temporal.size(); j++) {

				String produccion = temporal.get(j);
				for (int k = 0; k < produccion.length(); k++) {

					String caracter_producido = produccion.charAt(k)+"";
					if (Pattern.matches("[" + String.valueOf(gic_inicial.getVariables()) + "]", caracter_producido + "")
							&& !alcanzables.contains(caracter_producido)) {
						alcanzables.add(caracter_producido);
					}
				}
			}
		}

		eliminar_variables_no_alcanzables(alcanzables);
		
		
	}

	
	public void eliminar_variables_no_alcanzables(ArrayList<String> alcanzables) {
		
		
		for (int i = 0; i < gic_inicial.getVariables().length; i++) {
			String variable = gic_inicial.getVariables()[i]+"";
			if (!alcanzables.contains(variable)) {
				gic_inicial.getProducciones().remove(variable);
			}
		}
		
		
	}
	


}
