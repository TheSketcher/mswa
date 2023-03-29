package src.main.java.print;

public class VehicleConfigurationMetadata {
	private static final VehicleConfigurationMetadata[] MODELS_VALID = {
			new VehicleConfigurationMetadata("983.493", 4, 2, VehicleType.TRACTOR), // >16T
			new VehicleConfigurationMetadata("983.020", 6, 2, VehicleType.LORRY),
			new VehicleConfigurationMetadata("983.648", 6, 2, VehicleType.TRACTOR),
			new VehicleConfigurationMetadata("983.315", 6, 4, VehicleType.TRACTOR)
	};
	private static final VehicleConfigurationMetadata[] MODELS_INVALID = {
			new VehicleConfigurationMetadata("983.003", 4, 2, VehicleType.LORRY)
	};

	private String model;
	private int totalAxles;
	private int drivenAxles;
	private VehicleType type;

	public static VehicleConfigurationMetadata[] getValidModels() {
		return MODELS_VALID;
	}

	public static VehicleConfigurationMetadata[] getInValidModels() {
		return MODELS_INVALID;
	}

	public String getModel() {
		return model;
	}

	public int getTotalAxles() {
		return totalAxles;
	}

	public int getDrivenAxles() {
		return drivenAxles;
	}

	public VehicleType getType() {
		return type;
	}

	private VehicleConfigurationMetadata(final String model, final int totalAxles, final int drivenAxles, final VehicleType type) {
		this.model = model;
		this.totalAxles = totalAxles;
		this.drivenAxles = drivenAxles;
		this.type = type;
	}
}
