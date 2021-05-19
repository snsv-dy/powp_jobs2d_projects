package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureManager {
	private static Map<Class, Feature> featureMap = new HashMap<>();

	public static void addFeature(Feature feature){
		featureMap.put(feature.getClass(), feature);
	}

	public static void addFeatures(Feature ... features){
		for(Feature feature:features){
			addFeature(feature);
		}
	}

	public static <T extends Feature> T getFeature(Class type){
		if(!featureMap.containsKey(type)){
			throw new RuntimeException("No such feature");
		}

		return (T) featureMap.get(type);
	}

	public static void setup(Application application){
		for(Feature feature:featureMap.values()){
			feature.setup(application);
		}
	}
}
