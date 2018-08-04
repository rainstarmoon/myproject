package com.myproject.service.impl;

import javax.annotation.Resource;

import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;

import com.myproject.service.ImageAnalysis;

@Service
public class ImageAnalysisImpl implements ImageAnalysis {

	@Resource(name = "haarcascadeFrontalfaceAlt")
	private CascadeClassifier haarcascadeFrontalfaceAlt;

	@Override
	public void faceDetector() {
		Mat mat = Imgcodecs.imread("E:\\workspace_myself\\git\\myproject\\info\\微信图片_20180323124013.jpg");
		MatOfRect faceDetections = new MatOfRect();
		haarcascadeFrontalfaceAlt.detectMultiScale(mat, faceDetections);
		for (Rect rect : faceDetections.toArray()) {
			Mat face_img = new Mat(mat, rect);
			Imgcodecs.imwrite("E:\\workspace_myself\\git\\myproject\\info\\微信图片_20180323124013_2.jpg", face_img);
			/*
			 * Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x +
			 * rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
			 */
		}
		// Imgcodecs.imwrite("E:\\workspace_myself\\git\\myproject\\info\\20160407_233616_2.jpg",
		// mat);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void faceMatcherByFeaturePoint() {
		Mat matSourceRGB = Imgcodecs.imread("E:\\workspace_myself\\git\\myproject\\info\\WIN_20180805_01_27_01_Pro.jpg");
		Mat matTargetRGB = Imgcodecs.imread("E:\\workspace_myself\\git\\myproject\\info\\WIN_20180805_01_27_53_Pro.jpg");
		Mat matSourceGray = new Mat();
		Mat matTargetGray = new Mat();
		// 转换为灰度
		Imgproc.cvtColor(matSourceRGB, matSourceGray, Imgproc.COLOR_RGB2GRAY);
		Imgproc.cvtColor(matTargetRGB, matTargetGray, Imgproc.COLOR_RGB2GRAY);

		// 初始化ORB检测描述子
		
		FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.ORB);
		// 特别提示下这里opencv暂时不支持SIFT、SURF检测方法，这个好像是opencv(windows) java版的一个bug,本人在这里被坑了好久。
		DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

		// 关键点及特征描述矩阵声明
		MatOfKeyPoint keyPoint1 = new MatOfKeyPoint();
		MatOfKeyPoint keyPoint2 = new MatOfKeyPoint();
		Mat descriptorMat1 = new Mat();
		Mat descriptorMat2 = new Mat();

		// 计算ORB特征关键点
		featureDetector.detect(matSourceGray, keyPoint1);
		featureDetector.detect(matTargetGray, keyPoint2);

		// 计算ORB特征描述矩阵
		descriptorExtractor.compute(matSourceGray, keyPoint1, descriptorMat1);
		descriptorExtractor.compute(matTargetGray, keyPoint2, descriptorMat2);

		float result = 0;
		// 特征点匹配
		System.out.println("test5：" + keyPoint1.size());
		System.out.println("test3：" + keyPoint2.size());
		if (!keyPoint1.size().empty() && !keyPoint2.size().empty()) {
			// FlannBasedMatcher matcher = new FlannBasedMatcher();
			DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);
			MatOfDMatch matches = new MatOfDMatch();
			matcher.match(descriptorMat1, descriptorMat2, matches);
			// 最优匹配判断
			double minDist = 100;
			DMatch[] dMatchs = matches.toArray();
			int num = 0;
			for (int i = 0; i < dMatchs.length; i++) {
				if (dMatchs[i].distance <= 2 * minDist) {
					result += dMatchs[i].distance * dMatchs[i].distance;
					num++;
				}
			}
			result /= num;
		}
		System.out.println(result);
	}

}
