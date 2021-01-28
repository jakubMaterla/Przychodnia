package pl.jakubmaterla.clinic.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubmaterla.clinic.patient.model.*;
import pl.jakubmaterla.clinic.patient.services.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    private final AnimalService service;

    private final ColorService colorService;
    private final RaceService raceService;
    private final OwnerService ownerService;
    private final SexService sexService;
    private final SizeService sizeService;
    private final TreatmentService treatmentService;
    private final MedicineService medicineService;

    public PatientController(AnimalService service, ColorService colorService, RaceService raceService, OwnerService ownerService, SexService sexService, SizeService sizeService, TreatmentService treatmentService, MedicineService medicineService) {
        this.service = service;
        this.colorService = colorService;
        this.raceService = raceService;
        this.ownerService = ownerService;
        this.sexService = sexService;
        this.sizeService = sizeService;
        this.treatmentService = treatmentService;
        this.medicineService = medicineService;
    }

    @GetMapping("/patients")
    String readAll(Model model) {
        List<Animal> patients = service.findAll();
        model.addAttribute("patients", patients);
        return "patient";
    }

    @GetMapping("/new-owners")
    String createOwner(Model model) {
        List<Owner> owners = ownerService.readAll();
        model.addAttribute("owners", owners);
        model.addAttribute("Owner", new Owner());
        return "newOwner";
    }

    @PostMapping("/save-owner")
    String saveOwner(@Valid Owner toSave, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "newOwner";
        }

        ownerService.save(toSave);
        return "redirect:/new-patient";
    }

    @GetMapping("/new-patient")
    String newPatient(Model model) {
        List<Color> colorList = colorService.readAll();
        List<Race> raceList = raceService.readAll();
        List<Sex> sexList = sexService.readAll();
        List<Size> sizeList = sizeService.readAll();
        model.addAttribute("sexes", sexList);
        model.addAttribute("races", raceList);
        model.addAttribute("colors", colorList);
        model.addAttribute("sizes", sizeList);
        model.addAttribute("Patient", new Animal());
        return "newPatient";
    }

    @PostMapping("/save-patient")
    String savePatient(@Valid Animal toCreate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newPatient";
        }
        service.save(toCreate);
        return "redirect:/patients";
    }

    @GetMapping("/add-patient-medicine/{id}")
    String addMedicine(@PathVariable int id, Model model) {
        Optional<Animal> patient = service.findById(id);
        List<Medicine> medicines = medicineService.findAll();
        model.addAttribute("medicines", medicines);
        model.addAttribute("Patient", patient);
        model.addAttribute("patId", patient.get().getId());
//        model.addAttribute("Medicine", new Medicine());
        return "addPatientMedicine";
    }

    @PostMapping ("/save-patient-medicine/{id}")
    String savePetMedicine(@PathVariable int id, @Valid Medicine toSave, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addPatientMedicine";
        }
        medicineService.save(toSave);
        return "redirect:/add-patient-medicine";
    }

    @GetMapping("/add-medicine/{id}")
    String addMed(@PathVariable int id, Model model) {
        List<Medicine> medicines = medicineService.findAll();
        model.addAttribute("medicines", medicines);
        Optional<Animal> patient = service.findById(id);
        Medicine med = new Medicine();
        med.setAnimal(patient.get());
        medicineService.save(med);
        model.addAttribute("Medicine", med);
        return "addMed";
    }

    @PostMapping("/add-new-med/{id}")
    String addNewMed(@PathVariable int id, @Valid Medicine medicine, BindingResult bindingResult) {
        Optional<Medicine> oldMedicine = medicineService.findById(id);
        if (bindingResult.hasErrors()){
            return "addMed";
        }
        oldMedicine.get().setName(medicine.getName());
        medicineService.save(oldMedicine.get());
        int patientId = oldMedicine.get().getAnimal().getId();
        return "redirect:/add-patient-medicine/2";
    }
}
