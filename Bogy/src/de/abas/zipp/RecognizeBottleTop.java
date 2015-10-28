package de.abas.zipp;

import de.abas.zipp.behavior.BottleTopColorBehavior;
import de.abas.zipp.behavior.DriveForwardBehavior;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class RecognizeBottleTop {

	public static void main(String[] args) {
		
		Behavior bottleTopColorBehavior = new BottleTopColorBehavior();
		Behavior driveForwardBehavior = new DriveForwardBehavior();
		
		Behavior[] behaviorArray = {driveForwardBehavior, bottleTopColorBehavior};
		Arbitrator arbitrator = new Arbitrator(behaviorArray);
		
		arbitrator.start();
	}

}
