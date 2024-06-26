package com.spring.jwt.repository;


import com.spring.jwt.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

    @Query(value = "SELECT * FROM car WHERE area = ?1", nativeQuery = true)
    Optional<List<Car>> FindByArea(@Param("area") String area);

    @Query("SELECT c FROM Car c WHERE c.price > :minPrice AND c.price < :maxPrice AND c.area = :area AND c.year = :year AND c.brand = :brand AND c.model = :model AND c.transmission = :transmission AND c.fuelType = :fuelType")
    Optional<List<Car>> findCarsByParameters(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, @Param("area") String area, @Param("year") int year, @Param("brand") String brand, @Param("model") String model, @Param("transmission") String transmission, @Param("fuelType") String fuelType);

//    @Query("SELECT c FROM Car c WHERE c.dealerId = :dealerId AND c.carStatus = :status")
//    List<Car> findCarsByDealerIdAndStatus(@Param("dealerId") int dealerId, @Param("status") String status);
//    @Query("SELECT c FROM Car c WHERE dealerId = :dealerId and carStatus = 'pending'")
//    @Query("SELECT c FROM Car c WHERE c.dealerId= :dealerId AND c.carStatus= :carStatus")

    @Query(value = "SELECT * FROM car c WHERE c.dealer_id = :dealerId AND c.car_status = :carStatus ORDER BY c.date DESC", nativeQuery = true)
    List<Car> findByDealerIdAndCarStatus(@Param("dealerId") Integer dealerId, @Param("carStatus") String carStatus);

    @Query("SELECT c FROM Car c WHERE carStatus='active' OR carStatus='pending'")
    public List<Car> getPendingAndActivateCar();

    public Optional<List<Car>> getByDealerId(Integer dealerId);

    @Query("SELECT c FROM Car c WHERE c.carStatus = 'pending' OR c.carStatus = 'active' ORDER BY c.id DESC")
    List<Car> getPendingAndActivateCarOrderedByCreatedAtDesc();
}


