#!/bin/bash
set -euo pipefail

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
printf "%s\n" "$VERSION" > "$VERSION_FILE"

# =========================
# Maven 打包
# =========================
echo "開始 Maven 打包..."
mvn clean package -DskipTests

# =========================
# 找到 WAR 檔案
# =========================
WAR_FILE=$(find target -maxdepth 1 -name "*.war" | head -n 1)
if [[ -z "$WAR_FILE" ]]; then
    echo "❌ 找不到 WAR 檔案！"
    exit 1
fi

# 去掉 .war 與 SNAPSHOT 後綴
WAR_BASENAME=$(basename "$WAR_FILE" .war)
WAR_BASENAME=${WAR_BASENAME%-0.0.1-SNAPSHOT}

# =========================
# 生成帶版本號的 WAR 名稱
# =========================
NEW_WAR="target/${WAR_BASENAME}-${VERSION}.war"
mv "$WAR_FILE" "$NEW_WAR"
echo "✅ War 檔已改名為: $NEW_WAR"
