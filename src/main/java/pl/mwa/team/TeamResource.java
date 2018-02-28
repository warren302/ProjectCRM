package pl.mwa.team;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/teams")
@RestController
public class TeamResource {

	@Autowired
	TeamService service;
	
	
	
	
	
	@GetMapping("/{id}")
	ResponseEntity getTeam(@PathVariable Long id) {
		return ResponseEntity.ok(service.getTeam(id));
	}
	
	@GetMapping
	ResponseEntity getTeams() {
		return ResponseEntity.ok(service.getTeams());
	}
	
	@PostMapping
	ResponseEntity createTeam(@RequestBody @Valid Team team) {
		Long id = service.createTeam(team);
		return ResponseEntity.ok(id);
	}
	
	@PutMapping
	ResponseEntity updateTeam(@RequestBody @Valid Team team) {
		service.updateTeam(team);
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity deleteTeam(@PathVariable Long id) {
		service.deleteTeam(id);
		return ResponseEntity.accepted().build();
	}
		
	
	
		
		
		
	
	
}