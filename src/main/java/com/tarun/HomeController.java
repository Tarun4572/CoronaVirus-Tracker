package com.tarun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@Autowired
	ConoraVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		java.util.List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		
		int totalReportedCases =allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		
		int totalNewCases =allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}
}
