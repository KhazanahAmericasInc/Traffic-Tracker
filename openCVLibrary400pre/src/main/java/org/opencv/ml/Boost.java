//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

// C++: class Boost
//javadoc: Boost

public class Boost extends DTrees {

    protected Boost(long addr) {
        super(addr);
    }

    // internal usage only
    public static Boost __fromPtr__(long addr) {
        return new Boost(addr);
    }

    public static final int
            DISCRETE = 0,
            REAL = 1,
            LOGIT = 2,
            GENTLE = 3;


    //
    // C++: static Ptr_Boost cv::ml::Boost::create()
    //

    //javadoc: Boost::create()
    public static Boost create() {

        Boost retVal = Boost.__fromPtr__(create_0());

        return retVal;
    }


    //
    // C++: static Ptr_Boost cv::ml::Boost::load(String filepath, String nodeName = String())
    //

    //javadoc: Boost::load(filepath, nodeName)
    public static Boost load(String filepath, String nodeName) {

        Boost retVal = Boost.__fromPtr__(load_0(filepath, nodeName));

        return retVal;
    }

    //javadoc: Boost::load(filepath)
    public static Boost load(String filepath) {

        Boost retVal = Boost.__fromPtr__(load_1(filepath));

        return retVal;
    }


    //
    // C++:  double cv::ml::Boost::getWeightTrimRate()
    //

    // C++: static Ptr_Boost cv::ml::Boost::create()
    private static native long create_0();


    //
    // C++:  int cv::ml::Boost::getBoostType()
    //

    // C++: static Ptr_Boost cv::ml::Boost::load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);


    //
    // C++:  int cv::ml::Boost::getWeakCount()
    //

    // C++:  double cv::ml::Boost::getWeightTrimRate()
    private static native double getWeightTrimRate_0(long nativeObj);


    //
    // C++:  void cv::ml::Boost::setBoostType(int val)
    //

    // C++:  int cv::ml::Boost::getBoostType()
    private static native int getBoostType_0(long nativeObj);


    //
    // C++:  void cv::ml::Boost::setWeakCount(int val)
    //

    // C++:  int cv::ml::Boost::getWeakCount()
    private static native int getWeakCount_0(long nativeObj);


    //
    // C++:  void cv::ml::Boost::setWeightTrimRate(double val)
    //

    // C++:  void cv::ml::Boost::setBoostType(int val)
    private static native void setBoostType_0(long nativeObj, int val);

    // C++:  void cv::ml::Boost::setWeakCount(int val)
    private static native void setWeakCount_0(long nativeObj, int val);

    // C++:  void cv::ml::Boost::setWeightTrimRate(double val)
    private static native void setWeightTrimRate_0(long nativeObj, double val);

    private static native long load_1(String filepath);

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

    //javadoc: Boost::getWeightTrimRate()
    public double getWeightTrimRate() {

        double retVal = getWeightTrimRate_0(nativeObj);

        return retVal;
    }

    //javadoc: Boost::setWeightTrimRate(val)
    public void setWeightTrimRate(double val) {

        setWeightTrimRate_0(nativeObj, val);

        return;
    }

    //javadoc: Boost::getBoostType()
    public int getBoostType() {

        int retVal = getBoostType_0(nativeObj);

        return retVal;
    }

    //javadoc: Boost::setBoostType(val)
    public void setBoostType(int val) {

        setBoostType_0(nativeObj, val);

        return;
    }

    //javadoc: Boost::getWeakCount()
    public int getWeakCount() {

        int retVal = getWeakCount_0(nativeObj);

        return retVal;
    }

    //javadoc: Boost::setWeakCount(val)
    public void setWeakCount(int val) {

        setWeakCount_0(nativeObj, val);

        return;
    }

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
