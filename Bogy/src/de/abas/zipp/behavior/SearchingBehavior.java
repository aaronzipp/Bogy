package de.abas.zipp.behavior;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class SearchingBehavior implements Behavior {

	int motorDelay = 500;
	
	public EV3ColorSensor colorSensor;
	
	public SearchingBehavior(){
		colorSensor = new EV3ColorSensor(SensorPort.S2);	
	}
	
	@Override
	public boolean takeControl() {
		
		
		SensorMode colorMode = colorSensor.getColorIDMode();
		float sample[] = new float[colorSensor.sampleSize()];
		colorMode.fetchSample(sample, 0);
		
		if(sample[0] == 0.0){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void action() {
		
		Motor.B.stop();
		Motor.C.stop();
		Motor.C.rotate(540);
		Motor.B.stop();
		Motor.B.forward();
		Motor.C.forward();
		Delay.msDelay(motorDelay);
		motorDelay += 500;
	}

	@Override
	public void suppress() {
		Motor.B.stop();
		Motor.C.stop();

	}

}
