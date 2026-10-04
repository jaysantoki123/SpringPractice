package com.Placement.SpringPractice.AddressRepository;

import com.Placement.SpringPractice.AddressEntity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
}
