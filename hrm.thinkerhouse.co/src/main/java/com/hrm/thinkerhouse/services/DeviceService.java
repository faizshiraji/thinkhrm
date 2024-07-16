package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Devices;

public interface DeviceService {

	public List<Devices> getDevices();
	public Devices getDevices(int idDevice);
	public Devices addDevices(Devices devices);
	public Devices updateDevices(Devices devices);
	public void deleteDevice(int idDevice);
	public long countDevice();
}
