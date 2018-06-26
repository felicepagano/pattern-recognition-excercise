package it.fpagano.pattern_recognition.controller;

import it.fpagano.pattern_recognition.model.Point;
import it.fpagano.pattern_recognition.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SpaceController {

    @Autowired
    private IService service;

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
    public Set<Set<Point>> getLines(@PathVariable int n){
        return service.getLines(n);
    }

    @DeleteMapping("/space")
    void deleteAll() {
        service.deleteAll();
    }


}
