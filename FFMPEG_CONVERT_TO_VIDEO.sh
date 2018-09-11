#!/bin/bash

if [[ "$#" -eq "1" ]] || [[ "$#" -eq "2" ]]; then
	ffmpeg -y -i $1 -vf scale=640:480,setdar=4:3 -r 10 intermediateOutput.mp4
	
	if [[ "$#" -eq "1" ]]; then
		ffmpeg -y -i intermediateOutput.mp4 -c:v mjpeg intermediateOutput.mjpeg

	else

		ffmpeg -y -i intermediateOutput.mp4 -c:v mjpeg -q:v $2 intermediateOutput.mjpeg
	fi
	
	ffmpeg -y -r 10 -i intermediateOutput.mjpeg -c:v copy output.avi

	mkdir $1.
	\mv output.avi $1./output.avi

	rm intermediateOutput.mp4
	rm intermediateOutput.mjpeg
		
else
	echo "Please Enter 1 Arugument (Filename) or 2 Arguments (Filename & Conversion Quality). Quality ranges from 30(Worst) to 2(Best)."
fi



