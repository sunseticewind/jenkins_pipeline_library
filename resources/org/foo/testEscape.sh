#!/bin/bash
set +ex
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 bash command.sh | tee test.txt