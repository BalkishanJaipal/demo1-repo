package com.bkj.demo.Repository;

import com.bkj.demo.Entity.Workorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkorderRepo extends JpaRepository<Workorder,Long> {

}
