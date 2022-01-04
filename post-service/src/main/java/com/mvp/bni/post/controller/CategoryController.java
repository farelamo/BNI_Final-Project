package com.mvp.bni.post.controller;

import com.mvp.bni.post.dto.BaseResponse;
import com.mvp.bni.post.dto.input.CategoryInput;
import com.mvp.bni.post.dto.output.CategoryOutput;
import com.mvp.bni.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            CategoryOutput output = categoryService.getOne(id);
            return ResponseEntity.ok(new BaseResponse<>(output));
        } catch (Exception e) {
            if (e.getMessage().equals("No Content")) {
                return ResponseEntity.status(204).body(new BaseResponse<>(Boolean.FALSE,"Cannot found data with id " + id + " !!"));
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.ok(categoryService.getAll());
        }catch (Exception e){
            if (e.getMessage().equals("No Content")) {
                return ResponseEntity.status(204).body(new BaseResponse<>(Boolean.FALSE,"Data is empty !!"));
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @PostMapping
    public ResponseEntity<?> addOne(@Valid @RequestBody CategoryInput input) {
        try{
            categoryService.addOne(input);
            return ResponseEntity.ok(new BaseResponse<>(input));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody CategoryInput input){
        try{
            if(input.getCategoryName().isBlank()){
                return ResponseEntity.badRequest().build();
            }
            categoryService.update(id, input);
            return ResponseEntity.ok(new BaseResponse<>(input));
        }catch (Exception e){
            if (e.getMessage().equals("No Content")) {
                return ResponseEntity.status(204).body(new BaseResponse<>(Boolean.FALSE,"Cannot found data with id " + id + " !!"));
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        try{
            categoryService.deleteOne(id);
            return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE,"Successfully delete with id " + id + " !!"));
        }catch (Exception e){
            if (e.getMessage().equals("No Content")) {
                return ResponseEntity.status(204).body(new BaseResponse<>(Boolean.FALSE,"Cannot found data with id " + id + " !!"));
            }
            return ResponseEntity.internalServerError().body(new BaseResponse<>(Boolean.FALSE, "Internal Service Error"));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ResponseBody
    public BaseResponse<?> handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new BaseResponse<>(false, defaultMessage);
    }
}
