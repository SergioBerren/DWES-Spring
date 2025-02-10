package goya.daw2.ud5parte2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RepositorioUsuario extends  CrudRepository<Usuario, Long>{
	public List<Usuario> findByNombre(String nombre);
	public List<Usuario> findAllByOrderByNombreAsc();
}
