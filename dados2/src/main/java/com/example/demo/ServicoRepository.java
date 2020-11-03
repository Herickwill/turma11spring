package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoModel, Long>{
	
	//id do tipo long, portanto devemos colocar o Long ali

}
