package main.java.logistic;

import java.io.Serializable;

public class VehicleWeights implements Serializable {
	private static final long serialVersionUID = 2011974189537717226L;

	private int weight;
	private int maxPermissibleWeight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(final int weight) {
		this.weight = weight;
	}

	public int getMaxPermissibleWeight() {
		return maxPermissibleWeight;
	}

	public void setMaxPermissibleWeight(final int maxPermissibleWeight) {
		this.maxPermissibleWeight = maxPermissibleWeight;
	}
}
