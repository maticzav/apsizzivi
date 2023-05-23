#!/bin/bash

# Check if the correct number of arguments are provided
if [ "$#" -ne 1 ]; then
    echo "You must enter exactly 1 command line argument (the filename of the script to run)"
    exit 1
fi

SCRIPT="$1"
DIR="data"

# Iterate over .in files in the data directory
for INFILE in "$DIR"/*.in; do
    # Get the corresponding .out file
    OUTFILE="${INFILE%.in}.out"

    # Check if the .out file exists
    if [ ! -f "$OUTFILE" ]; then
        echo "No .out file found corresponding to $INFILE"
        continue
    fi

    # Run the script on the .in file and save the output
    OUTPUT=$(bash "$SCRIPT" < "$INFILE")

    # Load the contents of the .out file
    EXPECTED=$(cat "$OUTFILE")

    # Compare the output to the expected result
    if [ "$OUTPUT" = "$EXPECTED" ]; then
        echo "Test passed for $INFILE"
    else
        echo "Test failed for $INFILE"
        echo "Expected: $EXPECTED"
        echo "Got: $OUTPUT"
    fi
done
