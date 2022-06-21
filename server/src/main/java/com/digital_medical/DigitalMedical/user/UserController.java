package com.digital_medical.DigitalMedical.user;

import com.digital_medical.DigitalMedical.security.JwtTokenProvider;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/doctor")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private  UserServices userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JwtTokenProvider tokenProvider;


    @PostMapping("/register")
    public void doctorRegister(@RequestBody UserEntity doctor){
        userService.addNewDoctor(doctor);
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
}
