package main.java.vehicle;

import java.io.Serializable;
import java.time.LocalDate;

public class VehicleComponent implements Serializable {
	private static final long serialVersionUID = 6338783856494935380L;

	private int id;
	private ComponentType type;
	private LocalDate validFrom;
	private LocalDate validTo;
	private String codeRule;
	private boolean approved = true;
	private String componentXml;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public ComponentType getType() {
		return type;
	}

	public void setType(final ComponentType type) {
		this.type = type;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(final LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(final LocalDate validTo) {
		this.validTo = validTo;
	}

	public String getCodeRule() {
		return codeRule;
	}

	public void setCodeRule(final String codeRule) {
		this.codeRule = codeRule;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(final boolean approved) {
		this.approved = approved;
	}

	public String getComponentXml() {
		return componentXml;
	}

	public void setComponentXml(final String componentXml) {
		this.componentXml = componentXml;
	}
}
