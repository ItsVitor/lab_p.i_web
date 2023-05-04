package com.example.lab_0405;

import com.example.lab_0405.domain.*;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controla a produção de json para o cliente.
 * 
 * Implementa as 4 operações HTTP básicas.
 * 
 * @author jpalmeida 
 *
 */
@RestController
public class Lab0405Controller {

    public static List<Departamento> departamentos = new LinkedList<>();
	private static int nextId = 0;
 
	@GetMapping("/departamentos")
	public List<Departamento> getDepartamentos(@RequestParam(name = "nome", defaultValue = "") String nome) {
		return departamentos;
	}

	@GetMapping("/departamentos/{id}")
	public Departamento getDepartamento(@PathVariable(name = "id") int id) {
		if (departamentos.get(id) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado!");
            // veja opções para tratamento de erro em:
            // https://www.baeldung.com/spring-response-status-exception
		else
			return departamentos.get(id);
	}

    @GetMapping("/departamentos/{id}/funcionarios")
	public Departamento getFuncionarios(@PathVariable(name = "id") int id) {
		if (departamentos.get(id) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado!");
            // veja opções para tratamento de erro em:
            // https://www.baeldung.com/spring-response-status-exception2
		else
			return departamentos.getFuncionarios(id);
	}

	@PutMapping("/contatos/{id}")
	public Contato setContato(@PathVariable(name = "id") long id, @RequestBody Contato c) {
		if (agenda.get(id) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado!");
		c.setId(id);
		agenda.put(id, c);
		return c;
	}

	@DeleteMapping("/contatos/{id}")
	public void deleteContato(@PathVariable(name = "id") long id) {
		agenda.remove(id);
	}

	@PostMapping("/contatos")
	public Contato postContato(@RequestBody Contato c) {
		c.setId(nextId);
		agenda.put(nextId, c);
		nextId++;
		return c;
	}
}
