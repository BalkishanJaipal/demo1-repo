package com.bkj.demo.Controller;
import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Rules;
import com.bkj.demo.Entity.Workorder;
import com.bkj.demo.Repository.WorkorderRepo;
//import com.bkj.demo.Service.WorkOrderService;
import org.hibernate.jdbc.Work;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WorkOrderController {
   @Autowired
    WorkorderRepo workRepo;

    @GetMapping("/workorders")
    public List<Workorder> getAllWorkOrders()
    {
        return  workRepo.findAll();
    }

    @GetMapping("/workorder/{id}")
    public ResponseEntity<Workorder> getWorkOrder(@PathVariable Long id) {

        try{
            Optional<Workorder> workorder = workRepo.findById(id);
            return ResponseEntity.ok(workorder.get());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping("/workorder/create")
    public ResponseEntity<Workorder> createWorkorder(@RequestBody Workorder workorder)
    {
        workRepo.save(workorder);
        return ResponseEntity.ok(workorder);

    }


    //UPDATE
    @PutMapping("workorder/update/{id}")
    public ResponseEntity<Workorder> updateRule(@PathVariable Long id) {
        try {
            Optional<Workorder> workorder = workRepo.findById(id);
          //  wo.get().setOrg("Nile");
            workRepo.save(workorder.get());
            return ResponseEntity.ok(workorder.get());
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    //Delete
    @DeleteMapping("/workorder/delete/{id}")
    public Object deleteWorkorder(@PathVariable Long id)
    {
        try
        {
            Optional<Workorder> workorder=  workRepo.findById(id);
            workRepo.delete(workorder.get());
            return "Workorder deleted";
        }

        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }




//    @GetMapping ("/rule1")
//    public void rule1() {
//
//        Map<Workorder,List<Driver>>output = rulesService.getDriversWithBasicPayLessThan25(new WorkOrderGenerator().getWorkorders(), new DriverGenerator().getDrivers()    );
//        for (Map.Entry<Workorder, List<Driver>> entry : output.entrySet()) {
//            Workorder workorder = entry.getKey();
//            List<Driver> drive = entry.getValue();
//
//
//            System.out.println("Selected Drivers for Workorder id: " + workorder.getWorkorderid());
//            for (Driver driver : drive) {
//                System.out.println("  Driver: Name- " + driver.getDriverName() + ", Age: " + driver.getAge()+",BasicPay: "+driver.getBasicPay());
//            }
//        }
//
//    }
//
//
//    @GetMapping("/rule2")
//    public void rule2() {
//
//        System.out.println("Rule-2");
//        Map<Workorder,List<Driver>>output = rulesService.getDriversForSpecificWorkOrderCriteria(new WorkOrderGenerator().getWorkorders(),new DriverGenerator().getDrivers());
//        for (Map.Entry<Workorder, List<Driver>> entry : output.entrySet()) {
//            Workorder workorder = entry.getKey();
//            List<Driver> drive = entry.getValue();
//
//
//            System.out.println("Workorder id: " + workorder.getWorkorderid());
//            for (Driver driver : drive) {
//                System.out.println("  Driver: Name- " + driver.getDriverName() + ", Available City : " + driver.getAvailableCity()+",PostalCode : "+driver.getAvailablePostalcode());
//            }
//        }
//
//    }
//
//
//    @GetMapping("/rule3")
//    public void rule3() {
//
//        System.out.println("Rule-3");
//        List<Workorder>output = rulesService.getDriversForEmptyAppointment(new WorkOrderGenerator().getWorkorders());
//        for(Workorder wo:output)
//        {
//            System.out.println("Do not provide drivers for Workorder ID: " + wo.getWorkorderid());
//        }
//
//    }
//
//
//    @GetMapping("/rule4")
//    public void rule4() {
//
////        HelpingClass helper = new HelpingClass();
////        session.insert(helper);
//        System.out.println("Rule-4");
//        Map<Workorder,Driver>output = rulesService.sequenceWorkOrdersAndSelectDrivers(new WorkOrderGenerator().getWorkorders(),new DriverGenerator().getDrivers());
//        for (Map.Entry<Workorder, Driver> entry : output.entrySet()) {
//            Workorder workorder = entry.getKey();
//            Driver driver = entry.getValue();
//
//
//            System.out.println("Workorder id: " + workorder.getWorkorderid());
//
//                System.out.println("  Driver: Name- " + driver.getDriverName() + ", Age: " + driver.getAge()+",BasicPay: "+driver.getBasicPay());
//
//        }
//
//
//    }
//
//
//    @GetMapping("/rule5")
//    public void rule5() {
//
//        HelpingClass helper = new HelpingClass();
//        session.insert(helper);
//        System.out.println("Rule-5");
//        Map<String, Map<Workorder, List<Driver>>> output = rulesService.breakAndSequencingWorkOrders(new WorkOrderGenerator().getWorkorders(),new DriverGenerator().getDrivers());
//        for (Map.Entry<String, Map<Workorder, List<Driver>>> entry : output.entrySet()) {
//            String Key = entry.getKey();
//            Map<Workorder, List<Driver>> innerMap = entry.getValue();
//
//            System.out.println("Group: " + Key);
//            for (Map.Entry<Workorder, List<Driver>> innerEntry : innerMap.entrySet()) {
//                Workorder workorder = innerEntry.getKey();
//                List<Driver> drivers = innerEntry.getValue();
//
//
//                System.out.println("Workorder id: " + workorder.getWorkorderid());
//                for (Driver driver : drivers) {
//                    System.out.println("  Driver: Name- " + driver.getDriverName() + ", Age: " + driver.getAge()+",BasicPay: "+driver.getBasicPay());
//                }
//            }
//        }
//    }
//

}

