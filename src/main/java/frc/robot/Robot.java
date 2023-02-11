package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


public class Robot extends TimedRobot {
	

	final TalonFXInvertType kInvertType = TalonFXInvertType.CounterClockwise; // <<< What direction you want "forward/up" to be.
	final NeutralMode kBrakeDurNeutral = NeutralMode.Coast;


	WPI_TalonFX _talon = new WPI_TalonFX(1, "rio"); // <<< Choose the Talon ID - check in Tuner to see what ID it is.
	Joystick _joy = new Joystick(0);


	public void robotInit() {

		/*
		 * two styles of configs, older API that requires a config all per setting, or
		 * newer API that has everything in one call.
		 */
	// 	if (false) {
	// 		/* older legacy config API example */
	// 		_talon.configFactoryDefault();
	// 		/* select integ-sensor for PID0 (it doesn't matter if PID is actually used) */
	// 		_talon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
	// 	} else {
	// 		/* newer config API */
	// 		TalonFXConfiguration configs = new TalonFXConfiguration();
	// 		/* select integ-sensor for PID0 (it doesn't matter if PID is actually used) */
	// 		/* config all the settings */
	// 		_talon.configAllSettings(configs);
    // }
    
		_talon.setInverted(kInvertType);
		/*
		 * Talon FX does not need sensor phase set for its integrated sensor
		 * This is because it will always be correct if the selected feedback device is integrated sensor (default value)
		 * and the user calls getSelectedSensor* to get the sensor's position/velocity.
		 * 
		 * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#sensor-phase
		 */
		//_talon.setSensorPhase(true);

		/* brake or coast during neutral */
		_talon.setNeutralMode(kBrakeDurNeutral);
	}

	/**
	 * Get the selected sensor register and print it
	 */
	public void robotPeriodic() {

		/* drive talon based on joystick */
		_talon.set(ControlMode.PercentOutput, _joy.getY());

	}
}