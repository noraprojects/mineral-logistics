package be.kdg.prog6.landside.adapters.in;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    @GetMapping("/helloa")
    public void sayHelloA(){
        System.out.println("Hello BoundedContext A");
    }
}
