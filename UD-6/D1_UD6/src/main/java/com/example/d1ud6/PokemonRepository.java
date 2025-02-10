package com.example.d1ud6;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pokemon", path = "pokemon")
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Long>, CrudRepository<Pokemon,Long> {

  List<Pokemon> findByNombre(@Param("name") String name);

}