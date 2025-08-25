#!/bin/bash
set -e

# =========================
# 取得 Git 資訊
# =========================
BRANCH=$(git rev-parse --abbrev-ref HEAD)
COMMIT=$(git rev-parse --short HEAD)
VERSION="${BRANCH}-${COMMIT}"
echo "版本號: $VERSION"

# =========================
# 寫入 resources/version.txt
# =========================
VERSION_FILE="src/main/resources/version.txt"
echo "寫入版本號到 $VERSION_FILE"
echo "$VERSION" > "$VERSION_FILE"

# =========================
# Maven 打包
# =========================
echo "開始 Maven 打包..."
mvn clean install -DskipTests

# =========================
# 找到打包後的 WAR 檔
# =========================
WAR_FILE=$(ls target/*.war | head -n 1)
if [ -z "$WAR_FILE" ]; then
    echo "找不到 WAR 檔案！"
    exit 1
fi

# 去掉 .war 後綴，並去掉 -0.0.1-SNAPSHOT（如果有）
WAR_BASENAME=$(basename "$WAR_FILE" .war | sed 's/-0\.0\.1-SNAPSHOT$//')

# =========================
# 生成帶版本號的 WAR 名稱
# =========================
NEW_WAR="target/${WAR_BASENAME}-${VERSION}.war"

# 改名
mv "$WAR_FILE" "$NEW_WAR"
echo "War 檔已改名為: $NEW_WAR"