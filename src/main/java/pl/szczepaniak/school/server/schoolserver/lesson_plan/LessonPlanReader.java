package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.multipart.MultipartFile;
import pl.szczepaniak.school.server.schoolserver.SchoolServerApplication;
import pl.szczepaniak.school.server.schoolserver.files.FileController;

import java.io.*;


public class LessonPlanReader extends FileNotFoundException {

    private MultipartFile file;

    private PeriodRepository peroidRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private ClassRepository classRepository;
    private GroupRepository groupRepository;
    private DayRepository dayRepository;
    private ClassRoomRepository classRoomRepository;
    private WeekRepository weekRepository;
    private LessonRepository lessonRepository;
    private CardRepository cardRepository;

    private JsonObject jsonObject;

    public LessonPlanReader(MultipartFile file, FileController controller) {
        this.file = file;
        this.peroidRepository = controller.getPeroidRepository();
        this.subjectRepository = controller.getSubjectRepository();
        this.teacherRepository = controller.getTeacherRepository();
        this.classRepository = controller.getClassRepository();
        this.groupRepository = controller.getGroupRepository();
        this.dayRepository = controller.getDayRepository();
        this.classRoomRepository = controller.getClassRoomRepository();
        this.weekRepository = controller.getWeekRoomRepository();
        this.lessonRepository = controller.getLessonRepository();
        this.cardRepository = controller.getCardRepository();

        try {
            String json = new String(file.getBytes());
            jsonObject = new JsonParser().parse(json).getAsJsonObject();
        }catch (IOException ignored){}

        if(jsonObject != null) {
            savePeroids();
            saveSubjects();
            saveTeachers();
            saveClasses();
            saveGroups();
            saveDays();
            saveClassRooms();
            saveWeeks();
            saveLessons();
            saveCards();
        }
    }

    public LessonPlanReader(String json, FileController controller) {
        this.peroidRepository = controller.getPeroidRepository();
        this.subjectRepository = controller.getSubjectRepository();
        this.teacherRepository = controller.getTeacherRepository();
        this.classRepository = controller.getClassRepository();
        this.groupRepository = controller.getGroupRepository();
        this.dayRepository = controller.getDayRepository();
        this.classRoomRepository = controller.getClassRoomRepository();
        this.weekRepository = controller.getWeekRoomRepository();
        this.lessonRepository = controller.getLessonRepository();
        this.cardRepository = controller.getCardRepository();

        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        if(jsonObject != null) {
            savePeroids();
            saveSubjects();
            saveTeachers();
            saveClasses();
            saveGroups();
            saveDays();
            saveClassRooms();
            saveWeeks();
            saveLessons();
            saveCards();
        }
    }

    public LessonPlanReader(String s, SchoolServerApplication schoolServerApplication) {
    }

    private void savePeroids(){

        peroidRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("periods");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Period period = new Period();
                period.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                period.setPeriod(jsonPeroids.get(i).getAsJsonObject().get("period").getAsString());
                period.setStartTime(jsonPeroids.get(i).getAsJsonObject().get("starttime").getAsString());
                period.setEndTime(jsonPeroids.get(i).getAsJsonObject().get("endtime").getAsString());
                peroidRepository.save(period);
            }
            System.out.println("Save Periods");
        }

    }

    private void saveSubjects(){

        subjectRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("subjects");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Subject subject = new Subject();
                subject.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                String subj = jsonPeroids.get(i).getAsJsonObject().get("name").getAsString();
                if(subj.chars().count() > 15){
                    subj = jsonPeroids.get(i).getAsJsonObject().get("short").getAsString();
                }
                subject.setName(subj);
                subjectRepository.save(subject);
            }
            System.out.println("Save Subjects");
        }

    }

    private void saveTeachers(){

        teacherRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("teachers");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Teacher teacher = new Teacher();
                teacher.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                teacher.setName(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                teacherRepository.save(teacher);
            }
            System.out.println("Save Teachers");
        }

    }

    private  void saveClasses(){

        groupRepository.deleteAll();
        classRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("classes");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Class c = new Class();
                c.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                c.setName(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                classRepository.save(c);
            }
            System.out.println("Save Classes");
        }
    }

    void saveGroups(){

        groupRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("groups");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                GroupClass group = new GroupClass();
                group.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                group.setName(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                group.setaClass(classRepository.findByexternalID(jsonPeroids.get(i).getAsJsonObject().get("classid").getAsString()));
                groupRepository.save(group);
            }
            System.out.println("Save Groups");
        }

    }

    void saveDays(){

        dayRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("daysdefs");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Day day = new Day();
                day.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                day.setName(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                day.setDay(jsonPeroids.get(i).getAsJsonObject().get("days").getAsString());
                dayRepository.save(day);
            }
            System.out.println("Save Days");
        }
    }

    void saveClassRooms(){

        classRoomRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("classrooms");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                ClassRoom room = new ClassRoom();
                room.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                room.setName(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                classRoomRepository.save(room);
            }
            System.out.println("Save Rooms");
        }
    }

    void saveWeeks(){

        weekRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("weeksdefs");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Week week = new Week();
                week.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                week.setName(jsonPeroids.get(i).getAsJsonObject().get("name").getAsString());
                week.setWeek(jsonPeroids.get(i).getAsJsonObject().get("weeks").getAsString());
                weekRepository.save(week);
            }
            System.out.println("Save Weeks");
        }
    }

    void saveLessons(){

        lessonRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("lessons");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Lesson lesson = new Lesson();
                lesson.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("id").getAsString());
                lesson.setClassId(jsonPeroids.get(i).getAsJsonObject().get("classids").getAsString());
                lesson.setDayId(jsonPeroids.get(i).getAsJsonObject().get("daysdefid").getAsString());
                lesson.setGroupId(jsonPeroids.get(i).getAsJsonObject().get("groupids").getAsString());
                lesson.setPeroidId(jsonPeroids.get(i).getAsJsonObject().get("periodspercard").getAsString());
                lesson.setSubjectId(jsonPeroids.get(i).getAsJsonObject().get("subjectid").getAsString());
                lesson.setTeacherId(jsonPeroids.get(i).getAsJsonObject().get("teacherids").getAsString());
                lesson.setWeekId(jsonPeroids.get(i).getAsJsonObject().get("weeksdefid").getAsString());
                lessonRepository.save(lesson);
            }
            System.out.println("Save Lessons");
        }
    }

    void saveCards(){

        cardRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonArray("cards");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Card card = new Card();
                card.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("lessonid").getAsString());
                Lesson lesson = lessonRepository.findByexternalID(jsonPeroids.get(i).getAsJsonObject().get("lessonid").getAsString());
                Class c = classRepository.findByexternalID(lesson.getClassId());
                if(c != null)
                    card.setClassName(c.getName());
                Teacher teacher = teacherRepository.findByexternalID(lesson.getTeacherId());
                if(teacher != null)
                    card.setTeacher(teacher.getName());
                Period period = peroidRepository.findByePeroid(jsonPeroids.get(i).getAsJsonObject().get("period").getAsString());
                card.setPeroid(period.getStartTime() + " - " + period.getEndTime());
                card.setStartTime(period.getStartTime());
                card.setEndTime(period.getEndTime());
                card.setLessonNumber(Integer.parseInt(period.getPeriod()));
                Day day = dayRepository.findByexternalID(lesson.getDayId());
                card.setDay(jsonPeroids.get(i).getAsJsonObject().get("days").getAsString());
                Subject subject = subjectRepository.findByexternalID(lesson.getSubjectId());
                card.setSubject(subject.getName());
                Week week = weekRepository.findByexternalID(lesson.getWeekId());
                card.setWeek(week.getName());
                ClassRoom classRoom = classRoomRepository.findByexternalID(jsonPeroids.get(i).getAsJsonObject().get("classroomids").getAsString());
                if(classRoom != null)
                card.setRoom(classRoom.getName());
                    GroupClass groupClass = groupRepository.findByexternalID(lesson.getGroupId());
                if(groupClass != null)
                    card.setGroupName(groupClass.getName());
                cardRepository.save(card);
            }
            System.out.println("Save Cards");
        }
    }


}
