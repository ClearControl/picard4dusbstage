package pi4dusb.demo;

import pi4dusb.PiUsbLinearStage;

/**
 * This is example code showing how to run a Picard 4D USB stage
 *
 * Author: @haesleinhuepf
 * September 2018
 */
public class PiStageDemo {
    public static void main(String... args){
        PiUsbLinearStage stage = new PiUsbLinearStage(1);

        int position = stage.getPosition();
        stage.setPosition(position + 2);

        stage.dispose();
    }
}
