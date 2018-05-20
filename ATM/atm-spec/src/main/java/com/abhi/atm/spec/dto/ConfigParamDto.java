package com.abhi.atm.spec.dto;

import java.util.List;

public class ConfigParamDto {

	private int paramId;

	private String paramName;

	private List<ConfigParamValueDto> paramValues;

	public int getParamId() {
		return this.paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public List<ConfigParamValueDto> getParamValues() {
		return this.paramValues;
	}

	public void setParamValues(List<ConfigParamValueDto> paramValues) {
		this.paramValues = paramValues;
	}

}
