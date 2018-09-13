package com.example.wmmc88.traffictracker;

import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class KCFTrackerCountingSolution extends CountingSolution {
    private static final String TAG = KCFTrackerCountingSolution.class.getSimpleName();

    private static final int MAX_TRACKER_AGE = 30;//increase from 20
    private List<CustomKCFTracker> trackers = new LinkedList<>();

    public KCFTrackerCountingSolution(Size screenSize) {
        super(screenSize);
    }

    protected void process(Mat img) {
        super.process(img);
        this.updateTrackers(this.inputImage);
        this.addTrackers(this.inputImage, this.filteredBoundingBoxes);
    }

    private void updateTrackers(Mat img) {
        Iterator<CustomKCFTracker> iter = trackers.iterator();

        while (iter.hasNext()) {
            CustomKCFTracker tracker = iter.next();

            if (tracker.age < MAX_TRACKER_AGE) {
                tracker.update(img);
                if (!tracker.insideImage()) {
                    iter.remove();
                }
            } else {
                iter.remove();
            }
        }
    }

    private void addTrackers(Mat img, List<Rect> boundingBoxes) {
        for (Rect boundingBox : boundingBoxes) {
            //TODO change to user set rect
            Rect Zone1ExitZone = new Rect(new Point(-1, -1), new Point(25, this.inputImage.height() + 1));
            Rect Zone2ExitZone = new Rect(new Point(this.inputImage.width() - 25, -1), new Point(this.inputImage.width() + 1, this.inputImage.height() + 1));

            if (!Zone1ExitZone.contains(boundingBox.tl()) && !Zone2ExitZone.contains(boundingBox.br())) {
                boolean found = false;
                CustomKCFTracker trackerToReinitialize = null;
                for (CustomKCFTracker tracker : this.trackers) {
                    if (tracker.insideBoundingBox(boundingBox)) {

                        if (tracker.trackingLost) {
                            trackerToReinitialize = tracker;
                        } else {
                            found = true;
                            break;
                        }

                    }
                }
                //could not match bounding box to active tracker
                if (!found) {
                    if (trackerToReinitialize != null) {
                        trackerToReinitialize.reinitialize(img, boundingBox);
                    } else {
                        int boundingBoxCentreX = boundingBox.x + boundingBox.width / 2;
                        if (boundingBoxCentreX < this.inputImage.cols() / 2) { //going right
                            CustomKCFTracker tracker = new CustomKCFTracker(boundingBox, ExitDirection.ZONE2, img);
                            trackers.add(tracker);
                            this.mZone2Count++;
                            Log.i(TAG, "Vehicle Detected moving to Zone 2");
                            this.exportImage(boundingBox, ExitDirection.ZONE2);
                        } else {
                            CustomKCFTracker tracker = new CustomKCFTracker(boundingBox, ExitDirection.ZONE1, img);
                            trackers.add(tracker);
                            this.mZone1Count++;
                            Log.i(TAG, "Vehicle Detected moving to Zone 1");
                            this.exportImage(boundingBox, ExitDirection.ZONE1);
                        }
                    }
                }
            }
        }

    }

    private Point calculateCenter(Rect boundingBox) {
        int x = boundingBox.x + boundingBox.width / 2;
        int y = boundingBox.y + boundingBox.height / 2;
        return new Point(x, y);
    }

    public int getNumActiveTrackers() {
        return this.trackers.size();
    }

}
