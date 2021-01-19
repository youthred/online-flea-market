#!/bin/bash
projectName='online-flea-market'
pidFilePath="./ofm-pid"
clear
echo "==> Stopping ${projectName}..."
if [[ ! -f ${pidFilePath} ]]; then
    echo "==> No need to stop"
    exit
fi
pid="$(cat ${pidFilePath})"
if [[ -z $pid ]]; then
    echo "==> No need to stop"
    exit
fi
echo "==> ${projectName} pid = $pid"
$(kill -9 "$pid")
rm -f ${pidFilePath}
echo "==> Stop ${projectName} successfully"