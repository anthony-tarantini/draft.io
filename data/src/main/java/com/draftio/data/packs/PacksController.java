package com.draftio.data.packs;

import com.draftio.domain.errors.ApiError;
import com.draftio.domain.packs.PacksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
class PacksController {
    private PacksService service;

    @Autowired
    public PacksController(final PacksService service) {
        this.service = service;
    }

    @RequestMapping(value="/packs/{set}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PacksResponse getPacksForSet(@PathVariable final String set) {
        try {
            return new PacksResponse(service.getPacksForSet(set));
        } catch (final Exception exception) {
            throw new PacksException(exception.getMessage());
        }
    }

    @ExceptionHandler(PacksException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleException(final PacksException exception) {
        return new ApiError(exception.getMessage());
    }
}
