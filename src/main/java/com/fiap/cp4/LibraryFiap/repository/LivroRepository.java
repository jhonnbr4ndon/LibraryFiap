package com.fiap.cp4.LibraryFiap.repository;

import com.fiap.cp4.LibraryFiap.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
