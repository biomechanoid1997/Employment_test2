package com.example.employment_test2.controllers;

import com.example.employment_test2.models.*;
import com.example.employment_test2.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/import")
public class DatabaseImportController {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    CompanyUserRepo companyUserRepo;

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    PatientNoteRepo patientNoteRepo;

    @Autowired
    PatientProfileRepo patientProfileRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public RedirectView ImportData(){
       Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
      String dateModified = simpleDateFormat.format(date);
        ArrayList<UserModel>userModels = new ArrayList<UserModel>(userRepo.findAll());
        ArrayList<ClientModel> clientModels = new ArrayList<ClientModel>(clientRepo.findAll());
        ArrayList<NoteModel> noteModels = new ArrayList<NoteModel>(noteRepo.findAll());

        ArrayList<PatientProfileModel> patientProfileModels = new ArrayList<PatientProfileModel>(patientProfileRepo.findAll());
        ArrayList<CompanyUserModel> companyUserModels = new ArrayList<CompanyUserModel>(companyUserRepo.findAll());

        for (int i = 0; i < userModels.size(); i++) {
       boolean isUniqueUser = true;
       if(!companyUserModels.isEmpty()) {
           for (int j = 0; j < companyUserModels.size(); j++) {
               if (userModels.get(i).getLogin().equals(companyUserModels.get(j).getLogin())) {
                   isUniqueUser = false;
               }
           }
       }
            if (!isUniqueUser){
                continue;
            }
           CompanyUserModel companyUserModel = new CompanyUserModel();
            companyUserModel.setLogin(userModels.get(i).getLogin());
            companyUserRepo.save(companyUserModel);
        }

        for (int i = 0; i < clientModels.size(); i++) {
            boolean isPresent = false;
            if(!patientProfileModels.isEmpty()){
                for (int j = 0; j < patientProfileModels.size(); j++) {
                    if (
                            clientModels.get(i).getGuid().equals(patientProfileModels.get(j).getOldClientGuid()) &&
                                    clientModels.get(i).getFirstName().equals(patientProfileModels.get(j).getFirstName()) &&
                                    clientModels.get(i).getLastName().equals(patientProfileModels.get(j).getLastName())
                    ) {
                        isPresent = true;
                    }
                    }
                }
            if (isPresent){
                continue;
            }
            PatientProfileModel patientProfileModel = new PatientProfileModel();
            patientProfileModel.setOldClientGuid(clientModels.get(i).getGuid());
            patientProfileModel.setFirstName(clientModels.get(i).getFirstName());
           patientProfileModel.setLastName(clientModels.get(i).getLastName());
           if (clientModels.get(i).getStatus().equals("ACTIVE")){
               patientProfileModel.setStatusId(1);
         }else if (clientModels.get(i).getStatus().equals("INACTIVE")){
               patientProfileModel.setStatusId(0);
           }else {patientProfileModel.setStatusId(0);}
           patientProfileRepo.save(patientProfileModel);
        }

        for (int i = 0; i < noteModels.size(); i++) {
            PatientNoteModel patientNoteModel = new PatientNoteModel();
            CompanyUserModel companyUserModel = companyUserModels.get(i);
            NoteModel noteModel = noteModels.get(i);
            ///
           PatientProfileModel patientProfileModel = patientProfileRepo.findByOldClientGuid(noteModel.getClientGuid());
           patientNoteModel.setPatientId(patientProfileModel.getId());
          ///
           patientNoteModel.setCreatedBy(companyUserModel.getId());
           patientNoteModel.setLastModifiedBy(companyUserModel.getId());
           patientNoteModel.setCreationTime(noteModel.getCreatedDateTime());
           patientNoteModel.setNote(noteModel.getNote());
           patientNoteModel.setLastTimeModified(dateModified);
           patientNoteRepo.save(patientNoteModel);
        }

        return new RedirectView("/");

    }
}
