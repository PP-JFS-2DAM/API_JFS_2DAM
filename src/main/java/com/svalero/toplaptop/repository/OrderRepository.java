package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findByOrderDateContainingOrDescription(LocalDate orderDate, String description);



    @Query("select w from work_order w where w.computer.brand like %?1%" +
            " or w.computer.model like %?1%" +
            " or w.computer.user.name like %?2%" +
            " or w.computer.user.surname like %?2%")
    List<Order> findByComputer_BrandContainingOrComputer_ModelContainingOrComputer_User_NameContainingOrComputer_User_SurnameContaining(String brandModel, String nameSurname);


    /*
 @Query("select w from work_order w " +
            "where w.client.name like concat('%', ?1, '%') or w.client.surname like concat('%', ?1, '%') " +
            "or w.bike.brand like concat('%', ?2, '%') or w.bike.model like concat('%', ?2, '%') " +
            "or w.bike.licensePlate like concat('%', ?3, '%')")
    List<WorkOrder> findByClient_NameContainingOrClient_SurnameContainingOrBike_BrandContainingOrBike_ModelContainingOrBike_LicensePlateContaining
    (String nameSurname, String brandModel, String licensePlate);

*/

}
