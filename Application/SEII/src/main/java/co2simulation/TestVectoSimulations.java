package main.java.co2simulation;

import de.dhbw.ka.se2.vecto4j.Co2Simulator;
import de.dhbw.ka.se2.vecto4j.IncompleteVehicleException;
import de.dhbw.ka.se2.vecto4j.Simulator;
import de.dhbw.ka.se2.vecto4j.WrongVehicleClassException;
import main.java.logistic.LogisticsClient;
import main.java.logistic.VehicleWeightsClient;
import main.java.print.FullVehicle;
import main.java.print.VehicleConfigGenerator;

public class TestVectoSimulations {

	public static void main(String[] args) {
		VehicleWeightsClient weights = new LogisticsClient();
		Simulator vecto = new Co2Simulator();
		Co2SimulationsProcess process = new Co2SimulationsProcess(weights, vecto);
		VehicleConfigGenerator print = new VehicleConfigGenerator();
		for (int i = 0; i < 3; i++) {
			FullVehicle config = print.generateVehicle(false);
			try {
				System.out.println(process.simulateVehicle(config));
			} catch (IncompleteVehicleException | WrongVehicleClassException e) {
				e.printStackTrace();
			}
		}
	}
}
