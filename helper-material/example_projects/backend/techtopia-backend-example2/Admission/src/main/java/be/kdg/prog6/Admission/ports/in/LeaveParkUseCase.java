package be.kdg.prog6.Admission.ports.in;

public interface LeaveParkUseCase {
    void leavePark(LeaveParkCommand validateTicketCommand);
}
