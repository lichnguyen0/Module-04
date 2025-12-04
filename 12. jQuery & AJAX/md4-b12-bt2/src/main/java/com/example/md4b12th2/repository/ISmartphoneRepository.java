package com.example.md4b12th2.repository;

import com.example.md4b12th2.model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartphoneRepository extends JpaRepository<Smartphone, Long> {
}
