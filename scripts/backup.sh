set -e

DEPLOY_PATH="/home/ec2-user/backend/deploy"
BACKUP_PATH="/home/ec2-user/backend/backup/deploy"
APPLICATION_PATH="/home/ec2-user/backend/application"
APP_BACKUP_PATH="/home/ec2-user/backend/backup/application"
#이전 백업본 삭제
rm -rf BACKUP_PATH/*

#현재 버전 백업
cp -r $DEPLOY_PATH/* $BACKUP_PATH/

#기존 버전 삭제
rm -rf $DEPLOY_PATH/*

#APPLICATION 이전 백업본 삭제
rm -rf $APP_BACKUP_PATH/*
#APPLICATION 백업
cp $APPLICATION_PATH/* $APP_BACKUP_PATH/*
