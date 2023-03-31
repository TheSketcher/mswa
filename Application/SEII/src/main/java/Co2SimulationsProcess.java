package main.java;

import de.dhbw.ka.se2.vecto4j.IncompleteVehicleException;
import de.dhbw.ka.se2.vecto4j.Simulator;
import de.dhbw.ka.se2.vecto4j.WrongVehicleClassException;
import de.dhbw.ka.se2.vecto4j.input.VehicleInput;
import de.dhbw.ka.se2.vecto4j.input.VehicleType;
import de.dhbw.ka.se2.vecto4j.output.ElectricVehicleSimulationResult;
import main.java.logistic.VehicleWeightsClient;
import main.java.print.VehicleConfiguration;

public class Co2SimulationsProcess {
    private final VehicleWeightsClient weightsAccess;
    private final Simulator simulator;

    public Co2SimulationsProcess(final VehicleWeightsClient weightsAccess,final Simulator simulator){
        this.weightsAccess = weightsAccess;
        this.simulator=simulator;
    }
    public ElectricVehicleSimulationResult simulateVehicle(final fullVehicle vehicle) throws IncompleteVehicleException, WrongVehicleClassException {
        VehicleInput input = getInputForVehicle(vehicle);
        enrichInputWithComponents(vehicle.getConfig(), input);
        enrichInputWithWeights(vehicle.getConfig(),input);
        return simulator.calculateVehicle(input);
    }
    public VehicleInput getInputForVehicle(final fullVehicle vehicle){
        VehicleInput input = new VehicleInput();
        switch(vehicle.getMetaData().getType()){
            case LORRY:
                input.setType(VehicleType.LORRY);
                return input;
            case TRACTOR:
                input.setType(VehicleType.TRACTOR);
                return input;
                }
                throw new IllegalArgumentException("Unknown type " + vehicle.getMetaData().getType());
    }
    private void enrichInputWithComponents(final VehicleConfiguration config, final VehicleInput input){

    }
    private void enrichInputWithWeights(final VehicleConfiguration config, final VehicleInput input){

    }

}
