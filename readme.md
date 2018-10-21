Picard 4D USB stage
==

This library contains the device drivers of the [Picard Industries 4D USB stage](http://picard-industries.com/products/usb-4d-stage.html) and
associated java wrappers. The device driver (PiUsb.dll, PiUsb.h and PiUsb.lib) were
kindly proviced by Picard Industries. The files originate from here:
http://picard-industries.com/software/usb-4d-stage.zip 

Using this library
---
In order to use this library to control a Picard 4D USB stage from a maven based Java project, you need to add a dependency and a repository to your `pom.xml` file:

```xml
<dependencies>
  <dependency>
    <groupId>net.clearcontrol</groupId>
    <artifactId>picard4dusbstage</artifactId>
    <version>0.0.3</version>
  </dependency>
</dependencies>

<repositories>
  <repository>
    <id>hslh</id>
    <url>http://dl.bintray.com/haesleinhuepf/snapshots</url>
  </repository>
</repositories>
```

Import the class to connect to a stage like this:
```Java
import pi4dusb.PiUsbLinearStage;
```

You can then home and move the stage like this:
```java
int linearStageSerial = 122;
PiUsbLinearStage linearStage = new PiUsbLinearStage(linearStageSerial);

// home the stage
linearStage.home();

// move it ahead
linearStage.setPosition(linearStage.getPosition() + 2);

//cleanup
linearStage.dispose();
```

You find example code on how to use the stage in the demo code located in the 
[demo class](http://github.com/clearcontrol/picard4dusbstage/src/main/resources/pi4dusb/demo/PiStageDemo.java). To run the class, change the serial numbers first.


Developing this library
---

It is recommended to develop this library using an integraded development environment 
such as IntelliJ or Eclipse. Open the `build.gradle` in the root directory as project
to start developing. To check if the project was loaded correctly and the stage is functional,
it is recommended to run the main method [demo class](http://github.com/clearcontrol/picard4dusbstage/src/main/resources/pi4dusb/demo/PiStageDemo.java).

If you want to update the generated java wrapper, update the configuration file `piusb.config.win.jnaertor` and run jnaerate.bat from the command line.

Authors
---

[Robert Haase](mailto:rhaase@mpi-cbg.de)

Further information
---

[gradle](https://gradle.org/)

[maven](https://maven.apache.org/) 


