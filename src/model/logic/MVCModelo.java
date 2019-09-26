package model.logic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;

import model.data_structures.MaxColaCp;
import model.data_structures.MaxHeapCP;

public class MVCModelo {
	
	MaxColaCp<TravelTime> cola;
	MaxHeapCP<TravelTime> heap;
	MaxColaCp<TravelTime> muestraCola;
	MaxHeapCP<TravelTime> muestraHeap;
	
	public MVCModelo() {
		cola = new MaxColaCp<>();
		heap = new MaxHeapCP<>();
		muestraCola = new MaxColaCp<>();
		muestraHeap = new MaxHeapCP<>();
	}
	
	//TODO 5) generar muestra 
		public void generarMuestra(int num){
			
			int ram = 0;
			Iterator<TravelTime> iteratorCola = cola.iterator();
			Iterator<TravelTime> iteratorHeap = heap.iterator();
			
			for(int i=0; i<num; i++){
				iteratorCola = cola.iterator();
				ram = (int) (Math.random() * cola.size()) + 1;
				for(int j=0; j < ram; j++){
					iteratorCola.next();
					iteratorHeap.next();
				}
				muestraCola.insert(iteratorCola.next());
				muestraHeap.insert(iteratorHeap.next());
			}
			
		}
		
	//TODO 9) los N tiempos de viajes promedio más largos 
	public MaxColaCp<TravelTime> crearMaxColaCP(int num, int ini,int fin){
		MaxColaCp<TravelTime> ans = new MaxColaCp<TravelTime>();
		
		muestraCola = cola;
		TravelTime travel;
		int i=0;
		
		while(i!=num &&!muestraCola.isEmpty()){
			 travel = muestraCola.delMax();
			if(travel.getHod() >= ini && travel.getHod()<=fin){
				ans.insert(travel);
				i++;
			}
		}
		
		return ans;
	}
	
	public MaxHeapCP<TravelTime> crearMaxHeapCP(int num, int ini,int fin){
		MaxHeapCP<TravelTime> ans = new MaxHeapCP<TravelTime>();
		muestraHeap = heap;
		TravelTime travel;
		int i=0;
		
		while(i!=num &&!muestraHeap.isEmpty()){
			 travel = muestraHeap.delMax();
			if(travel.getHod() >= ini && travel.getHod()<=fin){
				ans.insert(travel);
				i++;
			}
		}
		
		
		return ans;
	}
	
	//TODO 2) LoadTravelTimes
	public void loadTravelTimes(){
		CSVReader reader1 = null;
		CSVReader reader2 = null;
		
		try{
			String[] nextLine;
			
			reader1 = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));
			reader2 = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));
			
			reader1.readNext();
			reader2.readNext();
			
			while ((nextLine = reader1.readNext()) != null) {
				TravelTime temp = createTravelTime(nextLine, 1);
				cola.insert(temp);
				heap.insert(temp);
			}
			
			while ((nextLine = reader2.readNext()) != null) {
				TravelTime temp = createTravelTime(nextLine, 2);
				cola.insert(temp);
				heap.insert(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
		//metodo para que la carga sea mas comoda
	private TravelTime createTravelTime(String[] datos, int pTrimester) {
			
		int sourceid = Integer.parseInt(datos[0]);
		int dstid = Integer.parseInt(datos[1]);
		int hod = Integer.parseInt(datos[2]);
		double mean = Double.parseDouble(datos[3]);
		double standard_deviation = Double.parseDouble(datos[4]);
			
		return new TravelTime(pTrimester, sourceid, dstid, hod, mean, standard_deviation);
	}

}
