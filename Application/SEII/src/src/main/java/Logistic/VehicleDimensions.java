package src.main.java.Logistic;

import java.io.Serializable;

public class VehicleDimensions implements Serializable {
	private static final long serialVersionUID = 2459206104263400791L;

	private int length;
	private int width;
	private int height;

	public int getLength() {
		return length;
	}

	public void setLength(final int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}
}
