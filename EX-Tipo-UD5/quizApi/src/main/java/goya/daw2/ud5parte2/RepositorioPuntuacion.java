package goya.daw2.ud5parte2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RepositorioPuntuacion extends CrudRepository<Puntuacion, Long> {
	public List<Puntuacion> findAllByOrderByPuntosDesc();
}
