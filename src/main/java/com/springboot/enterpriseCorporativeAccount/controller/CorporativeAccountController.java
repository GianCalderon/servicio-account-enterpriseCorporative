package com.springboot.enterpriseCorporativeAccount.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.enterpriseCorporativeAccount.document.CorporativeAccount;
import com.springboot.enterpriseCorporativeAccount.dto.AccountDto;
import com.springboot.enterpriseCorporativeAccount.service.CorporativeAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/corporativeAccount")
public class CorporativeAccountController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CorporativeAccountController.class);

	@Autowired
	CorporativeAccountImpl service;
	


	@GetMapping
	public Mono<ResponseEntity<Flux<CorporativeAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CorporativeAccount>> search(@PathVariable String id) {

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<CorporativeAccount>> save(@RequestBody CorporativeAccount CorporativeAccount) {

		return service.save(CorporativeAccount)
				.map(s -> ResponseEntity.created(URI.create("/api/CorporativeAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CorporativeAccount>> update(@RequestBody CorporativeAccount CorporativeAccount,
			@PathVariable String id) {
		
		LOGGER.info("Controller ---> :"+CorporativeAccount.toString());

		return service.update(CorporativeAccount, id)
				.map(s -> ResponseEntity.created(URI.create("/api/CorporativeAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	
	
	// OPERACIONES QUE EXPONEN SERVICIOS

	
	@PostMapping("/saveCorporative")
	public Mono<ResponseEntity<CorporativeAccount>> saveEnterprise(@RequestBody AccountDto accountDto) {

		LOGGER.info("Controller ---> :"+accountDto.toString());

		return service.savePyme(accountDto).map(s -> ResponseEntity.created(URI.create("/api/CorporativeAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<CorporativeAccount>(HttpStatus.CONFLICT));


	}
	

	
	@GetMapping("/cuenta/{numberAccount}")
	public Mono<ResponseEntity<CorporativeAccount>> searchByNumAccount(@PathVariable String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);

		return service.findByNumAccount(numberAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	
	

	
}
