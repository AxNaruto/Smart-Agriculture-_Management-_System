@echo off
setlocal enabledelayedexpansion

:: Switch to project directory even if run from Desktop
if exist "%~dp0pom.xml" (
    cd /d "%~dp0"
) else if exist "C:\Users\Sumesh\Documents\New folder\pom.xml" (
    cd /d "C:\Users\Sumesh\Documents\New folder"
)

title Smart Agriculture Management System - Server Launcher
color 0A

echo ======================================================================
echo    SMART AGRICULTURE MANAGEMENT SYSTEM (Spring Boot 3 + MySQL)
echo ======================================================================
echo.

:: Automatically configure Java and Maven paths
if not defined JAVA_HOME (
    if exist "C:\Program Files\Eclipse Adoptium\jdk-25.0.4.101-hotspot" (
        set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.4.101-hotspot"
    ) else if exist "C:\Users\Sumesh\.tools\jdk-17.0.10+7" (
        set "JAVA_HOME=C:\Users\Sumesh\.tools\jdk-17.0.10+7"
    )
)

if defined JAVA_HOME (
    set "PATH=%JAVA_HOME%\bin;%PATH%"
)

if exist "C:\Users\Sumesh\.tools\apache-maven-3.9.6" (
    set "M2_HOME=C:\Users\Sumesh\.tools\apache-maven-3.9.6"
    set "PATH=C:\Users\Sumesh\.tools\apache-maven-3.9.6\bin;%PATH%"
)

echo [INFO] Detected Java environment:
call java -version
if %errorlevel% neq 0 (
    echo [ERROR] Java could not be found!
    pause
    exit /b 1
)

echo.
echo [INFO] Checking port 8080...
for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":8080" ^| findstr "LISTENING"') do taskkill /f /pid %%a >nul 2>&1


echo [INFO] Starting Spring Boot Application on http://localhost:8080 ...
echo [INFO] Open your web browser at: http://localhost:8080
echo ======================================================================
echo.

call mvn spring-boot:run
pause

