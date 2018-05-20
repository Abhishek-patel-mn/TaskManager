package com.abhi.atm.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.atm.dao.entity.ConfigParam;

public interface ConfigParamRepository extends JpaRepository<ConfigParam, Integer> {

	ConfigParam findByParamName(String paramName);
}
