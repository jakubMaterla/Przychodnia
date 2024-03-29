package pl.jakubmaterla.clinic.patient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jakubmaterla.clinic.patient.model.*;
import pl.jakubmaterla.clinic.patient.services.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    private final PatientService service;
    private final ColorService colorService;
    private final RaceService raceService;
    private final OwnerService ownerService;
    private final SexService sexService;
    private final SizeService sizeService;
    private final TreatmentService treatmentService;
    private final MedicineService medicineService;

    public PatientController(PatientService service, ColorService colorService, RaceService raceService, OwnerService ownerService, SexService sexService, SizeService sizeService, TreatmentService treatmentService, MedicineService medicineService) {
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
    String newPatient(Model model) {
        List<Patient> patients = service.findAll();
        model.addAttribute("patients", patients);
        List<Color> colorList = colorService.readAll();
        List<Race> raceList = raceService.readAll();
        List<Sex> sexList = sexService.readAll();
        List<Size> sizeList = sizeService.readAll();
        model.addAttribute("sexes", sexList);
        model.addAttribute("races", raceList);
        model.addAttribute("colors", colorList);
        model.addAttribute("sizes", sizeList);
        return "admin/patient/patients";
    }

    @PostMapping("/patients/addNew")
    String savePatient(Patient toCreate) {
        service.save(toCreate);
        return "redirect:/patients";
    }

    @GetMapping("/patients/findById/{id}")
    @ResponseBody
    Optional<Patient> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/patients/update", method = {RequestMethod.PUT, RequestMethod.GET})
    String update(Patient patient){
        service.save(patient);
        return "redirect:/patients";
    }

    @RequestMapping(value = "patients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/patients";
    }


    /*@GetMapping("/medicines")
    String readAllMed(Model model) {
        List<Medicine> medicineList = medicineService.findAll();
        model.addAttribute("medicines", medicineList);
        model.addAttribute("Medicine", new Medicine());
        return "admin/patient/medicines";
    }

    @PostMapping("/save-new-med")
    String saveNedMed(@Valid Medicine medicine, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/patient/medicines";
        }
        medicineService.save(medicine);
        return "redirect:/medicines";
    }

    @GetMapping("/treatments")
    String readAllTreat(Model model) {
        List<Treatment> treatmentList = treatmentService.findAll();
        model.addAttribute("treatments", treatmentList);
        model.addAttribute("Treatment", new Treatment());
        return "admin/patient/treatments";
    }

    @PostMapping("/save-new-treat")
    String saveNedTreat(@Valid Treatment toSave, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/patient/medicines";
        }
        treatmentService.save(toSave);
        return "redirect:/treatments";
    }*/
   /* @GetMapping("/add-patient-med/{id}")
    String addMedicine(@PathVariable int id, Model model) {
        var pet = service.findById(id);
        List<Medicine> medicines = medicineService.findAll();
        model.addAttribute("medicines", medicines);
        model.addAttribute("Patient", pet);
        return "patient/addPatientMedicine";
    }

    @PostMapping("/save-patient-med/{id}")
    String savePetMedicine(@PathVariable int id, @Valid Patient toSave, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "patient/addPatientMedicine";
        }
        var pet = service.findById(id);
        int petId = pet.get().getId();
        toSave.setMedicine_id(petId);
        service.save(toSave);
        return "redirect:/patients";
    }*/
/*
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
    }*/
}
