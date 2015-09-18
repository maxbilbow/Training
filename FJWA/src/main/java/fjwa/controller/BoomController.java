package fjwa.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fjwa.model.Bomb;
import fjwa.model.Exercise;
import fjwa.model.Goal;
import fjwa.model.WeakBomb;
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
		this.findAllBombs(model); 
		return "boom"; //The jsp name?
	}

	@RequestMapping(value = "addBomb", method = RequestMethod.GET)
	public String addBomb(Model model){
		Bomb bomb = WeakBomb.newInstance();
		bombService.addBomb(bomb);
		
		return "redirect:boom.html";
	}
	
	
	@RequestMapping(value = "updateBomb", method = RequestMethod.GET)
	public String updateBomb(@Valid @ModelAttribute("bomb") Bomb bomb, Model model){
//		bombService.addBomb(bomb);s
		System.out.println("updateBomb??!");
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
	
	
	@RequestMapping(value = "findAllBombs", method = RequestMethod.GET) //bombs?
//	@Retention(RetentionPolicy.SOURCE)
	public @ResponseBody List<String> findAllBombs(Model model) {
		System.out.println("findAllBombs called");
		List<Bomb> bombs = bombService.findAllBombs();
		if (bombs == null || bombs.isEmpty()) 
			model.addAttribute("boom", "No bombs... safe!");
		else {
			model.addAttribute("boom", "Time is ticking...");

			String result = "";
			for (Bomb bomb : bombs) {
				result += "<br/> " + (bomb.hasTimeRunOut() ? "Boom!" :  "Bomb " + bomb.getName() + ": " + bomb.timeRemaining() + " seconds!" );
			}
			
			model.addAttribute("bombs", result);
			return Arrays.asList(result);
		}
		return Arrays.asList("No Bombs");
	}
	
	@RequestMapping(value = "updateBombs", method = RequestMethod.GET) //bombs?
	public @ResponseBody List<Bomb> updateBombs(Model model) {
//		System.out.println("updateBombs called");
		return bombService.update();
	}
	
	
	
}
