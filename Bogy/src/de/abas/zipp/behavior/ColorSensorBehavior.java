package de.abas.zipp.behavior;

//import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class ColorSensorBehavior implements Behavior {
	
	public static EV3ColorSensor colorSensor;
	
	public ColorSensorBehavior(){
		colorSensor = new EV3ColorSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		
		
		SensorMode colorMode = colorSensor.getColorIDMode();
		float sample[] = new float[colorSensor.sampleSize()];
		colorMode.fetchSample(sample, 0);
		System.out.println(sample[0]);
		
		return sample[0] == 0.0;
	}

	@Override
	public void action() {
		
		//Motor.B.stop();
		//Motor.C.stop();
		//Motor.B.forward();
		//Motor.C.forward();	
	}

	@Override
	public void suppress() {
			
	}

}
