import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class ClawPick implements Behavior {
	
	private CurrentSample current;
	private EV3MediumRegulatedMotor claw;
	private EV3LargeRegulatedMotor crane;
	
	
	
	public ClawPick(EV3MediumRegulatedMotor claw, CurrentSample current, EV3LargeRegulatedMotor crane){
		this.claw = claw;
		this.current = current;
		this.crane = crane;
	}
	
	float cranePos = crane.getPosition();
	float clawPos = claw.getPosition();
	


	@Override
	public boolean takeControl() {
		if(current.getSample() <= 0.08 && !crane.isMoving() /*crane.isMoving()*/) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Delay.msDelay(500);
		crane.setSpeed(0);
		claw.setSpeed(100);
		claw.forward();
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
