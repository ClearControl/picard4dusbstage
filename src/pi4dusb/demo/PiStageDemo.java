package pi4dusb.demo;

import pi4dusb.PiUsbLinearStage;
import pi4dusb.PiUsbTwisterStage;

/**
 * This is example code showing how to run a Picard 4D USB stage
 *
 * Author: @haesleinhuepf
 * September 2018
 */
public class PiStageDemo {
    public static void main(String... args){

        // Initialize the linear stage
        int linearStageSerial = 122;
        PiUsbLinearStage linearStage = new PiUsbLinearStage(linearStageSerial);

        // home the stage
        linearStage.home();

        // move it ahead
        System.out.println("Linear stage position before: " + linearStage.getPosition());
        linearStage.setPosition(linearStage.getPosition() + 2);
        System.out.println("Linear stage position after" + linearStage.getPosition());

        //cleanup
        linearStage.dispose();



        // initialize the twister stage
        int twisterStageSerial = 28;
        PiUsbTwisterStage twisterStage = new PiUsbTwisterStage(twisterStageSerial);

        // rotate the stage
        System.out.println(twisterStage.getPosition());
        twisterStage.setPosition(twisterStage.getPosition() + 45);
        System.out.println(twisterStage.getPosition());

        // clean up
        twisterStage.dispose();
    }
}

