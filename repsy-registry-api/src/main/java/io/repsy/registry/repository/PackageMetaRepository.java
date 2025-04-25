package io.repsy.registry.repository;

import io.repsy.registry.model.PackageMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageMetaRepository extends JpaRepository<PackageMeta, Long> {
    PackageMeta findByAPackageId(Long packageId);
}
