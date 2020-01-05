#!/bin/bash
i=14
max=16
while [ $i -le $max ];
do
fileResult="result_Takuzu_taille_${i}.txt"
java -jar Takuzu.jar $i > $fileResult
let "i = i + 2" 
done
