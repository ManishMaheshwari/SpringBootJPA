#!/bin/bash
set -e
#while ! timeout 1 bash -c "echo > /dev/tcp/localhost/3306"; do sleep 5; done
echo "create database springdb" | mysql -uroot -proot
cat springdb.sql | mysql -uroot -proot springdb
