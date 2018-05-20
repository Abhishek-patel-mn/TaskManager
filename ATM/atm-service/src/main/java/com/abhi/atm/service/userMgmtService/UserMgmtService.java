package com.abhi.atm.service.userMgmtService;

import java.util.List;

import com.abhi.atm.common.exception.BusinessServiceException;
import com.abhi.atm.common.exception.DataAccessException;
import com.abhi.atm.dao.entity.User;
import com.abhi.atm.spec.dto.UserDto;

/**
 * @author Abhishek Patel M N Jan 17, 2018
 */
public interface UserMgmtService {

	void addUser(User user) throws BusinessServiceException, DataAccessException;

	List<UserDto> getAllUsers() throws BusinessServiceException, Exception;

	UserDto getUserByUserId(Integer UserId) throws BusinessServiceException, DataAccessException;

	void deleteUser(Integer userId) throws BusinessServiceException, DataAccessException;

	void updateUser(User user) throws BusinessServiceException, DataAccessException;
}
