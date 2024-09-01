package com.pixabyte.helpdeskapi.projects.infrastructure.controllers;

import com.pixabyte.helpdeskapi.projects.application.find.FindAllProjectsUseCase;
import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.shared.domain.Pagination;
import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class FindAllProjectsGetController {

    private final FindAllProjectsUseCase findAllProjectsUseCase;
    private final Logger logger = LoggerFactory.getLogger(FindAllProjectsGetController.class);

    public FindAllProjectsGetController(FindAllProjectsUseCase findAllProjectsUseCase) {
        this.findAllProjectsUseCase = findAllProjectsUseCase;
    }

    @Observed(name = "findAll-Projects")
    @GetMapping("projects")
    public ResponseEntity<ResultsPage<Map<String, Object>>> findAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        logger.info("FindAllProjectsGetController#findAll - Starting findAll pageNumber={} pageSize={}",
                pageNumber,
                pageSize);
        Pagination pagination = new Pagination(pageSize, pageNumber);
        var response = findAllProjectsUseCase.execute(pagination);
        var jsonArray = response.getResults().stream()
                .map(Project::toPrimitives)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResultsPage<>(
                jsonArray,
                response.getPageNumber(),
                response.getPageSize(),
                response.getTotalElements()
        ));
    }

}
