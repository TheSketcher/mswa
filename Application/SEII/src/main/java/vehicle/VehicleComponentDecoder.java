package main.java.vehicle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import main.java.vehicle.VehicleComponentDecoder.NoDeserializer;
import main.java.vehicle.ComponentType;
import main.java.vehicle.VehicleComponent;
import de.dhbw.ka.se2.vecto4j.input.Airdrag;
import de.dhbw.ka.se2.vecto4j.input.Axle;
import de.dhbw.ka.se2.vecto4j.input.Battery;
import de.dhbw.ka.se2.vecto4j.input.Gearbox;
import de.dhbw.ka.se2.vecto4j.input.Motor;
import de.dhbw.ka.se2.vecto4j.input.Temperature;
import de.dhbw.ka.se2.vecto4j.input.Tyre;
import de.dhbw.ka.se2.vecto4j.input.VehicleInput;


public class VehicleComponentDecoder {

	public class NoDeserializer extends KeyDeserializer {

		@Override
		public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private XmlMapper xmlMapper = new XmlMapper();

	public VehicleComponentDecoder() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addKeyDeserializer(Temperature.class, new NoDeserializer());
		xmlMapper.registerModule(simpleModule);
	}

	public void decodeAndAddComponents(final List<VehicleComponent> components, final VehicleInput input) {
		input.setAirdrag(getAirdragData(components));
		input.setAxles(getAxlesWithTyres(components));
		input.setBatteries(getBatteriesData(components));
		input.setGearbox(getGearboxData(components));
		input.setMotors(getMotorsData(components));
	}

	private List<Axle> getAxlesWithTyres(final List<VehicleComponent> components) {
		List<Axle> axles = getAxlesData(components);
		List<Tyre> tyres = getTyresData(components);
		if (axles.size() != tyres.size()) {
			throw new IllegalArgumentException("Not matching tyres and axles!");
		}
		for (int i = 0; i < axles.size(); i++) {
			axles.get(i).setTyres(tyres.get(i));
		}
		return axles;
	}

	private Airdrag getAirdragData(final List<VehicleComponent> components) {
		return getSingleComponent(components, ComponentType.airdrag, Airdrag.class);
	}

	private List<Axle> getAxlesData(final List<VehicleComponent> components) {
		return getMultipleComponent(components, ComponentType.axle, Axle.class);
	}

	private List<Battery> getBatteriesData(final List<VehicleComponent> components) {
		return getMultipleComponent(components, ComponentType.battery, Battery.class);
	}

	private Gearbox getGearboxData(final List<VehicleComponent> components) {
		return getSingleComponent(components, ComponentType.gearbox, Gearbox.class);
	}

	private List<Motor> getMotorsData(final List<VehicleComponent> components) {
		return getMultipleComponent(components, ComponentType.motor, Motor.class);
	}

	private List<Tyre> getTyresData(final List<VehicleComponent> components) {
		return getMultipleComponent(components, ComponentType.tyre, Tyre.class);
	}

	private <T extends Serializable> List<T> getMultipleComponent(final List<VehicleComponent> components,
			final ComponentType type, final Class<T> expectedType) {
		List<VehicleComponent> filteredList = filterComponents(components, type);
		List<T> result = new ArrayList<>();
		for (VehicleComponent component: filteredList) {
			T decodedComponent = decodeComponentData(component.getComponentXml(), expectedType);
			result.add(decodedComponent);
		}
		return result;
	}

	private <T extends Serializable> T getSingleComponent(final List<VehicleComponent> components,
			final ComponentType type, final Class<T> expectedType) {
		List<VehicleComponent> filteredList = filterComponents(components, type);
		if (1 != filteredList.size()) {
			throw new IllegalArgumentException("Found not allowed number of " + type + " components!");
		}
		VehicleComponent component = filteredList.get(0);
		return decodeComponentData(component.getComponentXml(), expectedType);
	}

	private List<VehicleComponent> filterComponents(final List<VehicleComponent> components,
			final ComponentType filterFor) {
		return components.stream().filter(component -> component.getType().equals(filterFor)).toList();
	}

	private <T extends Serializable> T decodeComponentData(final String componentXml, final Class<T> expectedType) {
		System.out.println(componentXml);
		byte[] decodedXml = Base64.getDecoder().decode(componentXml);
		try {
			System.out.println(new String(decodedXml));
			return xmlMapper.readValue(decodedXml, expectedType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
