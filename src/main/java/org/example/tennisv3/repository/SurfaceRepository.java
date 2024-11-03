package org.example.tennisv3.repository;
import org.example.tennisv3.model.Surface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurfaceRepository extends JpaRepository<Surface, Long> {

    Surface findByName(String name);


}
