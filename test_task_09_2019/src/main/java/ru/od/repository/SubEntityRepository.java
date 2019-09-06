package ru.od.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.od.model.SubEntity;

@Repository
public interface SubEntityRepository extends JpaRepository<SubEntity, Long> {
}
