package pi4dusb;

import org.bridj.Pointer;
import pi4dusb.bindings.PiUsbLibrary;

/**
 * The PiUsbTwisterStage controls a twister stage
 *
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

    public void setPosition(int position) {
        setPosition(position, defaultVelocity, true);
    }

    /**
     * Set the position of the stage with a given velocity
     * @param position one step corresponds to 1.8 degrees
     * @param velocity valid values range from 1 to 12
     * @param waitToFinish
     * @return
     */
    public boolean setPosition(int position, int velocity, boolean waitToFinish) {
        checkError(PiUsbLibrary.piRunTwisterToPosition(position, velocity, stage));

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
                PiUsbLibrary.piGetTwisterStatus(currentPosition, moving, stage);
                if (currentPosition.getInt() == position) {
                    result = true;
                }
                currentPosition.release();
                moving.release();
                if (result) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }



    public void dispose() {
        PiUsbLibrary.piDisconnectTwister(stage);
    }

    public void setDefaultVelocity(int defaultVelocity) {
        this.defaultVelocity = defaultVelocity;
    }
}
