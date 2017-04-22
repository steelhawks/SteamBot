package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.opencv.core.Core.*;
import org.opencv.core.MatOfPoint;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.*;

/**
 *
 */
public class GearPipeline extends Subsystem implements VisionPipeline {

	private Mat hsvThresholdOutput = new Mat();
	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void process(Mat source0){
    	
    	//Set hsvThreshold
    	Mat hsvThresholdInput = source0;
    	double[] hsvThresholdHue = {53.0, 85.0};
    	double[] hsvThresholdSaturation = {149.0, 255.0};
    	double[] hsvThresholdValue = {149.0, 255.0};
    	hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);
    	
    	//Find Contours
    	Mat findContoursInput = hsvThresholdOutput;
    	boolean findContoursExternalOnly = false;
    	findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);
    	
    	//FilterContours
    	ArrayList<MatOfPoint> filteredContours = findContoursOutput;
    	double filterContoursMinArea = 100.0;
    	double filterContoursMinPerimeter = 0.0;
    	double filterContoursMinWidth = 0.0;
    	double filterContoursMaxWidth = 1000.0;
    	double filterContoursMinHeight = 0.0;
    	double filterContoursMaxHeight = 10000.0;
    	double[] filterContoursSolidity = {60.0, 100.0};
    	double filterContoursMaxVertices = 1000000.0;
    	double filterContoursMinVertices = 0.0;
    	double filterContoursMinRatio = 0.0;
    	double filterContoursMaxRatio = 1000.0;
    	filterContours(filteredContours, filterContoursMinArea, filterContoursMinPerimeter, 
    			filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, 
    			filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, 
    			filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);
    }
    
    //getter methods
    public Mat getHsvThresholdOutput(){
    	return hsvThresholdOutput;
    }
    
    public ArrayList<MatOfPoint> getFindContoursOutput(){
    	return findContoursOutput;
    }
    
    public ArrayList<MatOfPoint> getFilterContoursOutput(){
    	return filterContoursOutput;
    }
    
    private void hsvThreshold(Mat input, double[] hue, double[] saturation, double[] value, Mat out){
    	Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
    	Core.inRange(out, new Scalar(hue[0], saturation[0], value[0]),
    			new Scalar(hue[1], saturation[1], value[1]), out);
    }
    
    private void findContours(Mat input, boolean externalOnly, List<MatOfPoint> contours){
    	Mat hierarchy = new Mat();
    	contours.clear();
    	int mode;
    	if(externalOnly){
    		mode = Imgproc.RETR_EXTERNAL;
    	}else{
    		mode = Imgproc.RETR_LIST;
    	}
    	int method = Imgproc.CHAIN_APPROX_SIMPLE;
    	Imgproc.findContours(input, contours, hierarchy, mode, method);
    }
    
    private void filterContours(List<MatOfPoint> inputContours, double minArea, double minPerimeter,
    		double minWidth, double maxWidth, double minHeight, double maxHeight, double[] solidity,
    		double maxVertices, double minVertices, double maxRatio, double minRatio, List<MatOfPoint> output){
    	final MatOfInt hull = new MatOfInt();
    	output.clear();
    	//operation
    	for(int i = 0; i < inputContours.size(); i++){
    		final MatOfPoint contour = inputContours.get(i);
    		final Rect bb = Imgproc.boundingRect(contour);
    		if(bb.width < minWidth || bb.width > maxWidth)continue;
    		if(bb.height < minHeight || bb.height > maxHeight)continue;
    		final double area = Imgproc.contourArea(contour);
    		if(area < minArea)continue;
    		if(Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter)continue;
    		Imgproc.convexHull(contour, hull);
    		MatOfPoint mopHull = new MatOfPoint();
    		mopHull.create((int)hull.size().height, 1 , CvType.CV_32SC2);
    		for(int j = 0; j < hull.size().height; j++){
    			int index = (int)hull.get(j,  0)[0];
    			double[] point = new double[] {contour.get(index, 0)[0], contour.get(index, 0)[1]};
    			mopHull.put(j, 0, point);
    		}
    		final double solid = 100 * area / Imgproc.contourArea(mopHull);
    		if(solid < solidity[0] || solid > solidity[1])continue;
    		if(contour.rows() < minVertices || contour.rows() > maxVertices)continue;
    		final double ratio = bb.width / (double)bb.height;
    		if(ratio < minRatio || ratio > maxRatio)continue;
    		output.add(contour);
    	}
    	
    }
}

