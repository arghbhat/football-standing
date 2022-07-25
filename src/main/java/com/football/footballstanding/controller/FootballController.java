package com.football.footballstanding.controller;

import com.football.footballstanding.domain.ErrorModel;
import com.football.footballstanding.domain.Standing;
import com.football.footballstanding.service.FootBallStandingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.football.footballstanding.constant.Constants.REQUEST_PARAM_LEAGUE_ID;
import static com.football.footballstanding.constant.UrlConfig.GET_STANDINGS;
import static com.football.footballstanding.constant.UrlConfig.REST_API;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController(REST_API)
@Slf4j
@RequiredArgsConstructor
public class FootballController {

    private final FootBallStandingService footBallStandingService;

    @Operation(description = "Gets the list of all the standing for a league id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Invalid API key",
            content = @Content(schema = @Schema(implementation = ErrorModel.class))),
        @ApiResponse(responseCode = "404", description = "No Data Found Found",
            content = @Content(schema = @Schema(implementation = ErrorModel.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorModel.class)))})
    @GetMapping(value = GET_STANDINGS, produces = {"application/hal+json"})
    public HttpEntity<CollectionModel<Standing>> getStandings(
        @RequestParam(name = REQUEST_PARAM_LEAGUE_ID) Integer leagueId) {
        log.info("FootballController: getStandings() called with league id - " + leagueId);
        List<Standing> standings = footBallStandingService.getStandings(leagueId);
        return new ResponseEntity<>(addSelfLink(leagueId, standings), HttpStatus.OK);
    }

    /**
     * adds a new self link by making a fake calle
     *
     * @param leagueId to make call
     * @param standings this will be updated
     * @return returns a collectionModel
     */
    private CollectionModel<Standing> addSelfLink(Integer leagueId, List<Standing> standings) {
        Link link = linkTo(methodOn(FootballController.class).getStandings(leagueId)).withSelfRel();
        return CollectionModel.of(standings, link);
    }
}
