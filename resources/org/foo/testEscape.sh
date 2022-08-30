#!/bin/bash
set -ex
scp -r -o StrictHostKeyChecking=no -i $1 $WORKSPACE/command.sh $2@$3:/home/$USER
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash /home/$USER/command.sh | tee test.txt