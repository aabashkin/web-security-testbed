package mycompany.myproducts.websecuritytestbed.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycompany.myproducts.websecuritytestbed.model.VetVisit;

@Controller
public class StatefulScanController {
	
	private static final Logger logger = LoggerFactory.getLogger(StatefulScanController.class);

	private static ArrayList<VetVisit> visits = populateVisits();
	
	@RequestMapping(value = "/stateful-scan", method = RequestMethod.GET)
	public String statefulScan(HttpServletRequest request) {
		
		request.setAttribute("visits", visits);
		
		return "stateful-scan";
	}
	
	@RequestMapping(value = "/stateful-scan", method = RequestMethod.POST)
	public String closeVisit(HttpServletRequest request, @RequestParam("visitId") int visitId, @RequestParam("comment") String comment){
		
		VetVisit visit = visits.get(visitId - 1);
		
		if(!visit.getIsOpen()){
			logger.error("ERROR: Visit already closed");
			return "visitError";
		}
		
		visit.setIsOpen(false);
		visit.setComment(comment);
		
		request.setAttribute("visits", visits);
		
		return "stateful-scan";
	}
	
	
	private static ArrayList<VetVisit> populateVisits(){
		
		ArrayList<VetVisit> visits = new ArrayList<>();
		
		for(int visitId = 1; visitId < 1001; visitId++){
			VetVisit visit = new VetVisit();
			visit.setIsOpen(true);
			visit.setVisitId(visitId);
			visit.setDescription("Vet Visit #" + visitId);
			visit.setComment("");
			visits.add(visit);
		}
		
		logger.info("Visit list populated");

		return visits;
		
	}

}
