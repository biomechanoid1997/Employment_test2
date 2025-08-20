package com.example.employment_test2.services;

import com.example.employment_test2.models.*;
import com.example.employment_test2.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class DatabaseImportService {
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

    Date time = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    String currentTime = simpleDateFormat.format(time);
    String[] timetable={
            "00:15",
            "02:15",
            "04:15",
            "06:15",
            "08:15",
            "10:15",
            "12:15",
            "14:15",
            "16:15",
            "18:15",
            "20:15",
            "22:15",
    };
    boolean isActive = false;
    public void activateTimedImport(){
        boolean isActive = true;

        while (isActive){
            time = new Date();
            currentTime = simpleDateFormat.format(time);
            for (int i = 0; i < timetable.length; i++) {
                if (currentTime.equals(timetable[i])){

                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                    String dateModified = simpleDateFormat.format(date);
                    ArrayList<UserModel> userModels = new ArrayList<UserModel>(userRepo.findAll());
                    ArrayList<ClientModel> clientModels = new ArrayList<ClientModel>(clientRepo.findAll());
                    ArrayList<NoteModel> noteModels = new ArrayList<NoteModel>(noteRepo.findAll());

                    ArrayList<PatientProfileModel> patientProfileModels = new ArrayList<PatientProfileModel>(patientProfileRepo.findAll());
                    ArrayList<CompanyUserModel> companyUserModels = new ArrayList<CompanyUserModel>(companyUserRepo.findAll());

                    for (int a = 0; a < userModels.size(); a++) {
                        boolean isUniqueUser = true;
                        if(!companyUserModels.isEmpty()) {
                            for (int j = 0; j < companyUserModels.size(); j++) {
                                if (userModels.get(a).getLogin().equals(companyUserModels.get(j).getLogin())) {
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

                    for (int a = 0; a < clientModels.size(); a++) {
                        boolean isPresent = false;
                        if(!patientProfileModels.isEmpty()){
                            for (int j = 0; j < patientProfileModels.size(); j++) {
                                if (
                                        clientModels.get(a).getGuid().equals(patientProfileModels.get(j).getOldClientGuid()) &&
                                                clientModels.get(a).getFirstName().equals(patientProfileModels.get(j).getFirstName()) &&
                                                clientModels.get(a).getLastName().equals(patientProfileModels.get(j).getLastName())
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
                        if (clientModels.get(a).getStatus().equals("ACTIVE")){
                            patientProfileModel.setStatusId(1);
                        }else if (clientModels.get(a).getStatus().equals("INACTIVE")){
                            patientProfileModel.setStatusId(0);
                        }else {patientProfileModel.setStatusId(0);}
                        patientProfileRepo.save(patientProfileModel);
                    }

                    for (int a = 0; a < noteModels.size(); a++) {
                        PatientNoteModel patientNoteModel = new PatientNoteModel();
                        CompanyUserModel companyUserModel = companyUserModels.get(a);
                        NoteModel noteModel = noteModels.get(a);
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

                }
            }

        }
    }
}
