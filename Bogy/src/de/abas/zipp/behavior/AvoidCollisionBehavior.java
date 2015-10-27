package de.abas.zipp.behavior;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class AvoidCollisionBehavior implements Behavior {

	private EV3IRSensor irSensor;
    private boolean suppressed = false;
    
    public AvoidCollisionBehavior() {
		irSensor = new EV3IRSensor(SensorPort.S4);
	}

	@Override
	public boolean takeControl() {
		SensorMode distanceMode = irSensor.getDistanceMode();
		float[] sample = new float[distanceMode.sampleSize()];
		distanceMode.fetchSample(sample , 0);
		float closestDistance = 90f;
		for (float f : sample) {
			closestDistance = Math.min(closestDistance, f);
		}
		return closestDistance < 40;
	}

	@Override
	public void action() {
		suppressed = false;
		Motor.B.rotate(-180, true);
		Motor.C.rotate(-180, true);
		
		while (Motor.C.isMoving() && !suppressed)
			Thread.yield();
		
		Motor.B.stop();
		Motor.C.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
