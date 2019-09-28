package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.multipart.MultipartFile;
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

        try {
            String json = new String(file.getBytes());
            jsonObject = new JsonParser().parse(json).getAsJsonObject();
        }catch (IOException e){}

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
        }
    }

    private void savePeroids(){

        peroidRepository.deleteAll();

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

    }

    private void saveSubjects(){

        subjectRepository.deleteAll();

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

    }

    private void saveTeachers(){

        teacherRepository.deleteAll();

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

    }

    private  void saveClasses(){

        groupRepository.deleteAll();
        classRepository.deleteAll();

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
    }

    void saveGroups(){

        groupRepository.deleteAll();

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

    }

    void saveDays(){

        dayRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("daysdefs").getAsJsonArray("daysdef");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Day day = new Day();
                day.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                day.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                day.setDay(jsonPeroids.get(i).getAsJsonObject().get("_days").getAsString());
                dayRepository.save(day);
            }
            System.out.println("Save Days");
        }
    }

    void saveClassRooms(){

        classRoomRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("classrooms").getAsJsonArray("classroom");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                ClassRoom room = new ClassRoom();
                room.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                room.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                classRoomRepository.save(room);
            }
            System.out.println("Save Rooms");
        }
    }

    void saveWeeks(){

        weekRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("weeksdefs").getAsJsonArray("weeksdef");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Week week = new Week();
                week.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                week.setName(jsonPeroids.get(i).getAsJsonObject().get("_name").getAsString());
                week.setWeek(jsonPeroids.get(i).getAsJsonObject().get("_weeks").getAsString());
                weekRepository.save(week);
            }
            System.out.println("Save Weeks");
        }
    }

    void saveLessons(){

        lessonRepository.deleteAll();

        JsonArray jsonPeroids = jsonObject.getAsJsonObject("timetable").getAsJsonObject("lessons").getAsJsonArray("lesson");

        if(jsonPeroids != null) {
            for (int i = 0; i < jsonPeroids.size(); i++) {
                Lesson lesson = new Lesson();
                lesson.setExternalID(jsonPeroids.get(i).getAsJsonObject().get("_id").getAsString());
                lesson.setClassId(jsonPeroids.get(i).getAsJsonObject().get("_classids").getAsString());
                lesson.setDayId(jsonPeroids.get(i).getAsJsonObject().get("_daysdefid").getAsString());
                lesson.setGroupId(jsonPeroids.get(i).getAsJsonObject().get("_groupids").getAsString());
                lesson.setPeroidId(jsonPeroids.get(i).getAsJsonObject().get("_periodspercard").getAsString());
                lesson.setSubjectId(jsonPeroids.get(i).getAsJsonObject().get("_subjectid").getAsString());
                lesson.setTeacherId(jsonPeroids.get(i).getAsJsonObject().get("_teacherids").getAsString());
                lesson.setWeekId(jsonPeroids.get(i).getAsJsonObject().get("_weeksdefid").getAsString());
                lessonRepository.save(lesson);
            }
            System.out.println("Save Lessons");
        }
    }
}
