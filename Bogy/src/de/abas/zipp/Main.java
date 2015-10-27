package de.abas.zipp;
import de.abas.zipp.behavior.AvoidCollisionBehavior;
import de.abas.zipp.behavior.ColorSensorBehavior;
import de.abas.zipp.behavior.DriveForwardBehavior;
import de.abas.zipp.behavior.TouchSensorBehavior;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	

	public static void main(String[] args) {
	
		Behavior driveForwardBehavior = new DriveForwardBehavior();
		Behavior avoidCollisionBehavior = new AvoidCollisionBehavior();
		Behavior colorSensorBehavior = new ColorSensorBehavior();
		Behavior touchSensorBehavior = new TouchSensorBehavior();
		
		Behavior[] bArray = { driveForwardBehavior, avoidCollisionBehavior, colorSensorBehavior, touchSensorBehavior };
		Arbitrator arbitrator = new Arbitrator(bArray);
		
		while(true){
			arbitrator.start();
		}
	}

}
