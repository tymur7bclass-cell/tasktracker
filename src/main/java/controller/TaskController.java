package org.example.controller;

import org.example.model.Category;
import org.example.model.Task;
import org.example.repository.CategoryRepository;
import org.example.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;
    private final CategoryRepository categoryRepository;

    public TaskController(TaskRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Task> getAll() {
        return repository.findAll();
    }

    @GetMapping("/add")
    public Task addTask() {
        Task task = new Task();
        task.setTitle("Задача с категорией");
        task.setDescription("Demo");

        Category category = categoryRepository.findById(1L).orElse(null);
        task.setCategory(category);

        return repository.save(task);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return repository.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}