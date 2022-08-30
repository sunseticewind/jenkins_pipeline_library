#!/bin/bash
set +ex
scp -r -o StrictHostKeyChecking=no -i $1 $2@$3:$WORKSPACE/command.sh .
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash command.sh | tee test.txt