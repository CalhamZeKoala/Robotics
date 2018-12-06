package FinalProject;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class CollMainClass {

	public static void main(String[] args) {
		
		//new TunePlayer().start();
		
		//RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.A);
		//RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.D);
		
		EV3LargeRegulatedMotor crane = new EV3LargeRegulatedMotor(MotorPort.C);
		EV3MediumRegulatedMotor claw = new EV3MediumRegulatedMotor(MotorPort.B);
		
		CurrentSample current = new CurrentSample();
		//EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
		
		
		
		/*Wheel wL = WheeledChassis.modelWheel(left, 56).offset(-(125 / 2));
		Wheel wR = WheeledChassis.modelWheel(right, 56).offset((125 / 2));
		Chassis chassis = new WheeledChassis(new Wheel[] {wL, wR}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot mp = new MovePilot(chassis);
		
		chassis.setLinearSpeed(200);
		chassis.setAngularSpeed(50);*/
		
		//State state = new State();
		CraneLower a = new CraneLower(crane, current, claw);
		CraneRaise b = new CraneRaise(crane,current, claw);
		ClawPick d = new ClawPick(claw, current, crane);
		
		Behavior[] behave = {b, a, d };
		
		Arbitrator sort = new Arbitrator(behave);
		sort.go();
	}
}
