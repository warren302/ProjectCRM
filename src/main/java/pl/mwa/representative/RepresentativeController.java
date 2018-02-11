package pl.mwa.representative;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mwa.util.CSVUtils;

@Controller
@RequestMapping("/representative")
public class RepresentativeController {
	static final String REDIRECT_LISTALL_FULLPATH = "redirect:/representative/listall";
	
	
	@Autowired
	RepresentativeService rs;
	
	@Autowired
	RepresentativeRepository rr;
	
	
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("representative", new Representative());
		return "/representative/addrepresentative";
	}
	
	@PostMapping("/add")
	public String add(@Valid Representative representative, BindingResult result) {
		if (result.hasErrors()) {
			return "/representative/addrepresentative";
		} else {
			rs.save(representative);
			return "redirect:listall";
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("r", rs.findOne(id));
		return "/representative/editrepresentative";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@Valid Representative representative, BindingResult result) {
		if (result.hasErrors()) {
			return "/representative/editrepresentative";
		} else {
			rs.save(representative);
			return REDIRECT_LISTALL_FULLPATH;
		}
	}
	
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		model.addAttribute("r", rs.findOne(id));
		return "/representative/viewrepresentative";
	}
	

	@PostMapping("/view/{id}")
	public String view() {
		return REDIRECT_LISTALL_FULLPATH;
	}
	
	
	@PostMapping("/remove/{id}")
	public String remove(@PathVariable("id") long id) {
		rs.remove(id);
		return REDIRECT_LISTALL_FULLPATH;
	}
	
	@GetMapping("/listall")
	public String listAll(Model model, @SortDefault("id") Pageable pageable) {
		model.addAttribute("page", rs.findAll(pageable));
		return "representative/listrepresentatives";
	}
	
	
	@GetMapping("/import")
	public String readFromCSV(Model model) {
		model.addAttribute("filename", "plik.csv");
		return "utils/filename";
	}
	
	
	
	@ResponseBody
	@PostMapping("/import")
	public String readFromCSV(@RequestParam(name = "filename", required = false) String filename) {
		String url = "/home/mikolaj/Documents/feed1.csv";
		System.out.println(filename);
		CSVUtils.reader(url);
		
		return "plik wczytany";
	}
	
}
