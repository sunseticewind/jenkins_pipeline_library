echo $1 $2 $3 $4 $5
ssh -o StrictHostKeyChecking=no -tt $2@$3 $4 | tee $5.txt