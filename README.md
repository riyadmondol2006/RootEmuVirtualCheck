# RootEmuVirtualCheck

RootEmuVirtualCheck is a simple Kotlin library that provides methods to check if an Android device is rooted, running on an emulator, or running in a virtual space. This library can be useful for developers who want to prevent their app from running on devices that are not allowed.

## How it works

The library provides three static methods:
- `isDeviceRooted()`: Returns `true` if the device is rooted.
- `isRunningOnEmulator()`: Returns `true` if the app is running on an emulator.
- `isRunningOnVirtualSpace()`: Returns `true` if the app is running in a virtual space.

The `isDeviceRooted()` method checks for the presence of the `test-keys` string in the device's build tags and the `su` binary in the system's path.

The `isRunningOnEmulator()` method checks for various strings in the device's `Build` properties that are commonly associated with emulator devices.

The `isRunningOnVirtualSpace()` method checks for the presence of various known `su` binary paths in the file system.

# Contact

If you have any questions or suggestions, please feel free to email me at riyadmondol2006@gmail.com.
