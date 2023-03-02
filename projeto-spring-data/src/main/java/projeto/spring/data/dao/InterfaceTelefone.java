package projeto.spring.data.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.spring.data.model.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {

}
