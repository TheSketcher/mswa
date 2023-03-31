package main.java.print;

import java.io.Serializable;

public class FullVehicle implements Serializable {
	private static final long serialVersionUID = 2531073488464135007L;

	private VehicleConfigurationMetadata metadata;
	private VehicleConfiguration config;

	public VehicleConfigurationMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(final VehicleConfigurationMetadata metadata) {
		this.metadata = metadata;
	}

	public VehicleConfiguration getConfig() {
		return config;
	}

	public void setConfig(final VehicleConfiguration config) {
		this.config = config;
	}
}
