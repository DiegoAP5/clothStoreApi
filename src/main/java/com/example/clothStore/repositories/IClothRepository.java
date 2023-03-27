package com.example.clothStore.repositories;

import com.example.clothStore.entities.Cloth;
import com.example.clothStore.entities.Projections.ClothProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClothRepository extends JpaRepository<Cloth,Long> {
    @Query(value = "select cloths.* from cloths",nativeQuery = true)
    List<ClothProjection> listClothes();

    @Query(value = "select cloths.* from cloths"+
            "where cloths.name =:%clothName%",nativeQuery = true)
    List<ClothProjection> getClothByName(String clothName);
}
