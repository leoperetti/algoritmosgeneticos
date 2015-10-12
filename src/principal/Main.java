package principal;

import java.util.Random;

public class Main {

	static public String jugadores[][] = new String[2][16];
	static public String opciones[][] = {{"AA","4"},{"AN","0"},{"NA","5"},{"NN","1"}};
	
	static public String conjuntoCombinaciones[] = 
		{ "AAAA", "NAAA", "ANAA", "AANA", "AAAN", "NNAA", "AANN", "NANA", 
		  "NAAN", "NNAN", "NNNA", "ANAN", "NNNN", "ANNN", "ANNA", "NANN" };
	
	// comparar por combinacion y por jugador: sumar años para cada jugada y a la vez restar contra la misma jugada del jugador dos
	// la idea es que haya la menor diferencia posible y la menor cantidad de años para cada jugador
	
	public static void main(String[] args) {
		
		primerDecision();
		rellenoCombinaciones();
		for(int j=0;j<2;j++)
		{
			for(int i=0;i<16;i++)
			{
				System.out.println(jugadores[j][i]);
			}
			System.out.println(" ");
		}
		
		
	}

	private static void primerDecision() {
		int num;
		Random rdm = new Random();
		for(int j=0;j<2;j++)
		{
			num = (int)(rdm.nextDouble() * 4);
			for(int i=0;i<16;i++)
			{
				jugadores[j][i] = opciones[num][0];
			}
		}
	}
	
	private static void rellenoCombinaciones() {
		Random rdm = new Random();
		for(int j=0;j<2;j++)
		{
			int num, cont=0;
			int numRepetidos[] = new int[16];
			do
			{
				num = (int)(rdm.nextDouble() * 16);
				if(!(esRepetido(numRepetidos, num+1)))
				{
					jugadores[j][cont] += conjuntoCombinaciones[num];
					numRepetidos[cont] = num+1;
					cont += 1;
				}		
			}while (cont<16);
		}
		
	}
	
	private static boolean esRepetido(int[] numerosRep, int numero) {
		
		boolean esRepetido = false;
		for(int i=0;i < numerosRep.length;i++)
		{
			if(numerosRep[i] != numero)
			{
				esRepetido = false;
			}
			else
			{
				esRepetido = true;
				break;
			}
		}
		return esRepetido;
	}
	
	private static void sumaValores() {
		
		
		
	}
}
