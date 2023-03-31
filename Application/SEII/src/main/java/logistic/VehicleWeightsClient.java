package main.java.logistic;

import main.java.print.VehicleConfiguration;
import main.java.logistic.VehicleWeights;

public interface VehicleWeightsClient {

    VehicleWeights getWeights(final VehicleConfiguration vehicle);
}



