package br.com.juliano.springcloud.controller;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.juliano.springcloud.model.Contato;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(basePath = "/contatos", value = "Contatos", description = "Crud de Contatos", produces = "application/json")
@RequestMapping(value = "/contatos")
public class ContatoController {
	
	private List<Contato> contatos = new ArrayList<>();


	ContatoController() {
		this.contatos = buildContatos();
	}
	
	@ApiOperation(value = "Listar Contatos", notes = "Listar Contatos")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 201, message = "Contatos listados") })
	@GetMapping
	public List<Contato> getContatos() {
		return this.contatos;
	}
	@GetMapping(value = "/{id}")
	public Contato getContato(@PathVariable("id") Long id) {
		return this.contatos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
	}

	@PostMapping
	public Contato saveContato(@RequestBody Contato Contato) {
		Long nextId = 0L;
		if (this.contatos.size() != 0) {
			Contato lastContato = this.contatos.stream().skip(this.contatos.size() - 1).findFirst().orElse(null);
			nextId = lastContato.getId() + 1;
		}

		Contato.setId(nextId);
		this.contatos.add(Contato);
		return Contato;

	}

	@PutMapping
	public Contato updateContato(@RequestBody Contato contato) {
		Contato modifiedContato = this.contatos.stream().filter(u -> u.getId() == contato.getId()).findFirst().orElse(null);
		modifiedContato.setNome(contato.getNome());
		modifiedContato.setTelefone(contato.getTelefone());
		modifiedContato.setEmail(contato.getEmail());
		modifiedContato.setTipoContato(contato.getTipoContato());
		return modifiedContato;
	}

	@DeleteMapping(value = "/{id}")
	public boolean deleteContato(@PathVariable Long id) {
		Contato deleteContato = this.contatos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
		
		if (!isNull(deleteContato)) {
			this.contatos.remove(deleteContato);
			return true;
		} else  {
			return false;
		}
	}

	List<Contato> buildContatos() {
		List<Contato> Contatos = new ArrayList<>();

		Contato Contato1 = buildContato(1L, "John Doe", "11943399491", "john@email.com","trabalho");
		Contato Contato2 = buildContato(2L, "Jon Smith", "11943399492", "smith@email.com","trabalho");
		Contato Contato3 = buildContato(3L, "Will Craig", "11943399493", "will@email.com","trabalho");
		Contato Contato4 = buildContato(4L, "Sam Lernorad", "11943399494", "sam@email.com","trabalho");
		Contato Contato5 = buildContato(5L, "Ross Doe", "11943399495", "ross@email.com","trabalho");

		Contatos.add(Contato1);
		Contatos.add(Contato2);
		Contatos.add(Contato3);
		Contatos.add(Contato4);
		Contatos.add(Contato5);

		return Contatos;

	}

	Contato buildContato(Long id, String nome, String telefone, String email, String tipoContato) {
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		contato.setTipoContato(tipoContato);
		return contato;
	}

}
