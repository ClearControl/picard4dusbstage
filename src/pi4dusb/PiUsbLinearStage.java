package pi4dusb;

import org.bridj.Pointer;
import pi4dusb.bindings.PI4DUSBStageLibrary;


/**
 * The PiUsbLinearStage represents a 1D linear stage.
 *
 * Author: @haesleinhuepf
 * September 2018
 */
public class PiUsbLinearStage {

    private int defaultVelocity = 1;

    Pointer<?> stage;

    /**
     * Connect to the motor and home it
     *
     * @param serial
     */
    public PiUsbLinearStage(int serial) {
        Pointer<Integer> errorNumber = Pointer.allocateInt();
        stage = PI4DUSBStageLibrary.piConnectMotor(errorNumber, serial);

        PI4DUSBStageLibrary.piHomeMotor(1, stage);

        errorNumber.release();
    }

    /**
     * Read out the stage position
     * @return
     */
    public int getPosition() {
        Pointer<Integer> position= Pointer.allocateInt();

        PI4DUSBStageLibrary.piGetMotorPosition(position, stage);

        int result = position.getInt();
        position.release();

        return result;
    }

    /**
     * Set the position of the stage
     * @param position
     */
    public void setPosition(int position) {
        setPosition(position, defaultVelocity);
    }

    /**
     * Set the position of the stage with a given velocity
     * @param position
     * @param velocity valid values range from 1 to 12
     * @return
     */
    public void setPosition(int position, int velocity) {
        PI4DUSBStageLibrary.piRunMotorToPosition(position, velocity, stage);
    }



    public void dispose() {
        PI4DUSBStageLibrary.piDisconnectMotor(stage);
    }

    public void setDefaultVelocity(int defaultVelocity) {
        this.defaultVelocity = defaultVelocity;
    }

}
