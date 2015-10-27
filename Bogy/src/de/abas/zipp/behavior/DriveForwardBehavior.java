package de.abas.zipp.behavior;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class DriveForwardBehavior implements Behavior {

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		Motor.B.setSpeed(200);
		Motor.C.setSpeed(200);
		Motor.B.forward();
		Motor.C.forward();
	}

	@Override
	public void suppress() {
		Motor.B.stop(); // clean up
		Motor.C.stop();
	}

}
