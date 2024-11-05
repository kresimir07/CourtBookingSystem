package org.example.tennisv3.repository;
import org.example.tennisv3.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

    Court findByName(String name);

}
