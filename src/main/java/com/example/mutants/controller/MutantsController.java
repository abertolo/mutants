package com.example.mutants.controller;

import com.example.mutants.model.request.MutantsRequest;
import com.example.mutants.model.response.MutantsStatsResponse;
import com.example.mutants.service.MutantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by mnsantos on 10/2/17.
 */

@RestController
public class MutantsController {

    @Resource(name = "MutantsServiceImpl")
    private MutantsService mutantsService;

    private final static Logger logger = LoggerFactory.getLogger(MutantsController.class);

    @RequestMapping(value = "/mutants", method = RequestMethod.POST)
    public ResponseEntity mutants(@RequestBody @Valid MutantsRequest mutantsRequest) {
        logger.info(String.format("Processing %s", mutantsRequest));
        if (mutantsService.isMutant(mutantsRequest.getDna())) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseEntity<MutantsStatsResponse> stats() {
        return new ResponseEntity<>(mutantsService.stats(), HttpStatus.OK);
    }
}
