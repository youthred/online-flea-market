#!/bin/bash
projectName='online-flea-market'
projectLocation=$(pwd)
clear
echo "==> Start running ${projectName}"
if [[ ! -d "./logs/info/" ]]; then
  mkdir -p "./logs/info/"
fi
if [[ ! -d "./logs/error/" ]]; then
  mkdir -p "./logs/error/"
fi
nohup java -jar "./${projectName##*/}.jar" --spring.profiles.active=prod &>/dev/null &
echo "==> Logs at $projectLocation/logs"
sleep 2
echo "==> Reading log "`ls -t "./logs/info/" | head -1`
watch tail -n 30 "./logs/info/"`ls -t "./logs/info/" | head -1`