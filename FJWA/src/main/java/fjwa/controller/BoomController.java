package fjwa.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fjwa.model.Bomb;
import fjwa.model.Exercise;
import fjwa.model.Goal;
import fjwa.model.Bomb;
import fjwa.service.BombService;


@Controller
@SessionAttributes("bomb")
public class BoomController {
	@Autowired
	private BombService bombService;
	
	@RequestMapping(value = "/boom") //The page name (.whatever - i.e. html)
	public String sayBoom(Model model) {
		bombService.update();
		update(model); 
		return "boom"; //The jsp name?
	}


	@RequestMapping(value = "findAllBombs", method = RequestMethod.GET) //bombs?
	public @ResponseBody List<Bomb> findAllBombs() {
		return bombService.findAllBombs();
	}
	
	@RequestMapping(value = "addBomb", method = RequestMethod.GET)
	public String addBomb(Model model){
		bombService.addBomb();
		return "redirect:boom.html";
	}
	
	@RequestMapping(value = "cleanUp", method = RequestMethod.GET)
	public String cleanUp(Model model){
		bombService.cleanUp();
		return "redirect:boom.html";
	}
	
//	@RequestMapping(value = "addBomb", method = RequestMethod.POST)
//	public String addBomb(@Valid @ModelAttribute("bomb") Bomb bomb, BindingResult result){
//		
//		System.out.println("result has errors: " + result.hasErrors());
//		
//		System.out.println("Bomb set: " + bomb.getName());
//		
//		if(result.hasErrors()) {
//			return "boom";
//		}
//		
////		return "redirect:index.jsp";
//		return "boom";
//	}
	
	
	@RequestMapping(value = "defuse", method = RequestMethod.GET)
	public String defuse(Model model){
		bombService.defuse();
		return "redirect:boom.html";
	}
	
//	@RequestMapping(value = "defuse", method = RequestMethod.POST)
//	public String defuse(@Valid @ModelAttribute("bomb") Bomb bomb, BindingResult result){
//		
//		System.out.println("result has errors: " + result.hasErrors());
//		
//		System.out.println("Bomb set: " + bomb.getName());
//		
//		if(result.hasErrors()) {
//			return "addGoal";
//		}
//		
////		return "redirect:index.jsp";
//		return "boom";
//	}
	
	
	public void update(Model model) {
		List<Bomb> bombs = bombService.findAllBombs();
		if (bombs == null || bombs.isEmpty()) 
			model.addAttribute("boom", "No bombs... safe!");
		else {
			model.addAttribute("boom", "Time is ticking...");

			String result = "";
			for (Bomb bomb : bombs) {
				int time = bomb.getTimeinSeconds();
				result += "<br/> " + (time < 0 ? "Boom!" :  "Bomb " + bomb.getName() + ": " + time + " seconds!" );
			}
			
			model.addAttribute("bombs", result);
		}
	
	}
}