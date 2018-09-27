package pi4dusb;

import org.bridj.Pointer;
import pi4dusb.bindings.PiUsbLibrary;

import java.awt.*;


/**
 * The PiUsbLinearStage represents a 1D linear stage.
 *
 * Author: @haesleinhuepf
 * September 2018
 */
public class PiUsbLinearStage extends PiDevice {

    private int defaultVelocity = 1;

    Pointer<Long> stage;

    /**
     * Connect to the motor and home it
     *
     * @param serial
     */
    public PiUsbLinearStage(int serial) {
        Pointer<Integer> errorNumber = Pointer.allocateInt();
        stage = (Pointer<Long>) PiUsbLibrary.piConnectMotor(errorNumber, serial);
        checkError(errorNumber);

        errorNumber.release();
    }

    /**
     * Home the motor.
     * @return success
     */
    public boolean home() {
        int error = PiUsbLibrary.piHomeMotor(1, stage);
        return checkError(error);
    }

    /**
     * Read out the stage position
     * @return position in steps
     */
    public int getPosition() {
        Pointer<Integer> position= Pointer.allocateInt();

        PiUsbLibrary.piGetMotorPosition(position, stage);

        int result = position.getInt();
        position.release();

        return result;
    }


    /**
     * Set the position of the stage
     * @param position in steps
     */
    public void setPosition(int position) {
        setPosition(position, defaultVelocity, true);
    }

    /**
     * Set the position of the stage with a given velocity
     * @param position
     * @param velocity valid values range from 1 to 12
     * @return
     */
    public boolean setPosition(int position, int velocity, boolean waitToFinish) {
        PiUsbLibrary.piRunMotorToPosition(position, velocity, stage);
        if (waitToFinish) {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < timeoutInMillisecons) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }

                boolean result = false;
                Pointer<Integer> currentPosition = Pointer.allocateInt();
                Pointer<Integer> moving = Pointer.allocateInt();
                Pointer<Integer> atHome = Pointer.allocateInt();
                PiUsbLibrary.piGetMotorStatus(currentPosition, moving, atHome, stage);
                if (currentPosition.getInt() == position) {
                    result = true;
                }
                currentPosition.release();
                moving.release();
                atHome.release();
                if (result) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public void dispose() {
        PiUsbLibrary.piDisconnectMotor(stage);
    }

    public void setDefaultVelocity(int defaultVelocity) {
        this.defaultVelocity = defaultVelocity;
    }

}
