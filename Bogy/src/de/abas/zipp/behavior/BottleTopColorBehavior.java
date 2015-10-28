package de.abas.zipp.behavior;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class BottleTopColorBehavior implements Behavior {

	
	public static EV3ColorSensor colorSensor;
	
	public BottleTopColorBehavior() {
		colorSensor = new EV3ColorSensor(SensorPort.S2);
	}
	
	SensorMode colorMode = colorSensor.getColorIDMode();
	float sample[] = new float[colorSensor.sampleSize()];
	
	@Override
	public boolean takeControl() {
		
		colorMode.fetchSample(sample, 0);
		System.out.println(sample[0]);
		
		if(sample[0] == 7.0){
			return false;
		} else{
			return true;
		}
		
	}

	@Override
	public void action() {
		if(sample[0] == 13.0){
			System.out.println("Cola");
		} else if(sample[0] == 6.0){
			System.out.println("Cola light");
		} else{
			System.out.println("Fehler");
		}

	}

	@Override
	public void suppress() {
		

	}

}
