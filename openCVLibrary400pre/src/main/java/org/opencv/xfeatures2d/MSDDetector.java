//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

// C++: class MSDDetector
//javadoc: MSDDetector

public class MSDDetector extends Feature2D {

    protected MSDDetector(long addr) {
        super(addr);
    }

    // internal usage only
    public static MSDDetector __fromPtr__(long addr) {
        return new MSDDetector(addr);
    }

    // native support for java finalize()
    private static native void delete(long nativeObj);

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}