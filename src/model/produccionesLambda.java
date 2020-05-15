package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class produccionesLambda extends chomsky {

	@Override
	public void convertir_GIC() {

		ArrayList<String> anulables = new ArrayList<String>();
		String patron = "[/]";

		for (String variable : gic_inicial.getProducciones().keySet()) {

			ArrayList<String> temporal = gic_inicial.obtener_producciones(variable);
			boolean terminal = false;

			for (int i = 0; i < temporal.size() && !terminal; i++) {

				String produccion = temporal.get(i);
				if (Pattern.matches(patron + "*", produccion)) {
					anulables.add(variable);
					gic_inicial.getProducciones().get(variable).remove("/");
					terminal = true;
				}

			}

		}

		if (anulables.size() != 0) {
			boolean agregue = false;
			do {
				agregue = false;

				for (String variable : gic_inicial.getProducciones().keySet()) {

					if (!anulables.contains(variable)) {

						ArrayList<String> temporal = gic_inicial.obtener_producciones(variable);
						boolean anulable = false;

						for (int i = 0; i < temporal.size() && !anulable; i++) {

							String produccion = temporal.get(i);
							String nuevo_patron = String.valueOf(anulables) + "*";
							if (Pattern.matches(nuevo_patron, produccion)) {
								anulables.add(variable);
								anulable = true;
								agregue = true;
							}

						}

					}

				}

			} while (agregue);

			eliminar_producciones_lambda(anulables);
		}

	}

	public void eliminar_producciones_lambda(ArrayList<String> anulables) {

		for (String variable : gic_inicial.getProducciones().keySet()) {

			ArrayList<String> temporal = (ArrayList<String>) gic_inicial.obtener_producciones(variable).clone();

			for (int i = 0; i < temporal.size(); i++) {

				String produccion = temporal.get(i);
				ArrayList<String> array_produccion = new ArrayList<String>();
				ArrayList<String> array_produccion_copia = new ArrayList<String>();
				String posiciones = "";
				for (int j = 0; j < produccion.length(); j++) {
					array_produccion.add(produccion.toCharArray()[j] + "");
					array_produccion_copia.add(produccion.toCharArray()[j] + "");
					posiciones += j + "";
				}

				ArrayList<String> combinaciones = combinaciones_posibles(posiciones, new StringBuffer(), 0,
						new ArrayList<String>());

				for (int j = 0; j < combinaciones.size(); j++) {

					array_produccion = (ArrayList<String>) array_produccion_copia.clone();
					String combinacion = combinaciones.get(j);

					boolean modifico = false;
					for (int k = 0; k < combinacion.length(); k++) {

						int posicion = Integer.parseInt(combinacion.charAt(k) + "");
						if (Pattern.matches("[" + String.valueOf(anulables) + "]*", produccion.charAt(posicion) + "")) {
							array_produccion.remove(posicion);
							array_produccion.add(posicion, "");
							modifico = true;
						}
					}

					if (modifico) {
						String nueva_produccion = array_produccion.toString().replace("[", "").replace("]", "")
								.replace(", ", "");
						;

						if (!gic_inicial.getProducciones().get(variable).contains(nueva_produccion)) {

							if (nueva_produccion.isEmpty()) {
								if (variable.equals(gic_inicial.getVariable_Inicial())) {
									gic_inicial.getProducciones().get(variable).add("/");
								}
							} else {
								gic_inicial.getProducciones().get(variable).add(nueva_produccion);
							}

						}

					}

				}

			}

		}

	}

	public ArrayList combinaciones_posibles(String instr, StringBuffer outstr, int index,
			ArrayList<String> combinaciones) {
		for (int i = index; i < instr.length(); i++) {
			outstr.append(instr.charAt(i));
			combinaciones.add(outstr.toString());
			combinaciones_posibles(instr, outstr, i + 1, combinaciones);
			outstr.deleteCharAt(outstr.length() - 1);
		}

		return combinaciones;
	}



}
