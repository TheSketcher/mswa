package main.java.co2simulation;

import de.dhbw.ka.se2.vecto4j.IncompleteVehicleException;
import de.dhbw.ka.se2.vecto4j.Simulator;
import de.dhbw.ka.se2.vecto4j.WrongVehicleClassException;
import de.dhbw.ka.se2.vecto4j.input.VehicleInput;
import de.dhbw.ka.se2.vecto4j.input.VehicleType;
import de.dhbw.ka.se2.vecto4j.output.ElectricVehicleSimulationResult;
import main.java.logistic.VehicleWeights;
import main.java.logistic.VehicleWeightsClient;
import main.java.print.FullVehicle;
import main.java.print.VehicleConfiguration;
import main.java.vehicle.VehicleComponent;
import main.java.vehicle.VehicleComponentDecoder;
import main.java.vehicle.VehicleDataClient;

import java.util.List;

public class Co2SimulationsProcess {
    private final VehicleWeightsClient weightsAccess;
    private final Simulator simulator;

    public Co2SimulationsProcess(final VehicleWeightsClient weightsAccess,final Simulator simulator){
        this.weightsAccess = weightsAccess;
        this.simulator = simulator;
    }
    public ElectricVehicleSimulationResult simulateVehicle(final FullVehicle vehicle) throws IncompleteVehicleException, WrongVehicleClassException {
        VehicleInput input = getInputForVehicle(vehicle);
        enrichInputWithComponents(vehicle.getConfig(), input);
        enrichInputWithWeights(vehicle.getConfig(),input);
        return simulator.calculateVehicle(input);
    }
    public VehicleInput getInputForVehicle(final FullVehicle vehicle){
        VehicleInput input = new VehicleInput();
        switch(vehicle.getMetadata().getType()){
            case LORRY:
                input.setType(VehicleType.LORRY);
                return input;
            case TRACTOR:
                input.setType(VehicleType.TRACTOR);
                return input;
                }
                throw new IllegalArgumentException("Unknown type " + vehicle.getMetadata().getType());
    }
    private void enrichInputWithComponents(final VehicleConfiguration config, final VehicleInput input){
        List<VehicleComponent> components = new VehicleDataClient().getComponents(config);
        new VehicleComponentDecoder().decodeAndAddComponents(components, input);
    }
    private void enrichInputWithWeights(final VehicleConfiguration config, final VehicleInput input){
        VehicleWeights weights=weightsAccess.getWeights(config);
        input.setMaxPermissibleWeight(weights.getMaxPermissibleWeight());
        input.setWeight(weights.getWeight());
    }

}
