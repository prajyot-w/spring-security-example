package com.security.security.configuration.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Controller
@RestController
@RequestMapping("/service")
public @interface AppRestController {
}
