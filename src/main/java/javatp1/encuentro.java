package javatp1;

public class encuentro {
	
	private String linea;
	
	public encuentro (String lin) {
		this.linea = lin;
	}

	public int resultado() {	
		String[] alinea = linea.split(",") ;
		int numero = Integer.parseInt(alinea[0]);
		String local = alinea[1];				
		String visita = alinea[2];
		int tantos1 = Integer.parseInt(alinea[3]) ;	
		int tantos2 = Integer.parseInt(alinea[4]);			//]Integer.valueOf(alinea[4]);
		
		Equipo equipo1 = new Equipo(local) ;
		Equipo equipo2 = new Equipo(visita);
		Goles score = new Goles(tantos1, tantos2);

		Partido picado1 = new Partido();
		
		picado1.setNumero(numero);
		picado1.setNombre1(equipo1);
		picado1.setNombre2(equipo2);
		picado1.setGoles(score);

		return picado1.getresultado();
	}
}
