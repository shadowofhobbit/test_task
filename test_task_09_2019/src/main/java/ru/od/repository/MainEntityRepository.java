package ru.od.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.od.model.MainEntity;

@Repository
public interface MainEntityRepository extends JpaRepository<MainEntity, Long> {
}
