package com.springboot.enterpriseCorporativeAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.enterpriseCorporativeAccount.document.CorporativeAccount;
import com.springboot.enterpriseCorporativeAccount.dto.AccountDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);

	
	public CorporativeAccount convertAccountCorporative(AccountDto accountDto) {

		
		LOGGER.info("Convert ---> :"+accountDto.toString());

		CorporativeAccount  CorporativeAccount = new CorporativeAccount();

		CorporativeAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		CorporativeAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		CorporativeAccount.setState(accountDto.getState());
		CorporativeAccount.setBalance(accountDto.getBalance());
		CorporativeAccount.setTea(accountDto.getTea());
		CorporativeAccount.setCreateDate(new Date());
		CorporativeAccount.setUpdateDate(new Date());
		CorporativeAccount.setIdOperation(new ArrayList<String>());
		
		LOGGER.info("Convert ---> :"+CorporativeAccount.toString());
		
		return CorporativeAccount;

	}
//	
//	
//	public CorporativeAccount convertCorporativeAccountEnt(CorporativeAccountEntDto CorporativeAccountEntDto) {
//
//		CorporativeAccount  CorporativeAccount = new CorporativeAccount();
//
//		CorporativeAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
//		CorporativeAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
//		CorporativeAccount.setState(CorporativeAccountEntDto.getState());
//		CorporativeAccount.setBalance(CorporativeAccountEntDto.getBalance());
//		CorporativeAccount.setTea(CorporativeAccountEntDto.getTea());
//		CorporativeAccount.setCreateDate(new Date());
//		CorporativeAccount.setUpdateDate(new Date());
//		CorporativeAccount.setIdOperation(new ArrayList<String>());
//		
//		
//		return CorporativeAccount;
//
//	}
//	
//	public CorporativeAccount convertCorporativeAccountAdd(AccountDto accountDto) {
//
//		CorporativeAccount  CorporativeAccount = new CorporativeAccount();
//
//		CorporativeAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
//		CorporativeAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
//		CorporativeAccount.setState(accountDto.getState());
//		CorporativeAccount.setBalance(accountDto.getBalance());
//		CorporativeAccount.setTea(accountDto.getTea());
//		CorporativeAccount.setCreateDate(new Date());
//		CorporativeAccount.setUpdateDate(new Date());
//		CorporativeAccount.setIdOperation(new ArrayList<String>());
//
//		return CorporativeAccount;
//
//	}


}
