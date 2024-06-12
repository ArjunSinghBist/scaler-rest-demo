package org.scaler.demo.project.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{name}/{toName}")
    public String getName(@PathVariable("name") String userName, @PathVariable("toName") String toName) {
        return "Hello! from " + userName + " to " + toName;
    }

    @GetMapping()
    public String getRequestParam(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "toName", required = false) String toName,
                                  @RequestParam(value = "createdAt", required = false) String createdAt) {

        if(createdAt != null) createdAt = LocalDateTime.parse(createdAt).atZone(ZoneId.systemDefault()).toString();
        return "Hello! from " + name + " to " + toName + " at " + createdAt;
    }
}
