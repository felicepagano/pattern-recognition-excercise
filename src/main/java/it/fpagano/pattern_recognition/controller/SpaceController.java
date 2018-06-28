package it.fpagano.pattern_recognition.controller;

import it.fpagano.pattern_recognition.model.Point;
import it.fpagano.pattern_recognition.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SpaceController {

    @Autowired
    private SpaceService service;

    @PostMapping("/point")
    public ResponseEntity<Point> addPoint(@RequestBody Point p) {
        return service.addPoint(p).map(point -> ResponseEntity.status(HttpStatus.CREATED).body(point))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/space")
    public Set<Point> getPoints() {
        return service.getPoints();
    }

    @GetMapping("/lines/{n}")
    public ResponseEntity<Set<Set<Point>>> getLines(@PathVariable int n){
        Set<Set<Point>> lines = service.getLines(n);
        if(lines.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(lines);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/space")
    void deleteAll() {
        service.deleteAll();
    }


}
