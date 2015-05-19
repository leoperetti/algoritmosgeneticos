 package TP1;
 import java.util.ArrayList;
import java.util.Random;
 //Creo la clase Prueba
 public class Prueba {
 
 	//Declaración de variables
 	static int pc = 75;
 	static int pm = 5;
 	static float[] sumaObjetivaXpoblacion = new float[1002];
 	static float[] maxCromoXpoblacion = new float[1002];
 	static float[] promXpoblacion = new float[1002];
 	static float[] minCromoXpoblacion = new float[1002];
 	static float[] valoresFitness = new float[10];
 	static float[] valoresFitnessNuevaPoblacion = new float[10];
 
 	
 	//Comienzo del programa
 	public static void main(String[] args)
 	{	 
 
 		String[][] cromosomas = new String[10][30];
 		int[] cromosomasDecimal = new int[10];
 		float[] valoresEnFuncion = new float[10];
 		float[] porcentajeFit = new float[10];
 		ArrayList<String> cro = new ArrayList<String>();
 		float sumatoriaObj;
 		float sumatoriaFit;
 		float maxObj;
		float minObj;
 		float maxFit;
 		float promeObj,promeFit;
 		Random rnd = new Random();
 		String[] maximosCromos = new String[2];
 		int m = 0;
 		
 		//Cargo la matriz cromosomas con numeros binarios
 		//Lo guardo en un ArrayList de cromosomas
 		//Pido el binario -> decimal y guardo 
 		//Obtengo valores de la Funcion Objetiva
 
 		for(int i=0;i<10;i++)
 		{
 			String c = "";
 			for(int j=0;j<30;j++)
 			{
 				cromosomas[i][j] = String.valueOf((int)(rnd.nextDouble()*2+0));
 				c = c+cromosomas[i][j];
 			}
 			cro.add(c);
 			cromosomasDecimal[i] = dameDecimal(c);
 		}
 
 		asignarValoresFuncion(cro,valoresEnFuncion);
 
 		//Obtengo sumatoria de la Funcion Objetiva
 
 		sumatoriaObj = sumatoriaObj(valoresEnFuncion);
 
 		//Guardo el arreglo de fittnes y su Sumatoria
 
 		sumatoriaFit = guardarFit(valoresEnFuncion, valoresFitness,sumatoriaObj);
 
 		//Obtengo maximo y minimo de la objetiva y fittnes
 		
 		float[] hola = valoresFitness.clone();
 		
 		maximosCromos = buscarMaximos(hola,cro,m); 		
 		maxFit = obtenerMaxFit(valoresFitness);
 		maxObj = obtenerMaxObj(valoresEnFuncion);
     	minObj = obtenerMinObj(valoresEnFuncion);
 
 		obtenerPorcentajes(porcentajeFit, valoresFitness);
 
 		promeObj=sumatoriaObj/10;
 		promeFit=sumatoriaFit/10;
 
 		//Muestro todo
 		System.out.println("\t\tPoblación inicial\n");
 		System.out.println("POBLACION (BINARIO)\t\tDECIMAL\tFUNCION OBJ\tFITNESS\tPORCENTAJE");
 		for(int i=0;i<10;i++)
 		{
 			for(int j=0;j<30;j++)
 			{
 				System.out.print(cromosomas[i][j]);
 			}
 			System.out.println(" "+cromosomasDecimal[i]+" "+valoresEnFuncion[i]+" "+valoresFitness[i]+" "+porcentajeFit[i]);
 
 		}
 		System.out.println("\n\tSumatoria del Fittnes:    " + Math.round(sumatoriaFit));
 		System.out.println("\tSumatoria de la Objetiva: " + (sumatoriaObj));
 		System.out.println("\tPromedio del Fitness:     " + (promeFit));
 		System.out.println("\tPromedio de la Objetiva:  " + (promeObj));
 		System.out.println("\tMáximo del Fitness:       " + (maxFit));
 		System.out.println("\tMáximo de la Objetiva:    " + (maxObj));
		System.out.println("\tMínimo de la Objetiva:    " + (minObj));
 		
 		sumaObjetivaXpoblacion[0] = sumatoriaObj;
 		maxCromoXpoblacion[0] = maxObj;
 		promXpoblacion[0] = promeObj;
    	minCromoXpoblacion[0] = minObj;
 		
		
				
 		for(int n=0, l=1;n<1000;n++,l++)
 		{
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
 			if(n!=0)
			{				
				System.out.println("\nVamos a crear a partir de esta población de los mejores hijos, una nueva población...");
			}
			else
 			{
				System.out.println("\nVamos a crear a partir de esta población inicial, una nueva población...");
 			}
 
 			// asignarValoresFuncion(cro);
 			ArrayList<String> nuevaPobla = new ArrayList<String>();
 			ArrayList<String> cromoMutados = new ArrayList<String>();
 			
 			System.out.println("Los maximos cromsomas de la poblacion son:");
 			for(int k=0;k<2;k++)
 			{
 				System.out.println(maximosCromos[k]);
 			}
 			
 			
 			for(int k=0;k<2;k++)
 			{
 				cromoMutados.add(maximosCromos[k]);
 			}
 
 			//Comenzamos ruleta
 			int min=0,max=0;
 			ArrayList<Integer> ruleta = new ArrayList<Integer>();
 			for(int i=0;i<10;i++)
 			{
 				int trunca = Math.round(porcentajeFit[i]);
 				if (trunca==0)
 				{
 					max=max+1;
 				}
 				else
 				{
 					max=max+trunca;
 				}
 
 				for (int j=min;j<(max);j++)
 				{                
 					ruleta.add(i);                           
 				}
 				min=max;
 			}
 
 
		
			System.out.println("\nCreamos la Ruleta:");
 			for(Integer nro : ruleta)
 			{
 				System.out.print(nro);
 			}
 			System.out.println("\nTamaño ruleta:"+ruleta.size());
 			System.out.println(" ");
 
 			String[] tiradas = new String[8];
 			String[] probCross = new String[4];
 
 
		
			System.out.println("Hace 10 tiradas a la ruleta:");
 			for(int i=0;i<8;i++)
 			{
 				tiradas[i] = String.valueOf((int)(rnd.nextDouble()*ruleta.size()+0));
 				System.out.print(tiradas[i]+" ");
 			}
 

			System.out.println("\n\nY tiramos 5 nros aleatorios que representan el porcentaje de CrossOver por cada par de cromosomas: ");
 			for(int j=0;j<4;j++)
 			{
 				probCross[j] = String.valueOf((int)(rnd.nextDouble()*100+1));
 				System.out.print(probCross[j]+" ");
 			}
 			System.out.println("\n");
 			
 			
 			System.out.println("Se van a cruzar los pares de cromosomas que no superaron el Porcentaje de CrossOver: "+pc+".\n");
 			int a=0,b=1;
 			for(int j=0;j<4;j++)
 			{  
 				int numeroTirada1 = Integer.valueOf(tiradas[a]);
 				int numeroTirada2 = Integer.valueOf(tiradas[b]);
 				int nro1 = ruleta.get(numeroTirada1);
 				int nro2 = ruleta.get(numeroTirada2); 
 
 				String cromo1 = cro.get((nro1));
 				String cromo2 = cro.get((nro2));
 
 
 				if(Integer.valueOf(probCross[j])<=pc)
 				{
 
 					System.out.println("Con un porcentaje de CrossOver de: "+probCross[j]);
 					System.out.println("En la ubicacion del arreglo de cromosomas: " + nro1 + " y " + nro2);
 					System.out.println("Los cromosomas: "+cromo1+" y "+cromo2);
 
 					int ubicacion = (int)(rnd.nextDouble()*30+0);
 
 
 					String cromoParte11 = cromo1.substring(0,ubicacion);
 					String cromoParte12 =cromo2.substring(0, ubicacion);
 
 					String cromoParte21 = cromo1.substring(ubicacion, 30);
 					String cromoParte22 = cromo2.substring(ubicacion,30);
 
 					String cromoFinal1 = cromoParte11+cromoParte22 ;
 					String cromoFinal2 = cromoParte12+cromoParte21 ;
 
 					System.out.println("Los dos cromosomas se cortan en el punto "+ubicacion);
 					System.out.println("Quedando finalmente "+cromoFinal1+" y "+cromoFinal2+"\n");
 		
 					nuevaPobla.add(cromoFinal1);
 					nuevaPobla.add(cromoFinal2);
 				}
 				else
 				{
 					nuevaPobla.add(cromo1);
 					nuevaPobla.add(cromo2);
 				}
 				a=a+2;
 				b=b+2;
 			}
 
 			System.out.println("Entonces resultan estos hijos: ");
 			for(String c: nuevaPobla)
 			{
 				System.out.println(c);
 			}
 
 			System.out.println("\nAhora vamos a ver cuáles de estos hijos puede llegar a mutar (Porcentaje de mutación: "+pm+")...");
 			// Now, i´m going to create the mutation
 			for(int i=0;i<8;i++)
 			{
 				int nroRandom = (int)(rnd.nextDouble()*100+1);
 				System.out.println("Porcentaje de mutar del cromosoma hijo "+i+" : "+nroRandom );
 
 				if(nroRandom<=pm)
 				{
 					int ubicacion = (int)(rnd.nextDouble()*30+0);
 					String cromoMuta=nuevaPobla.get(i);
 					char[] cromoMu = cromoMuta.toCharArray();
 					
 					System.out.println("\nEl cromosoma hijo "+i+" va a mutar en el gen ubicado en la posición: "+ubicacion);
 					System.out.println("Antes:\n"+cromoMuta);
 					
 					if(cromoMuta.charAt(ubicacion) == '0')
 					{
 						cromoMu[ubicacion] = '1' ;
 					}
 					else
 					{
 						cromoMu[ubicacion]='0';
 					}
 					
 					cromoMuta= new String(cromoMu);
 					System.out.println("Después:\n"+cromoMuta+"\n");
 
 					cromoMutados.add(cromoMuta);
 				}
 
 				else {cromoMutados.add(nuevaPobla.get(i));}
 			}
 			cro.clear();
 			for(int i=0;i<10;i++)
 			{
 				cro.add(cromoMutados.get(i));
 			}
 
 			float[] valEnFunc = new float[10];
 			float[] porcFit = new float[10];
 			float sumaObj;
 			float sumaFit;
 			float maxO;
 			float maxF;
			float minO;
 			float promObj,promFit;
 
 			asignarValoresFuncion(cro,valEnFunc);
 			sumaObj = sumatoriaObj(valEnFunc);
 			sumaFit = guardarFit(valEnFunc, valoresFitnessNuevaPoblacion,sumaObj);
 			maxO = obtenerMaxObj(valEnFunc);
 			maxF = obtenerMaxFit(valoresFitnessNuevaPoblacion);
			minO = obtenerMinObj(valEnFunc);
 			obtenerPorcentajes(porcFit, valoresFitnessNuevaPoblacion);
 			porcentajeFit = porcFit;
 			promObj=sumaObj/10;
 			promFit=sumaFit/10;
 
 			System.out.println("\nFinalmente, la POBLACIÓN "+(n+1)+" RESULTANTE es: \n");
 
 			System.out.println("POBLACION (BINARIO)\t\tDECIMAL\tFUNCION OBJ\tFITNESS\tPORCENTAJE");
 			for(int i=0;i<10;i++)
 			{
 				System.out.println(cro.get(i)+" "+dameDecimal(cro.get(i))+" "+valEnFunc[i]+" "+valoresFitnessNuevaPoblacion[i]+" "+porcentajeFit[i]);
 			}
 
 			System.out.println("\n\tSumatoria del Fittnes:    " + Math.round(sumaFit));
 			System.out.println("\tSumatoria de la Objetiva: " + (sumaObj));
 			System.out.println("\tPromedio del Fitness:     " + (promFit));
 			System.out.println("\tPromedio de la Objetiva:  " + (promObj));
 			System.out.println("\tMáximo del Fitness:       " + (maxF));
 			System.out.println("\tMáximo de la Objetiva:    " + (maxO));
			System.out.println("\tMínimo de la Objetiva:    " + (minO));
 
 			sumaObjetivaXpoblacion[l] = sumaObj;
 			maxCromoXpoblacion[l] = maxO;
 			promXpoblacion[l] = promObj;
			minCromoXpoblacion[l] = minO;
			hola = valoresFitnessNuevaPoblacion.clone();
			m+=1;
			maximosCromos = buscarMaximos(hola,cro,m);
 
 		}
 		
 		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		System.out.println("\nPoblación"+"\t"+"Sumatoria F. Objetiva"+"\t"+"Max F.Objetiva"+"\t"+"Min F.Objetiva"+"\t"+"Promedio F. Objetivo");
 		for(int i=0;i<1001;i++)
 		{

			System.out.println(i+"\t\t"+ponerComa(sumaObjetivaXpoblacion[i])+"\t\t"+ponerComa(maxCromoXpoblacion[i])+"\t"+ponerComa(minCromoXpoblacion[i])+"\t\t"+ponerComa(promXpoblacion[i]));
 		}
 
 	}
 
	private static String[] buscarMaximos(float[] val, ArrayList<String> cro, int m) {
		float[] maximos = new float[2];
		String[] maxis = new String[2];
		float[] fitnessOrdenado = ordenarMenorAMayor(val);
		maximos[0] = fitnessOrdenado[9];
		maximos[1] = fitnessOrdenado[8];
		
		if(m==0)
		{
			for(int k=0;k<2;k++)
			{
				for(int l=0;l<10;l++)
				{
					if(valoresFitness[l]==maximos[k])
					{
						maxis[k]=cro.get(l);
					}

				}
			}
		}
		else
		{
			for(int k=0;k<2;k++)
			{
				for(int l=0;l<10;l++)
				{
					if(valoresFitnessNuevaPoblacion[l]==maximos[k])
					{
						maxis[k]=cro.get(l);
					}

				}
			}
		}
		return maxis;
	}
	//Ordeno de menor a mayor el arreglo valoresFitness
	private static float[] ordenarMenorAMayor(float[] vl) {
		float[] fit = vl;
		for(int k=0;k<10;k++) {
            for(int f=0;f<9-k;f++) {
                if (fit[f]>fit[f+1]) {
                    float aux;
                    aux=fit[f];
                    fit[f]=fit[f+1];
                    fit[f+1]=aux;
                }
            }
        }
		return fit;
	}

	private static float obtenerMinObj(float[] valoresEnFuncion) {
		float minObj = 10;
		for(int i=0;i<10;i++)
		{
			if(minObj>valoresEnFuncion[i])
			{
				minObj=valoresEnFuncion[i];
			}			

		}
		return minObj;
	}

 	private static void obtenerPorcentajes(float[] porcentajeFit,
 			float[] valoresFitness) {
 		for(int i=0;i<10;i++){
 
 			porcentajeFit[i]=(valoresFitness[i])*100;
 		}
 	}
 
 	private static float obtenerMaxFit(float[] valoresFitness) {
 		float maxFit=0;		
 		for(int i=0;i<10;i++)
 		{
 			if(maxFit<valoresFitness[i]){maxFit=valoresFitness[i];};
 		}
 		return maxFit;
 	}
 
 	private static float obtenerMaxObj(float[] valoresEnFuncion)
 	{
 		float maxObj = 0;
 		for(int i=0;i<10;i++)
 		{
 			if(maxObj<valoresEnFuncion[i])
 			{
 				maxObj=valoresEnFuncion[i];
 			}			
 
 		}
 
 		return maxObj;
 	}
 
 	private static float guardarFit(float[] valoresEnFuncion,
 			float[] valoresFitness, float sumatoriaObj) {
 		float sumatoriaFit = 0;
 		for(int i=0;i<10;i++)
 		{ 
 			valoresFitness[i] = valoresEnFuncion[i]/sumatoriaObj;
 			sumatoriaFit += valoresFitness[i];
 		}
 
 		return sumatoriaFit;
 	}
 
 	private static float sumatoriaObj(float[] valoresEnFuncion) {
 		float sumatoriaObj = 0;
 		for(int i=0;i<10;i++)
 		{ 
 			sumatoriaObj = sumatoriaObj + valoresEnFuncion[i];
 		}
 		return sumatoriaObj;
 	}
 
 	private static void asignarValoresFuncion(ArrayList<String> c,float[] valoresEnFuncion) {
 		for(int i=0;i<10;i++)
 		{
 			valoresEnFuncion[i] = funcion(dameDecimal(c.get(i).toString()));
 		}
 	}
 
 	//Funcion que recibe un objeto cromosoma devuelve un decimal
 
 	private static int dameDecimal(String cromos2) {
 		String nroBinario = cromos2.toString();
 		int num = Integer.parseInt(nroBinario,2);
 		return num;
 	}
 
 	//Funcion que recibe el numero decimal corresp al cromosoma 
 	//y devuelve el valor dado por la Funcion Objetiva
 
 	private static float funcion(int nro){
 		int coef = (int)Math.pow(2,30) - 1;
 		float rdo = 0;
 		float div = (float)nro/(float)coef;
 		rdo = (float)Math.pow(div, 2);		
 		return rdo;	
 	}
 	
 	private static String ponerComa(float nro)
 	{
 			char[] valor = String.valueOf(nro).toCharArray();
 			valor[1] = ',';
			return new String(valor);
 	}
 }