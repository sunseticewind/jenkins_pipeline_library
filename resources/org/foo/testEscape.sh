#!/bin/bash
set +ex
ssh -o StrictHostKeyChecking=no -tt -i $1 $2@$3 '$4' | tee test.txt