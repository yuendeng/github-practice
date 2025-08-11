@echo off
setlocal enabledelayedexpansion

echo 當前目錄: %cd%

for /f "delims=" %%i in ('git rev-parse --abbrev-ref HEAD') do set "GIT_BRANCH=%%i"
for /f "delims=" %%i in ('git rev-parse --short HEAD') do set "GIT_COMMIT=%%i"

set "VERSION=!GIT_BRANCH!-!GIT_COMMIT!"
echo Git version: !VERSION!

echo 目錄內容：
dir target\*.war

set "WAR_FILE=target\demo-0.0.1-SNAPSHOT.war"
set "NEW_WAR=demo-!VERSION!.war"

if not exist "!WAR_FILE!" (
    echo 找不到 WAR 檔: !WAR_FILE!
    exit /b 1
)

echo 嘗試改名...
ren "!WAR_FILE!" "!NEW_WAR!"
if errorlevel 1 (
    echo 改名失敗！
    exit /b 1
)

echo 改名成功！

echo 改名後目錄內容：
dir target\*.war

endlocal
