import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class CraneLower implements Behavior {
	
	private EV3LargeRegulatedMotor crane;
	
	private CurrentSample current;
	
	public CraneLower (EV3LargeRegulatedMotor crane, CurrentSample current) {
		this.crane = crane;
		this.current = current;
	}
	@Override
	public boolean takeControl() {
		if (current.getDistance() >= 0.06) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		crane.setSpeed(100);
		crane.forward();
	}

	@Override
	public void suppress() {
	}
}