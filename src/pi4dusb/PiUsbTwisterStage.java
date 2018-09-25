package pi4dusb;

import org.bridj.Pointer;
import pi4dusb.bindings.PiUsbLibrary;

/**
 * PiUsbTwisterStage
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 09 2018
 */
public class PiUsbTwisterStage extends PiDevice {

    private int defaultVelocity = 1;

    Pointer<Long> stage;

    /**
     * Connect to the motor and home it
     *
     * @param serial
     */
    public PiUsbTwisterStage(int serial) {
        Pointer<Integer> errorNumber = Pointer.allocateInt();
        stage = (Pointer<Long>) PiUsbLibrary.piConnectTwister(errorNumber, serial);
        checkError(errorNumber);

        //checkError(PiUsbLibrary.piSetTwisterPositionZero(this.stage));

        errorNumber.release();
    }

    /**
     * Read out the stage position
     * @return
     */
    public int getPosition() {
        Pointer<Integer> position = Pointer.allocateInt();

        checkError(PiUsbLibrary.piGetTwisterPosition(position, stage));
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
     * @param position one step corresponds to 1.8 degrees
     * @param velocity valid values range from 1 to 12
     * @return
     */
    public void setPosition(int position, int velocity) {
        checkError(PiUsbLibrary.piRunTwisterToPosition(position, velocity, stage));
    }



    public void dispose() {
        PiUsbLibrary.piDisconnectTwister(stage);
    }

    public void setDefaultVelocity(int defaultVelocity) {
        this.defaultVelocity = defaultVelocity;
    }
}
