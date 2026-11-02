package com.example.leave.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeLeave {

    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    @Size(min = 2, max = 50, message = "Employee name must be 2 to 50 characters")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "Employee name can contain only letters and spaces")
    private String employeeName;

    @NotBlank(message = "Leave type is mandatory")
    @Pattern(regexp = "^(Sick Leave|Casual Leave|Earned Leave)$",
             message = "Leave type must be Sick Leave, Casual Leave or Earned Leave")
    private String leaveType;

    @Min(value = 1, message = "Leave days should be minimum 1")
    @Max(value = 30, message = "Leave days cannot be more than 30")
    private int numberOfDays;

    @Size(max = 200, message = "Reason cannot be more than 200 characters")
    private String reason;

    private String status = "PENDING";

    public EmployeeLeave() {}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public int getNumberOfDays() { return numberOfDays; }
    public void setNumberOfDays(int numberOfDays) { this.numberOfDays = numberOfDays; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
