package main.java.logistic;

import main.java.logistic.VehicleWeights;
import main.java.print.VehicleConfiguration;

public interface VehicleWeightsClient {

	VehicleWeights getWeights(final VehicleConfiguration vehicle);
}
