package model.logic;

public class TravelTime implements Comparable<TravelTime> {

	private int trimester;
	private int sourceid;
	private int dstid;
	private int hod;
	private double mean;
	private double standard_deviation;
	
	public TravelTime(int pTrimester, int pSourceid, int pDstid, int pHod, double pMean, double pStandard) {
		trimester = pTrimester;
		sourceid = pSourceid;
		dstid = pDstid;
		hod = pHod;
		mean = pMean;
		standard_deviation = pStandard;
	}
	
	public int getTrimester(){
		return trimester;
	}
	
	public int getSourceid() {
		return sourceid;
	}
	
	public int getDstid() {
		return dstid;
	}
	
	public int getHod() {
		return hod;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getStandard_deviation() {
		return standard_deviation;
	}

	//TODO 4) :3
	public int compareTo(TravelTime viaje) {
		return (this.getMean()>viaje.getMean())? 1 : (this.getMean()<viaje.getMean())? -1 : 0;
	}	
}
