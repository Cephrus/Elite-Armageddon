# Implementing Elite-Armageddon
You can integrate EA into your mod by following these steps.

## IF You are Coding A Plugin
You do not need to include the source of the API in your mod.

## IF You are Integrating EA Into An Existing Mod
You may include the API in your mod under the terms of the Apache 2.0 license.

## Implementation
To implement EA into your mod:

1) Add the EA API to your build path/src folder.

2) In your main forge mod class (the one with the Mod annotation), extend the EAPlugin superclass.

3) Use the EAPlugin and EAToolkit classes to integrate EA with your mod.
