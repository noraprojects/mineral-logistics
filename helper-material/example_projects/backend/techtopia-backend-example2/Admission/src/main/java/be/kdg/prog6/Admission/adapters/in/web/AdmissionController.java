package be.kdg.prog6.Admission.adapters.in.web;


import be.kdg.prog6.Admission.ports.in.LeaveParkCommand;
import be.kdg.prog6.Admission.ports.in.LeaveParkUseCase;
import be.kdg.prog6.Admission.ports.in.ValdidateUserUseCase;
import be.kdg.prog6.Admission.ports.in.ValidateTicketCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class AdmissionController {

    private final ValdidateUserUseCase valdidateUserUseCase;
    private final LeaveParkUseCase leaveParkUseCase;


    // Define a list to save the added UUIDs //TEST PURPOSES!
    private List<UUID> addedUUIDs = new ArrayList<>();

    public AdmissionController(ValdidateUserUseCase valdidateUserUseCase, LeaveParkUseCase leaveParkUseCase) {
        this.valdidateUserUseCase = valdidateUserUseCase;
        this.leaveParkUseCase = leaveParkUseCase;
    }

    @PostMapping("/code/{code}")
    public boolean validateCode(@PathVariable String code) {
        return valdidateUserUseCase.validateTicket(new ValidateTicketCommand(code));

    }

    @PostMapping("/admission/{amount}")
    public void enterAlot(@PathVariable int amount) {
        for (int i = 0; i < amount; i++) {
            UUID randomUUID = UUID.randomUUID();
            valdidateUserUseCase.validateTicket(new ValidateTicketCommand(randomUUID.toString()));
            // Add the generated UUID to the list
            addedUUIDs.add(randomUUID);
        }
    }

    //TEST PURPOSES!
    @PostMapping("/dismission/{amount}")
    public void leaveAlot(@PathVariable int amount) {
        // Remove the latest UUIDs when dismissing someone
        for (int i = 0; i < amount; i++) {
            if (!addedUUIDs.isEmpty()) {
                UUID latestUUID = addedUUIDs.remove(addedUUIDs.size() - 1);
                leaveParkUseCase.leavePark(new LeaveParkCommand(latestUUID.toString()));
            } else {
                System.out.println("No more UUIDs to dismiss.");
            }
        }
    }


    @PostMapping("/leave/{code}")
    public void leavePark(@PathVariable String code) {
        leaveParkUseCase.leavePark(new LeaveParkCommand(code));
    }

}
