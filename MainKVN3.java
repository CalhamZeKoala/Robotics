import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainKVN3 {

	public static void main(String[] args) {
		
		//RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.A);
		//RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.D);

		EV3MediumRegulatedMotor claw = new EV3MediumRegulatedMotor(MotorPort.B); //Check this port
		
		EV3LargeRegulatedMotor crane = new EV3LargeRegulatedMotor(MotorPort.C);
		
		
		CurrentSample current = new CurrentSample();
		
		//EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
		
		/*Wheel wL = WheeledChassis.modelWheel(left, 56).offset(-(125 / 2));
		Wheel wR = WheeledChassis.modelWheel(right, 56).offset((125 / 2));
		Chassis chassis = new WheeledChassis(new Wheel[] {wL, wR}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot mp = new MovePilot(chassis);
		
		chassis.setLinearSpeed(200);
		chassis.setAngularSpeed(50);*/
		
		CraneLower a = new CraneLower(crane, current);
		CraneStop b = new CraneStop(crane, current);
		ClawPick c = new ClawPick(claw, current);
		CraneRaise d = new CraneRaise(crane, current, claw);

		Behavior[] behave1 = {b, a};
		Arbitrator sort = new Arbitrator(behave1);
		current.updateSortArbitrator(sort);
		
		Behavior[] behave2 = {c, d};
		Arbitrator pickup = new Arbitrator(behave2);
		current.updatePickupArbitrator(pickup);
		
		while (!(current.sorted)) {
			sort.go();
			pickup.go(); //I think this will only run AFTER the sort arbitrator is finished...
		}
	}
}