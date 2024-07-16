package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Devices;
import com.hrm.thinkerhouse.repo.DevicesRepo;
import com.hrm.thinkerhouse.services.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	public DevicesRepo devicesRepo;
	
	@Override
	public List<Devices> getDevices() {
		return devicesRepo.findAll();
	}

	@Override
	public Devices getDevices(int idDevice) {
		return devicesRepo.findById(idDevice).get();
	}

	@Override
	public Devices addDevices(Devices devices) {
		return devicesRepo.save(devices);
	}

	@Override
	public Devices updateDevices(Devices devices) {
		return devicesRepo.save(devices);
	}

	@Override
	public void deleteDevice(int idDevice) {
		devicesRepo.deleteById(idDevice);
	}

	@Override
	public long countDevice() {
		return devicesRepo.count();
	}

}
