import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class CraneStop implements Behavior {
	
	private EV3LargeRegulatedMotor crane;
	
	private CurrentSample current;
	
	public CraneStop(EV3LargeRegulatedMotor crane, CurrentSample current) {
		this.crane = crane;
		this.current = current;
	}
	@Override
	public boolean takeControl() {
		if(current.getDistance() <= 0.06) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		crane.stop();
		Sound.beepSequence();
		current.getSort().stop(); //Stops the current arbitrator
	}

	@Override
	public void suppress() {

	}

}
