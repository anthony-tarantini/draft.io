package com.draftio.domain.sets;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SetsResponse {
    @NonNull
    private List<String> sets;
}
