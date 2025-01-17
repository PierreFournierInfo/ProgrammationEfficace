#!/bin/bash

# Répertoire source
SRC_D="."

# Répertoire test
IN_D="sample"

# Répertoire tmp
TMP_D="tmp"


mkdir -p "$TMP_D"

#ajouter la compilation
javac Maxime.java

for in_file in "$IN_D"/*.in; do
    filename=$(basename "$in_file")
    file_ext="${filename%.*}"
    java Maxime $in_file >| "tmp/$file_ext.out"
done

for out_file in "$TMP_D"/*.out; do
    filename=$(basename "$out_file")
    if diff  "$out_file" "$IN_D/$filename"
    then
        echo "Le fichier $filename est identique au fichier fourni!"
    else
        echo "Le fichier $filename n'est pas identique au fichier fourni :("
    fi
done