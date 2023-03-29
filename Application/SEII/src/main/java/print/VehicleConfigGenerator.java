package main.java.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class VehicleConfigGenerator {
	private final Random random;

	public VehicleConfigGenerator() {
		this.random = new Random();
	}

	public VehicleConfiguration generateVehicle(final boolean allowInvalid) {
		VehicleConfiguration config = new VehicleConfiguration();
		VehicleConfigurationMetadata metamodel = getRandomizedModel(allowInvalid);
		config.setModel(metamodel.getModel());
		config.setBuildDate(getRandomizedDate(allowInvalid));
		config.setCodes(getRandomizedCodes(allowInvalid, metamodel));
		return config;
	}

	private VehicleConfigurationMetadata getRandomizedModel(final boolean allowInvalid) {
		VehicleConfigurationMetadata[] models = VehicleConfigurationMetadata.getValidModels();
		if (allowInvalid) {
			models = Stream.of(models, VehicleConfigurationMetadata.getInValidModels()).flatMap(Stream::of).toArray(VehicleConfigurationMetadata[]::new);
		}
		return models[random.nextInt(models.length)];
	}

	private LocalDate getRandomizedDate(final boolean allowInvalid) {
		int year;
		if (allowInvalid) {
			year = 2022 + random.nextInt(2);
		} else {
			year = 2023 + random.nextInt(1);
		}
		int month = random.nextInt(12) + 1;
		int dayOfMonth = random.nextInt(28) + 1;
		return LocalDate.of(year, month, dayOfMonth);
	}

	private String getRandomizedCodes(final boolean allowInvalid, final VehicleConfigurationMetadata metamodel) {
		String response = "";
		System.out.println("Generating motor codes...");
		response += getRandomizedMotorCodes(allowInvalid, metamodel);
		System.out.println("Generating battery codes...");
		response += getRandomizedBatteryCodes();
		System.out.println("Generating axle codes...");
		response += getRandomizedAxleCodes(allowInvalid, metamodel);
		System.out.println("Generating airdrag codes...");
		response += getRandomizedAirdragCodes();
		System.out.println("Generating codes...");
		response += getFullyRandomizedCodes(random.nextInt( 10)) + 5;
		return response;
	}

	private String getRandomizedMotorCodes(final boolean allowInvalid, final VehicleConfigurationMetadata metamodel) {
		int motors = 1;
		if (allowInvalid) {
			motors = 0;
		}
		motors += random.nextInt(metamodel.getTotalAxles() * 2);
		return getRandomizedCodes(motors, 'M');
	}

	private Object getRandomizedBatteryCodes() {
		int batteries = random.nextInt(4) + 3;
		return getRandomizedCodes(batteries, 'B');
	}

	private Object getRandomizedAxleCodes(final boolean allowInvalid, final VehicleConfigurationMetadata metamodel) {
		int axles = metamodel.getTotalAxles();
		if (allowInvalid) {
			axles -= 1;
			axles += random.nextInt(2);
		}
		return getRandomizedCodes(axles, 'A');
	}

	private Object getRandomizedAirdragCodes() {
		String response = "";
		response += 'F';
		response += random.nextInt(10);
		response += getRandomizedChar(new ArrayList<>());
		response += ',';
		response += 'S';
		response += random.nextInt(10);
		response += getRandomizedChar(new ArrayList<>());
		response += ',';
		response += 'W';
		response += random.nextInt(10);
		response += getRandomizedChar(new ArrayList<>());
		return response;
	}

	private String getFullyRandomizedCodes(final int numberOfCodes) {
		String response = "";
		for (int i = 0; i < numberOfCodes; i++) {
			response += getRandomizedChar(List.of('M', 'B', 'A', 'F', 'S', 'W'));
			response += random.nextInt(10);
			response += getRandomizedChar(new ArrayList<>());
			if (i < numberOfCodes - 1) {
				response += ',';
			}
		}
		return response;
	}

	private String getRandomizedCodes(final int numberOfCodes, final char firstChar) {
		String response = "";
		for (int i = 0; i < numberOfCodes; i++) {
			response += firstChar;
			response += random.nextInt(10);
			response += getRandomizedChar(new ArrayList<>());
			response += ',';
		}
		return response;
	}

	private char getRandomizedChar(final List<Character> excludedChars) {
		char result = (char)(random.nextInt(26) + 'A');
		if (null != excludedChars && !excludedChars.isEmpty() && excludedChars.contains(result)) {
			return getRandomizedChar(excludedChars);
		}
		return result;
	}
}
