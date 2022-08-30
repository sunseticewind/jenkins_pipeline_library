#!/bin/bash
set +ex
scp -r -o StrictHostKeyChecking=no -i $1 $HOME/command.sh $2@$3:$HOME
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash $HOME/command.sh | tee test.txt