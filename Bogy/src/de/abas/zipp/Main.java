package de.abas.zipp;
import de.abas.zipp.behavior.ColorSensorBehavior;
import de.abas.zipp.behavior.DriveForwardBehavior;
import de.abas.zipp.behavior.SearchingBehavior;
import de.abas.zipp.behavior.TouchSensorBehavior;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	
	public static void main(String[] args) {
		
		Behavior driveForwardBehavior = new DriveForwardBehavior();
		Behavior colorSensorBehavior = new ColorSensorBehavior();
		Behavior searchingBehavior = new SearchingBehavior();
		Behavior touchSensorBehavior = new TouchSensorBehavior();
		
		Behavior[] bArray = {driveForwardBehavior,  colorSensorBehavior, searchingBehavior, touchSensorBehavior };
		Arbitrator arbitrator = new Arbitrator(bArray);
		
		arbitrator.start();
	}

}
