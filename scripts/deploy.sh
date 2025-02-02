set -e

S3_BUCKET="s3-clipvault"
S3_PATH="/home/ec2-user/backend/s3"
DEPLOY_PATH="/home/ec2-user/backend/deploy"
DEPLOY_VERSION=$(aws s3 ls s3://$S3_BUCKET/backend/ | tail -n 1 | awk '{print $4}' | sed 's/deploy-//g' | sed 's/.zip//g')

echo "✅ Deploying version: $DEPLOY_VERSION"

# 기존 배포 파일 삭제
rm -rf $DEPLOY_PATH/*

# S3에서 최신 배포 파일 다운로드
aws s3 cp s3://$S3_BUCKET/deploy/clipVault_backend_${DEPLOY_VERSION}.zip $S3_PATH/
# 압축 해제 후 배포 경로로 이동
unzip $S3_PATH/clipVault_backend_${DEPLOY_VERSION}.zip -d $DEPLOY_PATH/