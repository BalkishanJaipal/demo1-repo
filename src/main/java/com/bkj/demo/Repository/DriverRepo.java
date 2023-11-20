package com.bkj.demo.Repository;

import com.bkj.demo.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver,Long> {
}
