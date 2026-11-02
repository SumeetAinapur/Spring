package com.example.leave.controller;

import com.example.leave.model.EmployeeLeave;
import com.example.leave.service.EmployeeLeaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService service;

    public EmployeeLeaveController(EmployeeLeaveService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeLeave applyLeave(@Valid @RequestBody EmployeeLeave leave) {
        return service.applyLeave(leave);
    }

    @GetMapping
    public List<EmployeeLeave> getAllLeaves() {
        return service.getAllLeaves();
    }

    @GetMapping("/search")
    public List<EmployeeLeave> searchByEmployeeName(@RequestParam String name) {
        return service.getLeavesByEmployeeName(name);
    }

    @PatchMapping("/{id}/status")
    public EmployeeLeave updateStatus(@PathVariable Long id,
                                      @RequestBody Map<String, String> request) {
        String status = request.get("status");
        if (status == null || !(status.equals("APPROVED") || status.equals("REJECTED"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Status must be APPROVED or REJECTED");
        }
        EmployeeLeave leave = service.updateStatus(id, status);
        if (leave == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found");
        }
        return leave;
    }

    @GetMapping("/{id}")
    public EmployeeLeave getLeaveById(@PathVariable Long id) {
        EmployeeLeave leave = service.getLeaveById(id);
        if (leave == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found");
        }
        return leave;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeave(@PathVariable Long id) {
        if (!service.deleteLeave(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found");
        }
    }
}
