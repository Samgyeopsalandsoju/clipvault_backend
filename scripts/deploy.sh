set -e

DEPLOY_PATH="/home/ec2-user/backend/deploy"

# 배포 파일 권한 변경
sudo chown ec2-user:ec2-user -R /home/ec2-user/backend/deploy/*