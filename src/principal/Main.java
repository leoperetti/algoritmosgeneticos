package principal;

import java.util.Random;

public class Main {

	static public String jugadores[][] = new String[2][16];
	static public String opciones[][] = {{"AA","4"},{"AN","0"},{"NA","5"},{"NN","1"}};
	static public int valoresXJugador[][] = new int[2][16];
	static public int sumatoria[] = new int[2];
	static public String conjuntoCombinaciones[] = 
		{ "AAAA", "NAAA", "ANAA", "AANA", "AAAN", "NNAA", "AANN", "NANA", 
		  "NAAN", "NNAN", "NNNA", "ANAN", "NNNN", "ANNN", "ANNA", "NANN" };
	
	// comparar por combinacion y por jugador: sumar a�os para cada jugada y a la vez restar contra la misma jugada del jugador dos
	// la idea es que haya la menor diferencia posible y la menor cantidad de a�os para cada jugador
	
	public static void main(String[] args) {
		
		primerDecision();
		rellenoCombinaciones();
		sumaValores();
		
		System.out.println("Jugador 1"+"\t\t"+"Jugador 2");		
		for(int i=0;i<16;i++)
			{
				System.out.println(jugadores[0][i]+" "+valoresXJugador[0][i]+"\t\t"+jugadores[1][i]+" "+valoresXJugador[1][i]);
			}
			System.out.println(" ");
		for(int m = 0; m<2; m++)
		{
			System.out.println(sumatoria[m]);
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
	
	for(int k=0;k<2;k++)
	{
		int cont = 0;
		for(int i=0;i<16;i++)
		{
			int sum = 0;
			String par;
			int a=0,b=2;
			for(int j=0;j<3;j++)
			{
				par = jugadores[k][i].substring(a, b);
				sum += buscarValorPar(par);
				a += 2;
				b += 2;
			}
			valoresXJugador[k][i] = sum;
		}
		for(int j = 0; j<16; j++)
		{
			cont = cont + valoresXJugador[k][j];
		}
		sumatoria[k] = cont;
	}
	
		
	}

	private static int buscarValorPar(String par) {
		int valor = 0;
		for(int i = 0; i<4; i++)
		{			
			if(opciones[i][0].equals(par))
				{
					valor = Integer.parseInt(opciones[i][1]);
					break;
				}
		}
		return valor;
	}
}
