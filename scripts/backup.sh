set -e

DEPLOY_PATH="/home/ec2-user/backend/deploy"
BACKUP_PATH="/home/ec2-user/backend/backup/deploy"
#이전 백업본 삭제
rm -rf BACKUP_PATH/*

#현재 버전 백업
cp -r $DEPLOY_PATH/* $BACKUP_PATH/

#기존 버전 삭제
rm -rf $DEPLOY_PATH/*
