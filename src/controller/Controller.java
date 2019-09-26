package controller;

import java.util.Scanner;

import model.data_structures.MaxColaCp;
import model.data_structures.MaxHeapCP;
import model.logic.MVCModelo;
import model.logic.TravelTime;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		int[] dato = new int[3];
		dato[0] = 0; dato[1] = 0; dato[2] = 0; 

		while( !fin ){
			System.out.println("\nDe el numero de viajes que desea ver: ");
			System.out.println("Escriba -1 para salir");
			dato[0] = lector.nextInt();
			if(dato[0] != -1){
				
				System.out.println("\nDe la hora inicial para los viajes: ");
				dato[1] = lector.nextInt();
				System.out.println("\nDe la hora final para los viajes: ");
				dato[2] = lector.nextInt();
				
				long startTime = System.currentTimeMillis(); // medición tiempo actual
				MaxHeapCP<TravelTime> heap = modelo.crearMaxHeapCP(dato[0], dato[1], dato[2]);
				long endTime = System.currentTimeMillis(); // medición tiempo actual
				long duration = endTime - startTime; // duracion de ejecucion del algoritmo
				view.printMessage("Tiempo de ejecucion crearMaxHeapCP: " + duration + " milisegundos");
				 
				startTime = System.currentTimeMillis(); // medición tiempo actual
				MaxColaCp<TravelTime> cola = modelo.crearMaxColaCP(dato[0], dato[1], dato[2]);
				endTime = System.currentTimeMillis(); // medición tiempo actual
				duration = endTime - startTime; // duracion de ejecucion del algoritmo
				view.printMessage("Tiempo de ejecucion crearMaxColaCP: " + duration + " milisegundos");
				
				
			}else{
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
			}		
		}
	}
	
}
