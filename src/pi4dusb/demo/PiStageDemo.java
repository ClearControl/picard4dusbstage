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

        // Move the linear stage
        PiUsbLinearStage linearStage = new PiUsbLinearStage(122);
        System.out.println(linearStage.getPosition());
        int position = linearStage.getPosition();
        linearStage.home();
        linearStage.setPosition(position + 2);
        System.out.println(linearStage.getPosition());
        linearStage.dispose();

        // rotate the twister stage
        PiUsbTwisterStage twisterStage = new PiUsbTwisterStage(28);
        System.out.println(twisterStage.getPosition());
        twisterStage.setPosition(twisterStage.getPosition() + 45);
        System.out.println(twisterStage.getPosition());
        twisterStage.dispose();

    }
}

