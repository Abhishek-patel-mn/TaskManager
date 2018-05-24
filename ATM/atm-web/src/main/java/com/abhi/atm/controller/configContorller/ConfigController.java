package com.abhi.atm.controller.configContorller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.atm.service.configParamMgmtService.ConfigParamMgmtService;
import com.abhi.atm.spec.dto.ConfigParamDto;

import net.sf.json.JSONObject;

@RestController
public class ConfigController {

	@Autowired
	ConfigParamMgmtService configParamMgmtService;

	@PostMapping(value = "/secured/configParam")
	public ResponseEntity<String> addConfigParam(@Valid @RequestBody ConfigParamDto configParamDto) {
		JSONObject jsonResponse = new JSONObject();
		try {
			configParamMgmtService.addConfigParam(configParamDto);
			jsonResponse.put("data", "Config Param " + configParamDto.getParamName() + " added successfully..");
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		} catch (Exception e) {
			jsonResponse.put("data", e.getMessage());
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/secured/configParam", "/rest/configParam" })
	public ResponseEntity<String> getAllConfigParams() {
		JSONObject jsonResponse = new JSONObject();
		try {
			List<ConfigParamDto> configParams = configParamMgmtService.getAllConfigParams();
			jsonResponse.put("data", configParams);
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		} catch (Exception e) {
			jsonResponse.put("data", e.getMessage());
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/secured/configParam/{paramId}")
	public ResponseEntity<String> updateConfigParam(@Valid @RequestBody ConfigParamDto configParamDto,
			@PathVariable(name = "paramId") Integer paramId) {
		JSONObject jsonResponse = new JSONObject();
		try {
			configParamDto.setParamId(paramId);
			configParamMgmtService.updateConfigParam(configParamDto);
			jsonResponse.put("data", "Config Param " + configParamDto.getParamName() + " updated successfully..");
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		} catch (Exception e) {
			jsonResponse.put("data", e.getMessage());
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/secured/configParam/{configParamId}")
	public ResponseEntity<String> deleteConfigParam(@Valid @PathVariable int configParamId) {
		JSONObject jsonResponse = new JSONObject();
		try {
			configParamMgmtService.deleteConfigParam(configParamId);
			jsonResponse.put("data", "Config Param " + configParamId + " updated successfully..");
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		} catch (Exception e) {
			jsonResponse.put("data", e.getMessage());
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/secured/configParam/{configParamId}", "/rest/configParam/{configparamId}" })
	public ResponseEntity<String> getConfigParamByConfigParamId(@Valid @PathVariable int configParamId) {
		JSONObject jsonResponse = new JSONObject();
		try {
			ConfigParamDto configParam = configParamMgmtService.getConfigParamByConfigParamId(configParamId);
			jsonResponse.put("data", configParam);
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		} catch (Exception e) {
			jsonResponse.put("data", e.getMessage());
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
