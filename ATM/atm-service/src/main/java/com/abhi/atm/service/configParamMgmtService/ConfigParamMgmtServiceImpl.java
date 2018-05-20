package com.abhi.atm.service.configParamMgmtService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.atm.common.exception.BusinessServiceException;
import com.abhi.atm.common.exception.DataAccessException;
import com.abhi.atm.dao.entity.ConfigParam;
import com.abhi.atm.repository.user.ConfigParamRepository;
import com.abhi.atm.spec.dto.ConfigParamDto;

/**
 * @author Abhishek Patel M N Jan 17, 2018
 */
@Service
public class ConfigParamMgmtServiceImpl implements ConfigParamMgmtService {

	@Autowired
	ConfigParamRepository configParamRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void addConfigParam(ConfigParamDto configParamDto) throws BusinessServiceException, DataAccessException {
		Boolean configExists = configParamRepository.findAll().stream()
				.anyMatch(cp -> cp.getParamName().equals(configParamDto.getParamName()));
		if (configExists)
			throw new BusinessServiceException("Configuration " + configParamDto.getParamName() + " already exists..");
		ConfigParam configParam = modelMapper.map(configParamDto, ConfigParam.class);
		configParamRepository.save(configParam);
	}

	@Override
	public List<ConfigParamDto> getAllConfigParams() throws BusinessServiceException, Exception {
		return configParamRepository.findAll().stream().map(cp -> modelMapper.map(cp, ConfigParamDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ConfigParamDto getConfigParamByConfigParamId(Integer configParamId)
			throws BusinessServiceException, DataAccessException {
		/*return modelMapper.map(configParamRepository.findOne(configParamId), ConfigParamDto.class);*/
	return null;
	}

	@Override
	public void deleteConfigParam(Integer configParamId) throws BusinessServiceException, DataAccessException {
		//configParamRepository.delete(configParamId);
	}

	@Override
	public void updateConfigParam(ConfigParamDto configParamDto) throws BusinessServiceException, DataAccessException {
		configParamRepository.save(modelMapper.map(configParamDto, ConfigParam.class));
	}

	@Override
	public ConfigParam findByParamName(String configParamName) {
		return configParamRepository.findByParamName(configParamName);
	}

}
