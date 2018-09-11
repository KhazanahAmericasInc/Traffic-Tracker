//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.bioinspired;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Size;

// C++: class TransientAreasSegmentationModule
//javadoc: TransientAreasSegmentationModule

public class TransientAreasSegmentationModule extends Algorithm {

    protected TransientAreasSegmentationModule(long addr) {
        super(addr);
    }

    // internal usage only
    public static TransientAreasSegmentationModule __fromPtr__(long addr) {
        return new TransientAreasSegmentationModule(addr);
    }

    //
    // C++: static Ptr_TransientAreasSegmentationModule cv::bioinspired::TransientAreasSegmentationModule::create(Size inputSize)
    //

    //javadoc: TransientAreasSegmentationModule::create(inputSize)
    public static TransientAreasSegmentationModule create(Size inputSize) {

        TransientAreasSegmentationModule retVal = TransientAreasSegmentationModule.__fromPtr__(create_0(inputSize.width, inputSize.height));

        return retVal;
    }


    //
    // C++:  Size cv::bioinspired::TransientAreasSegmentationModule::getSize()
    //

    // C++: static Ptr_TransientAreasSegmentationModule cv::bioinspired::TransientAreasSegmentationModule::create(Size inputSize)
    private static native long create_0(double inputSize_width, double inputSize_height);


    //
    // C++:  String cv::bioinspired::TransientAreasSegmentationModule::printSetup()
    //

    // C++:  Size cv::bioinspired::TransientAreasSegmentationModule::getSize()
    private static native double[] getSize_0(long nativeObj);


    //
    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::clearAllBuffers()
    //

    // C++:  String cv::bioinspired::TransientAreasSegmentationModule::printSetup()
    private static native String printSetup_0(long nativeObj);


    //
    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::getSegmentationPicture(Mat& transientAreas)
    //

    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::clearAllBuffers()
    private static native void clearAllBuffers_0(long nativeObj);


    //
    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::run(Mat inputToSegment, int channelIndex = 0)
    //

    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::getSegmentationPicture(Mat& transientAreas)
    private static native void getSegmentationPicture_0(long nativeObj, long transientAreas_nativeObj);

    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::run(Mat inputToSegment, int channelIndex = 0)
    private static native void run_0(long nativeObj, long inputToSegment_nativeObj, int channelIndex);


    //
    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::setup(String segmentationParameterFile = "", bool applyDefaultSetupOnFailure = true)
    //

    private static native void run_1(long nativeObj, long inputToSegment_nativeObj);

    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::setup(String segmentationParameterFile = "", bool applyDefaultSetupOnFailure = true)
    private static native void setup_0(long nativeObj, String segmentationParameterFile, boolean applyDefaultSetupOnFailure);

    private static native void setup_1(long nativeObj, String segmentationParameterFile);


    //
    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::write(String fs)
    //

    private static native void setup_2(long nativeObj);

    // C++:  void cv::bioinspired::TransientAreasSegmentationModule::write(String fs)
    private static native void write_0(long nativeObj, String fs);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: TransientAreasSegmentationModule::getSize()
    public Size getSize() {

        Size retVal = new Size(getSize_0(nativeObj));

        return retVal;
    }

    //javadoc: TransientAreasSegmentationModule::printSetup()
    public String printSetup() {

        String retVal = printSetup_0(nativeObj);

        return retVal;
    }

    //javadoc: TransientAreasSegmentationModule::clearAllBuffers()
    public void clearAllBuffers() {

        clearAllBuffers_0(nativeObj);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::getSegmentationPicture(transientAreas)
    public void getSegmentationPicture(Mat transientAreas) {

        getSegmentationPicture_0(nativeObj, transientAreas.nativeObj);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::run(inputToSegment, channelIndex)
    public void run(Mat inputToSegment, int channelIndex) {

        run_0(nativeObj, inputToSegment.nativeObj, channelIndex);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::run(inputToSegment)
    public void run(Mat inputToSegment) {

        run_1(nativeObj, inputToSegment.nativeObj);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::setup(segmentationParameterFile, applyDefaultSetupOnFailure)
    public void setup(String segmentationParameterFile, boolean applyDefaultSetupOnFailure) {

        setup_0(nativeObj, segmentationParameterFile, applyDefaultSetupOnFailure);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::setup(segmentationParameterFile)
    public void setup(String segmentationParameterFile) {

        setup_1(nativeObj, segmentationParameterFile);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::setup()
    public void setup() {

        setup_2(nativeObj);

        return;
    }

    //javadoc: TransientAreasSegmentationModule::write(fs)
    public void write(String fs) {

        write_0(nativeObj, fs);

        return;
    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
