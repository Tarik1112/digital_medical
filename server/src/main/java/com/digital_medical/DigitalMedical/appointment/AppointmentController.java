package com.digital_medical.DigitalMedical.appointment;


import com.digital_medical.DigitalMedical.user.UserController;
import com.digital_medical.DigitalMedical.user.UserEntity;
import com.digital_medical.DigitalMedical.user.UserServices;
import io.swagger.annotations.ApiImplicitParam;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/appointment")
public class AppointmentController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserServices userService;

    @GetMapping("/get-appointments")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Collection> getAppointments() {
        log.info("UserServiceImpl : get appointments");
        JSONObject jsonObject = new JSONObject();
        try {
            UserEntity currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if(currentUser != null) return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.OK);
            throw new JSONException("No appointments found in the database.");
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/add-appointments/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Optional<Object>> addAppointments(@PathVariable(value = "user_id") String user_id, @RequestBody AppointmentEntity appointmentRequest) throws JSONException {
        log.info("UserServiceImpl : add appointments");
        JSONObject jsonObject = new JSONObject();

            Optional<Object> appointment = userService.findById(user_id).map(user -> {
                appointmentRequest.setStatus("OPEN");
                appointmentRequest.setUser(user);
                return appointmentService.saveOrUpdate(appointmentRequest);
            });

            return new ResponseEntity<>(appointment, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update-appointments/{appointment_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<AppointmentEntity> updateAppointments(@PathVariable(value = "appointment_id") String appointment_id) throws JSONException {
        log.info("UserServiceImpl : add appointments");
        JSONObject jsonObject = new JSONObject();

            AppointmentEntity appointment = appointmentService.findById(appointment_id).get();
            appointment.setStatus("FINISHED");

        return new ResponseEntity<>(appointmentService.saveOrUpdate(appointment), HttpStatus.CREATED);

    }

}
