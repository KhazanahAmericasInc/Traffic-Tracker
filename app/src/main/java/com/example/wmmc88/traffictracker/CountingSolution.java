/*This class handles the object detection. It should be extended to implement a specific Tracking solution (ex. KCF)*/
package com.example.wmmc88.traffictracker;


import android.content.Context;
import android.util.Log;
import android.util.Pair;

import android.location.*;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.opencv.core.Core.FONT_HERSHEY_SIMPLEX;

class CountingSolution {
    private static final String TAG = CountingSolution.class.getSimpleName();

    protected final Size SCREEN_SIZE;
    //TODO Switch to using preferences
    private final int mBoxThreshold = 8000;
    protected BackgroundSubtractorMOG2 mBackgroundSubtractor;
    protected volatile int mZone1Count = 0;
    protected volatile int mZone2Count = 0;
    protected volatile Mat inputImage = null;
    protected volatile Mat rawForegroundMask = null;
    protected volatile Mat cleanedForegroundMask = null;
    protected volatile List<MatOfPoint> rawContours = null;
    protected volatile List<Rect> filteredBoundingBoxes = null;

    private String currLoc = "";

    protected CountingSolution(Size screenSize) {
        this.mBackgroundSubtractor = Video.createBackgroundSubtractorMOG2(500, 16, false);
        Log.e(TAG, "" + mBackgroundSubtractor.getShadowThreshold());
        this.SCREEN_SIZE = screenSize;
    }

    private Mat convertMat(Mat img) {
        Mat returnImg = img.clone();
        if (returnImg.channels() == 4) {
            Imgproc.cvtColor(returnImg, returnImg, Imgproc.COLOR_BGRA2BGR);
        } else if (returnImg.channels() == 3) {
            //already proper colour-space config
        } else if (returnImg.channels() == 1) {
            Imgproc.cvtColor(returnImg, returnImg, Imgproc.COLOR_GRAY2BGR);
        } else {
            //TODO output error Invalid image
        }
        return returnImg;
    }

    private Mat findMask(Mat img) {
        img = img.clone();
        this.mBackgroundSubtractor.apply(img, img);
        return img;
    }

    private Mat cleanMask(Mat img) {
        img = img.clone();
        Imgproc.medianBlur(img, img, 75);
        Imgproc.blur(img, img, new Size(5, 5));
        Imgproc.threshold(img, img, 125, 255, Imgproc.THRESH_BINARY);
        Imgproc.dilate(img, img, Mat.ones(3, 3, CvType.CV_8UC1));

//        Imgproc.medianBlur(img, img, 5);
        return img;
    }

    private List<MatOfPoint> findContours(Mat image) {
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        return contours;
    }

    private List<Rect> findFilteredBoundingBoxes(List<MatOfPoint> contours) {
        List<Rect> boundingBoxes = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Rect boundingBox = Imgproc.boundingRect(contour);
            if (boundingBox.area() >= mBoxThreshold) {
                boundingBoxes.add(boundingBox);
            }
        }
        return boundingBoxes;
    }

    protected void process(Mat img) {
        this.inputImage = convertMat(img);
        this.rawForegroundMask = findMask(this.inputImage);
        this.cleanedForegroundMask = cleanMask(this.rawForegroundMask);
        this.rawContours = findContours(this.cleanedForegroundMask);
        this.filteredBoundingBoxes = findFilteredBoundingBoxes(this.rawContours);
    }

    protected Mat getPreviewMat(boolean showGrid) {
        Mat contoursWithBoundingBoxesMat = Mat.zeros(inputImage.size(), CvType.CV_8UC3);
        Imgproc.drawContours(contoursWithBoundingBoxesMat, rawContours, -1, new Scalar(255, 255, 255));

        Mat inputImageWithBoundingBoxesMat = inputImage.clone();

        for (Rect boundingBox : filteredBoundingBoxes) {
            Imgproc.rectangle(contoursWithBoundingBoxesMat, boundingBox.tl(), boundingBox.br(), new Scalar(0, 255, 0));
            Imgproc.rectangle(inputImageWithBoundingBoxesMat, boundingBox.tl(), boundingBox.br(), new Scalar(0, 255, 0));
        }


        Mat previewMat = generatePreviewMat(2, 4, showGrid, inputImage, rawForegroundMask, cleanedForegroundMask, contoursWithBoundingBoxesMat, inputImageWithBoundingBoxesMat);
        return previewMat;
    }

    private Mat generatePreviewMat(final int NUM_ROWS, final int NUM_COLS, boolean showGrid, Mat... mats) {

        int previewFrameWidth = (int) (SCREEN_SIZE.width / NUM_COLS);
        int previewFrameHeight = (int) (SCREEN_SIZE.height / NUM_ROWS);

        Mat previewMat = new Mat(SCREEN_SIZE, CvType.CV_8UC3);

        Queue<Mat> matList = new LinkedList<>();

        for (Mat previewFrame : mats) {
            previewFrame = convertMat(previewFrame);

            if (showGrid) {
                drawGrid(previewFrame, 100);
            }

            Imgproc.resize(previewFrame, previewFrame, new Size(previewFrameWidth, previewFrameHeight));
            matList.add(previewFrame);
        }

        Mat blank = new Mat(new Size(previewFrameWidth, previewFrameHeight), CvType.CV_8UC3, new Scalar(255, 255, 255));
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Mat previewSubMat = matList.poll();
                if (previewSubMat != null) {
                    previewSubMat.copyTo(previewMat.submat(row * previewFrameHeight, (row + 1) * previewFrameHeight, col * previewFrameWidth, (col + 1) * previewFrameWidth));
                } else {
                    blank.copyTo(previewMat.submat(row * previewFrameHeight, (row + 1) * previewFrameHeight, col * previewFrameWidth, (col + 1) * previewFrameWidth));
                }
            }
        }

        //TODO more elelgant & Adaptable way to do labels
        String[] labelStrings = {"Source", "Raw Bg", "Cleaned Bg", "Contours", "Bounding Boxes"};
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS && labelStrings.length > row * NUM_COLS + col; col++) {
                Imgproc.putText(previewMat, labelStrings[row * NUM_COLS + col],
                        new Point(col * previewFrameWidth + 20, row * previewFrameHeight + 100),
                        FONT_HERSHEY_SIMPLEX, 2.5, new Scalar(0, 0, 255), 5, Core.FILLED);
            }
        }

        Imgproc.putText(previewMat, "Zone 1:"    + Integer.toString(mZone1Count)
                                     + " Zone 2: "   + Integer.toString(mZone2Count) + " Location: " + currLoc,
                new Point(30, SCREEN_SIZE.height - 30)
               ,FONT_HERSHEY_SIMPLEX, 1.5, new Scalar(0,0,255),5, Core.FILLED);



        return previewMat;
    }

    private void drawGrid(Mat mat, int size) {
        for (int row = 0; (row += size) < mat.rows(); ) {
            Imgproc.line(mat, new Point(0, row), new Point(mat.cols(), row), new Scalar(255, 170, 0));
        }
        for (int col = 0; (col += size) < mat.cols(); ) {
            Imgproc.line(mat, new Point(col, 0), new Point(col, mat.rows()), new Scalar(255, 170, 0));
        }
        Imgproc.rectangle(mat, new Point(0, 0), new Point(mat.cols() - 1, mat.rows() - 1), new Scalar(0, 0, 255));
    }

    protected void exportImage(Rect boundingBox, ExitDirection direction) {
        Log.i(TAG, "New Vehicle Image marked for export");
        Pair<Mat, ExitDirection> vehicleMat = new Pair<Mat, ExitDirection>(inputImage.submat(boundingBox).clone(), direction);

        boolean retry = true;
        while (retry) {
            Log.i(TAG, "Sending Image to CloudRails Queue");
            try {
                CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().UPLOAD_QUEUE.put(vehicleMat);
                retry = false;
            } catch (InterruptedException e) {
                Log.w(TAG, "Interrupted when trying to send image to CloudRails Queue");
                e.printStackTrace();
            }
        }
    }

    public void setCurrLoc(String location){
        currLoc = location;
    }

}
