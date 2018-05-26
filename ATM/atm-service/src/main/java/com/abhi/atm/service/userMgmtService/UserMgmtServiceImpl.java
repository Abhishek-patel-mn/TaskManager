package com.abhi.atm.service.userMgmtService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhi.atm.common.exception.BusinessServiceException;
import com.abhi.atm.common.exception.DataAccessException;
import com.abhi.atm.dao.entity.ConfigParam;
import com.abhi.atm.dao.entity.ConfigParamValue;
import com.abhi.atm.dao.entity.User;
import com.abhi.atm.repository.configParam.UserRepository;
import com.abhi.atm.repository.user.ConfigParamRepository;
import com.abhi.atm.spec.dto.UserDto;

/**
 * @author Abhishek Patel M N Jan 17, 2018
 */
@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConfigParamRepository configParamRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void addUser(User user) throws BusinessServiceException, DataAccessException {

		try {

			// Checking user.
			if (StringUtils.isEmpty(user.getUserName()))
				throw new BusinessServiceException("userName is empty..");

			if (StringUtils.isEmpty(user.getPassword()))
				throw new BusinessServiceException("password is empty..");

			if (user.getRole() != null) {
				if (user.getRole().getRoleName() == null)
					throw new BusinessServiceException("You can not create user without role..");
			} else {
				throw new BusinessServiceException("You can not create user without role..");
			}

			List<User> users = userRepository.findAll();

			Boolean userMatch = users.stream().anyMatch(u -> u.getUserName().equals(user.getUserName()));

			if (userMatch)
				throw new BusinessServiceException("User " + user.getUserName() + " already exists..");

			// Checking role.
			ConfigParam configParam = configParamRepository.findByParamName("USER_ROLES");
			if (configParam == null)
				throw new BusinessServiceException("USER_ROLES does not exists..");

			List<ConfigParamValue> availableRolesList = configParam.getParamValues();

			if (availableRolesList.size() < 1)
				throw new BusinessServiceException("Please create a role first...");

			Boolean roleMatch = availableRolesList.stream()
					.anyMatch(r -> user.getRole().getRoleName().equals(r.getParamValue()));

			if (!roleMatch) {
				String availableRolesStr = "";
				for (ConfigParamValue role : availableRolesList) {
					availableRolesStr = availableRolesStr + role.getParamValue() + " ";
				}
				throw new BusinessServiceException("Incorrect role, please select role from { " + availableRolesStr + "}");
			}

			// Updating user and roles date, saving both user and role.
			user.setCreatedDate(new Date());
			user.getRole().setCreatedDate(new Date());
			
			// Encoding password.
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} catch (Exception e) {
			throw new BusinessServiceException(e.getMessage());
		}
	}

	@Override
	public List<UserDto> getAllUsers() throws BusinessServiceException, Exception {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		users.stream().forEach(user -> {
			userDtos.add(modelMapper.map(user, UserDto.class));
		});
		return userDtos;
	}

	@Override
	public UserDto getUserByUserId(Integer userId) throws BusinessServiceException, DataAccessException {
		/*
		 * User user = userRepository.findOne(userId); return modelMapper.map(user,
		 * UserDto.class);
		 */
		return null;
	}

	@Override
	public void deleteUser(Integer userId) throws BusinessServiceException, DataAccessException {
		// userRepository.delete(userId);
	}

	@Override
	public void updateUser(User user) throws BusinessServiceException, DataAccessException {
		userRepository.save(user);
	}

}
