package com.springboot.enterpriseCorporativeAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.enterpriseCorporativeAccount.document.CorporativeAccount;

import reactor.core.publisher.Mono;

public interface CorporativeAccountRepo extends ReactiveMongoRepository<CorporativeAccount, String> {

	public Mono<CorporativeAccount> findByNumberAccount(String numberAccount);
	
	
}
