package pi4dusb;

import org.bridj.Pointer;
import pi4dusb.bindings.PiUsbLibrary;

import javax.xml.bind.annotation.XmlType;

import static pi4dusb.bindings.PiUsbLibrary.*;

/**
 * PiDevice
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 09 2018
 */
public class PiDevice {
    protected boolean checkError(int error) {
        switch (error) {
            case PI_NO_ERROR:
                return true;
            case PI_DEVICE_NOT_FOUND:
                System.out.println("Error: PI_DEVICE_NOT_FOUND");
                return false;
            case PI_OBJECT_NOT_FOUND:
                System.out.println("Error: PI_OBJECT_NOT_FOUND");
                return false;
            case PI_CANNOT_CREATE_OBJECT:
                System.out.println("Error: PI_CANNOT_CREATE_OBJECT");
                return false;
            case PI_INVALID_DEVICE_HANDLE:
                System.out.println("Error: PI_INVALID_DEVICE_HANDLE");
                return false;
            case PI_READ_TIMEOUT:
                System.out.println("Error: PI_READ_TIMEOUT");
                return false;
            case PI_READ_THREAD_ABANDONED:
                System.out.println("Error: PI_READ_THREAD_ABANDONED");
                return false;
            case PI_READ_FAILED:
                System.out.println("Error: PI_READ_FAILED");
                return false;
            case PI_INVALID_PARAMETER:
                System.out.println("Error: PI_INVALID_PARAMETER");
                return false;
            case PI_WRITE_FAILED:
                System.out.println("Error: PI_WRITE_FAILED");
                return false;
            default:
                System.out.println("Error: Unknown error. I would tell you more if I could.");
                return false;
        }
    }

    protected boolean checkError(Pointer<Integer> error) {
        return checkError(error.getInt());
    }
}
