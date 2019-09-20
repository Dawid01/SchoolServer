package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


public class LessonPlanReader extends FileNotFoundException {

    private MultipartFile file;

    private PeroidRepository peroidRepository;
    private SubjectRepository subjectRepository;


    public LessonPlanReader(MultipartFile file, PeroidRepository peroidRepository, SubjectRepository subjectRepository) {
        this.file = file;
        this.peroidRepository = peroidRepository;
        this.subjectRepository = subjectRepository;

        savePeroids();
        saveSubjects();
    }

    private void savePeroids(){

        peroidRepository.deleteAll();

        try {
            String json = new String(file.getBytes());
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("periods").getAsJsonArray("period");

            if(jsonPeroids != null) {
                for (int i = 0; i < jsonPeroids.size(); i++) {
                    Period period = new Period();
                    period.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                    period.setPeriod(jsonPeroids.get(i).getAsJsonObject().get("_period").getAsInt());
                    period.setStartTime(jsonPeroids.get(i).getAsJsonObject().get("_starttime").getAsString());
                    period.setEndTime(jsonPeroids.get(i).getAsJsonObject().get("_endtime").getAsString());
                    peroidRepository.save(period);
                }
                System.out.println("Save Peroids");
            }

        }catch (IOException e){

        }
    }

    private void saveSubjects(){

        subjectRepository.deleteAll();

        try {
            String json = new String(file.getBytes());
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("subjects").getAsJsonArray("subject");

            if(jsonPeroids != null) {
                for (int i = 0; i < jsonPeroids.size(); i++) {
                    Subject subject = new Subject();
                    subject.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                    subject.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                    subjectRepository.save(subject);
                }
                System.out.println("Save Subjects");
            }

        }catch (IOException e){

        }
    }
}
