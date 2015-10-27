package de.abas.zipp.behavior;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class TouchSensorBehavior implements Behavior {

	private EV3TouchSensor touchSensor;
	
	public TouchSensorBehavior() {
		touchSensor = new EV3TouchSensor(SensorPort.S1);
	}
	
	@Override
	public boolean takeControl() {
		SensorMode touchMode = touchSensor.getTouchMode();
		float sample[] = new float[touchSensor.sampleSize()];
		touchMode.fetchSample(sample, 0);
		//System.out.println(sample[0]);
		return sample[0] == 1;
	}

	@Override
	public void action() {
		Motor.B.stop();
		Motor.C.stop();
	}

	@Override
	public void suppress() {
	}

}
