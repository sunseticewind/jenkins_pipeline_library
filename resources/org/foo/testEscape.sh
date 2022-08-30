#!/bin/bash
set -ex
scp -r -o StrictHostKeyChecking=no -i $1 $WORKSPACE/command.sh $2@$3:/home/$2
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash /home/$2/command.sh | tee test.txt