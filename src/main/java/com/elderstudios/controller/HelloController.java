package com.elderstudios.controller;

import com.elderstudios.domain.MobileServiceEntry;
import com.elderstudios.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.validation.Valid;

@Controller
public class HelloController {

	@Autowired
	protected MobileService mobileService;

	private static final String MOBILE_FORM = "mobile";
	private static final String ENTRIES_KEY = "entries";
	private static final String FORM_KEY = "form";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayMobile( Model model ) {

		model.addAttribute(ENTRIES_KEY, mobileService.findAll());
		model.addAttribute(FORM_KEY, new MobileServiceEntry());

		return MOBILE_FORM;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String changeMobile(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) MobileServiceEntry form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, mobileService.findAll());
			return MOBILE_FORM;
		}

		mobileService.save(form);

		return "redirect:/";
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String deleteEntry (Model model, @PathVariable Long id) {

		mobileService.delete (id);

		return "redirect:/";
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.GET)
	public String editEntry (Model model, @PathVariable Long id) {
		model.addAttribute (FORM_KEY, mobileService.findOne (id));
		return MOBILE_FORM;
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.POST)
	public String editSaveMobile (Model model,
									 @PathVariable Long id,
									 @Valid @ModelAttribute(FORM_KEY) MobileServiceEntry form,
									 BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, mobileService.findAll());
			return MOBILE_FORM;
		}

		MobileServiceEntry existing = mobileService.findOne (id);
		existing.setMake (form.getMake());
		existing.setModel(form.getModel());
		existing.setColour(form.getColour());
		existing.setImei(form.getImei());
		mobileService.save (existing);

		return "redirect:/";
	}


}