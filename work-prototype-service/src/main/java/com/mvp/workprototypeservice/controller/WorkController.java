package com.mvp.workprototypeservice.controller;

import com.mvp.workprototypeservice.dto.BaseResponse;
import com.mvp.workprototypeservice.dto.input.WorkInput;
import com.mvp.workprototypeservice.dto.output.WorkOutput;
import com.mvp.workprototypeservice.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            WorkOutput output = workService.getOne(id);
            return ResponseEntity.ok(new BaseResponse<>(output));
        } catch (Exception e) {
            if (e.getMessage().equals("No Content")) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.ok(workService.getAll());
        }catch (Exception e){
            if (e.getMessage().equals("No Content")) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @PostMapping
    public ResponseEntity<?> addOne(@Valid @RequestBody WorkInput input) {
        try{
            workService.addOne(input);
            return ResponseEntity.ok(new BaseResponse<>(input));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }
}
