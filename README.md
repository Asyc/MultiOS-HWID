# MultiOS-HWID [![Maven Central](https://maven-badges.herokuapp.com/maven-central/me.asycc/MultiOS-HWID/badge.svg)](https://search.maven.org/artifact/me.asycc/MultiOS-HWID/1.1.1/jar)
A hardware ID generator that is based off of getting data from hardware located in the computer. This tool is designed to work with multiple of the most famous operating systems such as Linux/OS X/Windows.

# Use/License
You can use this tool freely, but some credit would be nice!

# Operating Systems 
Windows : Fully supported without the need of administrator privileges.

OS X : Fully supported without the need of administrator privileges.

Linux : Fully supported without the need of administrator privileges, but issues may occur without them.

Solaris : Support removed, may come back in the future, but with the need of administrator/root privileges.

# Usage Example
```java
  try {
   String hwid = HWID.getHWID();
  } catch (UnsupportedOSException | IOException | NoSuchAlgorithmException e) {
   e.printStackTrace();
  }
}
```

It is recommended to store the result instead of calling ```HWID.getHWID()``` multiple times.
