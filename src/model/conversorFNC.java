package model;

public class conversorFNC extends chomsky {

	
	

	
	public conversorFNC() {
		
		
	}
	
	
	
	public void crear_GIC_inicial(String var, String ter, String var_inicial, String prod) {
		
		
		GIC nueva = new GIC(var, ter, var_inicial, prod);
		
		super.setGic_inicial(nueva);
		
	
	}
	
	
	public void terminales() {
		terminales terminales = new terminales();
		terminales.setGic_inicial(gic_inicial);
		terminales.convertir_GIC();
	}
	
	public void alcanzables() {
		alcanzables alcanzables = new alcanzables();
		alcanzables.setGic_inicial(gic_inicial);
		alcanzables.convertir_GIC();
	}
	
	
	public void produccionesLambda() {
		produccionesLambda produccionesLambda = new produccionesLambda();
		produccionesLambda.setGic_inicial(gic_inicial);
		produccionesLambda.convertir_GIC();
	}
	
	
	public void produccionesUnitarias() {
		produccionesUnitarias produccionesUnitarias = new produccionesUnitarias();
		produccionesUnitarias.setGic_inicial(gic_inicial);
		produccionesUnitarias.convertir_GIC();
	}
	
	public void produccionesChomsky() {
		produccionesChomsky produccionesChomsky = new produccionesChomsky();
		produccionesChomsky.setGic_inicial(gic_inicial);
		produccionesChomsky.convertir_GIC();
	}
	
	


	@Override
	public void convertir_GIC() {
		
		terminales();
		alcanzables();
		produccionesLambda();
		produccionesUnitarias();
		produccionesChomsky();
		
	}
	
	

	
}
