@echo off
setlocal enabledelayedexpansion

:: 取 git 版本資訊
for /f "delims=" %%i in ('git rev-parse --abbrev-ref HEAD') do set GIT_BRANCH=%%i
for /f "delims=" %%i in ('git rev-parse --short HEAD') do set GIT_COMMIT=%%i
set VERSION=%GIT_BRANCH%-%GIT_COMMIT%

echo Git version: %VERSION%

:: 把版本字串寫入 resource/version.txt
echo %VERSION% > src\main\resources\version.txt

:: 先打包
mvn clean install -DskipTests
if errorlevel 1 (
    echo Maven build failed!
    exit /b 1
)

:: 找 war 檔
set WAR_FILE=
for %%f in (target\*.war) do (
    set WAR_FILE=%%f
    goto got_war
)

:got_war
if not defined WAR_FILE (
    echo WAR file not found.
    exit /b 1
)

set NEW_WAR=target\demo-%VERSION%.war

echo Ready to rename:
echo Source: %WAR_FILE%
echo Target: %NEW_WAR%

move "%WAR_FILE%" "%NEW_WAR%"
if errorlevel 1 (
    echo Failed to rename WAR file.
    exit /b 1
)

echo Renamed WAR file to %NEW_WAR%
endlocal
