package com.draftio.domain.packs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PacksResponse {
    @NonNull
    private List<Pack> packs;
}
