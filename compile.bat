@echo off
setlocal enabledelayedexpansion
if not exist build (
    mkdir build
) else (
    del /q build\*
)

echo Main-Class: main.Main > ./build/manifest.txt

for /r %%f in (*.java) do (
    set javaFiles=!javaFiles! "%%f"
)
javac -d build !javaFiles!
jar cfm build/MultiFloorHospital.jar ./build/manifest.txt -C build .
