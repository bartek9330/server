package com.fit_web_app.services;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.UsersEntity;
import com.fit_web_app.services.bl.*;
import net.minidev.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

@EnableWebMvc
@RestController
public class Endpoints implements ErrorController {
    private final CoursesBl coursesBl;

    private final StepsBl stepsBl;

    private final UserBl userBl;

    private final UserDataBl userDataBl;

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public Endpoints() {
        coursesBl = new CoursesBlImpl();
        stepsBl = new StepsBlImpl();
        userBl = new UserBlImpl();
        userDataBl = new UserDataBlImpl();
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        if (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).equals(HttpStatus.UNAUTHORIZED.value())) {
            return "Server is working";
        }
        return "Error: " + request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    }

    @PostMapping(value = "/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userBl.getAllUsers(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getUserByLogin")
    public ResponseEntity<?> getUserByLogin(@RequestBody HashMap<String, String> parameters, @RequestHeader HashMap<String, String> headers) {
        try {
            UsersEntity user = userBl.getUserByLogin(parameters.get("login"), headers.get("authorization"));
            user.setPassword("");

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody HashMap<String, String> parameters) {
        try {
            userBl.registerUser(parameters.get("login"), parameters.get("password"), parameters.get("email"), Integer.parseInt(parameters.get("roleId")));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageUserHasBeenAdded")), HttpStatus.CREATED);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody HashMap<String, Integer> parameters) {
        try {
            userBl.deleteUser(parameters.get("userId"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageUserHasBeenDeleted")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getAvailableRoles")
    public ResponseEntity<?> getAvailableRoles() {
        try {
            return new ResponseEntity<>(userBl.getAvailableRoles(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getAllCourses")
    public ResponseEntity<?> getAllCourses() {
        try {
            return new ResponseEntity<>(coursesBl.getAllCourses(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getCourses")
    public ResponseEntity<?> getCourses(@RequestBody HashMap<String, Integer> parameters) {
        try {
            return new ResponseEntity<>(coursesBl.getCourse(parameters.get("courseId")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody HashMap<String, String> parameters) {
        try {
            coursesBl.addCourse(parameters.get("name"), parameters.get("description"), parameters.get("image"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageCourseHasBeenAdded")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/deleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestBody HashMap<String, Integer> parameters) {
        try {
            coursesBl.deleteCourse(parameters.get("courseId"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageCourseHasBeenDeleted")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/editCourse")
    public ResponseEntity<?> editCourse(@RequestBody HashMap<String, String> parameters) {
        try {
            coursesBl.editCourse(Integer.valueOf(parameters.get("id")), parameters.get("name"), parameters.get("description"), parameters.get("image"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageCourseHasBeenChanged")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getStep")
    public ResponseEntity<?> getStep(@RequestBody HashMap<String, Integer> parameters) {
        try {
            return new ResponseEntity<>(stepsBl.getStep(parameters.get("stepId")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addStep")
    public ResponseEntity<?> addStep(@RequestBody HashMap<String, String> parameters) {
        try {
            stepsBl.addStep(Integer.valueOf(parameters.get("courseId")), parameters.get("name"), parameters.get("description"), parameters.get("image"), Integer.valueOf(parameters.get("stepNumber")));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageStepHasBeenAdded")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/deleteStep")
    public ResponseEntity<?> deleteStep(@RequestBody HashMap<String, Integer> parameters) {
        try {
            stepsBl.deleteStep(parameters.get("stepId"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageStepHasBeenDeleted")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/editStep")
    public ResponseEntity<?> editStep(@RequestBody HashMap<String, String> parameters) {
        try {
            stepsBl.editStep(parameters.get("name"), parameters.get("description"), parameters.get("image"), Integer.valueOf(parameters.get("id")));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageStepHasBeenChanged")), HttpStatus.OK);
        } catch (DevException exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getAvailableCoursesForUser")
    public ResponseEntity<?> getAvailableCoursesForUser(@RequestBody HashMap<String, Integer> parameters) {
        try {
            return new ResponseEntity<>(coursesBl.getAvailableCoursesForUser(parameters.get("userId")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/assignUserToCourse")
    public ResponseEntity<?> assignUserToCourse(@RequestBody HashMap<String, Integer> parameters) {
        try {
            coursesBl.assignUserToCourse(parameters.get("userId"), parameters.get("courseId"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageUserHasBeenAssigned")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/unassignUserToCourse")
    public ResponseEntity<?> unassignUserToCourse(@RequestBody HashMap<String, Integer> parameters) {
        try {
            coursesBl.unassignUserToCourse(parameters.get("userId"), parameters.get("courseId"));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageUserHasBeenUnassigned")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getMyCourses")
    public ResponseEntity<?> getMyCourses(@RequestBody HashMap<String, Integer> parameters) {
        try {
            return new ResponseEntity<>(coursesBl.getMyCourses(parameters.get("userId")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/saveBMI")
    public ResponseEntity<?> saveBMI(@RequestBody HashMap<String,String> parameters) {
        try {
            userDataBl.saveBMI(Integer.valueOf(parameters.get("userId")), Integer.valueOf(parameters.get("height")), Integer.valueOf(parameters.get("weight")), new SimpleDateFormat("dd-MM-yyyy").parse(parameters.get("date")));
            return new ResponseEntity<>(Collections.singletonMap("message", messages.getString("messageUserDataHasBeenSaved")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getMyBMI")
    public ResponseEntity<?> getMyBMI(@RequestBody HashMap<String, Integer> parameters) {
        try {
            return new ResponseEntity<>(userDataBl.getMyBMI(parameters.get("userId")), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(Collections.singletonMap("message", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}