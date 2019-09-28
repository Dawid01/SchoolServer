package pl.szczepaniak.school.server.schoolserver.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
import pl.szczepaniak.school.server.schoolserver.lesson_plan.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private PeriodRepository peroidRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Autowired
    WeekRepository weekRoomRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CardRepository cardRepository;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("uploadLessonPlan")
    public UploadFileResponse uploadLessonFile(@RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/lessonPlanFile/")
                .path(fileName)
                .toUriString();

        new LessonPlanReader(file, this);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    public PeriodRepository getPeroidRepository() {
        return peroidRepository;
    }

    public SubjectRepository getSubjectRepository() {
        return subjectRepository;
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public ClassRepository getClassRepository() {
        return classRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public DayRepository getDayRepository() {
        return dayRepository;
    }

    public ClassRoomRepository getClassRoomRepository() {
        return classRoomRepository;
    }

    public WeekRepository getWeekRoomRepository() {
        return weekRoomRepository;
    }

    public LessonRepository getLessonRepository() {
        return lessonRepository;
    }

    public CardRepository getCardRepository() {
        return cardRepository;
    }
}
