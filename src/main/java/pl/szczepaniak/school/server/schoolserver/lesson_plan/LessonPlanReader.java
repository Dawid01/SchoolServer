package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


public class LessonPlanReader extends FileNotFoundException {

    private MultipartFile file;

    private PeriodRepository peroidRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private ClassRepository classRepository;
    private GroupRepository groupRepository;


    public LessonPlanReader(MultipartFile file, PeriodRepository peroidRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, ClassRepository classRepository, GroupRepository groupRepository) {
        this.file = file;
        this.peroidRepository = peroidRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.groupRepository = groupRepository;

        savePeroids();
        saveSubjects();
        saveTeachers();
        saveClasses();
        saveGroups();
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

    private void saveTeachers(){

        teacherRepository.deleteAll();

        try {
            String json = new String(file.getBytes());
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("teachers").getAsJsonArray("teacher");

            if(jsonPeroids != null) {
                for (int i = 0; i < jsonPeroids.size(); i++) {
                    Teacher teacher = new Teacher();
                    teacher.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                    teacher.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                    teacherRepository.save(teacher);
                }
                System.out.println("Save Teachers");
            }

        }catch (IOException e){

        }
    }

    private  void saveClasses(){

        classRepository.deleteAll();

        try {
            String json = new String(file.getBytes());
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("classes").getAsJsonArray("class");

            if(jsonPeroids != null) {
                for (int i = 0; i < jsonPeroids.size(); i++) {
                    Class c = new Class();
                    c.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                    c.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                    classRepository.save(c);
                }
                System.out.println("Save Classes");
            }

        }catch (IOException e){

        }
    }

    void saveGroups(){

        groupRepository.deleteAll();

        try {
            String json = new String(file.getBytes());
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("groups").getAsJsonArray("group");

            if(jsonPeroids != null) {
                for (int i = 0; i < jsonPeroids.size(); i++) {
                    GroupClass group = new GroupClass();
                    group.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                    group.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                    group.setaClass(classRepository.findByexternalID(jsonPeroids.get(i).getAsJsonObject().get("_classid").getAsString()));
                    groupRepository.save(group);
                }
                System.out.println("Save Groups");
            }

        }catch (IOException e){

        }
    }

}
