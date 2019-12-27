package com.springboot.enterpriseCorporativeAccount.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.enterpriseCorporativeAccount.client.CorporativeClient;
import com.springboot.enterpriseCorporativeAccount.document.CorporativeAccount;
import com.springboot.enterpriseCorporativeAccount.dto.AccountDto;
import com.springboot.enterpriseCorporativeAccount.repo.CorporativeAccountRepo;
import com.springboot.enterpriseCorporativeAccount.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CorporativeAccountImpl implements CorporativeAccountInterface {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CorporativeAccountImpl.class);
	
	
	
	@Autowired
	CorporativeAccountRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	CorporativeClient client;
	

	
	@Override
	public Flux<CorporativeAccount> findAll() {
	
		return repo.findAll();
	}

	@Override
	public Mono<CorporativeAccount> findById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Mono<CorporativeAccount> save(CorporativeAccount CorporativeAccount) {
		
		CorporativeAccount.setCreateDate(new Date());
		CorporativeAccount.setUpdateDate(new Date());
		CorporativeAccount.setNameAccount("Cuenta-Corriente");
		CorporativeAccount.setIdOperation(new ArrayList<String>());
		
		return repo.save(CorporativeAccount);
	}

	@Override
	public Mono<CorporativeAccount> update(CorporativeAccount CorporativeAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(CorporativeAccount.getNameAccount());
			s.setNumberAccount(CorporativeAccount.getNumberAccount());
			s.setBalance(CorporativeAccount.getBalance());
			s.setState(CorporativeAccount.getState());
			s.setTea(CorporativeAccount.getTea());
			s.setUpdateDate(CorporativeAccount.getUpdateDate());
			s.setCreateDate(CorporativeAccount.getCreateDate());
			s.setIdOperation(CorporativeAccount.getIdOperation());
			
			
			return repo.save(s);
			});
	}
	

	@Override
	public Mono<Void> delete(CorporativeAccount CorporativeAccount) {
		
		return repo.delete(CorporativeAccount);
	}

	
	@Override
	public Mono<CorporativeAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}
	

//	@Override
//	public Mono<PersonalDto> saveHeadline(AccountDto accountDto) {
//	
//     return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
//			
//			int cont = 0;
//
//		     for (int i = 0; i < cuentas.size(); i++) {
//
//					AccountClient obj = cuentas.get(i);
//
//					LOGGER.info("PRUEBA 3 --->" + accountDto.toString());
//
//				    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;
//
//				}
//		     
//				if (cont == 0) {
//
//					return repo.save(convert.convertAccountEnt(accountDto)).flatMap(cuenta -> {
//
//						return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {
//
//							LOGGER.info("Flujo Inicial ---->: " + titular.toString());
//
//							titular.setIdAccount(cuenta.getId());
//							titular.setNameAccount(cuenta.getNameAccount());
//							titular.setNumberAccount(cuenta.getNumberAccount());
//
//							LOGGER.info("Flujo Final ----->: " + titular.toString());
//
//							return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
//								
//								p.setIdAccount(cuenta.getId());
//								return Mono.just(p);
//							});
//
//						});
//
//					});
//
//				} else {
//
//					return Mono.empty();
//				}
//
//			});
//	}
//
//	@Override
//	public Mono<CorporativeAccountPerDto> saveHeadlines(CorporativeAccountPerDto CorporativeAccountPerDto) {
//		
//		return save(convert.convertCorporativeAccountPer(CorporativeAccountPerDto)).flatMap(cuenta -> {
//
//			CorporativeAccountPerDto.getHeadlines().forEach(titular -> {
//
//				titular.setIdAccount(cuenta.getId());
//				titular.setNameAccount(cuenta.getNameAccount());
//				titular.setNumberAccount(cuenta.getNumberAccount());
//
//				client.save(titular);
//
//			});
//
//			return Mono.just(CorporativeAccountPerDto);
//		});
//	}
//	
//	
//	
//	
	@Override
	public Mono<CorporativeAccount> savePyme(AccountDto accountDto) {


		LOGGER.info("Service 1---> :"+accountDto.toString());

					return client.findByNumDoc(accountDto.getNumDoc()).flatMap(enteprise -> {
						
						LOGGER.info("Service 2---> :"+enteprise.toString());
						
						return repo.save(convert.convertAccountCorporative(accountDto)).flatMap(cuenta -> {

						LOGGER.info("Flujo Inicial ---->: " + cuenta.toString());

						enteprise.setIdAccount(cuenta.getId());
						enteprise.setNameAccount(cuenta.getNameAccount());
						enteprise.setNumberAccount(cuenta.getNumberAccount());

						LOGGER.info("Flujo Final ----->: " + enteprise.toString());

						 client.update(enteprise, accountDto.getNumDoc()).block();
					

						 return Mono.just(cuenta);
					});

				});

				} 
	


	
	
	

}
