package com.mvp.bni.post.controller;
import com.mvp.bni.post.dto.BaseResponse;
import com.mvp.bni.post.dto.input.PostInput;
import com.mvp.bni.post.dto.output.PostOutput;
import com.mvp.bni.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            PostOutput output = postService.getOne(id);
            return ResponseEntity.ok(new BaseResponse<>(output));
        } catch (Exception e) {
            if (e.getMessage().equals("No Content")) {
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,"Cannot found data with id " + id + " !!"), HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.ok(postService.getAll());
        }catch (Exception e){
            if (e.getMessage().equals("No Content")) {
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,"Data is empty !!"), HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @PostMapping
    public ResponseEntity<?> addOne(@Valid @RequestBody PostInput input) {
        try{
            postService.addOne(input);
            return ResponseEntity.ok(new BaseResponse<>(input));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody PostInput input){
        try{
            if(input.getTitle().isBlank() || input.getDescription().isBlank()){
                return ResponseEntity.badRequest().build();
            }
            postService.update(id, input);
            return ResponseEntity.ok(new BaseResponse<>(input));
        }catch (Exception e){
            System.out.println(e.getMessage());
            if (e.getMessage().equals("No Content")) {
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,"Cannot found data with id " + id + " !!"), HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        try{
            postService.deleteOne(id);
            return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE,"Successfully delete with id " + id + " !!"));
        }catch (Exception e){
            if (e.getMessage().equals("No Content")) {
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,"Cannot found data with id " + id + " !!"), HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    @ResponseBody
//    public BaseResponse<?> handleValidationError(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String defaultMessage = fieldError.getDefaultMessage();
//        return new BaseResponse<>(false, defaultMessage);
//    }
}
