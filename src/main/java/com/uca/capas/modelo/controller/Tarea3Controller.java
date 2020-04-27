package com.uca.capas.modelo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Tarea3Controller {

	@RequestMapping("/ingresar")
	public String index() {
		return "tareas/tarea3/index";
	}

	@RequestMapping("/resultadoAlumno")
	public ModelAndView resultadoAlumno(@RequestParam String names, @RequestParam String lastnames,
			@RequestParam String birthday, @RequestParam String birthplace, @RequestParam String school,
			@RequestParam String landline, @RequestParam String mobile) {

		ModelAndView mav = new ModelAndView();

		List<String> errores = new ArrayList<>();
		String msg = "Alumno ingresado con éxito";
		String phonePattern = "[0-9]{8}";
		boolean landlineMatches = Pattern.matches(phonePattern, landline);
		boolean mobileMatches = Pattern.matches(phonePattern, mobile);
		boolean birthdayFlag = false;

		LocalDate minDate = LocalDate.of(2003, 01, 01);

		if (!(birthday.isBlank() || birthday.isEmpty())) {
			LocalDate parsedBirthday = LocalDate.parse(birthday);
			birthdayFlag = parsedBirthday.isEqual(minDate) || parsedBirthday.isAfter(minDate);
		}

		if (names.length() < 1 || names.length() > 25) {
			errores.add("Los nombres deben estar entre 1 y 25 caracteres");
		}
		if (lastnames.length() < 1 || lastnames.length() > 25) {
			errores.add("Los apellidos deben estar entre 1 y 25 caracteres");
		}
		if (!birthdayFlag) {
			errores.add("La fecha no puede ser menor al 1 de enero de 2003");
		}
		if (birthplace.length() < 1 || birthplace.length() > 25) {
			errores.add("El lugar de nacimiento deben estar entre 1 y 25 caracteres");
		}
		if (school.length() < 1 || school.length() > 100) {
			errores.add("El instituto o colegio deben estar entre 1 y 25 caracteres");
		}
		if (!landlineMatches) {
			errores.add("El numero de telefono fijo tiene que ser igual a 8 numeros");
		}
		if (!mobileMatches) {
			errores.add("El numero de telefono movil tiene que ser igual a 8 numeros");
		}

		if (errores.isEmpty()) {
			mav.addObject("msg", msg);
			mav.setViewName("tareas/tarea3/resultadoAlumno");

		} else {
			mav.addObject("errores", errores);
			mav.setViewName("tareas/tarea3/errorAlumno");
		}

		return mav;
	}

}
