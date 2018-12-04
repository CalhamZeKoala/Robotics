import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;

public class CurrentSample {
	
	private float[] sample;
	
	private Arbitrator sort;
	
	EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);

	public void updateDistance(){
		SampleProvider sp = us.getDistanceMode();
		float[] sample = new float[1];
		sp.fetchSample(sample, 0);
		this.sample = sample;
	}
	
	public float getDistance() {
		updateDistance();
		return this.sample[0];
	}
	
	public void updateSortArbitrator(Arbitrator sort) {
		this.sort = sort;
	}
	
	public Arbitrator getSort() {
		return this.sort;
	}
}