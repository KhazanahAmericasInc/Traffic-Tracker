//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.xphoto;

// C++: class SimpleWB
//javadoc: SimpleWB

public class SimpleWB extends WhiteBalancer {

    protected SimpleWB(long addr) {
        super(addr);
    }

    // internal usage only
    public static SimpleWB __fromPtr__(long addr) {
        return new SimpleWB(addr);
    }

    //
    // C++:  float cv::xphoto::SimpleWB::getInputMax()
    //

    // C++:  float cv::xphoto::SimpleWB::getInputMax()
    private static native float getInputMax_0(long nativeObj);


    //
    // C++:  float cv::xphoto::SimpleWB::getInputMin()
    //

    // C++:  float cv::xphoto::SimpleWB::getInputMin()
    private static native float getInputMin_0(long nativeObj);


    //
    // C++:  float cv::xphoto::SimpleWB::getOutputMax()
    //

    // C++:  float cv::xphoto::SimpleWB::getOutputMax()
    private static native float getOutputMax_0(long nativeObj);


    //
    // C++:  float cv::xphoto::SimpleWB::getOutputMin()
    //

    // C++:  float cv::xphoto::SimpleWB::getOutputMin()
    private static native float getOutputMin_0(long nativeObj);


    //
    // C++:  float cv::xphoto::SimpleWB::getP()
    //

    // C++:  float cv::xphoto::SimpleWB::getP()
    private static native float getP_0(long nativeObj);


    //
    // C++:  void cv::xphoto::SimpleWB::setInputMax(float val)
    //

    // C++:  void cv::xphoto::SimpleWB::setInputMax(float val)
    private static native void setInputMax_0(long nativeObj, float val);


    //
    // C++:  void cv::xphoto::SimpleWB::setInputMin(float val)
    //

    // C++:  void cv::xphoto::SimpleWB::setInputMin(float val)
    private static native void setInputMin_0(long nativeObj, float val);


    //
    // C++:  void cv::xphoto::SimpleWB::setOutputMax(float val)
    //

    // C++:  void cv::xphoto::SimpleWB::setOutputMax(float val)
    private static native void setOutputMax_0(long nativeObj, float val);


    //
    // C++:  void cv::xphoto::SimpleWB::setOutputMin(float val)
    //

    // C++:  void cv::xphoto::SimpleWB::setOutputMin(float val)
    private static native void setOutputMin_0(long nativeObj, float val);


    //
    // C++:  void cv::xphoto::SimpleWB::setP(float val)
    //

    // C++:  void cv::xphoto::SimpleWB::setP(float val)
    private static native void setP_0(long nativeObj, float val);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: SimpleWB::getInputMax()
    public float getInputMax() {

        float retVal = getInputMax_0(nativeObj);

        return retVal;
    }

    //javadoc: SimpleWB::setInputMax(val)
    public void setInputMax(float val) {

        setInputMax_0(nativeObj, val);

        return;
    }

    //javadoc: SimpleWB::getInputMin()
    public float getInputMin() {

        float retVal = getInputMin_0(nativeObj);

        return retVal;
    }

    //javadoc: SimpleWB::setInputMin(val)
    public void setInputMin(float val) {

        setInputMin_0(nativeObj, val);

        return;
    }

    //javadoc: SimpleWB::getOutputMax()
    public float getOutputMax() {

        float retVal = getOutputMax_0(nativeObj);

        return retVal;
    }

    //javadoc: SimpleWB::setOutputMax(val)
    public void setOutputMax(float val) {

        setOutputMax_0(nativeObj, val);

        return;
    }

    //javadoc: SimpleWB::getOutputMin()
    public float getOutputMin() {

        float retVal = getOutputMin_0(nativeObj);

        return retVal;
    }

    //javadoc: SimpleWB::setOutputMin(val)
    public void setOutputMin(float val) {

        setOutputMin_0(nativeObj, val);

        return;
    }

    //javadoc: SimpleWB::getP()
    public float getP() {

        float retVal = getP_0(nativeObj);

        return retVal;
    }

    //javadoc: SimpleWB::setP(val)
    public void setP(float val) {

        setP_0(nativeObj, val);

        return;
    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
