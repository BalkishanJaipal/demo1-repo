package com.bkj.demo.Repository;

import com.bkj.demo.Entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepo extends JpaRepository<Stop,Long> {
}
