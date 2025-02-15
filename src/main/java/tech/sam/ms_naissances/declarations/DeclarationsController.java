package tech.sam.ms_naissances.declarations;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("declarations")
public class DeclarationsController {
    private DeclarationsService declarationsService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Declaration> search() {
        return this.declarationsService.search();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Declaration declaration) {

        this.declarationsService.create(declaration);
    }
//
//    @PreAuthorize("hasAuthority('SCOPE_DECLARATION_UPDATE')")
//    @PatchMapping(path = "{id}/status", consumes = APPLICATION_JSON_VALUE)
//    public void updateStatus(@PathVariable int id, @RequestBody Map<String, String> params) {
//        this.declarationsService.updateStatus(id, params);
//    }
}
