package projeto.spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;
import projeto.spring.data.dao.InterfaceTelefone;
import projeto.spring.data.model.Telefone;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired // Injeção de dependência Spring
	private InterfaceSpringDataUser interfaceSpringDataUser;

	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();

		usuarioSpringData.setLogin("teste");
		usuarioSpringData.setSenha("teste");
		usuarioSpringData.setEmail("teste@gmail.com");
		usuarioSpringData.setNome("aaaabbbb");
		usuarioSpringData.setIdade(21);

		interfaceSpringDataUser.save(usuarioSpringData);

		System.out.println("Quant de Users: " + interfaceSpringDataUser.count());
	}

	@Test
	public void testeConsulta() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);

		System.out.println(usuarioSpringData.get().getId());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getIdade());
		
		for(Telefone telefone: usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getId());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println("----------------------------");
		}
	}

	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();

		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println("----------------------------------");
		}
	}

	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		UsuarioSpringData data = usuarioSpringData.get();
		data.setNome("Name Updated");
		interfaceSpringDataUser.save(data);
	}

	@Test
	public void testeDelete() {
		interfaceSpringDataUser.deleteById(3L);
	}

	@Test
	public void testeDelete2() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(4L);
		interfaceSpringDataUser.delete(usuarioSpringData.get());
	}

	@Test
	public void testeConsultaPorNome() {
		List<UsuarioSpringData> lista = interfaceSpringDataUser.buscaPorNome("teste");

		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println("----------------------------------");
		}
	}

	@Test
	public void testeConsultaNomeParam() {
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("teste");

		System.out.println(usuarioSpringData.getId());
		System.out.println(usuarioSpringData.getLogin());
		System.out.println(usuarioSpringData.getSenha());
		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getEmail());
		System.out.println(usuarioSpringData.getIdade());
	}

	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("to delete");
	}

	@Test
	public void testeUpdateEmailPorNome() { // pra teste, void..
		interfaceSpringDataUser.updateEmailPorNome("emailAtualizado@gmail.com", "teste");
	}

	@Test
	public void testeInsertTelefone() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);

		Telefone telefone = new Telefone();
		telefone.setNumero("51 - 325435434321");
		telefone.setTipo("Casa");
		telefone.setUsuarioSpringData(usuarioSpringData.get());

		interfaceTelefone.save(telefone);
	}

}