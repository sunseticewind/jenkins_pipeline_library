#!/bin/bash
set -ex
scp -r -o StrictHostKeyChecking=no -i $1 $WORKSPACE/command.sh $2@$3:/home/local_admin
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash /home/local_admin/command.sh | tee test.txt