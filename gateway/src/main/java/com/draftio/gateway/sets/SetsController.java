package com.draftio.gateway.sets;

import com.draftio.domain.sets.SetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class SetsController {
    private SetsRepository repository;

    @Autowired
    public SetsController(final SetsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/sets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SetsResponse getAvailableSets() {
        List<String> strings = repository.allAvailableSets();
        return new SetsResponse(strings);
    }
}
