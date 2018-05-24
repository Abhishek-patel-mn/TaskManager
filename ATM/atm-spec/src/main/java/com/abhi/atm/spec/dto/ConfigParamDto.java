package com.abhi.atm.spec.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ConfigParamDto {

	private int paramId;

	@NotNull
	private String paramName;

	@NotNull
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
