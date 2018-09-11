//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.bioinspired;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Size;

// C++: class Retina
//javadoc: Retina

public class Retina extends Algorithm {

    protected Retina(long addr) {
        super(addr);
    }

    // internal usage only
    public static Retina __fromPtr__(long addr) {
        return new Retina(addr);
    }

    //
    // C++:  Mat cv::bioinspired::Retina::getMagnoRAW()
    //

    //javadoc: Retina::create(inputSize, colorMode, colorSamplingMethod, useRetinaLogSampling, reductionFactor, samplingStrenght)
    public static Retina create(Size inputSize, boolean colorMode, int colorSamplingMethod, boolean useRetinaLogSampling, float reductionFactor, float samplingStrenght) {

        Retina retVal = Retina.__fromPtr__(create_0(inputSize.width, inputSize.height, colorMode, colorSamplingMethod, useRetinaLogSampling, reductionFactor, samplingStrenght));

        return retVal;
    }


    //
    // C++:  Mat cv::bioinspired::Retina::getParvoRAW()
    //

    //javadoc: Retina::create(inputSize, colorMode, colorSamplingMethod, useRetinaLogSampling, reductionFactor)
    public static Retina create(Size inputSize, boolean colorMode, int colorSamplingMethod, boolean useRetinaLogSampling, float reductionFactor) {

        Retina retVal = Retina.__fromPtr__(create_1(inputSize.width, inputSize.height, colorMode, colorSamplingMethod, useRetinaLogSampling, reductionFactor));

        return retVal;
    }


    //
    // C++: static Ptr_Retina cv::bioinspired::Retina::create(Size inputSize, bool colorMode, int colorSamplingMethod = RETINA_COLOR_BAYER, bool useRetinaLogSampling = false, float reductionFactor = 1.0f, float samplingStrenght = 10.0f)
    //

    //javadoc: Retina::create(inputSize, colorMode, colorSamplingMethod, useRetinaLogSampling)
    public static Retina create(Size inputSize, boolean colorMode, int colorSamplingMethod, boolean useRetinaLogSampling) {

        Retina retVal = Retina.__fromPtr__(create_2(inputSize.width, inputSize.height, colorMode, colorSamplingMethod, useRetinaLogSampling));

        return retVal;
    }

    //javadoc: Retina::create(inputSize, colorMode, colorSamplingMethod)
    public static Retina create(Size inputSize, boolean colorMode, int colorSamplingMethod) {

        Retina retVal = Retina.__fromPtr__(create_3(inputSize.width, inputSize.height, colorMode, colorSamplingMethod));

        return retVal;
    }

    //javadoc: Retina::create(inputSize, colorMode)
    public static Retina create(Size inputSize, boolean colorMode) {

        Retina retVal = Retina.__fromPtr__(create_4(inputSize.width, inputSize.height, colorMode));

        return retVal;
    }

    //javadoc: Retina::create(inputSize)
    public static Retina create(Size inputSize) {

        Retina retVal = Retina.__fromPtr__(create_5(inputSize.width, inputSize.height));

        return retVal;
    }

    // C++:  Mat cv::bioinspired::Retina::getMagnoRAW()
    private static native long getMagnoRAW_0(long nativeObj);


    //
    // C++: static Ptr_Retina cv::bioinspired::Retina::create(Size inputSize)
    //

    // C++:  Mat cv::bioinspired::Retina::getParvoRAW()
    private static native long getParvoRAW_0(long nativeObj);


    //
    // C++:  Size cv::bioinspired::Retina::getInputSize()
    //

    // C++: static Ptr_Retina cv::bioinspired::Retina::create(Size inputSize, bool colorMode, int colorSamplingMethod = RETINA_COLOR_BAYER, bool useRetinaLogSampling = false, float reductionFactor = 1.0f, float samplingStrenght = 10.0f)
    private static native long create_0(double inputSize_width, double inputSize_height, boolean colorMode, int colorSamplingMethod, boolean useRetinaLogSampling, float reductionFactor, float samplingStrenght);


    //
    // C++:  Size cv::bioinspired::Retina::getOutputSize()
    //

    private static native long create_1(double inputSize_width, double inputSize_height, boolean colorMode, int colorSamplingMethod, boolean useRetinaLogSampling, float reductionFactor);


    //
    // C++:  String cv::bioinspired::Retina::printSetup()
    //

    private static native long create_2(double inputSize_width, double inputSize_height, boolean colorMode, int colorSamplingMethod, boolean useRetinaLogSampling);


    //
    // C++:  void cv::bioinspired::Retina::activateContoursProcessing(bool activate)
    //

    private static native long create_3(double inputSize_width, double inputSize_height, boolean colorMode, int colorSamplingMethod);


    //
    // C++:  void cv::bioinspired::Retina::activateMovingContoursProcessing(bool activate)
    //

    private static native long create_4(double inputSize_width, double inputSize_height, boolean colorMode);


    //
    // C++:  void cv::bioinspired::Retina::applyFastToneMapping(Mat inputImage, Mat& outputToneMappedImage)
    //

    // C++: static Ptr_Retina cv::bioinspired::Retina::create(Size inputSize)
    private static native long create_5(double inputSize_width, double inputSize_height);


    //
    // C++:  void cv::bioinspired::Retina::clearBuffers()
    //

    // C++:  Size cv::bioinspired::Retina::getInputSize()
    private static native double[] getInputSize_0(long nativeObj);


    //
    // C++:  void cv::bioinspired::Retina::getMagno(Mat& retinaOutput_magno)
    //

    // C++:  Size cv::bioinspired::Retina::getOutputSize()
    private static native double[] getOutputSize_0(long nativeObj);


    //
    // C++:  void cv::bioinspired::Retina::getMagnoRAW(Mat& retinaOutput_magno)
    //

    // C++:  String cv::bioinspired::Retina::printSetup()
    private static native String printSetup_0(long nativeObj);


    //
    // C++:  void cv::bioinspired::Retina::getParvo(Mat& retinaOutput_parvo)
    //

    // C++:  void cv::bioinspired::Retina::activateContoursProcessing(bool activate)
    private static native void activateContoursProcessing_0(long nativeObj, boolean activate);


    //
    // C++:  void cv::bioinspired::Retina::getParvoRAW(Mat& retinaOutput_parvo)
    //

    // C++:  void cv::bioinspired::Retina::activateMovingContoursProcessing(bool activate)
    private static native void activateMovingContoursProcessing_0(long nativeObj, boolean activate);


    //
    // C++:  void cv::bioinspired::Retina::run(Mat inputImage)
    //

    // C++:  void cv::bioinspired::Retina::applyFastToneMapping(Mat inputImage, Mat& outputToneMappedImage)
    private static native void applyFastToneMapping_0(long nativeObj, long inputImage_nativeObj, long outputToneMappedImage_nativeObj);


    //
    // C++:  void cv::bioinspired::Retina::setColorSaturation(bool saturateColors = true, float colorSaturationValue = 4.0f)
    //

    // C++:  void cv::bioinspired::Retina::clearBuffers()
    private static native void clearBuffers_0(long nativeObj);

    // C++:  void cv::bioinspired::Retina::getMagno(Mat& retinaOutput_magno)
    private static native void getMagno_0(long nativeObj, long retinaOutput_magno_nativeObj);

    // C++:  void cv::bioinspired::Retina::getMagnoRAW(Mat& retinaOutput_magno)
    private static native void getMagnoRAW_1(long nativeObj, long retinaOutput_magno_nativeObj);


    //
    // C++:  void cv::bioinspired::Retina::setup(String retinaParameterFile = "", bool applyDefaultSetupOnFailure = true)
    //

    // C++:  void cv::bioinspired::Retina::getParvo(Mat& retinaOutput_parvo)
    private static native void getParvo_0(long nativeObj, long retinaOutput_parvo_nativeObj);

    // C++:  void cv::bioinspired::Retina::getParvoRAW(Mat& retinaOutput_parvo)
    private static native void getParvoRAW_1(long nativeObj, long retinaOutput_parvo_nativeObj);

    // C++:  void cv::bioinspired::Retina::run(Mat inputImage)
    private static native void run_0(long nativeObj, long inputImage_nativeObj);


    //
    // C++:  void cv::bioinspired::Retina::setupIPLMagnoChannel(bool normaliseOutput = true, float parasolCells_beta = 0.f, float parasolCells_tau = 0.f, float parasolCells_k = 7.f, float amacrinCellsTemporalCutFrequency = 1.2f, float V0CompressionParameter = 0.95f, float localAdaptintegration_tau = 0.f, float localAdaptintegration_k = 7.f)
    //

    // C++:  void cv::bioinspired::Retina::setColorSaturation(bool saturateColors = true, float colorSaturationValue = 4.0f)
    private static native void setColorSaturation_0(long nativeObj, boolean saturateColors, float colorSaturationValue);

    private static native void setColorSaturation_1(long nativeObj, boolean saturateColors);

    private static native void setColorSaturation_2(long nativeObj);

    // C++:  void cv::bioinspired::Retina::setup(String retinaParameterFile = "", bool applyDefaultSetupOnFailure = true)
    private static native void setup_0(long nativeObj, String retinaParameterFile, boolean applyDefaultSetupOnFailure);

    private static native void setup_1(long nativeObj, String retinaParameterFile);

    private static native void setup_2(long nativeObj);

    // C++:  void cv::bioinspired::Retina::setupIPLMagnoChannel(bool normaliseOutput = true, float parasolCells_beta = 0.f, float parasolCells_tau = 0.f, float parasolCells_k = 7.f, float amacrinCellsTemporalCutFrequency = 1.2f, float V0CompressionParameter = 0.95f, float localAdaptintegration_tau = 0.f, float localAdaptintegration_k = 7.f)
    private static native void setupIPLMagnoChannel_0(long nativeObj, boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency, float V0CompressionParameter, float localAdaptintegration_tau, float localAdaptintegration_k);

    private static native void setupIPLMagnoChannel_1(long nativeObj, boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency, float V0CompressionParameter, float localAdaptintegration_tau);

    private static native void setupIPLMagnoChannel_2(long nativeObj, boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency, float V0CompressionParameter);


    //
    // C++:  void cv::bioinspired::Retina::setupOPLandIPLParvoChannel(bool colorMode = true, bool normaliseOutput = true, float photoreceptorsLocalAdaptationSensitivity = 0.7f, float photoreceptorsTemporalConstant = 0.5f, float photoreceptorsSpatialConstant = 0.53f, float horizontalCellsGain = 0.f, float HcellsTemporalConstant = 1.f, float HcellsSpatialConstant = 7.f, float ganglionCellsSensitivity = 0.7f)
    //

    private static native void setupIPLMagnoChannel_3(long nativeObj, boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency);

    private static native void setupIPLMagnoChannel_4(long nativeObj, boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k);

    private static native void setupIPLMagnoChannel_5(long nativeObj, boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau);

    private static native void setupIPLMagnoChannel_6(long nativeObj, boolean normaliseOutput, float parasolCells_beta);

    private static native void setupIPLMagnoChannel_7(long nativeObj, boolean normaliseOutput);

    private static native void setupIPLMagnoChannel_8(long nativeObj);

    // C++:  void cv::bioinspired::Retina::setupOPLandIPLParvoChannel(bool colorMode = true, bool normaliseOutput = true, float photoreceptorsLocalAdaptationSensitivity = 0.7f, float photoreceptorsTemporalConstant = 0.5f, float photoreceptorsSpatialConstant = 0.53f, float horizontalCellsGain = 0.f, float HcellsTemporalConstant = 1.f, float HcellsSpatialConstant = 7.f, float ganglionCellsSensitivity = 0.7f)
    private static native void setupOPLandIPLParvoChannel_0(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain, float HcellsTemporalConstant, float HcellsSpatialConstant, float ganglionCellsSensitivity);

    private static native void setupOPLandIPLParvoChannel_1(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain, float HcellsTemporalConstant, float HcellsSpatialConstant);

    private static native void setupOPLandIPLParvoChannel_2(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain, float HcellsTemporalConstant);

    private static native void setupOPLandIPLParvoChannel_3(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain);


    //
    // C++:  void cv::bioinspired::Retina::write(String fs)
    //

    private static native void setupOPLandIPLParvoChannel_4(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant);

    private static native void setupOPLandIPLParvoChannel_5(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant);

    private static native void setupOPLandIPLParvoChannel_6(long nativeObj, boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity);

    private static native void setupOPLandIPLParvoChannel_7(long nativeObj, boolean colorMode, boolean normaliseOutput);

    private static native void setupOPLandIPLParvoChannel_8(long nativeObj, boolean colorMode);

    private static native void setupOPLandIPLParvoChannel_9(long nativeObj);

    // C++:  void cv::bioinspired::Retina::write(String fs)
    private static native void write_0(long nativeObj, String fs);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: Retina::getMagnoRAW()
    public Mat getMagnoRAW() {

        Mat retVal = new Mat(getMagnoRAW_0(nativeObj));

        return retVal;
    }

    //javadoc: Retina::getParvoRAW()
    public Mat getParvoRAW() {

        Mat retVal = new Mat(getParvoRAW_0(nativeObj));

        return retVal;
    }

    //javadoc: Retina::getInputSize()
    public Size getInputSize() {

        Size retVal = new Size(getInputSize_0(nativeObj));

        return retVal;
    }

    //javadoc: Retina::getOutputSize()
    public Size getOutputSize() {

        Size retVal = new Size(getOutputSize_0(nativeObj));

        return retVal;
    }

    //javadoc: Retina::printSetup()
    public String printSetup() {

        String retVal = printSetup_0(nativeObj);

        return retVal;
    }

    //javadoc: Retina::activateContoursProcessing(activate)
    public void activateContoursProcessing(boolean activate) {

        activateContoursProcessing_0(nativeObj, activate);

        return;
    }

    //javadoc: Retina::activateMovingContoursProcessing(activate)
    public void activateMovingContoursProcessing(boolean activate) {

        activateMovingContoursProcessing_0(nativeObj, activate);

        return;
    }

    //javadoc: Retina::applyFastToneMapping(inputImage, outputToneMappedImage)
    public void applyFastToneMapping(Mat inputImage, Mat outputToneMappedImage) {

        applyFastToneMapping_0(nativeObj, inputImage.nativeObj, outputToneMappedImage.nativeObj);

        return;
    }

    //javadoc: Retina::clearBuffers()
    public void clearBuffers() {

        clearBuffers_0(nativeObj);

        return;
    }

    //javadoc: Retina::getMagno(retinaOutput_magno)
    public void getMagno(Mat retinaOutput_magno) {

        getMagno_0(nativeObj, retinaOutput_magno.nativeObj);

        return;
    }

    //javadoc: Retina::getMagnoRAW(retinaOutput_magno)
    public void getMagnoRAW(Mat retinaOutput_magno) {

        getMagnoRAW_1(nativeObj, retinaOutput_magno.nativeObj);

        return;
    }

    //javadoc: Retina::getParvo(retinaOutput_parvo)
    public void getParvo(Mat retinaOutput_parvo) {

        getParvo_0(nativeObj, retinaOutput_parvo.nativeObj);

        return;
    }

    //javadoc: Retina::getParvoRAW(retinaOutput_parvo)
    public void getParvoRAW(Mat retinaOutput_parvo) {

        getParvoRAW_1(nativeObj, retinaOutput_parvo.nativeObj);

        return;
    }

    //javadoc: Retina::run(inputImage)
    public void run(Mat inputImage) {

        run_0(nativeObj, inputImage.nativeObj);

        return;
    }

    //javadoc: Retina::setColorSaturation(saturateColors, colorSaturationValue)
    public void setColorSaturation(boolean saturateColors, float colorSaturationValue) {

        setColorSaturation_0(nativeObj, saturateColors, colorSaturationValue);

        return;
    }

    //javadoc: Retina::setColorSaturation(saturateColors)
    public void setColorSaturation(boolean saturateColors) {

        setColorSaturation_1(nativeObj, saturateColors);

        return;
    }

    //javadoc: Retina::setColorSaturation()
    public void setColorSaturation() {

        setColorSaturation_2(nativeObj);

        return;
    }

    //javadoc: Retina::setup(retinaParameterFile, applyDefaultSetupOnFailure)
    public void setup(String retinaParameterFile, boolean applyDefaultSetupOnFailure) {

        setup_0(nativeObj, retinaParameterFile, applyDefaultSetupOnFailure);

        return;
    }

    //javadoc: Retina::setup(retinaParameterFile)
    public void setup(String retinaParameterFile) {

        setup_1(nativeObj, retinaParameterFile);

        return;
    }

    //javadoc: Retina::setup()
    public void setup() {

        setup_2(nativeObj);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency, V0CompressionParameter, localAdaptintegration_tau, localAdaptintegration_k)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency, float V0CompressionParameter, float localAdaptintegration_tau, float localAdaptintegration_k) {

        setupIPLMagnoChannel_0(nativeObj, normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency, V0CompressionParameter, localAdaptintegration_tau, localAdaptintegration_k);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency, V0CompressionParameter, localAdaptintegration_tau)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency, float V0CompressionParameter, float localAdaptintegration_tau) {

        setupIPLMagnoChannel_1(nativeObj, normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency, V0CompressionParameter, localAdaptintegration_tau);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency, V0CompressionParameter)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency, float V0CompressionParameter) {

        setupIPLMagnoChannel_2(nativeObj, normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency, V0CompressionParameter);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k, float amacrinCellsTemporalCutFrequency) {

        setupIPLMagnoChannel_3(nativeObj, normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k, amacrinCellsTemporalCutFrequency);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau, float parasolCells_k) {

        setupIPLMagnoChannel_4(nativeObj, normaliseOutput, parasolCells_beta, parasolCells_tau, parasolCells_k);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta, parasolCells_tau)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta, float parasolCells_tau) {

        setupIPLMagnoChannel_5(nativeObj, normaliseOutput, parasolCells_beta, parasolCells_tau);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput, parasolCells_beta)
    public void setupIPLMagnoChannel(boolean normaliseOutput, float parasolCells_beta) {

        setupIPLMagnoChannel_6(nativeObj, normaliseOutput, parasolCells_beta);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel(normaliseOutput)
    public void setupIPLMagnoChannel(boolean normaliseOutput) {

        setupIPLMagnoChannel_7(nativeObj, normaliseOutput);

        return;
    }

    //javadoc: Retina::setupIPLMagnoChannel()
    public void setupIPLMagnoChannel() {

        setupIPLMagnoChannel_8(nativeObj);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain, HcellsTemporalConstant, HcellsSpatialConstant, ganglionCellsSensitivity)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain, float HcellsTemporalConstant, float HcellsSpatialConstant, float ganglionCellsSensitivity) {

        setupOPLandIPLParvoChannel_0(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain, HcellsTemporalConstant, HcellsSpatialConstant, ganglionCellsSensitivity);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain, HcellsTemporalConstant, HcellsSpatialConstant)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain, float HcellsTemporalConstant, float HcellsSpatialConstant) {

        setupOPLandIPLParvoChannel_1(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain, HcellsTemporalConstant, HcellsSpatialConstant);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain, HcellsTemporalConstant)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain, float HcellsTemporalConstant) {

        setupOPLandIPLParvoChannel_2(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain, HcellsTemporalConstant);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant, float horizontalCellsGain) {

        setupOPLandIPLParvoChannel_3(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant, horizontalCellsGain);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant, float photoreceptorsSpatialConstant) {

        setupOPLandIPLParvoChannel_4(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant, photoreceptorsSpatialConstant);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity, float photoreceptorsTemporalConstant) {

        setupOPLandIPLParvoChannel_5(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity, photoreceptorsTemporalConstant);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput, float photoreceptorsLocalAdaptationSensitivity) {

        setupOPLandIPLParvoChannel_6(nativeObj, colorMode, normaliseOutput, photoreceptorsLocalAdaptationSensitivity);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode, normaliseOutput)
    public void setupOPLandIPLParvoChannel(boolean colorMode, boolean normaliseOutput) {

        setupOPLandIPLParvoChannel_7(nativeObj, colorMode, normaliseOutput);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel(colorMode)
    public void setupOPLandIPLParvoChannel(boolean colorMode) {

        setupOPLandIPLParvoChannel_8(nativeObj, colorMode);

        return;
    }

    //javadoc: Retina::setupOPLandIPLParvoChannel()
    public void setupOPLandIPLParvoChannel() {

        setupOPLandIPLParvoChannel_9(nativeObj);

        return;
    }

    //javadoc: Retina::write(fs)
    public void write(String fs) {

        write_0(nativeObj, fs);

        return;
    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
