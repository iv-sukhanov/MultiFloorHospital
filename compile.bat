@echo off
setlocal enabledelayedexpansion
if not exist build (
    mkdir build
) else (
    del /q build\*
)

for /r %%f in (*.java) do (
    set javaFiles=!javaFiles! "%%f"
)
javac -d build !javaFiles!
