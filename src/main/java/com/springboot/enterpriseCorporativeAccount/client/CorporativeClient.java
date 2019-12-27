package com.springboot.enterpriseCorporativeAccount.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.enterpriseCorporativeAccount.dto.AccountClient;
import com.springboot.enterpriseCorporativeAccount.dto.CorporativeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CorporativeClient {

private static final Logger LOGGER = LoggerFactory.getLogger(CorporativeClient.class);
	
     WebClient client = WebClient.create("http://localhost:8014/api/corporative");	

//	@Autowired
//	private WebClient clientEmp;
	
	public Flux<CorporativeDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(CorporativeDto.class));
	}


	public Mono<CorporativeDto> findById(String id) {
		
		LOGGER.info("Buscando con ID ---> "+id);
		
		return client.get()
				.uri("/{id}",Collections.singletonMap("id",id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CorporativeDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<CorporativeDto> save(CorporativeDto PymeDto) {
		
		LOGGER.info("Listo para Guardar ---> "+PymeDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(PymeDto))
			   .retrieve()
			   .bodyToMono(CorporativeDto.class);

	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<CorporativeDto> update(CorporativeDto PymeDto, String numDoc) {
		
		LOGGER.info("Listo para Actualizar ---> "+PymeDto.toString());
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",numDoc))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(PymeDto)
				   .retrieve()
				   .bodyToMono(CorporativeDto.class);
	}
	
	public Mono<CorporativeDto> findByNumDoc(String ruc) {

		return client.get()
				.uri("/doc/{ruc}",Collections.singletonMap("ruc",ruc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CorporativeDto.class);
		        
	}
	
	
	public Flux<AccountClient> extractAccounts(String ruc) {

		return client.get()
				.uri("/valid/{ruc}",Collections.singletonMap("ruc",ruc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountClient.class);
			
	}
}
