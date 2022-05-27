package org.luizinfo.controller;

import java.util.List;
import java.util.Optional;

import org.luizinfo.model.Internationalization;
import org.luizinfo.repository.IInternationalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Métodos do Controller de Internationalizations")
@RequestMapping(value = "/internationalization")
public class InternationalizationController implements CrudController<Internationalization> {
	
	@Autowired
	private IInternationalization iInternationalization;

	@Override
	public ResponseEntity<?> listar() {
		
		List<Internationalization> internationalizations = iInternationalization.findAll();
		
		if (internationalizations.size() > 0) {
			return new ResponseEntity<List<Internationalization>>(internationalizations, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Não foram encontrados registros cadastrados!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> inserir(Internationalization objeto, String token) {

		if (objeto.getId() != null) {
			return new ResponseEntity<String>("ID do Registro não deve ser Informado para cadastrar!", HttpStatus.BAD_REQUEST);
		}
		
		Internationalization internationalizationAux = iInternationalization.save(objeto);
		
		return new ResponseEntity<Internationalization>(internationalizationAux, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> atualizar(Internationalization objeto, String token) {
		
		if (objeto.getId() == null) {
			return new ResponseEntity<String>("ID do Registro não foi Informado para atualizar!", HttpStatus.BAD_REQUEST);
		}

		Internationalization internationalizationAux = iInternationalization.save(objeto);
		
		return new ResponseEntity<Internationalization>(internationalizationAux, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> excluir(Long id, String token) {

		Optional<Internationalization> internationalizationOp = iInternationalization.findById(id);

		if (internationalizationOp.isPresent()) {
			iInternationalization.delete(internationalizationOp.get());
			return new ResponseEntity<String>("Registro Excluído com Sucesso!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Registro não Encontrado com o ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> localizar(Long id) {

		Optional<Internationalization> internationalizationOp = iInternationalization.findById(id);
		
		if (internationalizationOp.isPresent()) {
			return new ResponseEntity<Internationalization>(internationalizationOp.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Registro não Encontrado com o ID: " + id, HttpStatus.BAD_REQUEST);
		}
	}

}
