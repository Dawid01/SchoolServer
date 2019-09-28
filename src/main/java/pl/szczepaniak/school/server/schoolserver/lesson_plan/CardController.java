package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/cards")
    public Page<CardDto> getAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable).map(this::convert);
    }

    @GetMapping("/cards/classname/{className}/{day}")
    public Page<CardDto> getCards(@PathVariable String className, @PathVariable String day, Pageable pageable) {
        return cardRepository.findCards(className, day, pageable).map(this::convert);
    }

    @GetMapping("/cards/{externalID}")
    public CardDto getByExternalID(@PathVariable String externalID){
        return  convert(cardRepository.findByexternalID(externalID));
    }

    @PostMapping("/cards")
    public CardDto createQuestion(@Valid @RequestBody Card card) {
        return convert(cardRepository.save(card));
    }

    @PutMapping("/cards/{id}")
    public CardDto updateCards(@PathVariable Long cardId,
                                  @Valid @RequestBody Card card) {
        return cardRepository.findById(cardId)
                .map(question -> {
                    question.setId(card.getId());
                    question.setExternalID(card.getExternalID());
                    question.setDay(card.getDay());
                    question.setLessonNumber(card.getLessonNumber());
                    question.setPeroid(card.getPeroid());
                    question.setSubject(card.getSubject());
                    question.setTeacher(card.getTeacher());
                    question.setWeek(card.getWeek());
                    question.setClassName(card.getClassName());
                    return convert(cardRepository.save(question));
                }).orElseThrow(() -> new ResourceNotFoundException("Card room not found with id " + cardId));
    }



    @DeleteMapping("/cards/{groupId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long cardId) {
        return cardRepository.findById(cardId)
                .map(question -> {
                    cardRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Card room not found with id " + cardId));
    }

    private CardDto convert(Card card) {
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setExternalID(card.getExternalID());
        dto.setDay(card.getDay());
        dto.setLessonNumber(card.getLessonNumber());
        dto.setPeroid(card.getPeroid());
        dto.setSubject(card.getSubject());
        dto.setTeacher(card.getTeacher());
        dto.setWeek(card.getWeek());
        dto.setClassName(card.getClassName());
        return dto;
    }
}
