package projeto.spring.data.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.model.UsuarioSpringData;

@Repository //Spring
//Classe/entity/entidade/tabela do DB, Long->id
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
  
	@Transactional(readOnly = true)
	@Query(value="select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);
	
	@Lock(LockModeType.READ)
	@Transactional(readOnly = true)// pra realmente n modificar nada no DB
	@Query(value="select p from UsuarioSpringData p where p.nome=:paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		// processa qualquer coisa
		return save(entity);
	}
	
	@Modifying
	//propagation =  varios propagations types.., propriedades possiveis
	//@Transactional rollbackFor=ExceptionEspecifica.class, se n informar, qualquer erro da roll
	@Transactional //do Spring
	@Query("delete from UsuarioSpringData u where u.nome=?1") //com ou sem value pelo visto
	public void deletePorNome(String nome);
	
	@Modifying
	@Transactional
	@Query("update UsuarioSpringData u set u.email=?1 where u.nome=?2")
	public void updateEmailPorNome(String email, String nome);
	
}