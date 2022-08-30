#!/bin/bash
set +ex
sudo scp -r -o StrictHostKeyChecking=no -i $1 $WORKSPACE/command.sh $2@$3:$HOME
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash $HOME/command.sh | tee test.txt