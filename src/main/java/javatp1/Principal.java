package javatp1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Principal {

	public static void main(String[] args) {

		boolean primero = true;
		int puntaje = 0;

		try {
			/////////////////////////////////////////////////////////////////////////////////////////
			Path archPartidos = Paths.get(args[0]);

			List<String> lineasArch = Files.readAllLines(archPartidos);
			
			Map<Integer, String> repoPartido = new HashMap<Integer, String>();

			primero = true;
			for (String lineapar : lineasArch) {
				if (primero) {
					primero = false;
				} else {
					if (!lineapar.isBlank()) {	
						
						String[] pk = lineapar.split(",");
						Integer pkey = Integer.valueOf(pk[0]);

						repoPartido.put(pkey, lineapar);
					}
				}
			}

			/////////////////////////////////////////////////////////////////////////////////////////
			Path archPronosticos = Paths.get(args[1]);

			List<String> lineasArchPro = Files.readAllLines(archPronosticos);
			Map<Integer, String> repoPronostico = new HashMap<Integer, String>();

			primero = true;
			for (String lineapar : lineasArchPro) {
				if (primero) {
					primero = false;
				} else {
					if (!lineapar.isBlank()) {	
						
						String[] pk = lineapar.split(",");
						Integer pkey = Integer.valueOf(pk[0]);

						repoPronostico.put(pkey, lineapar);
					}
				}
			}
		
			/////////////////////////////////////////////////////////////////////////////////////////
			for (Map.Entry<Integer,String> lineaPartido: repoPartido.entrySet()) {
				int keyPartido = lineaPartido.getKey();

				encuentro match = new encuentro(lineaPartido.getValue());
				int resultado = match.resultado() ;
					
				System.out.println(	"KeyPar = "     + lineaPartido.getKey() +
									", Partido    = " + lineaPartido.getValue() + " --> Resultado " + resultado );

				for (Map.Entry<Integer,String> lineaPronostico: repoPronostico.entrySet()) {
					int keyPronostico = lineaPronostico.getKey();

					encuentro bet = new encuentro(lineaPronostico.getValue());
					int resulpron = bet.resultado() ;
					
					if (keyPartido == keyPronostico) {
						System.out.println(	"KeyPro = " + lineaPronostico.getKey() 
											+ ", Pronostico = " + lineaPronostico.getValue() + " --> Pronosticado " + resulpron );
						System.out.println(	"" );

						if (resultado == resulpron) {
							puntaje ++ ;
						}
					}	
				}
			}

			System.out.println(	"PUNTAJE FINAL = " + puntaje ); 
			
			
		} catch (IOException e) {
			System.out.println("Fallo la apertura del archivos CVS");
			System.exit(1);
		}
	}
}
