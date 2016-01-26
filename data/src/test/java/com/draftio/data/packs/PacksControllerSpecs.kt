package com.draftio.data.packs

import com.draftio.domain.errors.ApiError
import com.draftio.domain.packs.Pack
import com.draftio.domain.packs.PacksResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

class PacksControllerSpecs : Spek() {
    init {
        given("a controller to handle the /packs endpoint") {
            val mockPacksService = mock(PacksService::class.java);
            val packsController = PacksController(mockPacksService)
            val mockMvc = MockMvcBuilders.standaloneSetup(packsController).build()
            val objectMapper = ObjectMapper()

            on("a succesful request being made for a certain set") {
                reset(mockPacksService);
                val expectedPacksList = ArrayList<Pack>()
                doReturn(expectedPacksList).`when`(mockPacksService).getPacksForSet(anyString());
                val result = mockMvc.perform(get("/packs/M13")).andReturn();

                it("returns a PacksResponse") {
                    val data = objectMapper.readValue(result.response.contentAsString, PacksResponse::class.java);
                    assertThat(data.packs).isNotNull();
                }

                it("returns JSON") {
                    assertThat(result.response.contentType).isEqualTo(MediaType.APPLICATION_JSON_VALUE)
                }

                it("calls the PacksService with the set") {
                    verify(mockPacksService).getPacksForSet("M13");
                }

                it("returns with an OK status") {
                    assertThat(result.response.status).isEqualTo(HttpStatus.OK.value())
                }
            }

            on("an unsuccessful request being made for a certain set") {
                reset(mockPacksService);
                doThrow(Exception()).`when`(mockPacksService).getPacksForSet(anyString());
                val result = mockMvc.perform(get("/packs/M13")).andReturn();

                it("returns an API Error") {
                    val data = objectMapper.readValue(result.response.contentAsString, ApiError::class.java);
                    assertThat(data.message).isNotNull();
                }

                it("returns JSON") {
                    assertThat(result.response.contentType).isEqualTo(MediaType.APPLICATION_JSON_VALUE)
                }

                it("returns with Bad Request status") {
                    assertThat(result.response.status).isEqualTo(HttpStatus.BAD_REQUEST.value())
                }
            }

        }
    }
}