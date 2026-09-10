# Rubiks

An Android application demonstrating 3D model rendering using [Google Filament](https://github.com/google/filament) and Jetpack Compose.

## Current State

The project currently has a solid architecture for loading and displaying `.gltf`/`.glb` 3D models in an Android application using the Filament rendering engine alongside Jetpack Compose. 

It handles:
* Seamlessly rendering 3D models within a Compose UI (`AndroidView`)
* Properly loading models and external assets (`.bin` files, `.png` textures) asynchronously
* Loading and applying Image-Based Lighting (IBL) and Skyboxes from `.ktx` assets.
* Touch controls (orbiting, panning, zooming)

https://github.com/user-attachments/assets/3d138b5d-10d4-4220-9cd4-053f886cf8f6



## Future Plan

The ultimate goal of this project is to implement an interactive Rubik's Cube Solver. 
The immediate next steps include:
1. Loading a 3D model of a Rubik's Cube.
2. Implementing the logic to recognize the cube's state (potentially via camera).
3. Animating the 3D model to show the user the solution steps visually.

## Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **Filament** (3D Rendering Engine)
- **Material 3**
