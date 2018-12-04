
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class CraneRaise implements Behavior {
	
	private EV3LargeRegulatedMotor crane;
	
	private CurrentSample current;
	
	private EV3MediumRegulatedMotor claw;
	
	public CraneRaise(EV3LargeRegulatedMotor crane, CurrentSample current, EV3MediumRegulatedMotor claw) {
		this.crane = crane;
		this.current = current;
		this.claw = claw;
	}
	@Override
	public boolean takeControl() {
		if(current.getSample() <= 0.08 & claw.getSpeed() > 0 /*claw.isMoving()*/) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		Delay.msDelay(2000);
		crane.setSpeed(100);
		crane.backward();
	}

	@Override
	public void suppress() {

	}

}
