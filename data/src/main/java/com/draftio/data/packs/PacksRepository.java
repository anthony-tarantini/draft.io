package com.draftio.data.packs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PacksRepository extends JpaRepository<PackEntity, Long> {
    List<PackEntity> findTop3BySetCode(final String setCode);
}
