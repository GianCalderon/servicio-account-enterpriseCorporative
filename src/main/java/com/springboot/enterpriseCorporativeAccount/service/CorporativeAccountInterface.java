package com.springboot.enterpriseCorporativeAccount.service;

import com.springboot.enterpriseCorporativeAccount.document.CorporativeAccount;
import com.springboot.enterpriseCorporativeAccount.dto.AccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CorporativeAccountInterface {

	
	public Flux<CorporativeAccount> findAll();
	public Mono<CorporativeAccount> findById(String id);
	public Mono<CorporativeAccount> save(CorporativeAccount CorporativeAccount);
	public Mono<CorporativeAccount> update(CorporativeAccount CorporativeAccount ,String id);
	public Mono<Void> delete(CorporativeAccount CorporativeAccount);
	
	public Mono<CorporativeAccount> findByNumAccount(String numAccount);
//	public Mono<CorporativeAccount> saveOperation(OperationDto operationDto);	
//	
//	public Mono<PersonalDto> saveHeadline(AccountDto accountDto);     
//	public Mono<CorporativeAccountPerDto> saveHeadlines (CorporativeAccountPerDto CorporativeAccountPerDto);
//	
	public Mono<CorporativeAccount> savePyme(AccountDto accountDto);
	

	

	
	
	
	
}
