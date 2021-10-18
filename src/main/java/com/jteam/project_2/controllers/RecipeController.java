package com.jteam.project_2.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "recipes")
@Transactional
public class RecipeController {
}
