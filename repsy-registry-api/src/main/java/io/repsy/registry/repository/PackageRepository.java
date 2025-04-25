package io.repsy.registry.repository;

import io.repsy.registry.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Package findByPackageNameAndVersion(String packageName, String version);
}
