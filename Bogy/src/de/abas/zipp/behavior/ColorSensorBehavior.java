package de.abas.zipp.behavior;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class ColorSensorBehavior implements Behavior {
	
	public EV3ColorSensor colorSensor;
	
	public ColorSensorBehavior(){
		colorSensor = new EV3ColorSensor(SensorPort.S2);
	}
	@Override
	public boolean takeControl() {
		
		SensorMode colorMode = colorSensor.getColorIDMode();
		float sample[] = new float[colorSensor.sampleSize()];
		colorMode.fetchSample(sample, 0);
		System.out.println(sample[0]);
		
		if(sample[0] == 0.0){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void action() {
			
	}

	@Override
	public void suppress() {
	
		Motor.B.forward();
		Motor.C.forward();		
	}

}
