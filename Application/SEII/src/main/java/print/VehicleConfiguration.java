package src.main.java.print;

import java.io.Serializable;
import java.time.LocalDate;

public class VehicleConfiguration implements Serializable {
	private static final long serialVersionUID = -1223656660080318069L;

	private String model;
	private String codes;
	private LocalDate buildDate;

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(final String codes) {
		this.codes = codes;
	}

	public LocalDate getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(final LocalDate buildDate) {
		this.buildDate = buildDate;
	}
}
