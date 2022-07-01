package com.digital_medical.DigitalMedical.user;

import com.digital_medical.DigitalMedical.role.RoleService;
import com.digital_medical.DigitalMedical.security.JwtTokenProvider;
import io.swagger.annotations.ApiImplicitParam;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/doctor")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private  UserServices userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JwtTokenProvider tokenProvider;


    @PostMapping("/register")
    public ResponseEntity<String> doctorRegister(@RequestBody UserEntity doctor) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        userService.addNewDoctor(doctor);
        String responseMessage = "User successfully registered on DigitalMedical.";
        jsonObject.put("status", responseMessage);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @PostMapping("/register_patient")
    public ResponseEntity<String> patientRegister(@RequestBody UserEntity doctor) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        userService.addNewPatient(doctor);
        String responseMessage = "Patient successfully registered on DigitalMedical.";
        jsonObject.put("status", responseMessage);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticateUser(@RequestBody UserEntity user) {
        log.info("UserServiceImpl : authenticate a user");
        JSONObject jsonObject = new JSONObject();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String email = user.getEmail();
                UserEntity loggedInUser = userService.findByEmail(email);
                jsonObject.put("name", email);
                jsonObject.put("role", loggedInUser.getRole().getName());
                jsonObject.put("token", tokenProvider.createToken(email, loggedInUser.getRole()));
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
            }
            throw new JSONException("Incorrect credentials");
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-current-user")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<UserEntity> getCurrentUser() {
        log.info("UserServiceImpl : get current user");
        JSONObject jsonObject = new JSONObject();
        try {
            UserEntity currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if(currentUser != null) return new ResponseEntity<>(currentUser, HttpStatus.OK);
            throw new JSONException("User was not found in the database.");
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-patients")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Collection> getPatients() {
        log.info("UserServiceImpl : get patients");
        JSONObject jsonObject = new JSONObject();
        try {
            if(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getRole() == roleService.findByName("DOCTOR")){
                return new ResponseEntity<>(userService.findAllPatients(), HttpStatus.OK);
            }
            throw new JSONException("No patients found in the database.");

        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
