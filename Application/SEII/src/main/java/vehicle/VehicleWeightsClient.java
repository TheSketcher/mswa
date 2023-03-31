package main.java.vehicle;

import de.dhbw.ka.se2.domain.logistics.VehicleWeights;
import de.dhbw.ka.se2.domain.print.VehicleConfiguration;

public interface VehicleWeightsClient {

	VehicleWeights getWeights(final VehicleConfiguration vehicle);
}
