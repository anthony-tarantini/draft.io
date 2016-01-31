package com.draftio.data.sets;

import com.draftio.domain.errors.ApiError;
import com.draftio.domain.sets.SetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
class SetsController {
    private final SetsService setsService;

    @Autowired
    public SetsController(final SetsService setsService) {
        this.setsService = setsService;
    }

    @RequestMapping(value = "/sets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SetsResponse fetchAllSets() {
        try {
            return new SetsResponse(setsService.fetchAllSets());
        } catch(final Exception exception) {
            throw new SetsException(exception.getMessage());
        }
    }

    @ExceptionHandler(SetsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleException(final SetsException exception) {
        return new ApiError(exception.getMessage());
    }
}
