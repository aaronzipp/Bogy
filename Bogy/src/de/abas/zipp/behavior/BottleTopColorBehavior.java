package de.abas.zipp.behavior;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import de.abas.zipp.msg.MessageHandler;
//import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class BottleTopColorBehavior implements Behavior {
	
	public static EV3ColorSensor colorSensor;
	private MessageHandler msgHandler;
	
	public BottleTopColorBehavior(MessageHandler msgHandler) {
		this.msgHandler = msgHandler;
		colorSensor = new EV3ColorSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
	
		SensorMode colorMode = colorSensor.getColorIDMode();
		float sample[] = new float[colorSensor.sampleSize()];
		int offset = colorSensor.sampleSize();
		
		
		
		try {
			colorMode.fetchSample(sample, offset);
		} catch (ArrayIndexOutOfBoundsException e) {
			log("expected sample size: " +colorSensor.sampleSize());
			log("effective size of sample: " +sample.length);	log(e.getMessage());
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log(sw.toString());
		}
		
		return sample[0] != 6.0;
	}

	void log(String message) {
		try {
			msgHandler.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void action() {
		//
		//Motor.C.setSpeed(50);
		//Motor.B.setSpeed(50);
		//Motor.C.forward();
		//Motor.B.forward();
	
		float r, g, b;
		float rTotal = 0, gTotal = 0, bTotal = 0;
		int factor = 4;
		int divisor = 0;
		
		SampleProvider rgbColor = colorSensor.getRGBMode();
		float[] sample = new float[rgbColor.sampleSize()];
		rgbColor.fetchSample(sample, 0);
		
		SampleProvider white = colorSensor.getColorIDMode();
		float[] sampleWhite = new float[white.sampleSize()];
		white.fetchSample(sampleWhite, 0);
		
		while(sampleWhite[0] != 6.0){
			
			r = sample[0];
			g = sample[1];
			b = sample[2];
			
			r = r * 256 * factor;
			g = g * 256 * factor;
			b = b * 256 * factor;
			
			rTotal = rTotal + r;
			gTotal = gTotal + g;
			bTotal = bTotal + b;
			
			divisor++;
			rgbColor.fetchSample(sample, 0);
			white.fetchSample(sampleWhite, 0);
			
		}
			
		r = rTotal / divisor;
		g = gTotal / divisor;
		b = bTotal / divisor;
		
		try {
			msgHandler.sendMessage("RGB: (" + r + ", " + g + ", " + b + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}
			

	}

	@Override
	public void suppress() {
		
		//Motor.C.setSpeed(50);
		//Motor.B.setSpeed(50);
		//Motor.C.forward();
		//Motor.B.forward();
	}

}
